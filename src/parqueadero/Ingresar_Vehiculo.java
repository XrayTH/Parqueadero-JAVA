/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueadero;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author santi
 */
public class Ingresar_Vehiculo extends JDialog {

    JLabel et1, et2;
    JTextField a;
    JButton mostrar, mostrar_todo, validar, cancelar;

    JComboBox combo;

    JTable tabla;
    DefaultTableModel modelo;

    public Ingresar_Vehiculo(int uCod, Frame e, boolean modal) {

        super(e, modal);

        Container cont = getContentPane();
        cont.setLayout(new FlowLayout());

        ConsultasFactura fact = new ConsultasFactura();

        et1 = new JLabel("Vehiculo: ");
        et2 = new JLabel("V-P-PLACA-HE-ME: ");

        a = new JTextField(10);

        mostrar = new JButton("Mostrar");
        mostrar_todo = new JButton("Mostrar Todos");
        cancelar = new JButton("Cancelar");
        validar = new JButton("Validar");

        String vehiculos[] = {"Carro", "Moto", "Bicicleta"};
        combo = new JComboBox(vehiculos);

        cont.add(et1);
        cont.add(combo);
        cont.add(mostrar);
        cont.add(mostrar_todo);

        modelo = new DefaultTableModel(new Object[]{"1", "2", "3", "4", "5"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Desactiva la edición de todas las celdas
            }
        };
        tabla = new JTable(modelo);

        for (int i = 0; i < 5; i++) {
            modelo.addRow(new Object[]{"", "", "", "", ""});
        }

        JScrollPane scroll = new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        cont.add(scroll);

        cont.add(et2);
        cont.add(a);
        cont.add(validar);
        cont.add(cancelar);

        mostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vaciar();
                int p = 1;
                for (int f = 0; f < 5; f++) {

                    for (int c = 0; c < 5; c++) {

                        if (fact.findFacturaXPuesto(p) != null) {

                            switch (fact.findFacturaXPuesto(p).getTipo()) {

                                case "carro":
                                    if (combo.getSelectedItem() == "Carro") {
                                        tabla.setValueAt("c", f, c);
                                    } else {
                                        tabla.setValueAt(p, f, c);
                                    }
                                    break;
                                case "moto":
                                    if (combo.getSelectedItem() == "Moto") {
                                        tabla.setValueAt("m", f, c);
                                    } else {
                                        tabla.setValueAt(p, f, c);
                                    }
                                    break;
                                case "bicicleta":
                                    if (combo.getSelectedItem() == "Bicicleta") {
                                        tabla.setValueAt("b", f, c);
                                    } else {
                                        tabla.setValueAt(p, f, c);
                                    }
                                    break;
                                default:
                                    break;

                            }

                        } else {
                            tabla.setValueAt(p, f, c);
                        }
                        p++;
                    }

                }

            }
        });

        mostrar_todo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vaciar();
                int p = 1;
                for (int f = 0; f < 5; f++) {

                    for (int c = 0; c < 5; c++) {

                        if (fact.findFacturaXPuesto(p) != null) {

                            switch (fact.findFacturaXPuesto(p).getTipo()) {

                                case "carro":
                                    tabla.setValueAt("c", f, c);
                                    break;
                                case "moto":
                                    tabla.setValueAt("m", f, c);
                                    break;
                                case "bicicleta":
                                    tabla.setValueAt("b", f, c);
                                    break;
                                default:
                                    break;

                            }

                        } else {
                            tabla.setValueAt(p, f, c);
                        }
                        p++;
                    }

                }

            }
        });

        validar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String com = a.getText();
                String[] datos;

                if (com.matches("^[cmb]-(?:0[1-9]|1\\d|2[0-5])-((?:[A-Za-z]{3}\\d{3})|(?:[A-Za-z]{3}\\d{2}[A-Za-z])|(?:0000))-([0-1]\\d|2[0-3])-([0-5]\\d)$")) {
                    datos = com.split("-");

                    String v = datos[0];
                    String p = datos[1];
                    String placa = datos[2];
                    String he = datos[3];
                    String me = datos[4];

                    boolean error = false;
                    String texto = "";

                    if (v.equals("b")) {//pregunta si es una bicicleta

                        if (fact.findFacturaXPuesto(Integer.parseInt(p)) != null) {//pregunta si el puesto esta ocupado
                            error = true;
                            texto = texto + "Puesto ocupado.";
                        }

                    } else if (fact.verificarEstancia(placa, Integer.parseInt(p)) != null) {//si no, pregunta si el vehiculo esta en el parqueadero
                        error = true;
                        texto = texto + "Vehiculo ya ingresado o puesto ocupado.";
                    }

                    if (!v.matches("[cmb]+")) {//verifica que el tipo sea valido
                        error = true;
                        texto = texto + "El vehiculo debe ser carro, moto o bicicleta.";
                    }

                    if (!p.matches("(0[0-9]|1[0-9]|2[0-5])")) {//verifica que sea un puesto valido
                        error = true;
                        texto = texto + "Puesto invalido o ocupado.";
                    }

                    switch (v) {//verifica que la placa sea valida
                        case "c":
                            if (!placa.matches("^[A-Za-z]{3}\\d{3}$")) {
                                error = true;
                                texto = texto + "Placa invalida.";
                            }
                            break;
                        case "m":
                            if (!placa.matches("^[a-zA-Z]{3}\\d{2}[a-zA-Z]$")) {
                                error = true;
                                texto = texto + "Placa invalida.";
                            }
                            break;
                        case "b":
                            if (!placa.equals("0000")) {
                                error = true;
                                texto = texto + "Placa invalida.";
                            }
                            break;
                        default:
                            break;
                    }

                    switch (v) {
                        case "c":
                            if (Integer.parseInt(p) > 10) {
                                error = true;
                                texto = texto + "Puesto incorrecto para carros.";
                            }
                            break;
                        case "m":
                            if (Integer.parseInt(p) <= 10) {
                                error = true;
                                texto = texto + "Puesto incorrecto para motos.";
                            } else if (Integer.parseInt(p) > 20) {
                                error = true;
                                texto = texto + "Puesto incorrecto para motos.";
                            }
                            break;
                        case "b":
                            if (Integer.parseInt(p) <= 20) {
                                error = true;
                                texto = texto + "Puesto incorrecto para bicicletas.";
                            }
                            break;
                    }

                    if (!he.matches("(0[0-9]|1[0-9]|2[0-3])")) {

                        error = true;
                        texto = texto + "Hora invalida.";

                    }

                    if (!me.matches("(0[0-9]|[1-5][0-9])")) {
                        texto = texto + "Minuto invalido.";
                        error = true;

                    }

                    if (!error) {
                        new Detalle_Ingreso(null, true, v, p, placa, he, me, uCod);
                        dispose();
                    } else {

                        JOptionPane.showMessageDialog(null, texto);

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "El formato no es válido.");
                }

            }
        });

        cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setSize(600, 590);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void vaciar() {

        int numRows = tabla.getRowCount();
        int numColumns = tabla.getColumnCount();

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                tabla.setValueAt(null, row, col);
            }
        }

    }
}

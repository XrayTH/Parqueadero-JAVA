/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueadero;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author santi
 */
public class Buscar_Registro extends JDialog {

    JLabel et1, et2;
    JTextField codigo, total;
    JButton buscar, cerrar;

    JComboBox combo;

    JTable tabla;
    DefaultTableModel modelo;

    public Buscar_Registro(Frame e, boolean modal) {

        super(e, modal);
        Container cont = getContentPane();
        cont.setLayout(new FlowLayout());

        ConsultasFactura fact = new ConsultasFactura();
        ConsultasUsuario consultasUsuarios = new ConsultasUsuario();

        et1 = new JLabel("Usuarios: ");
        et2 = new JLabel("Total: ");

        codigo = new JTextField(5);
        total = new JTextField(5);

        String usuarios[] = {"Placa", "Factura", "Empleado", "Todos"};
        combo = new JComboBox(usuarios);

        buscar = new JButton("Buscar");
        cerrar = new JButton("Cerrar");

        cont.add(et1);
        cont.add(combo);
        cont.add(codigo);
        cont.add(buscar);

        modelo = new DefaultTableModel();
        modelo.addColumn("Factura");

        modelo.addColumn("Vehiculo");
        modelo.addColumn("Placa");
        modelo.addColumn("Cliente");
        modelo.addColumn("H Entrada");
        modelo.addColumn("H Salida");
        modelo.addColumn("Horas");
        modelo.addColumn("Valor Hora");
        modelo.addColumn("Total");
        modelo.addColumn("Empleado");

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        tabla.setDefaultEditor(Object.class, null);
        cont.add(scroll);

        cont.add(et2);
        cont.add(total);

        cont.add(cerrar);

        combo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (combo.getSelectedItem() == "Todos") {

                    codigo.setEditable(false);
                    modelo.setRowCount(0);

                    ArrayList<Factura> f = fact.findAllFacturas();
                    for (Factura factura : f) {

                        double vv = 0, hh = 0;

                        String[] datosE;
                        datosE = factura.getHoraE().split(":");
                        String e1 = datosE[0];
                        String e2 = datosE[1];

                        String[] datosS;
                        String s1 = "";
                        String s2 = "";
                        if (factura.getHoraS() != null) {
                            datosS = factura.getHoraS().split(":");
                            s1 = datosS[0];
                            s2 = datosS[1];

                            hh = Integer.parseInt(s1) - Integer.parseInt(e1);

                            if (hh == 0) {
                                hh = 1;
                            }

                            vv = factura.getValor() / hh;

                        }

                        Object[] rowData = new Object[]{
                            factura.getCodigo(),
                            factura.getTipo(),
                            factura.getPlaca(),
                            factura.getNombre(),
                            factura.getHoraE(),
                            factura.getHoraS(),
                            hh,
                            vv,
                            factura.getValor(),
                            consultasUsuarios.findXCod(factura.getUsuarioCod()).getNombre()
                        };

                        // Verificar si algún valor es nulo y asignar una cadena vacía en su lugar
                        for (int i = 0; i < rowData.length; i++) {
                            if (rowData[i] == null) {
                                rowData[i] = "";
                            }
                        }

                        modelo.addRow(rowData);
                    }

                    sumar();

                } else {

                    codigo.setEditable(true);

                }

            }
        });

        buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                modelo.setRowCount(0);

                if (combo.getSelectedItem() != "Todos") {

                    if (combo.getSelectedItem() == "Placa") {

                        if (codigo.getText().matches("^[A-Za-z]{3}\\d{3}$")
                                || codigo.getText().matches("^[a-zA-Z]{3}\\d{2}[a-zA-Z]$")
                                || codigo.getText().equals("0000")) {

                            ArrayList<Factura> f = fact.findAllFacturaXPlaca(codigo.getText());
                            for (Factura factura : f) {

                                double vv = 0, hh = 0;

                                String[] datosE;
                                datosE = factura.getHoraE().split(":");
                                String e1 = datosE[0];
                                String e2 = datosE[1];

                                String[] datosS;
                                String s1 = "";
                                String s2 = "";
                                if (factura.getHoraS() != null) {
                                    datosS = factura.getHoraS().split(":");
                                    s1 = datosS[0];
                                    s2 = datosS[1];

                                    hh = Integer.parseInt(s1) - Integer.parseInt(e1);

                                    if (hh == 0) {
                                        hh = 1;
                                    }

                                    vv = factura.getValor() / hh;

                                }

                                Object[] rowData = new Object[]{
                                    factura.getCodigo(),
                                    factura.getTipo(),
                                    factura.getPlaca(),
                                    factura.getNombre(),
                                    factura.getHoraE(),
                                    factura.getHoraS(),
                                    hh,
                                    vv,
                                    factura.getValor(),
                                    consultasUsuarios.findXCod(factura.getUsuarioCod()).getNombre()
                                };

                                // Verificar si algún valor es nulo y asignar una cadena vacía en su lugar
                                for (int i = 0; i < rowData.length; i++) {
                                    if (rowData[i] == null) {
                                        rowData[i] = "";
                                    }
                                }

                                modelo.addRow(rowData);
                            }

                        } else {

                            JOptionPane.showMessageDialog(null, "Placa no valida");

                        }

                    }

                    if (combo.getSelectedItem() == "Factura") {

                        if (codigo.getText().matches("\\d+")) {

                            ArrayList<Factura> f = fact.findAllFacturaXCodigo(Integer.parseInt(codigo.getText()));
                            for (Factura factura : f) {

                                double vv = 0, hh = 0;

                                String[] datosE;
                                datosE = factura.getHoraE().split(":");
                                String e1 = datosE[0];
                                String e2 = datosE[1];

                                String[] datosS;
                                String s1 = "";
                                String s2 = "";
                                if (factura.getHoraS() != null) {
                                    datosS = factura.getHoraS().split(":");
                                    s1 = datosS[0];
                                    s2 = datosS[1];

                                    hh = Integer.parseInt(s1) - Integer.parseInt(e1);

                                    if (hh == 0) {
                                        hh = 1;
                                    }

                                    vv = factura.getValor() / hh;

                                }

                                Object[] rowData = new Object[]{
                                    factura.getCodigo(),
                                    factura.getTipo(),
                                    factura.getPlaca(),
                                    factura.getNombre(),
                                    factura.getHoraE(),
                                    factura.getHoraS(),
                                    hh,
                                    vv,
                                    factura.getValor(),
                                    consultasUsuarios.findXCod(factura.getUsuarioCod()).getNombre()
                                };

                                // Verificar si algún valor es nulo y asignar una cadena vacía en su lugar
                                for (int i = 0; i < rowData.length; i++) {
                                    if (rowData[i] == null) {
                                        rowData[i] = "";
                                    }
                                }

                                modelo.addRow(rowData);
                            }

                        } else {

                            JOptionPane.showMessageDialog(null, "Factura no valida");

                        }

                    }

                    if (combo.getSelectedItem() == "Empleado") {

                        if (codigo.getText().matches("^[a-zA-Z\\s]+$") && consultasUsuarios.findXName(codigo.getText()) != null) {

                            ArrayList<Factura> f = fact.findAllFacturaXUsuarioCOD(consultasUsuarios.findXName(codigo.getText()).getCodigo());
                            for (Factura factura : f) {

                                double vv = 0, hh = 0;

                                String[] datosE;
                                datosE = factura.getHoraE().split(":");
                                String e1 = datosE[0];
                                String e2 = datosE[1];

                                String[] datosS;
                                String s1 = "";
                                String s2 = "";
                                if (factura.getHoraS() != null) {
                                    datosS = factura.getHoraS().split(":");
                                    s1 = datosS[0];
                                    s2 = datosS[1];

                                    hh = Integer.parseInt(s1) - Integer.parseInt(e1);

                                    if (hh == 0) {
                                        hh = 1;
                                    }

                                    vv = factura.getValor() / hh;

                                }

                                Object[] rowData = new Object[]{
                                    factura.getCodigo(),
                                    factura.getTipo(),
                                    factura.getPlaca(),
                                    factura.getNombre(),
                                    factura.getHoraE(),
                                    factura.getHoraS(),
                                    hh,
                                    vv,
                                    factura.getValor(),
                                    consultasUsuarios.findXCod(factura.getUsuarioCod()).getNombre()
                                };

                                // Verificar si algún valor es nulo y asignar una cadena vacía en su lugar
                                for (int i = 0; i < rowData.length; i++) {
                                    if (rowData[i] == null) {
                                        rowData[i] = "";
                                    }
                                }

                                modelo.addRow(rowData);
                            }

                        } else {

                            JOptionPane.showMessageDialog(null, "Empleado no valido");

                        }

                    }

                }

                sumar();

            }
        });

        cerrar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setSize(500, 590);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void sumar() {

        double tt = 0;

        for (int i = 0; i < modelo.getRowCount(); i++) {

            tt = tt + Double.parseDouble("" + modelo.getValueAt(i, 8));
        }

        total.setText("" + tt);

    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueadero;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author santi
 */
public class Modificar_Registro extends JDialog {

    JLabel et1, et2;
    JTextField codigo, total;
    JButton buscar, modificar, cerrar;

    JComboBox combo;

    JTable tabla;
    DefaultTableModel modelo;

    public Modificar_Registro(Frame e, boolean modal) {

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
        modificar = new JButton("Modificar Seleccionados");

        cont.add(et1);
        cont.add(combo);
        cont.add(codigo);
        cont.add(buscar);

        modelo = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 10) {
                    return Boolean.class;
                }
                return super.getColumnClass(columnIndex);
            }
        };

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
        modelo.addColumn("Modificar");

        tabla = new JTable(modelo);
        tabla.getColumnModel().getColumn(10).setCellEditor(new DefaultCellEditor(new JCheckBox()));

        tabla.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JTextField()) {
            @Override
            public boolean isCellEditable(EventObject e) {
                int columnIndex = tabla.getColumnModel().getColumnIndexAtX(((MouseEvent) e).getX());
                return columnIndex != 0; //permite la edición en todas las columnas excepto la columna 0
            }
        });

        tabla.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(new JTextField()) {
            @Override
            public boolean isCellEditable(EventObject e) {
                int columnIndex = tabla.getColumnModel().getColumnIndexAtX(((MouseEvent) e).getX());
                return columnIndex != 6;
            }
        });

        tabla.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(new JTextField()) {
            @Override
            public boolean isCellEditable(EventObject e) {
                int columnIndex = tabla.getColumnModel().getColumnIndexAtX(((MouseEvent) e).getX());
                return columnIndex != 8;
            }
        });

        JScrollPane scroll = new JScrollPane(tabla);
        cont.add(scroll);

        cont.add(modificar);
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
                            consultasUsuarios.findXCod(factura.getUsuarioCod()).getNombre(),
                            false
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
                                    consultasUsuarios.findXCod(factura.getUsuarioCod()).getNombre(),
                                    false
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
                                    consultasUsuarios.findXCod(factura.getUsuarioCod()).getNombre(),
                                    false
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
                                    consultasUsuarios.findXCod(factura.getUsuarioCod()).getNombre(),
                                    false
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

            }
        });

        cerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        modificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < tabla.getRowCount(); i++) {

                    boolean seleccionado = (boolean) tabla.getValueAt(i, 10);
                    if (seleccionado) {

                        boolean error = false;
                        String texto = "";
                        boolean salida = false;

                        int c = Integer.parseInt("" + tabla.getValueAt(i, 0));

                        int us = 0;

                        if (consultasUsuarios.findXName("" + tabla.getValueAt(i, 9)) == null) {

                            error = true;
                            texto = texto + "Empleado no existe.";

                        } else {

                            us = consultasUsuarios.findXName("" + tabla.getValueAt(i, 9)).getCodigo();

                        }

                        

                        if (fact.verificarExistenciaDeOtrasPlacas("" + tabla.getValueAt(i, 2), c) != null) {

                                error = true;
                                texto = texto + "Vehiculo ya ingresado.";
                            
                        }

                        String p = "" + tabla.getValueAt(i, 1);
                        String pl = "" + tabla.getValueAt(i, 2);

                        if (!p.equals("carro")
                                && !p.equals("moto")
                                && !p.equals("bicicleta")) {//verifica que el tipo sea valido
                            error = true;
                            texto = texto + "El vehiculo debe ser carro, moto o bicicleta.";
                        }

                        switch (p) {//verifica que la placa sea valida
                            case "carro":
                                if (!pl.matches("^[A-Za-z]{3}\\d{3}$")) {
                                    error = true;
                                    texto = texto + "Placa invalida.";
                                }
                                break;
                            case "moto":
                                if (!pl.matches("^[a-zA-Z]{3}\\d{2}[a-zA-Z]$")) {
                                    error = true;
                                    texto = texto + "Placa invalida.";
                                }
                                break;
                            case "bicicleta":
                                if (!pl.equals("0000")) {
                                    error = true;
                                    texto = texto + "Placa invalida.";
                                }
                                break;
                            default:
                                break;
                        }

                        String he = "" + tabla.getValueAt(i, 4);

                        String[] datosE;
                        datosE = he.split(":");
                        String e1 = datosE[0];

                        int nh = 0;
                        double vvv = 0;
                        String hs = "";

                        if (!he.matches("^(([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9])$")) {

                            error = true;
                            texto = texto + "Hora invalida.";
                        }
                        if (tabla.getValueAt(i, 5) != "") {

                            salida = true;

                            hs = "" + tabla.getValueAt(i, 5);

                            String[] datosS;
                            datosS = hs.split(":");
                            String s1 = datosS[0];

                            if (!hs.matches("^(([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9])$")) {

                                error = true;
                                texto = texto + "Hora invalida.";

                            } else if (Integer.parseInt(s1) < Integer.parseInt(e1)) {

                                error = true;
                                texto = texto + "Hora incongruente.";

                            }

                            nh = Integer.parseInt(s1) - Integer.parseInt(e1);
                            vvv = nh * Double.parseDouble("" + tabla.getValueAt(i, 7));

                        } else {

                            salida = false;

                        }

                        if (!error && salida == true) {
                            fact.updateAll(c, hs, vvv, "" + salida, us, "" + tabla.getValueAt(i, 3), p, pl, he);
                            JOptionPane.showMessageDialog(null, "Modificado. (Vehiculo ha salido)");
                        } else if (!error && salida == false) {
                            fact.updateAll(c, null, 0, "" + salida, us, "" + tabla.getValueAt(i, 3), p, pl, he);
                            JOptionPane.showMessageDialog(null, "Modificado. (Vehiculo sin salir)");
                        } else {
                            JOptionPane.showMessageDialog(null, texto);
                        }

                    }

                }
            }
        });

        setSize(500, 590);
        setLocationRelativeTo(null);
        setVisible(true);

    }

}

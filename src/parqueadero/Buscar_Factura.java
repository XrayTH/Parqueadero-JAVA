/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueadero;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author santi
 */
public class Buscar_Factura extends JDialog {

    JLabel et1, et2, et3, et4, et5, et6, et7, et8, et9, et10, et11;
    JTextField n_fac, vehiculo, nombre, placa, h_entra, min_entra,
            h_salid, min_salid, valor, horas, total;

    JButton buscar, cancelar;

    public Buscar_Factura(double vc, double vm, double vb, Frame e, boolean modal) {

        super(e, modal);

        Container con = getContentPane();
        con.setLayout(new FlowLayout());
        ConsultasFactura fact = new ConsultasFactura();

        et1 = new JLabel("N° Factura: ");
        et2 = new JLabel("Vehiculo");
        et3 = new JLabel("Nombre Cliente");
        et4 = new JLabel("Placa");
        et5 = new JLabel("Hora Entrada");
        et6 = new JLabel("Min Entrada");
        et7 = new JLabel("Hora Salida");
        et8 = new JLabel("Min Salida");
        et9 = new JLabel("Valor Hora");
        et10 = new JLabel("Horas");
        et11 = new JLabel("Total a Pagar");

        n_fac = new JTextField(10);
        vehiculo = new JTextField(10);
        nombre = new JTextField(10);
        placa = new JTextField(10);
        h_entra = new JTextField(10);
        min_entra = new JTextField(10);
        h_salid = new JTextField(10);
        min_salid = new JTextField(10);
        valor = new JTextField(10);
        horas = new JTextField(10);
        total = new JTextField(10);

        buscar = new JButton("Buscar");
        cancelar = new JButton("Cancelar");

        con.add(et1);
        con.add(n_fac);
        con.add(buscar);
        con.add(et2);
        con.add(vehiculo);
        con.add(et3);
        con.add(nombre);
        con.add(et4);
        con.add(placa);
        con.add(et5);
        con.add(h_entra);
        con.add(et6);
        con.add(min_entra);
        con.add(et7);
        con.add(h_salid);
        con.add(et8);
        con.add(min_salid);
        con.add(et9);
        con.add(valor);
        con.add(et10);
        con.add(horas);
        con.add(et11);
        con.add(total);
        con.add(cancelar);

        vehiculo.setEditable(false);
        nombre.setEditable(false);
        placa.setEditable(false);
        h_entra.setEditable(false);
        min_entra.setEditable(false);
        h_salid.setEditable(false);
        min_salid.setEditable(false);
        valor.setEditable(false);
        horas.setEditable(false);
        total.setEditable(false);

        

        buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (n_fac.getText().matches("\\d+") && fact.findFacturaXCodigo(Integer.parseInt(n_fac.getText())) != null) {
                    Factura f = fact.findFacturaXCodigo(Integer.parseInt(n_fac.getText()));

                    String[] datosE;
                    datosE = f.getHoraE().split(":");
                    String e1 = datosE[0];
                    String e2 = datosE[1];

                    String[] datosS;
                    datosS = f.getHoraS().split(":");
                    String s1 = datosS[0];
                    String s2 = datosS[1];

                    int hh = Integer.parseInt(s1) - Integer.parseInt(e1);

                    switch (f.getTipo()) {
                        case "carro":
                            valor.setText("" + vc);
                            break;
                        case "moto":
                            valor.setText("" + vm);
                            break;
                        case "bicicleta":
                            valor.setText("" + vb);
                            break;
                        default:
                            break;
                    }

                    vehiculo.setText(f.getTipo());
                    nombre.setText(f.getNombre());
                    placa.setText(f.getPlaca());
                    h_entra.setText(e1);
                    min_entra.setText(e2);
                    h_salid.setText(s1);
                    min_salid.setText(s2);

                    horas.setText("" + hh);
                    total.setText("" + f.getValor());

                } else {
                    JOptionPane.showMessageDialog(null, "El codigo no es valido o no encontrado.");
                }
            }
        });

        cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setSize(100, 650);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }

}

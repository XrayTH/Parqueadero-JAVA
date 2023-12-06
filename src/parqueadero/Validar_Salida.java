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
public class Validar_Salida extends JDialog {

    JLabel et1, et2, et3, et4, et5, et6, et7, et8, et9, et10;
    JTextField vehiculo, nombre, placa, h_entra, min_entra,
            h_salid, min_salid, valor, horas, total;

    JButton guardar, cancelar;

    public Validar_Salida(Frame e, boolean modal, String vv, String vp, String vplaca, String vhs, String vms, double vc, double vm, double vb) {

        super(e, modal);
        Container con = getContentPane();
        con.setLayout(new FlowLayout());
        ConsultasFactura fact = new ConsultasFactura();

        et1 = new JLabel("Vahiculo");
        et2 = new JLabel("Nombre Cliente");
        et3 = new JLabel("Placa");
        et4 = new JLabel("Hora Entrada");
        et5 = new JLabel("Min Entrada");
        et6 = new JLabel("Hora Salida");
        et7 = new JLabel("Min Salida");
        et8 = new JLabel("Valor Hora");
        et9 = new JLabel("Horas");
        et10 = new JLabel("Total a Pagar");

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

        guardar = new JButton("Guardar");
        cancelar = new JButton("Cancelar");

        con.add(et1);
        con.add(vehiculo);
        con.add(et2);
        con.add(nombre);
        con.add(et3);
        con.add(placa);
        con.add(et4);
        con.add(h_entra);
        con.add(et5);
        con.add(min_entra);
        con.add(et6);
        con.add(h_salid);
        con.add(et7);
        con.add(min_salid);
        con.add(et8);
        con.add(valor);
        con.add(et9);
        con.add(horas);
        con.add(et10);
        con.add(total);

        con.add(guardar);
        con.add(cancelar);

        switch (vv) {
            case "c":
                vehiculo.setText("carro");
                valor.setText(""+vc);
                break;
            case "m":
                vehiculo.setText("moto");
                valor.setText(""+vm);
                break;
            case "b":
                vehiculo.setText("bicicleta");
                valor.setText(""+vb);
                break;
            default:
                break;
        }

        int vCodigo = fact.verificarEstancia(vplaca, Integer.parseInt(vp)).getCodigo();
        
        nombre.setText(fact.verificarEstancia(vplaca, Integer.parseInt(vp)).getNombre());

        placa.setText(vplaca);

        String[] datos;
        datos = fact.verificarEstancia(vplaca, Integer.parseInt(vp)).getHoraE().split(":");
        String e1 = datos[0];
        String e2 = datos[1];
        String e3 = datos[2];

        h_entra.setText(e1);
        min_entra.setText(e2);
        h_salid.setText(vhs);
        min_salid.setText(vms);
        
        
        double hh = Double.parseDouble(vhs) - Double.parseDouble(e1);
        
        if(hh == 0){
            hh = 1;
        }
        
        horas.setText(""+hh);
        
        double pagar = Double.parseDouble(valor.getText()) * hh;
        
        total.setText(""+pagar);
        
        guardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                fact.updateSalida(vCodigo, ""+vhs+":"+vms+":00", Double.parseDouble(total.getText()));
                JOptionPane.showMessageDialog(null, "El codigo de su factura es: "+vCodigo);
                dispose();
                
            }
        });

        cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setSize(100, 600);
        setLocationRelativeTo(null);
        setVisible(true);

    }

}

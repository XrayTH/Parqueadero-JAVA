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
public class Detalle_Ingreso extends JDialog {

    JLabel et1, et2, et3, et4, et5;
    JTextField vehiculo, placa, hora_entrada, min_entrada, nombre;

    boolean error = false;

    JButton guardar, cancelar;

    public Detalle_Ingreso(Frame e, boolean modal, String vv, String vp, String vplaca, String vhe, String vme, int uCod) {

        super(e, modal);
        Container cont = getContentPane();
        cont.setLayout(new FlowLayout());
        
        ConsultasFactura fact = new ConsultasFactura();

        et1 = new JLabel("Vehiculo: ");
        et2 = new JLabel("Placa: ");
        et3 = new JLabel("Hora Entrada: ");
        et4 = new JLabel("Min Entrada: ");
        et5 = new JLabel("Nombre Cliente: ");

        vehiculo = new JTextField(10);
        placa = new JTextField(10);
        hora_entrada = new JTextField(10);
        min_entrada = new JTextField(10);
        nombre = new JTextField(10);

        guardar = new JButton("Guardar");
        cancelar = new JButton("Cancelar");

        cont.add(et1);
        cont.add(vehiculo);
        cont.add(et2);
        cont.add(placa);
        cont.add(et3);
        cont.add(hora_entrada);
        cont.add(et4);
        cont.add(min_entrada);
        cont.add(et5);
        cont.add(nombre);
        cont.add(guardar);
        cont.add(cancelar);

        switch (vv) {
                    case "c":
                        vehiculo.setText("carro");   break;
                    case "m":
                        vehiculo.setText("moto");   break;
                    case "b":
                        vehiculo.setText("bicicleta");   break;
                    default:
                        break;
                }
        
        placa.setText(vplaca);
        hora_entrada.setText(vhe);
        min_entrada.setText(vme);
        
        vehiculo.setEditable(false);
        hora_entrada.setEditable(false);
        min_entrada.setEditable(false);
        placa.setEditable(false);

        guardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if(nombre.getText().matches("[A-Za-z]+")){//nombre valido
                    
                    Factura ing = new Factura();
                        
                        ing.setTipo(vehiculo.getText());
                        ing.setUsuarioCod(uCod);
                        ing.setNombre(nombre.getText());
                        ing.setPuesto(Integer.parseInt(vp));
                        ing.setPlaca(vplaca);
                        ing.setHoraE(vhe+":"+vme+":00");
                        ing.setSalio("false");
                        
                        fact.insertEntrada(ing);
                        JOptionPane.showMessageDialog(null, "Creado con Exito.");
                        dispose();
                    
                }else{
                
                JOptionPane.showMessageDialog(null, "Nombre invalido.");
                    
                }
                
                
            }
        });

        cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        
        setSize(200, 250);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }

}

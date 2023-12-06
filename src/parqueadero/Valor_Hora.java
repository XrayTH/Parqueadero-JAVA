/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueadero;

import javax.swing.JDialog;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author santi
 */
public class Valor_Hora extends JDialog {

    JLabel et1, et2, et3;
    JTextField a, b, c;
    JButton guardar, cancelar;

    double vc, vm, vb;

    public Valor_Hora(Frame e, boolean modal) {

        super(e, modal);
        
        Container con = getContentPane();
        con.setLayout(new FlowLayout());

        et1 = new JLabel("Hora Carro: ");
        et2 = new JLabel("Hora Moto: ");
        et3 = new JLabel("Hora Bicicleta: ");

        a = new JTextField(10);
        b = new JTextField(10);
        c = new JTextField(10);

        guardar = new JButton("Guardar");
        cancelar = new JButton("Cancelar");

        con.add(et1);
        con.add(a);
        con.add(et2);
        con.add(b);
        con.add(et3);
        con.add(c);

        con.add(guardar);
        con.add(cancelar);

        

        guardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (a.getText().matches("^[-+]?\\d*\\.?\\d+$")
                        && b.getText().matches("^[-+]?\\d*\\.?\\d+$")
                        && c.getText().matches("^[-+]?\\d*\\.?\\d+$")) {

                    vc = Double.parseDouble(a.getText());
                    vm = Double.parseDouble(b.getText());
                    vb = Double.parseDouble(c.getText());
                    dispose();

                }else{
                        JOptionPane.showMessageDialog(null, "Los valores deben ser numericos.");
                    }

            }
        });

        cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        setSize(250, 200);
        setLocationRelativeTo(null);
        setVisible(true);
        
        
    }

}

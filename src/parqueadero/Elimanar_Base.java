/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueadero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author santi
 */
public class Elimanar_Base extends JDialog {

    JLabel et1, et2, et3;
    JButton si, no;
    JTextField Usuario, Contraseña;

    public Elimanar_Base(String nBD, Frame e, boolean modal) {

        super(e, modal);
        Container con = getContentPane();
        con.setLayout(new FlowLayout());

        et1 = new JLabel("¿Desea Eliminar La Base de Datos?");
        et2 = new JLabel("Usuario: ");
        et3 = new JLabel("Contraseña: ");

        si = new JButton("Si");
        no = new JButton("No");
        Usuario = new JTextField(10);
        Contraseña = new JTextField(10);

        con.add(et1);
        con.add(et2);
        con.add(Usuario);
        con.add(et3);
        con.add(Contraseña);
        con.add(si);
        con.add(no);

        

        si.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Usuario.getText().equals("developer") && Contraseña.getText().equals("developer")) {

                    BorradorBaseDatos borrador = new BorradorBaseDatos();
                    borrador.borrarBaseDatos(nBD);
                    JOptionPane.showMessageDialog(null, "Base de datos borrada.");
                    dispose();

                } else {

                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");

                }
            }
        });

        no.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        setSize(225, 150);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }

}

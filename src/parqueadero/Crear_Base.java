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
public class Crear_Base extends JDialog {

    JLabel et1, et2, et3;
    JTextField nombre_Base, Usuario, Contraseña;
    JButton crear, cerrar;
    String nBD, uBD, cBD;

    public Crear_Base(Frame e, boolean modal) {

        super(e, modal);
        Container con = getContentPane();
        con.setLayout(new FlowLayout());
        
        ConsultasUsuario usu = new ConsultasUsuario();

        et1 = new JLabel("Nombre de BD: ");
        et2 = new JLabel("Usuario: ");
        et3 = new JLabel("Contraseña: ");

        nombre_Base = new JTextField(10);
        Usuario = new JTextField(10);
        Contraseña = new JTextField(10);

        crear = new JButton("Crear");
        cerrar = new JButton("Cerrar");

        con.add(et1);
        con.add(nombre_Base);
        con.add(et2);
        con.add(Usuario);
        con.add(et3);
        con.add(Contraseña);
        con.add(crear);
        con.add(cerrar);

        

        crear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                

                    String BD = nombre_Base.getText();
                    String u = Usuario.getText();
                    String c = Contraseña.getText();
                    
                    DatabaseCreator creator = new DatabaseCreator();
                    
                    nBD = BD;
                    uBD = u;
                    cBD = c;
                    
                    creator.crearBaseDeDatos(BD, u, c);
                    
                    dispose();

                

            }
        });

        cerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        setSize(150, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }

    
    
}

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
public class Iniciar_Sesion extends JDialog {

    JLabel et1, et2;
    JTextField correo, contraseña;
    JButton aceptar;
    int uCod, uNivel;
    String uNombre, uContraseña, uCorreo;

    public Iniciar_Sesion(Frame e, boolean modal) {

        super(e, modal);

        Container cont = getContentPane();
        cont.setLayout(new FlowLayout());

        ConsultasUsuario consultasUsuarios = new ConsultasUsuario();

        et1 = new JLabel("Correo: ");
        et2 = new JLabel("Contraseña: ");

        correo = new JTextField(10);
        contraseña = new JTextField(10);

        aceptar = new JButton("Aceptar");

        cont.add(et1);
        cont.add(correo);
        cont.add(et2);
        cont.add(contraseña);

        cont.add(aceptar);

        aceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (consultasUsuarios.validarLog(correo.getText(), contraseña.getText()) != null) {
                    uCod = consultasUsuarios.validarLog(correo.getText(), contraseña.getText()).getCodigo();
                    uNombre = consultasUsuarios.validarLog(correo.getText(), contraseña.getText()).getNombre();
                    uContraseña = consultasUsuarios.validarLog(correo.getText(), contraseña.getText()).getContraseña();
                    uCorreo = consultasUsuarios.validarLog(correo.getText(), contraseña.getText()).getCorreo();
                    uNivel = consultasUsuarios.validarLog(correo.getText(), contraseña.getText()).getNivel();
                    JOptionPane.showMessageDialog(null, "Logueo Exitoso. Bienvenido " + uNombre);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
                }
            }
        });

        setSize(225, 170);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }

}

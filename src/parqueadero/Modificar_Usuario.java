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
public class Modificar_Usuario extends JDialog {

    JLabel et1, et2, et3, et4, et5;
    JTextField codigo, nombre, correo, contraseña;
    JButton buscar, modificar, cerrar;

    boolean error;
    int cod;
    String texto = "";

    JComboBox combo;

    public Modificar_Usuario(Frame e, boolean modal) {

        super(e, modal);

        ConsultasUsuario consultasUsuarios = new ConsultasUsuario();

        Container cont = getContentPane();
        cont.setLayout(new FlowLayout());

        et1 = new JLabel("Codigo: ");
        et2 = new JLabel("Nombre: ");
        et3 = new JLabel("Correo: ");
        et4 = new JLabel("Contraseña: ");
        et5 = new JLabel("Nivel: ");

        codigo = new JTextField(5);
        nombre = new JTextField(10);
        correo = new JTextField(10);
        contraseña = new JTextField(10);

        String niveles[] = {"", "1", "2", "3"};
        combo = new JComboBox(niveles);

        buscar = new JButton("Buscar");
        modificar = new JButton("Modificar");
        cerrar = new JButton("Cerrar");

        cont.add(et1);
        cont.add(codigo);
        cont.add(buscar);
        cont.add(et2);
        cont.add(nombre);
        cont.add(et3);
        cont.add(correo);
        cont.add(et4);
        cont.add(contraseña);
        cont.add(et5);
        cont.add(combo);

        cont.add(modificar);
        cont.add(cerrar);

        

        buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                error = false;

                if (consultasUsuarios.findXCod(Integer.parseInt(codigo.getText())) == null) {//Verifica si ya existe un usuario con ese correo
                    error = true;
                    JOptionPane.showMessageDialog(null, "Usuario No Encontrado");

                } else {

                    cod = Integer.parseInt(codigo.getText());
                    nombre.setText(consultasUsuarios.findXCod(cod).getNombre());
                    correo.setText(consultasUsuarios.findXCod(cod).getCorreo());
                    contraseña.setText(consultasUsuarios.findXCod(cod).getContraseña());
                    combo.setSelectedItem("" + consultasUsuarios.findXCod(cod).getNivel());

                }

            }
        });

        modificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (consultasUsuarios.findXCorreo(correo.getText()) != null && consultasUsuarios.findXCorreo(correo.getText()).getCodigo() != cod) {
                    error = true;
                    texto = texto + "Usuario ya existente.";
                }

                if (!correo.getText().matches(".*@gmail\\.com$")) {
                    error = true;
                    texto = texto + "Ingrese un correo valido.";
                }

                if (!nombre.getText().matches("[a-zA-Z]+")) {
                    error = true;
                    texto = texto + "Ingrese un nombre valido.";
                }

                if ("".equals(contraseña.getText())) {
                    error = true;
                    texto = texto + "Ingrese una contraseña valida.";
                }

                if (combo.getSelectedItem() == "") {
                    error = true;
                    texto = texto + "Escoja un nivel.";
                }

                if (!error) {

                    consultasUsuarios.update(cod, nombre.getText(), contraseña.getText(), correo.getText(), Integer.parseInt((String) combo.getSelectedItem()));
                    JOptionPane.showMessageDialog(null, "Modificado con Exito.");
                    dispose();

                } else {
                    JOptionPane.showMessageDialog(null, texto);
                }

            }
        });

        cerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        setSize(100, 350);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }

    
}

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
public class Crear_Usuario extends JDialog{
    
    
    
    JLabel et1, et2, et3, et4;
    JTextField nombre, correo, contraseña;
    JButton crear;
    JButton cerrar;
    boolean error = false;
    
    JComboBox combo;
    
    public Crear_Usuario(Frame e, boolean modal) {

        super(e, modal);
        
        ConsultasUsuario consultasUsuarios = new ConsultasUsuario();
        
        Container cont = getContentPane();
        cont.setLayout(new FlowLayout());
        
        
        
        et1 = new JLabel("Nombre: ");
        et2 = new JLabel("Correo: ");
        et3 = new JLabel("Contraseña: ");
        et4 = new JLabel("Nivel: ");
        
        nombre = new JTextField(10);
        correo = new JTextField(10);
        contraseña = new JTextField(10);
                
        String niveles[] = {"", "1", "2", "3"};
        combo = new JComboBox(niveles);
        
        crear = new JButton("Crear");
        cerrar = new JButton("Cerrar");
        
        cont.add(et1);
        cont.add(nombre);
        cont.add(et2);
        cont.add(correo);
        cont.add(et3);
        cont.add(contraseña);
        cont.add(et4);
        
        cont.add(combo);
        
        cont.add(crear);
        cont.add(cerrar);
        
        
        
        crear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                String texto = "";
                error = false;
                
                if(consultasUsuarios.findXCorreo(correo.getText()) != null){//Verifica si ya existe un usuario con ese correo
                    
                    error = true;
                    texto = texto + "Usuario ya existente.";
                }
                    
                    
                    if(!correo.getText().matches(".*@gmail\\.com$")){
                        error = true;
                        texto = texto + "Ingrese un correo valido.";
                    }
                    
                    if(!nombre.getText().matches("[a-zA-Z]+")){
                        error = true;
                        texto = texto + "Ingrese un nombre valido.";
                    }
                    
                    if("".equals(contraseña.getText())){
                        error = true;
                        texto = texto + "Ingrese una contraseña valida.";
                    }
                    
                    if(combo.getSelectedItem() == ""){
                        error = true;
                        texto = texto + "Escoja un nivel.";
                    }
                    
                    if(!error){
                        
                        Usuario usu = new Usuario();
                        
                        usu.setNombre(nombre.getText());
                        usu.setCorreo(correo.getText());
                        usu.setContraseña(contraseña.getText());
                        usu.setNivel(Integer.parseInt((String) combo.getSelectedItem()));
                        
                        consultasUsuarios.insert(usu);
                        JOptionPane.showMessageDialog(null, "Creado con Exito.");
                        dispose();
                        
                    }else{
                        JOptionPane.showMessageDialog(null, texto);
                    }
                    
                }
                
            
        });
        
        cerrar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });
        
        setSize(200, 180);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    
}

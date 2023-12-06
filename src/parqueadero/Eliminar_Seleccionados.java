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
public class Eliminar_Seleccionados extends JDialog{
    
    JLabel et1;
    JTextField a;
    JButton si, no;
    
    public Eliminar_Seleccionados(){
        
        Container con = getContentPane();
        con.setLayout(new FlowLayout());
        
        et1 = new JLabel("¿Seguro que desea Eliminar los Usuarios Seleccionados?");
        
        si = new JButton("Si");
        no = new JButton("No");
        
        con.add(et1);
        con.add(si);
        con.add(no);
        
        
        
        si.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Usuarios Eliminados");
                dispose();
            }
        });
        
        no.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });
        
        setSize(380, 100);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
}

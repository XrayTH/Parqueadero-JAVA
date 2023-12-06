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
public class Cerrar_Sesion extends JDialog{
    
    JLabel et1;
    JButton si, no;
    
    public Cerrar_Sesion(/*Frame pa, boolean modal, String x[][]*/){
        //super(pa, modal);
        
        Container cont = getContentPane();
        cont.setLayout(new FlowLayout());
        
        et1 = new JLabel("¿Desea Cerrar su Usuario?");
        
        si = new JButton("Si");
        no = new JButton("No");
        
        cont.add(et1);
        cont.add(si);
        cont.add(no);
        
        
        
        si.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });
        
        no.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });
        
        setSize(200, 100);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
}

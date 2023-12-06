/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import parqueadero.Conexion;
/**
 *
 * @author santi
 */
public class Test_Conexion {
    public static void main(String[] args) {
        Conexion connection = new Conexion();
        connection.connect();
        
        connection.disconnect();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueadero;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BorradorBaseDatos {
    FileManager fm = new FileManager();
    FileManagerUsu fm1 = new FileManagerUsu();
    FileManagerCon fm2= new FileManagerCon();
    
    String nBD = fm.leerArchivo();
    String usu = fm1.leerArchivo();
    String con = fm2.leerArchivo();
   
    
    public final String SERVER = "jdbc:mysql://localhost:3306";
    public final String USER = usu;
    public final String PASSWORD = con;

    public void borrarBaseDatos(String nombreBaseDatos) {
        try {
            // Establecer conexión con el servidor MySQL
            Connection connection = DriverManager.getConnection(SERVER, usu, con);
            Statement statement = connection.createStatement();

            // Ejecutar el comando SQL para borrar la base de datos
            String sql = "DROP DATABASE IF EXISTS " + nombreBaseDatos;
            statement.executeUpdate(sql);

            // Cerrar la conexión
            statement.close();
            connection.close();

            System.out.println("La base de datos " + nombreBaseDatos + " ha sido eliminada exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar la base de datos: " + e.getMessage());
        }
    }

}


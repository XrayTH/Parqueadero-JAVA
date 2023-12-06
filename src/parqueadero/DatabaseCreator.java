/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueadero;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DatabaseCreator {

    FileManager fm = new FileManager();
    FileManagerUsu fm1 = new FileManagerUsu();
    FileManagerCon fm2 = new FileManagerCon();

    String nBD = fm.leerArchivo();
    String usu = fm.leerArchivo();
    String con = fm.leerArchivo();

    public final String SERVER = "jdbc:mysql://localhost:3306";
    public final String USER = usu;
    public final String PASSWORD = con;

    public void crearBaseDeDatos(String nombreBD, String usu, String con) {
        // Crear la base de datos
        String crearBDQuery = "CREATE DATABASE IF NOT EXISTS " + nombreBD;
        try (Connection connection = DriverManager.getConnection(SERVER, usu, con); Statement statement = connection.createStatement()) {
            statement.executeUpdate(crearBDQuery);
            System.out.println("Base de datos creada: " + nombreBD);
            JOptionPane.showMessageDialog(null, "Base de datos creada: " + nombreBD);
        } catch (SQLException e) {
            System.out.println("Error al crear la base de datos: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al crear la base de datos: " + e.getMessage());
            return;
        }

        // Ejecutar el archivo "database.sql" para llenar la base de datos
        try (Connection connection = DriverManager.getConnection(SERVER + "/" + nombreBD, usu, con); Statement statement = connection.createStatement()) {
            String archivoSQL = "database.sql";
            BufferedReader reader = new BufferedReader(new FileReader(archivoSQL));
            String linea;
            StringBuilder scriptSQL = new StringBuilder();
            while ((linea = reader.readLine()) != null) {
                scriptSQL.append(linea);
                if (linea.endsWith(";")) {
                    statement.executeUpdate(scriptSQL.toString());
                    scriptSQL.setLength(0);
                }
            }
            reader.close();
            System.out.println("Archivo SQL ejecutado exitosamente en la base de datos: " + nombreBD);
        } catch (Exception e) {
            System.out.println("Error al ejecutar el archivo SQL: " + e.getMessage());
        }
    }
}

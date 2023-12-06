/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueadero;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class Conexion {

    public static Connection connection;
    public static Statement statement;

    FileManager fm = new FileManager();
    FileManagerUsu fm1 = new FileManagerUsu();
    FileManagerCon fm2 = new FileManagerCon();

    String nBD = fm.leerArchivo();
    String usu = fm1.leerArchivo();
    String con = fm2.leerArchivo();

    public final String SERVER = "jdbc:mysql://localhost:3306";
    public final String BD = nBD;
    public final String USER = usu;
    public final String PASSWORD = con;

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection(SERVER + "/" + BD, USER, PASSWORD);
            statement = connection.createStatement();

            System.out.println("Conectando...");

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            System.out.println("Error de Coneccion a la Base de Datos: " + e.getMessage());
        }
    }

    public void disconnect() {
        try {
            // Llamamos al método para generar el archivo .sql antes de cerrar la conexión
            //generarArchivoSQL();

            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }

            System.out.println("Desconectando...");
        } catch (SQLException e) {
            System.out.println("Error de Desconeccion a la Base de Datos: " + e.getMessage());
        }
    }

    public void generarArchivoSQL() {
        connect();
        try (FileWriter writer = new FileWriter("database.sql")) {
            DatabaseMetaData metaData = connection.getMetaData();
            String catalog = connection.getCatalog();

            // Obtener las tablas de la base de datos
            ResultSet tables = metaData.getTables(catalog, null, null, new String[]{"TABLE"});
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                writer.write("DROP TABLE IF EXISTS " + tableName + ";\n");

                // Obtener la estructura de la tabla
                ResultSet columns = metaData.getColumns(catalog, null, tableName, null);
                StringBuilder createTableQuery = new StringBuilder("CREATE TABLE " + tableName + " (\n");
                StringBuilder primaryKeyQuery = new StringBuilder();
                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    String columnType = columns.getString("TYPE_NAME");
                    int columnSize = columns.getInt("COLUMN_SIZE");
                    boolean isPrimaryKey = isPrimaryKey(tableName, columnName); // Verificar si es clave primaria
                    if (isPrimaryKey) {
                        primaryKeyQuery.append(columnName).append(", ");
                    }
                    if (!columnType.equalsIgnoreCase("DOUBLE") && !columnType.equalsIgnoreCase("TIME")) {
                        createTableQuery.append("  ").append(columnName).append(" ").append(columnType);
                        if (columnSize > 0) {
                            createTableQuery.append("(").append(columnSize).append(")");
                        }
                        if (isPrimaryKey) {
                            createTableQuery.append(" AUTO_INCREMENT");
                        }
                        createTableQuery.append(",\n");
                    } else {
                        createTableQuery.append("  ").append(columnName).append(" ").append(columnType).append(",\n");
                    }
                }
                if (primaryKeyQuery.length() > 0) {
                    primaryKeyQuery.setLength(primaryKeyQuery.length() - 2); // Eliminar la última coma
                    createTableQuery.append("  PRIMARY KEY (").append(primaryKeyQuery).append("),\n");
                }
                createTableQuery.setLength(createTableQuery.length() - 2); // Eliminar la última coma

                createTableQuery.append("\n);\n");
                writer.write(createTableQuery.toString());

                // Obtener los datos de la tabla
                Statement statement = connection.createStatement();
                ResultSet data = statement.executeQuery("SELECT * FROM " + tableName);
                ResultSetMetaData resultSetMetaData = data.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();
                while (data.next()) {
                    StringBuilder insertQuery = new StringBuilder("INSERT INTO " + tableName + " VALUES (");
                    for (int i = 1; i <= columnCount; i++) {
                        Object value = data.getObject(i);
                        if (value == null) {
                            insertQuery.append("NULL");
                        } else {
                            if (value instanceof String) {
                                insertQuery.append("'").append(value).append("'");
                            } else if (value instanceof Time) {
                                insertQuery.append("'").append(data.getTime(i).toString()).append("'");
                            } else {
                                insertQuery.append(value);
                            }
                        }
                        if (i < columnCount) {
                            insertQuery.append(", ");
                        }
                    }
                    insertQuery.append(");\n");
                    writer.write(insertQuery.toString());
                }
                writer.write("\n");
            }
        } catch (IOException | SQLException e) {
            System.out.println("Error al generar el archivo .sql: " + e.getMessage());
        } finally {
            disconnect();
        }
    }

    private boolean isPrimaryKey(String tableName, String columnName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableName);
        while (primaryKeys.next()) {
            String primaryKeyColumnName = primaryKeys.getString("COLUMN_NAME");
            if (columnName.equalsIgnoreCase(primaryKeyColumnName)) {
                return true;
            }
        }
        return false;
    }
}

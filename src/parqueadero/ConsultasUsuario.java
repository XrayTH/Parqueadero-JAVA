/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueadero;

import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Xray
 */
public class ConsultasUsuario extends Conexion {

    
    
    public void insert(Usuario usu) {
        try {

            connect();
            String sql = "insert into usuarios (Nombre, Correo, Contrasena, Nivel) values (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usu.getNombre());
            preparedStatement.setString(2, usu.getCorreo());
            preparedStatement.setString(3, usu.getContraseña());
            preparedStatement.setInt(4, usu.getNivel());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null,
                    "Error al insertar: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        } finally {
            disconnect();
        }
    }
    
    public List<Usuario> findAll() {
    List<Usuario> resultados = new ArrayList<>();

    try {
        connect();
        
        String sql = "SELECT * FROM usuarios";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int codigo = resultSet.getInt("Codigo");
            String nombre = resultSet.getString("Nombre");
            String contrasena = resultSet.getString("Contrasena");
            String correo = resultSet.getString("Correo");
            int nivel = resultSet.getInt("Nivel");

            Usuario usuario = new Usuario();
            usuario.setCodigo(codigo);
            usuario.setNombre(nombre);
            usuario.setContraseña(contrasena);
            usuario.setCorreo(correo);
            usuario.setNivel(nivel);
            
            resultados.add(usuario);
        }

        resultSet.close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al consultar usuarios por nivel: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        disconnect();
    }

    return resultados;
}
    
    
    
    public List<Usuario> findAllXNvl(int nivel) {
    List<Usuario> resultados = new ArrayList<>();

    try {
        connect();
        String sql = "SELECT * FROM usuarios WHERE Nivel = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, nivel);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int codigo = resultSet.getInt("Codigo");
            String nombre = resultSet.getString("Nombre");
            String contrasena = resultSet.getString("Contrasena");
            String correo = resultSet.getString("Correo");
            nivel = resultSet.getInt("Nivel");

            Usuario usuario = new Usuario();
            usuario.setCodigo(codigo);
            usuario.setNombre(nombre);
            usuario.setContraseña(contrasena);
            usuario.setCorreo(correo);
            usuario.setNivel(nivel);
            
            resultados.add(usuario);
        }

        resultSet.close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al consultar usuarios por nivel: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        disconnect();
    }

    return resultados;
}


    public Usuario findXCod(int codigo) {

        Usuario usuario = null;

        try {

            connect();
            String sql = "SELECT * FROM usuarios WHERE Codigo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuario = new Usuario();

                usuario.setCodigo(resultSet.getInt("Codigo"));
                usuario.setNombre(resultSet.getString("Nombre"));
                usuario.setContraseña(resultSet.getString("Contrasena"));
                usuario.setCorreo(resultSet.getString("Correo"));
                usuario.setNivel(resultSet.getInt("Nivel"));

            }

            preparedStatement.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null,
                    "Error al buscar: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        } finally {
            disconnect();
        }

        return usuario;
    }

    public Usuario findXName(String Nombre) {

        Usuario usuario = null;

        try {

            connect();
            String sql = "SELECT * FROM usuarios WHERE Nombre = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Nombre);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuario = new Usuario();

                usuario.setCodigo(resultSet.getInt("Codigo"));
                usuario.setNombre(resultSet.getString("Nombre"));
                usuario.setContraseña(resultSet.getString("Contrasena"));
                usuario.setCorreo(resultSet.getString("Correo"));
                usuario.setNivel(resultSet.getInt("Nivel"));

            }

            preparedStatement.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null,
                    "Error al buscar: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        } finally {
            disconnect();
        }

        return usuario;
    }
    
    public Usuario findXCorreo(String Correo) {

        Usuario usuario = null;

        try {

            connect();
            String sql = "SELECT * FROM usuarios WHERE Correo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Correo);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuario = new Usuario();

                usuario.setCodigo(resultSet.getInt("Codigo"));
                usuario.setNombre(resultSet.getString("Nombre"));
                usuario.setContraseña(resultSet.getString("Contrasena"));
                usuario.setCorreo(resultSet.getString("Correo"));
                usuario.setNivel(resultSet.getInt("Nivel"));

            }

            preparedStatement.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null,
                    "Error al buscar: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        } finally {
            disconnect();
        }

        return usuario;
    }
    
    public Usuario validarLog(String Correo, String contraseña) {

        Usuario usuario = null;

        try {

            connect();
            String sql = "SELECT * FROM usuarios WHERE Correo = ? AND Contrasena = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Correo);
            preparedStatement.setString(2, contraseña);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuario = new Usuario();

                usuario.setCodigo(resultSet.getInt("Codigo"));
                usuario.setNombre(resultSet.getString("Nombre"));
                usuario.setContraseña(resultSet.getString("Contrasena"));
                usuario.setCorreo(resultSet.getString("Correo"));
                usuario.setNivel(resultSet.getInt("Nivel"));

            }

            preparedStatement.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null,
                    "Error al buscar: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        } finally {
            disconnect();
        }

        return usuario;
    }
    
    public void update(int codigo, String nombre, String contraseña, String correo, int nivel) {
        try {

            connect();
            String sql = "UPDATE usuarios SET Nombre = ?, Contrasena = ?, Correo = ?, Nivel = ?  WHERE Codigo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, contraseña);
            preparedStatement.setString(3, correo);
            preparedStatement.setInt(4, nivel);
            preparedStatement.setInt(5, codigo);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null,
                    "Error al actualizar: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        } finally {
            disconnect();
        }
    }
    
    public void delete(int codigo) {
    try {
        connect();
        String sql = "DELETE FROM usuarios WHERE Codigo = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, codigo);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null,
                "Error al eliminar: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    } finally {
        disconnect();
    }
}


}

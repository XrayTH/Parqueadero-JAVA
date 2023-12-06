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
public class ConsultasFactura extends Conexion {
    
    

    public void insertEntrada(Factura factura) {
        try {
            connect();
            
            String sql = "INSERT INTO factura (Codigo, Usuario_COD, Nombre, Puesto, Vehiculo_Tipo, Vehiculo_Placa, Hora_Entrada, Hora_Salida, Valor_Pagar, Salio) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, factura.getCodigo());
            preparedStatement.setInt(2, factura.getUsuarioCod());
            preparedStatement.setString(3, factura.getNombre());
            preparedStatement.setInt(4, factura.getPuesto());
            preparedStatement.setString(5, factura.getTipo());
            preparedStatement.setString(6, factura.getPlaca());
            preparedStatement.setString(7, factura.getHoraE());
            preparedStatement.setString(8, factura.getHoraS());
            preparedStatement.setDouble(9, factura.getValor());
            preparedStatement.setString(10, factura.getSalio());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            disconnect();
        }
    }

    public Factura verificarEstancia(String placa, int puesto) {

        Factura factura = null;

        try {

            connect();
            String sql = "SELECT * FROM factura WHERE (Vehiculo_Placa = ? OR Puesto = ?) AND Salio = 'false'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, placa);
            preparedStatement.setInt(2, puesto);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                factura = new Factura();

                factura.setCodigo(resultSet.getInt("Codigo"));
                factura.setUsuarioCod(resultSet.getInt("Usuario_COD"));
                factura.setNombre(resultSet.getString("Nombre"));
                factura.setTipo(resultSet.getString("Vehiculo_Tipo"));
                factura.setPlaca(resultSet.getString("Vehiculo_Placa"));
                factura.setPuesto(resultSet.getInt("Puesto"));
                factura.setHoraE(resultSet.getString("Hora_Entrada"));
                factura.setHoraS(resultSet.getString("Hora_Salida"));
                factura.setSalio(resultSet.getString("Salio"));
                factura.setValor(resultSet.getDouble("Valor_Pagar"));

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

        return factura;
    }
    
    public Factura verificarEstanciaEspecifica(String placa, int puesto) {

        Factura factura = null;

        try {

            connect();
            String sql = "SELECT * FROM factura WHERE (Vehiculo_Placa = ? AND Puesto = ?) AND Salio = 'false'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, placa);
            preparedStatement.setInt(2, puesto);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                factura = new Factura();

                factura.setCodigo(resultSet.getInt("Codigo"));
                factura.setUsuarioCod(resultSet.getInt("Usuario_COD"));
                factura.setNombre(resultSet.getString("Nombre"));
                factura.setTipo(resultSet.getString("Vehiculo_Tipo"));
                factura.setPlaca(resultSet.getString("Vehiculo_Placa"));
                factura.setPuesto(resultSet.getInt("Puesto"));
                factura.setHoraE(resultSet.getString("Hora_Entrada"));
                factura.setHoraS(resultSet.getString("Hora_Salida"));
                factura.setSalio(resultSet.getString("Salio"));
                factura.setValor(resultSet.getDouble("Valor_Pagar"));

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

        return factura;
    }
    
    public Factura verificarExistenciaDeOtrasPlacas(String placa, int codigo) {

        Factura factura = null;

        try {

            connect();
            String sql = "SELECT * FROM factura WHERE Vehiculo_Placa = ? AND Codigo != ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, placa);
            preparedStatement.setInt(2, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                factura = new Factura();

                factura.setCodigo(resultSet.getInt("Codigo"));
                factura.setUsuarioCod(resultSet.getInt("Usuario_COD"));
                factura.setNombre(resultSet.getString("Nombre"));
                factura.setTipo(resultSet.getString("Vehiculo_Tipo"));
                factura.setPlaca(resultSet.getString("Vehiculo_Placa"));
                factura.setPuesto(resultSet.getInt("Puesto"));
                factura.setHoraE(resultSet.getString("Hora_Entrada"));
                factura.setHoraS(resultSet.getString("Hora_Salida"));
                factura.setSalio(resultSet.getString("Salio"));
                factura.setValor(resultSet.getDouble("Valor_Pagar"));

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

        return factura;
    }

    public Factura findFacturaXPuesto(int puesto) {

        Factura factura = null;

        try {

            connect();
            String sql = "SELECT * FROM factura WHERE Puesto = ? AND Salio = 'false'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, puesto);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                factura = new Factura();

                factura.setCodigo(resultSet.getInt("Codigo"));
                factura.setUsuarioCod(resultSet.getInt("Usuario_COD"));
                factura.setNombre(resultSet.getString("Nombre"));
                factura.setTipo(resultSet.getString("Vehiculo_Tipo"));
                factura.setPlaca(resultSet.getString("Vehiculo_Placa"));
                factura.setPuesto(resultSet.getInt("Puesto"));
                factura.setHoraE(resultSet.getString("Hora_Entrada"));
                factura.setHoraS(resultSet.getString("Hora_Salida"));
                factura.setSalio(resultSet.getString("Salio"));
                factura.setValor(resultSet.getDouble("Valor_Pagar"));

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

        return factura;
    }

    public ArrayList<Factura> findAllFacturas() {
        ArrayList<Factura> resultados = new ArrayList<>();

        try {
            connect();
            String sql = "SELECT * FROM factura";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Factura factura = new Factura();

                factura.setCodigo(resultSet.getInt("Codigo"));
                factura.setUsuarioCod(resultSet.getInt("Usuario_COD"));
                factura.setNombre(resultSet.getString("Nombre"));
                factura.setTipo(resultSet.getString("Vehiculo_Tipo"));
                factura.setPlaca(resultSet.getString("Vehiculo_Placa"));
                factura.setPuesto(resultSet.getInt("Puesto"));
                factura.setHoraE(resultSet.getString("Hora_Entrada"));
                factura.setHoraS(resultSet.getString("Hora_Salida"));
                factura.setSalio(resultSet.getString("Salio"));
                factura.setValor(resultSet.getDouble("Valor_Pagar"));

                resultados.add(factura);
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

        return resultados;
    }

    public ArrayList<Factura> findAllFacturaXPlaca(String placa) {
        ArrayList<Factura> resultados = new ArrayList<>();

        try {
            connect();
            String sql = "SELECT * FROM factura WHERE Vehiculo_Placa = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, placa);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Factura factura = new Factura();

                factura.setCodigo(resultSet.getInt("Codigo"));
                factura.setUsuarioCod(resultSet.getInt("Usuario_COD"));
                factura.setNombre(resultSet.getString("Nombre"));
                factura.setTipo(resultSet.getString("Vehiculo_Tipo"));
                factura.setPlaca(resultSet.getString("Vehiculo_Placa"));
                factura.setPuesto(resultSet.getInt("Puesto"));
                factura.setHoraE(resultSet.getString("Hora_Entrada"));
                factura.setHoraS(resultSet.getString("Hora_Salida"));
                factura.setSalio(resultSet.getString("Salio"));
                factura.setValor(resultSet.getDouble("Valor_Pagar"));

                resultados.add(factura);
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

        return resultados;
    }
    
    public ArrayList<Factura> findAllFacturaXPlacaDis(String placa, int codigo) {
        ArrayList<Factura> resultados = new ArrayList<>();

        try {
            connect();
            String sql = "SELECT * FROM factura WHERE Vehiculo_Placa = ? AND Codigo != ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, placa);
            preparedStatement.setInt(2, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Factura factura = new Factura();

                factura.setCodigo(resultSet.getInt("Codigo"));
                factura.setUsuarioCod(resultSet.getInt("Usuario_COD"));
                factura.setNombre(resultSet.getString("Nombre"));
                factura.setTipo(resultSet.getString("Vehiculo_Tipo"));
                factura.setPlaca(resultSet.getString("Vehiculo_Placa"));
                factura.setPuesto(resultSet.getInt("Puesto"));
                factura.setHoraE(resultSet.getString("Hora_Entrada"));
                factura.setHoraS(resultSet.getString("Hora_Salida"));
                factura.setSalio(resultSet.getString("Salio"));
                factura.setValor(resultSet.getDouble("Valor_Pagar"));

                resultados.add(factura);
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

        return resultados;
    }

    public ArrayList<Factura> findAllFacturaXCodigo(int codigo) {
        ArrayList<Factura> resultados = new ArrayList<>();

        try {
            connect();
            String sql = "SELECT * FROM factura WHERE Codigo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Factura factura = new Factura();

                factura.setCodigo(resultSet.getInt("Codigo"));
                factura.setUsuarioCod(resultSet.getInt("Usuario_COD"));
                factura.setNombre(resultSet.getString("Nombre"));
                factura.setTipo(resultSet.getString("Vehiculo_Tipo"));
                factura.setPlaca(resultSet.getString("Vehiculo_Placa"));
                factura.setPuesto(resultSet.getInt("Puesto"));
                factura.setHoraE(resultSet.getString("Hora_Entrada"));
                factura.setHoraS(resultSet.getString("Hora_Salida"));
                factura.setSalio(resultSet.getString("Salio"));
                factura.setValor(resultSet.getDouble("Valor_Pagar"));

                resultados.add(factura);
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

        return resultados;
    }

    public Factura findFacturaXCodigo(int codigo) {

        Factura factura = null;

        try {

            connect();
            String sql = "SELECT * FROM factura WHERE Codigo = ? AND Salio = 'true'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                factura = new Factura();

                factura.setCodigo(resultSet.getInt("Codigo"));
                factura.setUsuarioCod(resultSet.getInt("Usuario_COD"));
                factura.setNombre(resultSet.getString("Nombre"));
                factura.setTipo(resultSet.getString("Vehiculo_Tipo"));
                factura.setPlaca(resultSet.getString("Vehiculo_Placa"));
                factura.setPuesto(resultSet.getInt("Puesto"));
                factura.setHoraE(resultSet.getString("Hora_Entrada"));
                factura.setHoraS(resultSet.getString("Hora_Salida"));
                factura.setSalio(resultSet.getString("Salio"));
                factura.setValor(resultSet.getDouble("Valor_Pagar"));

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

        return factura;
    }

    public ArrayList<Factura> findAllFacturaXUsuarioCOD(int usuarioCOD) {
        ArrayList<Factura> resultados = new ArrayList<>();

        try {
            connect();
            String sql = "SELECT * FROM factura WHERE Usuario_COD = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, usuarioCOD);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Factura factura = new Factura();

                factura.setCodigo(resultSet.getInt("Codigo"));
                factura.setUsuarioCod(resultSet.getInt("Usuario_COD"));
                factura.setNombre(resultSet.getString("Nombre"));
                factura.setTipo(resultSet.getString("Vehiculo_Tipo"));
                factura.setPlaca(resultSet.getString("Vehiculo_Placa"));
                factura.setPuesto(resultSet.getInt("Puesto"));
                factura.setHoraE(resultSet.getString("Hora_Entrada"));
                factura.setHoraS(resultSet.getString("Hora_Salida"));
                factura.setSalio(resultSet.getString("Salio"));
                factura.setValor(resultSet.getDouble("Valor_Pagar"));

                resultados.add(factura);
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

        return resultados;
    }

    public void updateSalida(int codigo, String hs, double valor) {
        try {

            connect();
            String sql = "UPDATE factura SET Hora_Salida = ?, Valor_Pagar = ?, Salio = 'true' WHERE Codigo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, hs);
            preparedStatement.setDouble(2, valor);
            preparedStatement.setInt(3, codigo);
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

    public void updateAll(int codigo, String hs, double valor, String salio, int usuarioCod, String nombre, String vehiculoTipo, String vehiculoPlaca, String he) {
        try {
            connect();
            String sql = "UPDATE factura SET Hora_Salida = ?, Valor_Pagar = ?, Salio = ?, Usuario_COD = ?, Nombre = ?, Vehiculo_Tipo = ?, Vehiculo_Placa = ?, Hora_Entrada = ? WHERE Codigo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, hs);
            preparedStatement.setDouble(2, valor);
            preparedStatement.setString(3, salio);  // Salio
            preparedStatement.setInt(4, usuarioCod);  // Usuario_COD
            preparedStatement.setString(5, nombre);  // Nombre
            preparedStatement.setString(6, vehiculoTipo);  // Vehiculo_Tipo
            preparedStatement.setString(7, vehiculoPlaca);  // Vehiculo_Placa
            preparedStatement.setString(8, he);  // Hora_Entrada
            preparedStatement.setInt(9, codigo);  // Codigo (condición de búsqueda)
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            disconnect();
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parqueadero;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author santi
 */
public class Parqueadero extends JFrame {

    /**
     * @param args the command line arguments
     */
    JLabel l1, l2;
    JTextField t1, t2;

    JMenu base_datos;
    JMenuItem crear_base;
    JMenuItem eliminar_base;

    JMenu login;
    JMenuItem iniciar_sesion;
    JMenuItem cerrar_sesion;

    JMenu usuario;
    JMenuItem crear;
    JMenuItem modificar;
    JMenuItem visualizar_eliminar;

    JMenu vehiculo;
    JMenuItem ingresa_vehiculo;
    JMenuItem salida_vehiculo;
    JMenuItem buscar_registro;
    JMenuItem modificar_registro;
    JMenuItem buscar_factura;
    JMenuItem valor_hora;

    int uCod = 0, uNivel = 0;
    String uNombre, uContraseña, uCorreo;
    double vc = 3000, vm = 2000, vb = 1500;

    public Parqueadero() {

        Container e = getContentPane();
        e.setLayout(new FlowLayout());

        FileManager fm = new FileManager();
        FileManagerUsu fm1 = new FileManagerUsu();
        FileManagerCon fm2 = new FileManagerCon();
        Conexion db = new Conexion();

        JMenuBar barra = new JMenuBar();
        setJMenuBar(barra);

        l1 = new JLabel("Su base de datos conectada es: ");
        l2 = new JLabel("Su usuario es: ");

        t1 = new JTextField(10);
        t2 = new JTextField(10);

        base_datos = new JMenu("Base de Datos");
        crear_base = new JMenuItem("Crear Base de Datos");
        eliminar_base = new JMenuItem("Eliminar Base de Datos");

        login = new JMenu("Login");
        iniciar_sesion = new JMenuItem("Iniciar Sesion");
        cerrar_sesion = new JMenuItem("Cerrar Sesion");

        usuario = new JMenu("Usuario");
        crear = new JMenuItem("Crear Usuario");
        modificar = new JMenuItem("Modificar Usuario");
        visualizar_eliminar = new JMenuItem("Visualizar y/o Eliminar");

        vehiculo = new JMenu("Vehiculo");
        ingresa_vehiculo = new JMenuItem("Ingreso Vehiculo");
        salida_vehiculo = new JMenuItem("Salida Vehiculo");
        buscar_registro = new JMenuItem("Buscar Registro");
        modificar_registro = new JMenuItem("Modificar Registro");
        buscar_factura = new JMenuItem("Buscar Factura");
        valor_hora = new JMenuItem("Valor Hora");
        /*
        e.add(l1);
        e.add(t1);
        e.add(l2);
        e.add(t2);
         */
        barra.add(base_datos);
        barra.add(login);
        barra.add(usuario);
        barra.add(vehiculo);

        // Base de Datos
        base_datos.add(crear_base);
        base_datos.add(eliminar_base);

        // Login
        login.add(iniciar_sesion);
        login.add(cerrar_sesion);

        // Usuario
        usuario.add(crear);
        usuario.add(modificar);
        usuario.add(visualizar_eliminar);

        // Vehiculo
        vehiculo.add(ingresa_vehiculo);
        vehiculo.add(salida_vehiculo);
        vehiculo.add(buscar_registro);
        vehiculo.add(modificar_registro);
        vehiculo.add(buscar_factura);
        vehiculo.add(valor_hora);

        t1.setEditable(false);
        t2.setEditable(false);

        // Base de Datos
        crear_base.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Crear_Base obj = new Crear_Base(null, true);
                if (obj.nBD != null && !obj.nBD.equals("")) {
                    fm.escribirArchivo(obj.nBD);
                    fm1.escribirArchivo(obj.uBD);
                    fm2.escribirArchivo(obj.cBD);

                }

            }
        });

        eliminar_base.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fm.leerArchivo() == null || fm.leerArchivo().equals("")) {

                    JOptionPane.showMessageDialog(null, "Base de datos no creada.");

                } else {
                    db.generarArchivoSQL();
                    new Elimanar_Base(fm.leerArchivo(), null, true);
                    fm.escribirArchivo("");
                }
            }
        });

        // Login
        iniciar_sesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (fm.leerArchivo() == null || fm.leerArchivo().equals("")) {

                    JOptionPane.showMessageDialog(null, "Base de datos no creada.");

                } else {

                    if (uCod == 0) {

                        Iniciar_Sesion obj = new Iniciar_Sesion(null, true);
                        uCod = obj.uCod;
                        uNombre = obj.uNombre;
                        uContraseña = obj.uContraseña;
                        uCorreo = obj.uContraseña;
                        uNivel = obj.uNivel;

                    } else {
                        JOptionPane.showMessageDialog(null, "Sesion ya iniciada.");
                    }
                }
            }
        });

        cerrar_sesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (fm.leerArchivo() == null || fm.leerArchivo().equals("")) {

                    JOptionPane.showMessageDialog(null, "Base de datos no creada.");

                } else {
                    if (uCod != 0) {

                        uCod = 0;
                        uNombre = null;
                        uContraseña = null;
                        uCorreo = null;
                        uNivel = 0;

                        JOptionPane.showMessageDialog(null, "Se ha cerrado sesion.");

                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha iniciado sesion.");
                    }
                }
            }
        });

        // Usuario
        crear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (fm.leerArchivo() == null || fm.leerArchivo().equals("")) {

                    JOptionPane.showMessageDialog(null, "Base de datos no creada.");

                } else if (uNivel > 1 || uNivel == 0) {
                    JOptionPane.showMessageDialog(null, "No posee permisos para esta accion.");
                } else {
                    new Crear_Usuario(null, true);
                    db.generarArchivoSQL();

                }

            }
        });

        modificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fm.leerArchivo() == null || fm.leerArchivo().equals("")) {

                    JOptionPane.showMessageDialog(null, "Base de datos no creada.");

                } else if (uNivel > 1 || uNivel == 0) {
                    JOptionPane.showMessageDialog(null, "No posee permisos para esta accion.");
                } else {
                    new Modificar_Usuario(null, true);
                    db.generarArchivoSQL();
                }
            }
        });

        visualizar_eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (fm.leerArchivo() == null || fm.leerArchivo().equals("")) {

                    JOptionPane.showMessageDialog(null, "Base de datos no creada.");

                } else if (uNivel > 1 || uNivel == 0) {
                    JOptionPane.showMessageDialog(null, "No posee permisos para esta accion.");
                } else {
                    new Vizualizar_Eliminar_Usuario(null, true);
                    db.generarArchivoSQL();

                }
            }
        });

        // Vehiculo
        ingresa_vehiculo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fm.leerArchivo() == null || fm.leerArchivo().equals("")) {

                    JOptionPane.showMessageDialog(null, "Base de datos no creada.");

                } else if (uNivel > 2 || uNivel == 0) {
                    JOptionPane.showMessageDialog(null, "No posee permisos para esta accion.");
                } else {
                    new Ingresar_Vehiculo(uCod, null, true);
                    db.generarArchivoSQL();
                }
            }
        });

        salida_vehiculo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fm.leerArchivo() == null || fm.leerArchivo().equals("")) {

                    JOptionPane.showMessageDialog(null, "Base de datos no creada.");

                } else if (uNivel > 2 || uNivel == 0) {
                    JOptionPane.showMessageDialog(null, "No posee permisos para esta accion.");
                } else {
                    new Salida_Vehiculo(vc, vm, vb, null, true);
                    db.generarArchivoSQL();
                }
            }
        });

        buscar_registro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fm.leerArchivo() == null || fm.leerArchivo().equals("")) {

                    JOptionPane.showMessageDialog(null, "Base de datos no creada.");

                } else if (uNivel > 1 || uNivel == 0) {
                    JOptionPane.showMessageDialog(null, "No posee permisos para esta accion.");
                } else {
                    new Buscar_Registro(null, true);
                }
            }
        });

        modificar_registro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fm.leerArchivo() == null || fm.leerArchivo().equals("")) {

                    JOptionPane.showMessageDialog(null, "Base de datos no creada.");

                } else if (uNivel > 1 || uNivel == 0) {
                    JOptionPane.showMessageDialog(null, "No posee permisos para esta accion.");
                } else {
                    new Modificar_Registro(null, true);
                    db.generarArchivoSQL();
                }
            }
        });

        buscar_factura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fm.leerArchivo() == null || fm.leerArchivo().equals("")) {

                    JOptionPane.showMessageDialog(null, "Base de datos no creada.");

                } else if (uNivel > 3 || uNivel == 0) {
                    JOptionPane.showMessageDialog(null, "No posee permisos para esta accion.");
                } else {
                    new Buscar_Factura(vc, vm, vb, null, true);
                }
            }
        });

        valor_hora.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fm.leerArchivo() == null || fm.leerArchivo().equals("")) {

                    JOptionPane.showMessageDialog(null, "Base de datos no creada.");

                } else if (uNivel > 1 || uNivel == 0) {
                    JOptionPane.showMessageDialog(null, "No posee permisos para esta accion.");
                } else {
                    Valor_Hora obj = new Valor_Hora(null, true);
                    vc = obj.vc;
                    vm = obj.vm;
                    vb = obj.vb;
                }
            }
        });

        setSize(350, 300);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Parqueadero();
    }

}

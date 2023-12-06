/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueadero;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class Vizualizar_Eliminar_Usuario extends JDialog {

    JLabel et1, et2;
    JTextField codigo;
    JButton buscar, eliminar, cerrar;

    JComboBox<String> combo;

    JTable tabla;
    DefaultTableModel modelo;

    public Vizualizar_Eliminar_Usuario(Frame e, boolean modal) {

        super(e, modal);

        Container cont = getContentPane();
        cont.setLayout(new FlowLayout());

        ConsultasUsuario consultasUsuarios = new ConsultasUsuario();

        et1 = new JLabel("Usuarios: ");
        et2 = new JLabel("Codigo: ");

        codigo = new JTextField(5);

        String usuarios[] = {"Codigo", "Todos", "Nivel 1", "Nivel 2", "Nivel 3"};
        combo = new JComboBox<>(usuarios);

        buscar = new JButton("Buscar");
        eliminar = new JButton("Eliminar Seleccionados");
        cerrar = new JButton("Cerrar");

        cont.add(et1);
        cont.add(combo);
        cont.add(et2);
        cont.add(codigo);
        cont.add(buscar);

        modelo = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 5) {
                    return Boolean.class;
                }
                return super.getColumnClass(columnIndex);
            }
        };

        
        modelo.addColumn("Codigo");
        modelo.addColumn("Nivel");
        modelo.addColumn("Nombre");
        modelo.addColumn("Correo");
        modelo.addColumn("Contraseña");
        modelo.addColumn("Eliminar");

        tabla = new JTable(modelo);
        tabla.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(new JCheckBox()));
        tabla.setDefaultEditor(Object.class, null);
        JScrollPane scroll = new JScrollPane(tabla);
        cont.add(scroll);

        cont.add(eliminar);
        cont.add(cerrar);

        buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (combo.getSelectedItem().equals("Codigo")) {
                    modelo.setRowCount(0);

                    if (codigo.getText().matches("\\d+")) {
                        int cod = Integer.parseInt(codigo.getText());
                        Usuario usuario = consultasUsuarios.findXCod(cod);
                        if (usuario != null) {
                            modelo.addRow(new Object[]{
                                usuario.getCodigo(),
                                usuario.getNivel(),
                                usuario.getNombre(),
                                usuario.getCorreo(),
                                usuario.getContraseña(),
                                false
                            });
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "El codigo no es valido.");
                    }
                }
            }
        });

        combo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!combo.getSelectedItem().equals("Codigo")) {
                    codigo.setEditable(false);
                    modelo.setRowCount(0);

                    if (combo.getSelectedItem().equals("Todos")) {
                        List<Usuario> usuarios = consultasUsuarios.findAll();
                        for (Usuario usuario : usuarios) {
                            modelo.addRow(new Object[]{
                                usuario.getCodigo(),
                                usuario.getNivel(),
                                usuario.getNombre(),
                                usuario.getCorreo(),
                                usuario.getContraseña(),
                                false
                            });
                        }
                    }

                    if (combo.getSelectedItem().equals("Nivel 1")) {
                        List<Usuario> usuarios = consultasUsuarios.findAllXNvl(1);
                        for (Usuario usuario : usuarios) {
                            modelo.addRow(new Object[]{
                                usuario.getCodigo(),
                                usuario.getNivel(),
                                usuario.getNombre(),
                                usuario.getCorreo(),
                                usuario.getContraseña(),
                                false
                            });
                        }
                    }

                    if (combo.getSelectedItem().equals("Nivel 2")) {
                        List<Usuario> usuarios = consultasUsuarios.findAllXNvl(2);
                        for (Usuario usuario : usuarios) {
                            modelo.addRow(new Object[]{
                                usuario.getCodigo(),
                                usuario.getNivel(),
                                usuario.getNombre(),
                                usuario.getCorreo(),
                                usuario.getContraseña(),
                                false
                            });
                        }
                    }

                    if (combo.getSelectedItem().equals("Nivel 3")) {
                        List<Usuario> usuarios = consultasUsuarios.findAllXNvl(3);
                        for (Usuario usuario : usuarios) {
                            modelo.addRow(new Object[]{
                                usuario.getCodigo(),
                                usuario.getNivel(),
                                usuario.getNombre(),
                                usuario.getCorreo(),
                                usuario.getContraseña(),
                                false
                            });
                        }
                    }

                } else {

                    codigo.setEditable(true);
                    modelo.setRowCount(0);

                }

            }
        });

        eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Integer> filasEliminar = new ArrayList<>();

                for (int i = 0; i < tabla.getRowCount(); i++) {
                    boolean seleccionado = (boolean) tabla.getValueAt(i, 5);
                    if (seleccionado) {
                        int codigoEliminar = (int) tabla.getValueAt(i, 0);
                        consultasUsuarios.delete(codigoEliminar);
                        filasEliminar.add(i);
                    }
                }

                for (int i = filasEliminar.size() - 1; i >= 0; i--) {
                    int fila = filasEliminar.get(i);
                    modelo.removeRow(fila);
                }
            }
        });

        cerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setSize(600, 590);
        setLocationRelativeTo(null);
        setVisible(true);

    }
    

}

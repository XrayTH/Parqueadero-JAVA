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
public class Salida_Vehiculo extends JDialog {

    JLabel et1;
    JTextField a;
    JButton validar, cancelar;

    public Salida_Vehiculo(double vc, double vm, double vb, Frame e, boolean modal) {

        super(e, modal);

        Container cont = getContentPane();
        cont.setLayout(new FlowLayout());

        ConsultasFactura fact = new ConsultasFactura();

        et1 = new JLabel("V-P-PLACA-HS-MS: ");

        a = new JTextField(10);

        cancelar = new JButton("Cancelar");
        validar = new JButton("Validar");

        cont.add(et1);
        cont.add(a);
        cont.add(validar);
        cont.add(cancelar);

        validar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String com = a.getText();
                String[] datos;

                if (com.matches("[A-Za-z]-\\d{2}-[A-Za-z\\d]{1,6}-\\d{2}-\\d{2}")) {
                    datos = com.split("-");

                    String v = datos[0];
                    String p = datos[1];
                    String placa = datos[2];
                    String hs = datos[3];
                    String ms = datos[4];

                    boolean error = false;
                    String texto = "";

                    if (fact.verificarEstanciaEspecifica(placa, Integer.parseInt(p)) == null) {
                        error = true;
                        texto = texto + "Vehiculo no ha ingresado.";
                    }

                    if (!v.matches("[cmb]+")) {//verifica que el tipo sea valido
                        error = true;
                        texto = texto + "El vehiculo debe ser carro, moto o bicicleta.";
                    }

                    if (!p.matches("(0[0-9]|1[0-9]|2[0-5])")) {//verifica que sea un puesto valido
                        error = true;
                        texto = texto + "Puesto invalido o ocupado.";
                    }

                    switch (v) {//verifica que la placa sea valida
                        case "c":
                            if (!placa.matches("^[A-Za-z]{3}\\d{3}$")) {
                                error = true;
                                texto = texto + "Placa invalida.";
                            }
                            break;
                        case "m":
                            if (!placa.matches("^[a-zA-Z]{3}\\d{2}[a-zA-Z]$")) {
                                error = true;
                                texto = texto + "Placa invalida.";
                            }
                            break;
                        case "b":
                            if (!placa.equals("0000")) {
                                error = true;
                                texto = texto + "Placa invalida.";
                            }
                            break;
                        default:
                            break;
                    }

                    if (!hs.matches("(0[0-9]|1[0-9]|2[0-3])")) {

                        error = true;
                        texto = texto + "Hora invalida.";

                    }

                    if (!ms.matches("(0[0-9]|[1-5][0-9])")) {
                        texto = texto + "Minuto invalido.";
                        error = true;

                    }
                   
                    switch (v) {
                        case "c":
                            if (Integer.parseInt(p) > 10) {
                                error = true;
                                texto = texto + "Puesto incorrecto para carros.";
                            }
                            break;
                        case "m":
                            if (Integer.parseInt(p) <= 10) {
                                error = true;
                                texto = texto + "Puesto incorrecto para motos.";
                            } else if (Integer.parseInt(p) > 20) {
                                error = true;
                                texto = texto + "Puesto incorrecto para motos.";
                            }
                            break;
                        case "b":
                            if (Integer.parseInt(p) <= 20) {
                                error = true;
                                texto = texto + "Puesto incorrecto para bicicletas.";
                            }
                            break;
                    }

                    if (!error) {

                        String[] datosEntr;
                        datos = fact.verificarEstancia(placa, Integer.parseInt(p)).getHoraE().split(":");
                        String e1 = datos[0];
                        String e2 = datos[1];
                        String e3 = datos[2];

                        if (!(Integer.parseInt(e1) < Integer.parseInt(hs)
                                || (Integer.parseInt(e1) == Integer.parseInt(hs)
                                && Integer.parseInt(e2) < Integer.parseInt(ms)))) {

                            error = true;
                            texto = texto + "Hora de entrada y salida no son congruentes.";

                        }
                    }

                    if (!error) {

                        new Validar_Salida(null, true, v, p, placa, hs, ms, vc, vm, vb);
                        dispose();
                    } else {

                        JOptionPane.showMessageDialog(null, texto);

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "El formato no es válido.");
                }

            }
        });

        cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setSize(200, 170);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}

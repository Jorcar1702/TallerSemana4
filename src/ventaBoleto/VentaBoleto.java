package ventaBoleto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentaBoleto {
    private JPanel Ventana;
    private JTextField textCedula;
    private JTextField textNombre;
    private JComboBox comboRuta;
    private JTextField textNumeroBoleto;
    private JButton comprarButton;
    private JButton mostrarSiguienteButton;
    private JButton embarcarButton;
    private JTextArea textArea1;
    private JLabel vendidosGuayaquil;
    private JLabel disponiblesGuayaquil;
    private JLabel precioGuayaquil;
    private JLabel precioCuenca;
    private JLabel vendidosCuenca;
    private JLabel disponiblesCuenca;
    private JLabel precioLoja;
    private JLabel vendidosLoja;
    private JLabel disponiblesLoja;
    private JLabel Recaudacion;

    private ColaBus colaGuayaquil = new ColaBus("Quito-Guayaquil", 10.50);
    private ColaBus colaCuenca = new ColaBus("Quito-Cuenca", 12.75);
    private ColaBus colaLoja = new ColaBus("Quito-Loja", 15.00);

    public VentaBoleto(){

        actualizarLabels();
        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            try {
            String nombreRuta = comboRuta.getSelectedItem().toString();
            String cedula = textCedula.getText();
            String nombre = textNombre.getText();
            int numeroBoleto = Integer.parseInt(textNumeroBoleto.getText());
            Pasajero pasajero= new Pasajero(nombreRuta, cedula,nombre,numeroBoleto);
                ColaBus colaSeleccionada;

                if (nombreRuta.equals("Quito-Guayaquil")) {
                    colaSeleccionada = colaGuayaquil;
                } else if (nombreRuta.equals("Quito-Cuenca")) {
                    colaSeleccionada = colaCuenca;
                } else if (nombreRuta.equals("Quito-Loja")) {
                    colaSeleccionada = colaLoja;
                } else {
                    throw new Exception("Seleccione una ruta válida");
                }
                colaSeleccionada.venderPasaje(pasajero);
                double total = numeroBoleto * colaSeleccionada.getPrecio();
                textArea1.append("Ruta: " + nombreRuta + "\n"
                        + "Cédula: " + cedula + "\n"
                        + "Pasajero: " + nombre + "\n"
                        + "Boletos: " + numeroBoleto + "\n"
                        +("Total: "+ total)+ "\n"
                        + "----------------------------\n");
                actualizarLabels();
                textCedula.setText("");
                textNombre.setText("");
                textNumeroBoleto.setText("");

                JOptionPane.showMessageDialog(Ventana, "Compra realizada correctamente");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(Ventana, "Ingrese un número válido de boletos");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Ventana, ex.getMessage());
            }
            }


        });

        embarcarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String nombreRuta = comboRuta.getSelectedItem().toString();

                    ColaBus colaSeleccionada;

                    if (nombreRuta.equals("Quito-Guayaquil")) {
                        colaSeleccionada = colaGuayaquil;
                    } else if (nombreRuta.equals("Quito-Cuenca")) {
                        colaSeleccionada = colaCuenca;
                    } else if (nombreRuta.equals("Quito-Loja")) {
                        colaSeleccionada = colaLoja;
                    } else {
                        throw new Exception("Seleccione una ruta válida");
                    }

                    Pasajero pasajero = colaSeleccionada.embarcar();

                    textArea1.append("EMBARQUE\n" + "Ruta: " + nombreRuta + "Sube: " + pasajero.getNombrePasajero()
                                    + "Acompañantes: " + (pasajero.getNumeroBoleto() - 1));

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Ventana, ex.getMessage());
                }
            }
        });

        mostrarSiguienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String nombreRuta = comboRuta.getSelectedItem().toString();

                    ColaBus colaSeleccionada;

                    if (nombreRuta.equals("Quito-Guayaquil")) {
                        colaSeleccionada = colaGuayaquil;
                    } else if (nombreRuta.equals("Quito-Cuenca")) {
                        colaSeleccionada = colaCuenca;
                    } else if (nombreRuta.equals("Quito-Loja")) {
                        colaSeleccionada = colaLoja;
                    } else {
                        throw new Exception("Seleccione una ruta válida");
                    }


                    Pasajero pasajero = colaSeleccionada.mostrarSiguiente();

                    JOptionPane.showMessageDialog(Ventana,
                            "Siguiente en " + nombreRuta + ":\n"
                                    + "Pasajero: " + pasajero.getNombrePasajero() + "\n"
                                    + "Boletos: " + pasajero.getNumeroBoleto() + "\n"
                                    + "Acompañantes: " + (pasajero.getNumeroBoleto() - 1));

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Ventana, ex.getMessage());
                }
            }
        });

        }
    private void actualizarLabels() {
        precioGuayaquil.setText("$" + colaGuayaquil.getPrecio());
        vendidosGuayaquil.setText("Vendidos: " + colaGuayaquil.boletosVendidos());
        disponiblesGuayaquil.setText("Disponibles: " + colaGuayaquil.boletosDisponibles());

        precioCuenca.setText("$" + colaCuenca.getPrecio());
        vendidosCuenca.setText("Vendidos: " + colaCuenca.boletosVendidos());
        disponiblesCuenca.setText("Disponibles: " + colaCuenca.boletosDisponibles());

        precioLoja.setText("$" + colaLoja.getPrecio());
        vendidosLoja.setText("Vendidos: " + colaLoja.boletosVendidos());
        disponiblesLoja.setText("Disponibles: " + colaLoja.boletosDisponibles());
        double recaudado = colaGuayaquil.totalRecaudado()+colaCuenca.totalRecaudado()+colaLoja.totalRecaudado();
        Recaudacion.setText("Recaudado Total: "+recaudado);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VentaBoleto");
        frame.setContentPane(new VentaBoleto().Ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

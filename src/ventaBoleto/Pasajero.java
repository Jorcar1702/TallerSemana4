package ventaBoleto;

public class Pasajero {
    private String nombreRuta;
    private String cedula;
    private String nombrePasajero;
    private int numeroBoleto;

    public Pasajero(String nombreRuta, String cedula, String nombrePasajero, int numeroBoleto) {
        this.nombreRuta = nombreRuta;
        this.cedula = cedula;
        this.nombrePasajero = nombrePasajero;
        this.numeroBoleto = numeroBoleto;
    }

    public String getNombreRuta() {
        return nombreRuta;
    }

    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombrePasajero() {
        return nombrePasajero;
    }

    public void setNombrePasajero(String nombrePasajero) {
        this.nombrePasajero = nombrePasajero;
    }

    public int getNumeroBoleto() {
        return numeroBoleto;
    }

    public void setNumeroBoleto(int numeroBoleto) {
        this.numeroBoleto = numeroBoleto;
    }

    @Override
    public String toString() {
        return "Pasajero{" + "nombreRuta='" + nombreRuta  + " | cedula='" + cedula  + " | nombrePasajero='" + nombrePasajero  +
                " | numeroBoleto=" + numeroBoleto + '}';
    }
}

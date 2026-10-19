package ventaBoleto;

import java.util.LinkedList;

public class ColaBus {

    private final int CapacidadBus= 20;
    private final int MaximoBoleto=5;

    LinkedList<Pasajero> pasajeros;
    LinkedList<Pasajero> historialVentas;
    private String nombreRuta;
    private double precio;
    public ColaBus(String nombreRuta, double precio){
        this.nombreRuta= nombreRuta;
        this.precio= precio;
        pasajeros= new LinkedList<Pasajero>();
        historialVentas = new LinkedList<Pasajero>();
    }

    public double getPrecio() {
        return precio;
    }

    public Boolean estaVacia(){
        return pasajeros.isEmpty();
    }

    public void venderPasaje(Pasajero pasajero)throws Exception{
        if (pasajero.getNumeroBoleto() <= 0) {
            throw new Exception("Debe comprar al menos un boleto");
        }

        if (pasajero.getNumeroBoleto() > MaximoBoleto) {
            throw new Exception("No puede comprar mas de 5 boletos");
        }

        if (pasajero.getNumeroBoleto() > boletosDisponibles()) {
            throw new Exception("No hay suficientes boletos disponibles");
        }
        int comprados = boletosPorCedula(pasajero.getCedula());

        if (comprados + pasajero.getNumeroBoleto() > MaximoBoleto) {
            throw new Exception("Esta cedula ya alcanzo el limite de 5 boletos");
        }
        pasajeros.add(pasajero);
        historialVentas.add(pasajero);
    }

    public Pasajero embarcar() throws Exception{
        if(!estaVacia()){
            return pasajeros.poll();
        }
       throw new Exception("La cola esta vacia");
    }
    public Pasajero mostrarSiguiente() throws Exception {
        if (!estaVacia()) {
            return pasajeros.peek();
        }

        throw new Exception("La cola esta vacia");
    }
    public int boletosVendidos() {
        int total = 0;
        for (Pasajero p : historialVentas) {
            total = total+ p.getNumeroBoleto();
        }

        return total;
    }
    public int boletosDisponibles() {
        return CapacidadBus - boletosVendidos();
    }
    public int boletosPorCedula(String cedula) {
        int total = 0;

        for (Pasajero p : historialVentas) {
            if (p.getCedula().equals(cedula)) {
                total += p.getNumeroBoleto();
            }
        }

        return total;
    }
    public double totalRecaudado() {
        return boletosVendidos() * precio;
    }

}

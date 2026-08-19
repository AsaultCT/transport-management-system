import java.time.LocalTime;
import java.util.ArrayList;

public class Viaje {

    private Bus busAsignado;
    private LocalTime horarioViaje;
    private int asientosDisponibles;
    private ArrayList<Tiquete> tiquetesViaje;
    private int idViaje;

    public Viaje(Bus busAsignado, LocalTime horarioViaje, int idViaje) {
        this.busAsignado = busAsignado;
        this.horarioViaje = horarioViaje;
        this.idViaje = idViaje;
        this.asientosDisponibles = busAsignado.getCapacidadBus();
        this.tiquetesViaje = new ArrayList<>();
    }

    public void venderTiquetes(int tipoTiquete, int cantidad, int kmRecorridos, int[] contadorTiquetes) {
        if (cantidad > asientosDisponibles) {
            System.out.println("No hay suficientes asientos disponibles. Disponibles: " + asientosDisponibles);
            return;
        }

        for (int i = 0; i < cantidad; i++) {
            contadorTiquetes[0]++;
            Tiquete tiquete = new Tiquete(contadorTiquetes[0], tipoTiquete, this, kmRecorridos);
            tiquetesViaje.add(tiquete);
        }
        asientosDisponibles -= cantidad;
        System.out.println("Se vendieron " + cantidad + " tiquete(s) correctamente.");
    }

    public void devolucionTiquete(int idTiquete) {
        for (int i = 0; i < tiquetesViaje.size(); i++) {
            if (tiquetesViaje.get(i).getIdTiquete() == idTiquete) {
                tiquetesViaje.remove(i);
                asientosDisponibles++;
                System.out.println("Tiquete #" + idTiquete + " cancelado correctamente.");
                return;
            }
        }
        System.out.println("No se encontro el tiquete #" + idTiquete + " en este viaje.");
    }

    public double arqueoViaje() {
        double recaudoCompleto = 0;
        double recaudoParcial = 0;
        double recaudoDescuento = 0;

        for (int i = 0; i < tiquetesViaje.size(); i++) {
            Tiquete t = tiquetesViaje.get(i);
            switch (t.getTipoTiquete()) {
                case 1:
                    recaudoCompleto += t.getValorTiquete();
                    break;
                case 2:
                    recaudoParcial += t.getValorTiquete();
                    break;
                case 3:
                    recaudoDescuento += t.getValorTiquete();
                    break;
            }
        }

        double gastoCombustible = busAsignado.getKmRuta() * 200;
        double gananciaViaje = (recaudoCompleto + recaudoParcial + recaudoDescuento) - gastoCombustible;

        System.out.println("\n--- ARQUEO VIAJE #" + idViaje + " ---");
        System.out.println("Bus: " + busAsignado.getIdBus() + " | Ruta: " + busAsignado.getRuta());
        System.out.println("Horario: " + horarioViaje);
        System.out.println("Recaudo completo:  $" + String.format("%.0f", recaudoCompleto));
        System.out.println("Recaudo parcial:   $" + String.format("%.0f", recaudoParcial));
        System.out.println("Recaudo descuento: $" + String.format("%.0f", recaudoDescuento));
        System.out.println("Gasto combustible: -$" + String.format("%.0f", gastoCombustible));
        System.out.println("GANANCIA VIAJE:     $" + String.format("%.0f", gananciaViaje));

        return gananciaViaje;
    }

    public Bus getBusAsignado() {
        return busAsignado;
    }

    public LocalTime getHorarioViaje() {
        return horarioViaje;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public ArrayList<Tiquete> getTiquetesViaje() {
        return tiquetesViaje;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public void mostrarInfo() {
        System.out.println("Viaje #" + idViaje +
                           " | Bus: " + busAsignado.getIdBus() +
                           " | Ruta: " + busAsignado.getRuta() +
                           " | Horario: " + horarioViaje +
                           " | Asientos disp: " + asientosDisponibles +
                           " | Tiquetes vendidos: " + tiquetesViaje.size());
    }

    public void mostrarTiquetes() {
        if (tiquetesViaje.isEmpty()) {
            System.out.println("  No hay tiquetes vendidos en este viaje.");
            return;
        }
        for (int i = 0; i < tiquetesViaje.size(); i++) {
            System.out.println("  " + tiquetesViaje.get(i));
        }
    }
}

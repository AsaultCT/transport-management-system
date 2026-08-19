import java.time.LocalTime;

public class Bus {

    private char idBus;
    private String ruta;
    private LocalTime[] horaSalida;
    private double kmRuta;
    private int capacidadBus;

    public Bus(char idBus, String ruta, LocalTime[] horaSalida, double kmRuta, int capacidadBus) {
        this.idBus = idBus;
        this.ruta = ruta;
        this.horaSalida = horaSalida;
        this.kmRuta = kmRuta;
        this.capacidadBus = capacidadBus;
    }

    public Bus(char idBus, String ruta, LocalTime[] horaSalida) {
        this(idBus, ruta, horaSalida, 150, 20);
    }

    public char getIdBus() {
        return idBus;
    }

    public String getRuta() {
        return ruta;
    }

    public LocalTime[] getHoraSalida() {
        return horaSalida;
    }

    public LocalTime getHorario(int index) {
        return horaSalida[index];
    }

    public double getKmRuta() {
        return kmRuta;
    }

    public int getCapacidadBus() {
        return capacidadBus;
    }

    public void mostrarInfo() {
        System.out.println("Bus " + idBus + " | Ruta: " + ruta + " | Capacidad: " + capacidadBus + " asientos | Km: " + kmRuta);
        System.out.print("  Horarios: ");
        for (int i = 0; i < horaSalida.length; i++) {
            System.out.print(horaSalida[i]);
            if (i < horaSalida.length - 1) System.out.print(", ");
        }
        System.out.println();
    }

    public void mostrarHorarios() {
        System.out.print("Bus " + idBus + " - Horarios: ");
        for (int i = 0; i < horaSalida.length; i++) {
            System.out.print("[" + (i + 1) + "] " + horaSalida[i]);
            if (i < horaSalida.length - 1) System.out.print("  ");
        }
        System.out.println();
    }
}

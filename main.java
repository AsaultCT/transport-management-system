import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

    static Bus[] arrBuses = new Bus[10];
    static ArrayList<Viaje> viajes = new ArrayList<>();
    static int contadorViajes = 0;
    static int[] contadorTiquetes = {0};
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        precargarBuses();
        crearViajesIniciales();

        int opcion = 0;
        while (opcion != 3) {
            System.out.println("\n=== SISTEMA DE GESTION DE RUTAS ===");
            System.out.println("1. Programador de Rutas");
            System.out.println("2. Auxiliar de Taquilla");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    menuProgramador();
                    break;
                case 2:
                    menuAuxiliar();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
        scanner.close();
    }

    // ==================== PRECARGA DE DATOS ====================

    static void precargarBuses() {
        arrBuses[0] = new Bus('A', "Medellin--Bogota", new LocalTime[]{LocalTime.of(6, 0), LocalTime.of(10, 0), LocalTime.of(16, 0)}, 150, 80);
        arrBuses[1] = new Bus('B', "Medellin--Cali", new LocalTime[]{LocalTime.of(6, 30), LocalTime.of(11, 0), LocalTime.of(17, 0)}, 200, 76);
        arrBuses[2] = new Bus('C', "Bogota--Barranquilla", new LocalTime[]{LocalTime.of(7, 0), LocalTime.of(12, 0), LocalTime.of(18, 0)}, 300, 75);
        arrBuses[3] = new Bus('D', "Cali--Pasto", new LocalTime[]{LocalTime.of(5, 30), LocalTime.of(9, 0), LocalTime.of(14, 0)}, 180, 60);
        arrBuses[4] = new Bus('E', "Bogota--Bucaramanga", new LocalTime[]{LocalTime.of(8, 0), LocalTime.of(13, 0), LocalTime.of(19, 0)}, 250, 50);
        arrBuses[5] = new Bus('F', "Medellin--Santa Marta", new LocalTime[]{LocalTime.of(6, 0), LocalTime.of(10, 30), LocalTime.of(15, 0)}, 350, 45);
        arrBuses[6] = new Bus('G', "Cali--Popayan", new LocalTime[]{LocalTime.of(7, 30), LocalTime.of(11, 30), LocalTime.of(16, 30)}, 130, 40);
        arrBuses[7] = new Bus('H', "Bogota--Manizales", new LocalTime[]{LocalTime.of(5, 0), LocalTime.of(9, 30), LocalTime.of(14, 30)}, 220, 35);
        arrBuses[8] = new Bus('I', "Medellin--Quibdo", new LocalTime[]{LocalTime.of(6, 0), LocalTime.of(10, 0), LocalTime.of(15, 30)}, 160, 30);
        arrBuses[9] = new Bus('J', "Bogota--Ibague", new LocalTime[]{LocalTime.of(7, 0), LocalTime.of(11, 0), LocalTime.of(17, 30)}, 130, 20);

        ordenarBuses();
        System.out.println("10 buses cargados correctamente.");
    }

    static void ordenarBuses() {
        for (int i = 0; i < arrBuses.length - 1; i++) {
            for (int j = 0; j < arrBuses.length - 1 - i; j++) {
                boolean intercambiar = false;
                if (arrBuses[j].getCapacidadBus() < arrBuses[j + 1].getCapacidadBus()) {
                    intercambiar = true;
                } else if (arrBuses[j].getCapacidadBus() == arrBuses[j + 1].getCapacidadBus()) {
                    if (arrBuses[j].getIdBus() > arrBuses[j + 1].getIdBus()) {
                        intercambiar = true;
                    }
                }
                if (intercambiar) {
                    Bus temp = arrBuses[j];
                    arrBuses[j] = arrBuses[j + 1];
                    arrBuses[j + 1] = temp;
                }
            }
        }
    }

    static void crearViajesIniciales() {
        for (int i = 0; i < arrBuses.length; i++) {
            for (int h = 0; h < 3; h++) {
                contadorViajes++;
                Viaje viaje = new Viaje(arrBuses[i], arrBuses[i].getHorario(h), contadorViajes);
                viajes.add(viaje);
            }
        }
        System.out.println("30 viajes creados (3 por bus).");
    }

    // ==================== MENU PROGRAMADOR DE RUTAS ====================

    static void menuProgramador() {
        int opcion = 0;
        while (opcion != 5) {
            System.out.println("\n=== PROGRAMADOR DE RUTAS ===");
            System.out.println("1. Ver listado de buses (ordenados por capacidad)");
            System.out.println("2. Ver horarios de un bus especifico");
            System.out.println("3. Ver viajes programados");
            System.out.println("4. Ver arqueo de un viaje");
            System.out.println("5. Volver");
            System.out.print("Seleccione una opcion: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    verBuses();
                    break;
                case 2:
                    verHorariosBus();
                    break;
                case 3:
                    verViajes();
                    break;
                case 4:
                    verArqueo();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }

    static void verBuses() {
        System.out.println("\n--- BUSES ORDENADOS POR CAPACIDAD ---");
        for (int i = 0; i < arrBuses.length; i++) {
            System.out.print((i + 1) + ". ");
            arrBuses[i].mostrarInfo();
        }
    }

    static void verHorariosBus() {
        verBuses();
        System.out.print("Ingrese la letra del bus: ");
        char id = scanner.nextLine().toUpperCase().charAt(0);
        Bus bus = buscarBus(id);
        if (bus != null) {
            System.out.println("\n--- HORARIOS BUS " + bus.getIdBus() + " ---");
            bus.mostrarInfo();
        } else {
            System.out.println("Bus no encontrado.");
        }
    }

    static void verViajes() {
        System.out.println("\n--- VIAJES PROGRAMADOS ---");
        for (int i = 0; i < viajes.size(); i++) {
            viajes.get(i).mostrarInfo();
        }
    }

    static void verArqueo() {
        verViajes();
        System.out.print("Ingrese el ID del viaje: ");
        int id = Integer.parseInt(scanner.nextLine());
        Viaje viaje = buscarViaje(id);
        if (viaje != null) {
            viaje.arqueoViaje();
        } else {
            System.out.println("Viaje no encontrado.");
        }
    }

    // ==================== MENU AUXILIAR DE TAQUILLA ====================

    static void menuAuxiliar() {
        int opcion = 0;
        while (opcion != 4) {
            System.out.println("\n=== AUXILIAR DE TAQUILLA ===");
            System.out.println("1. Vender tiquetes");
            System.out.println("2. Cancelar/devolver tiquete");
            System.out.println("3. Ver viajes disponibles con asientos");
            System.out.println("4. Volver");
            System.out.print("Seleccione una opcion: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    venderTiquetes();
                    break;
                case 2:
                    cancelarTiquete();
                    break;
                case 3:
                    verViajesDisponibles();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }

    static void venderTiquetes() {
        System.out.println("\n--- VENTA DE TIQUETES ---");

        // Paso 1: seleccionar bus
        System.out.println("Buses disponibles:");
        for (int i = 0; i < arrBuses.length; i++) {
            System.out.println("  " + arrBuses[i].getIdBus() + " - " + arrBuses[i].getRuta() + " (" + arrBuses[i].getCapacidadBus() + " asientos)");
        }
        System.out.print("Seleccione el bus (letra): ");
        char idBus = scanner.nextLine().toUpperCase().charAt(0);
        Bus bus = buscarBus(idBus);
        if (bus == null) {
            System.out.println("Bus no encontrado.");
            return;
        }

        // Paso 2: seleccionar horario del bus
        System.out.println("\nHorarios del bus " + bus.getIdBus() + ":");
        ArrayList<Viaje> viajesBus = new ArrayList<>();
        for (int i = 0; i < viajes.size(); i++) {
            if (viajes.get(i).getBusAsignado().getIdBus() == bus.getIdBus()) {
                viajesBus.add(viajes.get(i));
            }
        }
        for (int i = 0; i < viajesBus.size(); i++) {
            System.out.println("  [" + (i + 1) + "] " + viajesBus.get(i).getHorarioViaje() +
                               " | Asientos disp: " + viajesBus.get(i).getAsientosDisponibles());
        }
        System.out.print("Seleccione el horario (1-3): ");
        int horario = Integer.parseInt(scanner.nextLine());
        if (horario < 1 || horario > viajesBus.size()) {
            System.out.println("Horario no valido.");
            return;
        }
        Viaje viajeSeleccionado = viajesBus.get(horario - 1);

        if (viajeSeleccionado.getAsientosDisponibles() == 0) {
            System.out.println("No hay asientos disponibles en este viaje.");
            return;
        }

        // Paso 3: tipo de tiquete
        System.out.println("\nTipos de tiquete:");
        System.out.println("  [1] Completo - $" + String.format("%.0f", bus.getKmRuta() * 1000));
        System.out.println("  [2] Parcial  - Ingrese sus km");
        System.out.println("  [3] Descuento - $" + String.format("%.0f", (bus.getKmRuta() * 1000) * 0.5));
        System.out.print("Seleccione tipo (1-3): ");
        int tipo = Integer.parseInt(scanner.nextLine());
        if (tipo < 1 || tipo > 3) {
            System.out.println("Tipo no valido.");
            return;
        }

        int kmRecorridos = 0;
        if (tipo == 2) {
            System.out.print("Ingrese los km a recorrer (max " + (int) bus.getKmRuta() + "): ");
            kmRecorridos = Integer.parseInt(scanner.nextLine());
            if (kmRecorridos <= 0 || kmRecorridos > (int) bus.getKmRuta()) {
                System.out.println("Km no validos.");
                return;
            }
        } else {
            kmRecorridos = (int) bus.getKmRuta();
        }

        // Paso 4: cantidad
        System.out.print("Ingrese la cantidad de tiquetes: ");
        int cantidad = Integer.parseInt(scanner.nextLine());
        if (cantidad <= 0) {
            System.out.println("Cantidad no valida.");
            return;
        }

        // Paso 5: confirmar
        double precioUnitario = 0;
        if (tipo == 1) precioUnitario = bus.getKmRuta() * 1000;
        else if (tipo == 2) precioUnitario = kmRecorridos * 1000;
        else if (tipo == 3) precioUnitario = (bus.getKmRuta() * 1000) * 0.5;

        System.out.println("\nResumen de compra:");
        System.out.println("  Bus: " + bus.getIdBus() + " | Ruta: " + bus.getRuta());
        System.out.println("  Horario: " + viajeSeleccionado.getHorarioViaje());
        System.out.println("  Tipo: " + (tipo == 1 ? "Completo" : tipo == 2 ? "Parcial" : "Descuento"));
        System.out.println("  Km: " + kmRecorridos);
        System.out.println("  Cantidad: " + cantidad);
        System.out.println("  Precio unitario: $" + String.format("%.0f", precioUnitario));
        System.out.println("  TOTAL: $" + String.format("%.0f", precioUnitario * cantidad));
        System.out.print("Confirmar compra? (s/n): ");
        String confirmar = scanner.nextLine();

        if (confirmar.equalsIgnoreCase("s")) {
            viajeSeleccionado.venderTiquetes(tipo, cantidad, kmRecorridos, contadorTiquetes);
        } else {
            System.out.println("Compra cancelada.");
        }
    }

    static void cancelarTiquete() {
        System.out.println("\n--- CANCELACION DE TIQUETES ---");

        // Viajes con tiquetes vendidos
        ArrayList<Viaje> viajesConTiquetes = new ArrayList<>();
        for (int i = 0; i < viajes.size(); i++) {
            if (!viajes.get(i).getTiquetesViaje().isEmpty()) {
                viajesConTiquetes.add(viajes.get(i));
            }
        }

        if (viajesConTiquetes.isEmpty()) {
            System.out.println("No hay tiquetes vendidos en ningun viaje.");
            return;
        }

        System.out.println("Viajes con tiquetes vendidos:");
        for (int i = 0; i < viajesConTiquetes.size(); i++) {
            System.out.print("  [" + (i + 1) + "] ");
            viajesConTiquetes.get(i).mostrarInfo();
        }
        System.out.print("Seleccione el viaje: ");
        int idxViaje = Integer.parseInt(scanner.nextLine());
        if (idxViaje < 1 || idxViaje > viajesConTiquetes.size()) {
            System.out.println("Opcion no valida.");
            return;
        }
        Viaje viajeSeleccionado = viajesConTiquetes.get(idxViaje - 1);

        // Tiquetes del viaje
        System.out.println("\nTiquetes del viaje #" + viajeSeleccionado.getIdViaje() + ":");
        viajeSeleccionado.mostrarTiquetes();
        System.out.print("Ingrese el ID del tiquete a cancelar: ");
        int idTiquete = Integer.parseInt(scanner.nextLine());

        System.out.print("Confirmar cancelacion? (s/n): ");
        String confirmar = scanner.nextLine();
        if (confirmar.equalsIgnoreCase("s")) {
            viajeSeleccionado.devolucionTiquete(idTiquete);
        } else {
            System.out.println("Cancelacion abortada.");
        }
    }

    static void verViajesDisponibles() {
        System.out.println("\n--- VIAJES CON ASIENTOS DISPONIBLES ---");
        boolean hayDisponibles = false;
        for (int i = 0; i < viajes.size(); i++) {
            if (viajes.get(i).getAsientosDisponibles() > 0) {
                viajes.get(i).mostrarInfo();
                hayDisponibles = true;
            }
        }
        if (!hayDisponibles) {
            System.out.println("No hay viajes con asientos disponibles.");
        }
    }

    // ==================== BUSQUEDAS ====================

    static Bus buscarBus(char id) {
        for (int i = 0; i < arrBuses.length; i++) {
            if (arrBuses[i].getIdBus() == id) {
                return arrBuses[i];
            }
        }
        return null;
    }

    static Viaje buscarViaje(int id) {
        for (int i = 0; i < viajes.size(); i++) {
            if (viajes.get(i).getIdViaje() == id) {
                return viajes.get(i);
            }
        }
        return null;
    }
}

public class Tiquete {

    private int idTiquete;
    private int tipoTiquete;
    private double valorTiquete;
    private Viaje viajeAsignado;
    private int kmRecorridos;

    public Tiquete(int idTiquete, int tipoTiquete, Viaje viajeAsignado, int kmRecorridos) {
        this.idTiquete = idTiquete;
        this.tipoTiquete = tipoTiquete;
        this.viajeAsignado = viajeAsignado;
        this.kmRecorridos = kmRecorridos;
        this.valorTiquete = calcularValor(viajeAsignado.getBusAsignado().getKmRuta());
    }

    public double calcularValor(double kmRuta) {
        switch (tipoTiquete) {
            case 1:
                return kmRuta * 1000;
            case 2:
                return kmRecorridos * 1000;
            case 3:
                return (kmRuta * 1000) * 0.5;
            default:
                return 0;
        }
    }

    public int getIdTiquete() {
        return idTiquete;
    }

    public int getTipoTiquete() {
        return tipoTiquete;
    }

    public double getValorTiquete() {
        return valorTiquete;
    }

    public Viaje getViajeAsignado() {
        return viajeAsignado;
    }

    public int getKmRecorridos() {
        return kmRecorridos;
    }

    public String getNombreTipo() {
        switch (tipoTiquete) {
            case 1: return "Completo";
            case 2: return "Parcial";
            case 3: return "Descuento";
            default: return "Desconocido";
        }
    }

    public String toString() {
        return "Tiquete #" + idTiquete +
               " | Tipo: " + getNombreTipo() +
               " | Valor: $" + String.format("%.0f", valorTiquete) +
               " | Km: " + kmRecorridos;
    }
}

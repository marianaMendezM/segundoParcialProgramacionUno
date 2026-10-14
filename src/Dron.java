public class Dron extends EquipoAudiovisual {
    private int autonomiaVuelo;
    private boolean bateriaAdicional;
    private double costoHora ;

    public Dron(String codigo, String marca, String modelo, EstadoEquipo estado, int autonomiaVuelo, boolean bateriaAdicional) {
        super(codigo, marca, modelo, estado);
        this.autonomiaVuelo = autonomiaVuelo;
        this.bateriaAdicional = bateriaAdicional;
        this.costoHora = 18000.0; // se lo asignamos por que el parcial dice que este es el valor base
    }

    @Override
    public double calcularCostoAlquiler(int horas) {
        double total = costoHora * horas;
        if (bateriaAdicional) {
            total += 20000.0;
        }
        return total;
    }

    public int getAutonomiaVuelo() {
        return autonomiaVuelo;
    }

    public void setAutonomiaVuelo(int autonomiaVuelo) {
        this.autonomiaVuelo = autonomiaVuelo;
    }

    public boolean isBateriaAdicional() {
        return bateriaAdicional;
    }

    public void setBateriaAdicional(boolean bateriaAdicional) {
        this.bateriaAdicional = bateriaAdicional;
    }

    public double getCostoHora() {
        return costoHora;
    }

    public void setCostoHora(double costoHora) {
        this.costoHora = costoHora;
    }
}
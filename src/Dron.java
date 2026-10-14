public class Dron extends EquipoAudiovisual{
    private int autonomiaVuelo;
    private boolean bateriaAdicional;
    private double costoHora=18000;

    public Dron(String codigo, String marca, String modelo, EstadoEquipo estado, int autonomiaVuelo, boolean bateriaAdicional, double costoHora) {
        super(codigo, marca, modelo, estado);
        this.autonomiaVuelo = autonomiaVuelo;
        this.bateriaAdicional = bateriaAdicional;
        this.costoHora = costoHora;
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

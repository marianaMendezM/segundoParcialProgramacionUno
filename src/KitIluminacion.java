public class KitIluminacion extends  EquipoAudiovisual{
    private int cantidadLuces;
    private boolean controlInalambrico;
    private double costoHora=10000;

    public KitIluminacion(String codigo, String marca, String modelo, EstadoEquipo estado, int cantidadLuces, boolean controlInalambrico, double costoHora) {
        super(codigo, marca, modelo, estado);
        this.cantidadLuces = cantidadLuces;
        this.controlInalambrico = controlInalambrico;
        this.costoHora = costoHora;
    }
    @Override
    public double calcularCostoAlquiler(int horas){
        double total = costoHora * horas;
        if (controlInalambrico){
        total += 12000.0;
        }
        return total;
    }

    public int getCantidadLuces() {
        return cantidadLuces;
    }

    public void setCantidadLuces(int cantidadLuces) {
        this.cantidadLuces = cantidadLuces;
    }

    public boolean isControlInalambrico() {
        return controlInalambrico;
    }

    public void setControlInalambrico(boolean controlInalambrico) {
        this.controlInalambrico = controlInalambrico;
    }

    public double getCostoHora() {
        return costoHora;
    }

    public void setCostoHora(double costoHora) {
        this.costoHora = costoHora;
    }
}

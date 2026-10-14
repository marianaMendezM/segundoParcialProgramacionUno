public  class Camara extends EquipoAudiovisual {
    private double costoHora ;
    private boolean estabilizador;
    private String resolucionMaxima;


    public Camara(String codigo, String marca, String modelo, EstadoEquipo estado, boolean estabilizador, String resolucionMaxima) {
        super(codigo, marca, modelo, estado);
        this.costoHora = 12000.0; // el costo lo podenos fijo , ya que nos dice que la base es de este valor
        this.estabilizador = estabilizador;
        this.resolucionMaxima = resolucionMaxima;
    }

    @Override
    public double calcularCostoAlquiler(int horas) {
        double total = costoHora * horas;
        if (estabilizador) {
            total += 15000.0;
        }
        return total;
    }

    public double getCostoHora() {
        return costoHora;
    }

    public void setCostoHora(double costoHora) {
        this.costoHora = costoHora;
    }

    public boolean isEstabilizador() {
        return estabilizador;
    }

    public void setEstabilizador(boolean estabilizador) {
        this.estabilizador = estabilizador;
    }

    public String getResolucionMaxima() {
        return resolucionMaxima;
    }

    public void setResolucionMaxima(String resolucionMaxima) {
        this.resolucionMaxima = resolucionMaxima;
    }
}
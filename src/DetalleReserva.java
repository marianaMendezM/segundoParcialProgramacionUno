public class DetalleReserva {
    private String id;
    private EquipoAudiovisual equipo;
    private int cantidadHoras;

    public DetalleReserva(String id, EquipoAudiovisual equipo, int cantidadHoras) {
        this.id = id;
        this.equipo = equipo;
        this.cantidadHoras = cantidadHoras;
    }
    public double calcularSubtotal() {
        return equipo.calcularCostoAlquiler(cantidadHoras);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EquipoAudiovisual getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoAudiovisual equipo) {
        this.equipo = equipo;
    }

    public int getCantidadHoras() {
        return cantidadHoras;
    }

    public void setCantidadHoras(int cantidadHoras) {
        this.cantidadHoras = cantidadHoras;
    }
}

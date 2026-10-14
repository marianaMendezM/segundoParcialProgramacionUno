import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private String id;
    private Cliente cliente;
    private final LocalDate fecha;
    private final Turno turnoo;
    private EstadoReserva estado;
    private Operador operador;
    private  List<DetalleReserva> listDetalleReserva;

    public Reserva(LocalDate fecha, Turno turnoo, String id, Cliente cliente, EstadoReserva estado, Operador operador, List<DetalleReserva> listDetalleReserva) {
        this.fecha = fecha;
        this.turnoo = turnoo;
        this.id = id;
        this.cliente = cliente;
        this.estado = EstadoReserva.SOLICITADA;
        this.listDetalleReserva = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Turno getTurnoo() {
        return turnoo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public EstadoReserva getEstado() {
        return estado;
    }



}



import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private String id;
    private Cliente cliente;
    private final LocalDate fecha; // Atributo inmutable que nos pide el parcial, no es record por q la clase no es completamente inmutable
    private final Turno turno;   // Atributo inmutable que nos pide el parcial, no es record por q la clase no es completamente inmutable
    private EstadoReserva estado;
    private Operador operador;
    private List<DetalleReserva> listDetalleReserva;

    public Reserva(String id, Cliente cliente, LocalDate fecha, Turno turno) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.turno = turno;
        this.estado = EstadoReserva.SOLICITADA;
        this.listDetalleReserva = new ArrayList<>();
    }
    //agrego un equipo audioVisual
    public boolean agregarEquipo(EquipoAudiovisual equipo, int horas) {
        if (equipo.getEstado() != EstadoEquipo.DISPONIBLE) {
            JOptionPane.showMessageDialog(null, "⚠ Error: " + equipo.getCodigo() + " no está disponible para alquiler.");
            return false;
        }
        for (DetalleReserva detalle : listDetalleReserva) {
            if (detalle.getEquipo().getCodigo().equalsIgnoreCase(equipo.getCodigo())) {
                JOptionPane.showMessageDialog(null, "⚠ Error: El equipo ya se encuentra agregado a esta reserva.");
                return false;
            }
        }
        String detalleId = "DET-" + (listDetalleReserva.size() + 1);
        listDetalleReserva.add(new DetalleReserva(detalleId, equipo, horas));
        return true;
    }
    //asigno el operador a la reserva
    public void asignarOperador(Operador operador) {
        this.operador = operador;
    }

    //calculo el total
    public double calcularTotal() {
        double total = 0.0;
        for (DetalleReserva detalle : listDetalleReserva) {
            total += detalle.calcularSubtotal();
        }
        return total;
    }

    public boolean tieneDron() {
        for (DetalleReserva detalle : listDetalleReserva) {
            if (detalle.getEquipo() instanceof Dron) {
                return true;
            }
        }
        return false;
    }

    public boolean setEstado(EstadoReserva nuevoEstado) {
        if (this.estado == EstadoReserva.CANCELADA || this.estado == EstadoReserva.FINALIZADA) {
            if (nuevoEstado == EstadoReserva.CONFIRMADA) {
                JOptionPane.showMessageDialog(null, "⚠ Error: Una reserva cancelada o finalizada no puede regresar al estado confirmada.");
                return false;
            }
        }
        this.estado = nuevoEstado;
        return true;
    }

    // GETTERS
    public String getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Turno getTurno() {return turno;}

    public EstadoReserva getEstado() {
        return estado;
    }

    public Operador getOperador() {
        return operador;
    }

    public List<DetalleReserva> getListDetalleReserva() {
        return listDetalleReserva;
    }
}


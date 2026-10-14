import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private String id;
    private Cliente cliente;
    private final LocalDate fecha; // Atributo inmutable
    private final Turno turnoo; // Atributo inmutable
    private EstadoReserva estado;
    private Operador operador;
    private List<DetalleReserva> listDetalleReserva;

    public Reserva(LocalDate fecha, Turno turno, String id, Cliente cliente, EstadoReserva estado, Operador operador, List<DetalleReserva> listDetalleReserva) {
        this.fecha = fecha;
        this.turnoo = turno;
        this.id = id;
        this.cliente = cliente;
        this.estado = EstadoReserva.SOLICITADA;
        this.listDetalleReserva = new ArrayList<>();
    }

    public boolean agregarEquipo(EquipoAudiovisual equipo, int horas) {
        if (equipo.getEstado() != EstadoEquipo.DISPONIBLE) {
            JOptionPane.showMessageDialog(null, "⚠ Error ⚠ " + equipo.getCodigo() + " no esta disponible para alquiler");
            return false;
        }
        for (DetalleReserva detalle : listDetalleReserva) {
            if (detalle.getEquipo().getCodigo().equalsIgnoreCase(equipo.getCodigo())) {
                JOptionPane.showMessageDialog(null, "⚠ Error ⚠ " + "\n" + "El quipo no esta disponible para alquiler");
                return false;
            }
        }

        String detalleId = "Codigo: " + (listDetalleReserva.size() + 1);
        listDetalleReserva.add(new DetalleReserva(detalleId, equipo, horas));
        return true;
    }

    public void asignarOperador(Operador operador) {
        this.operador = operador;
    }

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
                JOptionPane.showMessageDialog(null, "⚠ Error ⚠ " + "\n" + "Una reserva cancelada o finalizada no puede regresar al estado confirmada.");
                return false;
            }
        }
        this.estado = nuevoEstado;
        return true;
    }

    public Operador getOperador() {
        return operador;
    }

    public List<DetalleReserva> getListDetalleReserva() {
        return listDetalleReserva;
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



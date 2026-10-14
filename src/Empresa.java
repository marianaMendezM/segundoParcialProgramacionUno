import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String nit;
    private String nombre;
    private List<Sede> listSede;
    private List<EquipoAudiovisual> listEquipoAudiovisual;
    private List<Cliente> listCliente;
    private List<Operador> listOperador;
    private List<Reserva> listReserva;

    public Empresa(String id, String nombre) {
        this.nit = nit;
        this.nombre = nombre;
        this.listSede = new ArrayList<>();
        this.listEquipoAudiovisual = new ArrayList<>();
        this.listCliente = new ArrayList<>();
        this.listOperador = new ArrayList<>();
        this.listReserva = new ArrayList<>();
    }
    // Registro de la Sede
    public boolean registrarSede(Sede sede) {
        for (Sede s : listSede) {
            if (s.getCodigo().equalsIgnoreCase(sede.getCodigo())) {
                System.out.println("Error: Ya existe una sede con el código: " + sede.getCodigo());
                return false;
            }
        }
        listSede.add(sede);
        return true;
    }
    //Registro de Equipos
    public boolean registrarEquipo(EquipoAudiovisual equipo) {
        for (EquipoAudiovisual eq : listEquipoAudiovisual) {
            if (eq.getCodigo().equalsIgnoreCase(equipo.getCodigo())) {
                System.out.println("Error: Ya existe un equipo con el código: " + equipo.getCodigo());
                return false;
            }
        }
        listEquipoAudiovisual.add(equipo);
        return true;
    }
    //Registro del cliente
    public boolean registrarCliente(Cliente cliente) {
        for (Cliente c : listCliente) {
            if (c.cedula().equalsIgnoreCase(cliente.cedula())) {
                System.out.println("Error: Ya existe un cliente con el documento " + cliente.cedula());
                return false;
            }
        }
        listCliente.add(cliente);
        return true;
    }
    //Registro del operador
    public boolean registrarOperador(Operador operador) {
        for (Operador op : listOperador) {
            if (op.getId().equalsIgnoreCase(operador.getId())) {
                System.out.println("Error: Ya existe un operador con el ID " + operador.getId());
                return false;
            }
        }
        listOperador.add(operador);
        return true;
    }
    //Registro Rserva
    public boolean registrarReserva(Reserva reserva) {
        for (Reserva r : listReserva) {
            if (r.getId().equalsIgnoreCase(reserva.getId())) {
                System.out.println("Error: Ya existe una reserva con el código " + reserva.getId());
                return false;
            }
        }
        listReserva.add(reserva);
        return true;
    }
    // Cambio de Estado de la reserva con las reglas de la empresa (Dron con operador, que exista reserva,
    // una finalizada y cancelada no puede pasar a confirmada
    //contener al menos un equipo la reserva
    public boolean cambiarEstadoReserva(String idReserva, EstadoReserva nuevoEstado) {
        Reserva reserva = buscarReservaPorId(idReserva);
        if (reserva == null) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error: Reserva no encontrada.");
            return false;
        }
        if (nuevoEstado == EstadoReserva.CONFIRMADA) {

            if (reserva.getEstado() == EstadoReserva.CANCELADA || reserva.getEstado() == EstadoReserva.FINALIZADA) {
                javax.swing.JOptionPane.showMessageDialog(null, "⚠ Error: Una reserva cancelada o finalizada no puede regresar al estado confirmada.");
                return false;
            }
            if (reserva.getListDetalleReserva().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(null, "⚠ Error: La reserva debe contener al menos un equipo antes de ser confirmada.");
                return false;
            }
            if (reserva.tieneDron() && reserva.getOperador() == null) {
                javax.swing.JOptionPane.showMessageDialog(null, "⚠ Error: La reserva contiene un Dron y requiere un operador asignado para confirmarse.");
                return false;
            }
            // Validamos la disponibilidad de los equipos
            for (DetalleReserva detalle : reserva.getListDetalleReserva()) {
                EquipoAudiovisual eq = detalle.getEquipo();
                for (Reserva rConf : listReserva) {
                    if (!rConf.getId().equalsIgnoreCase(reserva.getId()) &&
                            rConf.getEstado() == EstadoReserva.CONFIRMADA &&
                            rConf.getFecha().equals(reserva.getFecha()) &&
                            rConf.getTurno() == reserva.getTurno()) {

                        for (DetalleReserva detConf : rConf.getListDetalleReserva()) {
                            if (detConf.getEquipo().getCodigo().equalsIgnoreCase(eq.getCodigo())) {
                                javax.swing.JOptionPane.showMessageDialog(null, "⚠ Error: El equipo " + eq.getCodigo()
                                        + " ya está reservado por otra reserva confirmada en la fecha "
                                        + reserva.getFecha() + " y turno " + reserva.getTurno());
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return reserva.setEstado(nuevoEstado);
    }

    public List<EquipoAudiovisual> consultarEquiposDisponibles(LocalDate fecha, Turno turno) {
        List<EquipoAudiovisual> disponibles = new ArrayList<>();
        for (EquipoAudiovisual eq : listEquipoAudiovisual) {
            if (eq.getEstado() == EstadoEquipo.DISPONIBLE) {
                boolean ocupado = false;
                for (Reserva r : listReserva) {
                    if (r.getEstado() == EstadoReserva.CONFIRMADA &&
                            r.getFecha().equals(fecha) &&
                            r.getTurno() == turno) {
                        for (DetalleReserva det : r.getListDetalleReserva()) {
                            if (det.getEquipo().getCodigo().equalsIgnoreCase(eq.getCodigo())) {
                                ocupado = true;
                                break;
                            }
                        }
                    }
                    if (ocupado) break;
                }
                if (!ocupado) {
                    disponibles.add(eq);
                }
            }
        }
        return disponibles;
    }
    //se consulta que reservas tiene un cliente por medio de la cedula
    public List<Reserva> consultarReservasCliente(String idCliente) {
        List<Reserva> delCliente = new ArrayList<>();
        for (Reserva r : listReserva) {
            if (r.getCliente().cedula().equalsIgnoreCase(idCliente) && r.getEstado() == EstadoReserva.CONFIRMADA) {
                delCliente.add(r);
            }
        }
        return delCliente;
    }

    public Reserva buscarReservaPorId(String idReserva) {
        for (Reserva r : listReserva) {
            if (r.getId().equalsIgnoreCase(idReserva)) {
                return r;
            }
        }
        return null;
    }

    public EquipoAudiovisual buscarEquipoPorCodigo(String codigo) {
        for (EquipoAudiovisual eq : listEquipoAudiovisual) {
            if (eq.getCodigo().equalsIgnoreCase(codigo)) {
                return eq;
            }
        }
        return null;
    }

    public Cliente buscarClientePorId(String id) {
        for (Cliente c : listCliente) {
            if (c.cedula().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    public Operador buscarOperadorPorId(String id) {
        for (Operador op : listOperador) {
            if (op.getId().equalsIgnoreCase(id)) {
                return op;
            }
        }
        return null;
    }

    public String getNit() { return nit; }
    public String getNombre() { return nombre; }
    public List<Sede> getListSede() { return listSede; }
}
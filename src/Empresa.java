import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class Empresa {
    private String nit;
    private String nombre;
    private List<Sede> listSede;
    private  List<EquipoAudiovisual> listEquipoAudiovisual;
    private  List<Cliente> listCliente;
    private  List<Operador> listOperador;
    private  List<Reserva> listReserva;

    public Empresa(String nit, String nombre, List<Sede> listSede, List<EquipoAudiovisual> listEquipoAudiovisual, List<Cliente> listCliente, List<Operador> listOperador, List<Reserva> listReserva) {
        this.nit = nit;
        this.nombre = nombre;
        this.listSede = new ArrayList<>();
        this.listEquipoAudiovisual = new ArrayList<>();
        this.listCliente = new ArrayList<>();
        this.listOperador = new ArrayList<>();
        this.listReserva = new ArrayList<>();
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

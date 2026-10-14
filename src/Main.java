import java.time.LocalDate;
import java.util.List;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Empresa empresa = new Empresa("MJC-1827", "PixelRent");

        int opcion = 0;
        do {
            String menu = "°❀⋆.ೃ࿔*:･°❀⋆.ೃ࿔*:･ Menu PixelRent °❀⋆.ೃ࿔*:･°❀⋆.ೃ࿔*:･ \n \n"
                    + "1. Registrar sede \n"
                    + "2. Registar cliente \n"
                    + "3. Registrar equipo \n"
                    + "4. Registar operador \n"
                    + "5. Crear reserva  \n"
                    + "6. Agregar equipo a reserva \n"
                    + "7. Asignar operador a reserva \n"
                    + "8. Cambiar estado a reserva  \n"
                    + "9. Comsultar equipos disponibles \n"
                    + "10. Consultar reservas confirmadas de clientes \n"
                    + "11. Consultar valor total de reserva \n"
                    + "12. Salir  \n"
                    + "-> Por favor ingresar un opcion validad (1 - 12) <-"
                    + "──────────────────────── ⋆⋅☆⋅⋆ ────────────────────────";
            opcion = pedirEntero(menu);

            switch (opcion) {
                case 1:
                    registrarSede(empresa);
                    break;
                case 2:
                    registrarCliente(empresa);
                    break;
                case 3:
                    registrarEquipo(empresa);
                    break;
                case 4:
                    registrarOperador(empresa);
                    break;
                case 5:
                    crearReserva(empresa);
                    break;
                case 6:
                    agregarEquipoAReserva(empresa);
                    break;
                case 7:
                    asignarOperadorAReserva(empresa);
                    break;
                case 8:
                    cambiarEstadoReserva(empresa);
                    break;
                case 9:
                    consultarEquiposDisponibles(empresa);
                    break;
                case 10:
                    consultarReservasCliente(empresa);
                    break;
                case 11:
                    consultarValorReserva(empresa);
                    break;
                case 12:
                    mostrarMensaje("Saliendo del sistema PixelRent...");
                    break;

                default:
                    if (opcion != 0) mostrarMensaje("Esta opción es inavlida, Por favor reintenta");
                    break;
            }
        } while (opcion!=12);
    }
}


import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;

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

    //  metodos que piden datos, que nos ayudan a simplificar un poco el codigo
    private static String pedirTexto(String mensaje) {
        String entrada = JOptionPane.showInputDialog(null, mensaje);
        if (entrada == null) return null;
        return entrada.trim();
    }

    private static int pedirEntero(String mensaje) {
        String entrada = pedirTexto(mensaje);
        if (entrada == null || entrada.isEmpty()) return 0;
        try {
            return Integer.parseInt(entrada);
        } catch (Exception e) {
            return 0;
        }
    }
    private static boolean pedirBoolean(String mensaje) {
        int respuesta = JOptionPane.showConfirmDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION);
        return (respuesta == JOptionPane.YES_OPTION);
    }

    private static LocalDate pedirFecha(String titulo) {
        String entrada = pedirTexto(titulo + "\n\nIngrese la fecha en formato AAAA-MM-DD (ej: 2026-10-15):");
        if (entrada == null || entrada.isEmpty()) return null;

        try {
            return LocalDate.parse(entrada);
        } catch (Exception e) {
            mostrarMensaje("⚠ Error: La fecha '" + entrada + "' no es válida.\nDebe ingresar una fecha real en formato AAAA-MM-DD (ej: 2026-10-15).");
            return null;
        }
    }
    //metodo para mostrar

    private static void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    // Función para registrar los datos de  la sede
    private static void registrarSede(Empresa empresa) {
        String id = pedirTexto("Código de la Sede (ej: SED-01):");
        if (id == null || id.isEmpty()) return;
        String nombre = pedirTexto("Nombre de la Sede:");
        if (nombre == null || nombre.isEmpty()) return;
        String direccion = pedirTexto("Dirección:");
        if (direccion == null || direccion.isEmpty()) return;

        if (empresa.registrarSede(new Sede(id, nombre, direccion))) {
            mostrarMensaje("¡Sede registrada exitosamente!");
        }
    }
    // Función para registrar los datos del cliente
    private static void registrarCliente(Empresa empresa) {
        String id = pedirTexto("Cedula del Cliente:");
        if (id == null || id.isEmpty()) return;
        String nombre = pedirTexto("Nombre Completo:");
        if (nombre == null || nombre.isEmpty()) return;
        String telefono = pedirTexto("Teléfono:");
        String correo = pedirTexto("Correo Electrónico:");

        if (empresa.registrarCliente(new Cliente(id, nombre, telefono, correo))) {
            mostrarMensaje("¡Cliente registrado exitosamente!");
        }
    }
    // Función para registrar los datos de  los equipos, con cada uno de las 3 opciones que tenemos (camara,dron, kit)
    private static void registrarEquipo(Empresa empresa) {
        int tipo = pedirEntero("Seleccione el número del tipo de Equipo:\n1. Cámara\n2. Dron\n3. Kit de Iluminación");
        if (tipo < 1 || tipo > 3) return;

        String codigo = pedirTexto("Código único del equipo:");
        if (codigo == null || codigo.isEmpty()) return;
        String marca = pedirTexto("Marca:");
        String modelo = pedirTexto("Modelo:");

        if (tipo == 1) {
            boolean estabilizador = pedirBoolean("¿Tiene estabilizador?");
            String resolucion = pedirTexto("Resolución máxima (ej: 4K):");
            empresa.registrarEquipo(new Camara(codigo, marca, modelo, EstadoEquipo.DISPONIBLE, estabilizador, resolucion));
        } else if (tipo == 2) {
            int autonomia = pedirEntero("Autonomía de vuelo (en minutos):");
            boolean bateriaAdicional = pedirBoolean("¿Tiene batería adicional?");
            empresa.registrarEquipo(new Dron(codigo, marca, modelo, EstadoEquipo.DISPONIBLE, autonomia, bateriaAdicional));
        } else if (tipo == 3) {
            int luces = pedirEntero("Cantidad de luces:");
            boolean controlInalambrico = pedirBoolean("¿Tiene control inalámbrico?");
            empresa.registrarEquipo(new KitIluminacion(codigo, marca, modelo, EstadoEquipo.DISPONIBLE, luces, controlInalambrico));
        }
        mostrarMensaje("¡Equipo registrado exitosamente!");
    }
    // Función para registrar los datos del operador
    private static void registrarOperador(Empresa empresa) {
        String id = pedirTexto("ID del Operador:");
        if (id == null || id.isEmpty()) return;
        String nombre = pedirTexto("Nombre Completo:");
        String certificacion = pedirTexto("Certificación:");
        String experiencia = pedirTexto("Años de Experiencia:");

        if (empresa.registrarOperador(new Operador(id, nombre, certificacion, experiencia))) {
            mostrarMensaje("¡Operador registrado exitosamente!");
        }
    }

}


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
                    + "6. Agregar equipo a reserva y mostrar costo \n"
                    + "7. Asignar operador a reserva \n"
                    + "8. Cambiar estado a reserva  \n"
                    + "9. Consultar equipos disponibles \n"
                    + "10. Consultar reservas confirmadas de clientes \n"
                    + "11. Consultar valor total de reserva \n"
                    + "12. Salir  \n"
                    + "-> Por favor ingresar un opcion validad (1 - 12) <- \n"
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
                    agregarEquipoReserva(empresa);
                    break;
                case 7:
                    asignarOperadorReserva(empresa);
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
                    if (opcion != 0) mostrarMensaje("Esta opción es invalida, Por favor reintenta");
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
        String entrada = pedirTexto(titulo + "\n\n Ingrese la fecha en formato AAAA-MM-DD (ej: 2026-10-15):");
        if (entrada == null || entrada.isEmpty()) return null;

        try {
            return LocalDate.parse(entrada);
        } catch (Exception e) {
            mostrarMensaje("⚠ Error: La fecha ⚠ '" + entrada + "' no es válida.\n Debe ingresar una fecha real en formato AAAA-MM-DD (ej: 2026-10-15).");
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
            mostrarMensaje("¡Sede registrada exitosamente! ✔");
        }
    }
    // Función para registrar los datos del cliente
    private static void registrarCliente(Empresa empresa) {
        String id = pedirTexto("Cedula del cliente:");
        if (id == null || id.isEmpty()) return;
        String nombre = pedirTexto("Nombre completo:");
        if (nombre == null || nombre.isEmpty()) return;
        String telefono = pedirTexto("Teléfono:");
        String correo = pedirTexto("Correo electrónico:");

        if (empresa.registrarCliente(new Cliente(id, nombre, telefono, correo))) {
            mostrarMensaje("¡Cliente registrado exitosamente! ✔");
        }
    }
    // Función para registrar los datos de  los equipos, con cada uno de las 3 opciones que tenemos (camara,dron, kit)
    private static void registrarEquipo(Empresa empresa) {
        int tipo = pedirEntero("Seleccione el número del tipo de Equipo: \n" +
                "1. Cámara\n" +
                "2. Dron\n" +
                "3. Kit de Iluminación");
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
            boolean controlInalambrico = pedirBoolean("¿Tiene control inalambrico?");
            empresa.registrarEquipo(new KitIluminacion(codigo, marca, modelo, EstadoEquipo.DISPONIBLE, luces, controlInalambrico));
        }
        mostrarMensaje("¡Equipo registrado exitosamente! ✔");
    }
    // Función para registrar los datos del operador
    private static void registrarOperador(Empresa empresa) {
        String id = pedirTexto("Cedula del Operador:");
        if (id == null || id.isEmpty()) return;
        String nombre = pedirTexto("Nombre Completo: ");
        String certificacion = pedirTexto("Certificación:");
        String experiencia = pedirTexto("Años de Experiencia:");

        if (empresa.registrarOperador(new Operador(id, nombre, certificacion, experiencia))) {
            mostrarMensaje("¡Operador registrado exitosamente! ✔");
        }
    }
    // creacion de la reserva, con su turno y  cliente respectivo
    private static void crearReserva(Empresa empresa) {
        String idReserva = pedirTexto("Código para la Reserva: ");
        if (idReserva == null || idReserva.isEmpty()) return;

        String idCliente = pedirTexto("Cedula del Cliente: ");
        if (idCliente == null || idCliente.isEmpty()) return;

        Cliente cliente = empresa.buscarClienteId(idCliente);
        if (cliente == null) {
            mostrarMensaje("⚠ Error ⚠ El cliente con cedula: '" + idCliente + "' no existe.");
            return;
        }
        LocalDate fecha = pedirFecha("── ✦✦ Creación de reserva ✦✦──");
        if (fecha == null) return;

        int turnoNum = pedirEntero("Seleccione el número del Turno:\n" +
                "1. Mañana\n" +
                "2. Tarde\n" +
                "3. Noche");
        if (turnoNum < 1 || turnoNum > 3) {
            mostrarMensaje("⚠ Error ⚠ Opción de turno no válida.");
            return;
        }

        Turno turno = Turno.MANIANA;
        if (turnoNum == 2) turno = Turno.TARDE;
        else if (turnoNum == 3) turno = Turno.NOCHE;

        if (empresa.registrarReserva(new Reserva(idReserva, cliente, fecha, turno))) {
            mostrarMensaje("¡Reserva creada exitosamente en estado SOLICITADA!");
        }
    }
    //agrega el equipo a la reserva
    private static void agregarEquipoReserva(Empresa empresa) {
        String idReserva = pedirTexto("Codigo de la reserva: ");
        if (idReserva == null || idReserva.isEmpty()) return;

        Reserva reserva = empresa.buscarReservaId(idReserva);
        if (reserva == null) {
            mostrarMensaje("⚠ Error ⚠ Reserva no encontrada ");
            return;
        }
        String codigoEquipoAudi = pedirTexto("Codigo del equipo a agregar: ");
        if (codigoEquipoAudi == null || codigoEquipoAudi.isEmpty()) return;

        EquipoAudiovisual eq = empresa.buscarEquipoCodigo(codigoEquipoAudi);
        if (eq == null) {
            mostrarMensaje("⚠ Error ⚠ Equipo no encontrado ");
            return;
        }

        int horas = pedirEntero("Cantidad de horas de alquiler:");
        if (horas <= 0) {
            mostrarMensaje("⚠ Error ⚠ Las horas de alquiler deben ser mayores a 0 ");
            return;
        }

        // Revisa que otra reserva CONFIRMADA no tenga ya este equipo en la misma fecha y turno
        for (Reserva r : empresa.getListReserva()) {
            if (!r.getId().equalsIgnoreCase(reserva.getId())
                    && r.getEstado() == EstadoReserva.CONFIRMADA // Solo bloquea las reservas CONFIRMADAS
                    && r.getFecha().equals(reserva.getFecha())
                    && r.getTurno() == reserva.getTurno()) {

                for (DetalleReserva detalle : r.getListDetalleReserva()) {
                    if (detalle.getEquipo().getCodigo().equalsIgnoreCase(eq.getCodigo())) {
                        mostrarMensaje("⚠ Error ⚠ El equipo '" + eq.getCodigo()
                                + "' ya está reservado en una reserva CONFIRMADA para la fecha " + reserva.getFecha()
                                + " en el turno " + reserva.getTurno());
                        return; // Cancela la operación sin agregar el equipo
                    }
                }
            }
        }

        // SI PASÓ LA VALIDACIÓN, SE AGREGA AQUÍ
        if (reserva.agregarEquipo(eq, horas)) {
            mostrarMensaje("¡Equipo agregado a la reserva exitosamente! ✔\n\n"
                    + "✦Valor acumulado de la reserva: $" + reserva.calcularTotal());
        }
    }

    private static void asignarOperadorReserva(Empresa empresa){
        String idReserva= pedirTexto("Codigo de la reserva: ");
        Reserva res = empresa.buscarReservaId(idReserva);
        if (res == null) {
            mostrarMensaje("⚠ Error ⚠ Reserva no encontrada ");
            return;
        }
        String idOperador = pedirTexto("Id del operador a asignar: ");
        Operador op = empresa.buscarOperadorId(idOperador);
        if (op == null) {
            mostrarMensaje("⚠ Error ⚠ Operador no encontrado ");
            return;
        }
        res.asignarOperador(op);
        mostrarMensaje("¡Operador asignado exitosamente! ✔");
    }

    private static void cambiarEstadoReserva(Empresa empresa){
        String idReserva = pedirTexto("Codigo de la reserva a modificar: ");
        Reserva res = empresa.buscarReservaId(idReserva);
        if (res == null) {
            mostrarMensaje("⚠ Error ⚠ Operador no encontrado ");
            return;
        }
        int opcionEstado = pedirEntero("Estado Actual: " + res.getEstado() + "\n Seleccione el nuevo Estado:\n"
                + "1. Confirmada\n"
                + "2. Cancelada\n"
                + "3. Finalizada");
        EstadoReserva nuevoEstado = null;
        if (opcionEstado == 1) nuevoEstado = EstadoReserva.CONFIRMADA;
        else if (opcionEstado == 2) nuevoEstado = EstadoReserva.CANCELADA;
        else if (opcionEstado == 3) nuevoEstado = EstadoReserva.FINALIZADA;
        else return;

        if (empresa.cambiarEstadoReserva(idReserva, nuevoEstado)) {
            mostrarMensaje("¡Estado de la reserva actualizado a " + nuevoEstado + "!");
        }
    }
    private static void consultarEquiposDisponibles(Empresa empresa) {
        LocalDate fecha = pedirFecha(" . ݁₊ ⊹ . ݁ Consulta de disponibilidad ݁ . ⊹ ₊ ݁. ");
        if (fecha == null)
            return;
        int tNum = pedirEntero("✴Seleccione el numero del turno:\n" +
                "1. Mañana\n" +
                "2. Tarde \n" +
                "3. Noche");
        if (tNum < 1 || tNum > 3) {
            mostrarMensaje("⚠ Error ⚠  Opcion de turno no valida.");
            return;
        }

        Turno turno = Turno.MANIANA;
        if (tNum == 2) {
            turno = Turno.TARDE;
        }else if (tNum == 3){
            turno = Turno.NOCHE;
        }

        List<EquipoAudiovisual> dispo = empresa.consultarEquipoDisponible(fecha, turno);
        if (dispo.isEmpty()) {
            mostrarMensaje("No hay equipos disponibles para la fecha " + fecha + " y turno " + turno);
            return;
        }

        String reporte = "⊹₊˚‧︵‿₊୨ Equipos disponibles ୧₊‿︵‧˚₊⊹\n\n";
        for (EquipoAudiovisual eq : dispo) {
            reporte += "꩜ Código: " + eq.getCodigo()
                    + " ★ Marca: " + eq.getMarca()
                    + " ★ Modelo: " + eq.getModelo()
                    + " ★ Estado: " + eq.getEstado() + "\n";
        }
        mostrarMensaje(reporte);
    }
    private static void consultarReservasCliente(Empresa empresa) {
        String idCliente = pedirTexto("Documento del Cliente ");
        if (idCliente == null || idCliente.isEmpty()) return;

        List<Reserva> lista = empresa.consultarReservaCliente(idCliente);
        if (lista.isEmpty()) {
            mostrarMensaje("No se encontraron reservas confirmadas para el cliente: " + idCliente);
            return;
        }
        String reporte = "‿̩͙⊱༒༻Reservas confirmadas del cliente༺༒⊰‿̩͙ \n\n";
        for (Reserva r : lista) {
            reporte += "✦ ID:  " + r.getId()
                    + " ✿ Fecha: " + r.getFecha()
                    + " ✿ Turno: " + r.getTurno()
                    + " ✿ Total: $" + r.calcularTotal() + "\n";
        }
        mostrarMensaje(reporte);
    }
    private static void consultarValorReserva(Empresa empresa) {
        String idReserva = pedirTexto("Codigo de la Reserva ");
        if (idReserva == null || idReserva.isEmpty()) return;

        Reserva resultado = empresa.buscarReservaId(idReserva);
        if (resultado != null) {
            mostrarMensaje("˚｡⋆｡˚☽˚｡⋆ Consulta de valor en tiempo real ˚｡⋆｡˚☽˚｡⋆ \n\n"
                    + "♥ Reserva ID: " + resultado.getId() + "\n"
                    + "♥ Estado actual: " + resultado.getEstado() + "\n"
                    + "♥ Equipos agregados: " + resultado.getListDetalleReserva().size() + "\n"
                    + "────────────────୨ৎ────────────────\n"
                    + "♥ Valor Total Acumulado: $" + resultado.calcularTotal());
        } else {
            mostrarMensaje("⚠ Error ⚠ Reserva no encontrada ");
        }
    }
}


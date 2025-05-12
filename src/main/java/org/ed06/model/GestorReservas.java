package org.ed06.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Clase que gestiona las reservas del hotel.
 * Permite realizar reservas, actualizar el estado VIP de los clientes y listar reservas existentes.
 */
public class GestorReservas {
    private final Map<Integer, List<Reserva>> reservasPorHabitacion = new HashMap<>();
    private final GestorClientes gestorClientes;
    private final GestorHabitaciones gestorHabitaciones;
    /**
     * Constructor de la clase GestorReservas.
     * @param gestorClientes Gestor de clientes.
     * @param gestorHabitaciones Gestor de habitaciones.
     */
    public GestorReservas(GestorClientes gestorClientes, GestorHabitaciones gestorHabitaciones) {
        this.gestorClientes = gestorClientes;
        this.gestorHabitaciones = gestorHabitaciones;
    }
    /**
     * Realiza una reserva de habitación si hay disponibilidad.
     * @param clienteId ID del cliente que realiza la reserva.
     * @param tipo Tipo de habitación solicitada.
     * @param fechaEntrada Fecha de inicio de la reserva.
     * @param fechaSalida Fecha de fin de la reserva.
     * @return Número de habitación reservada, o un código de error si la reserva no puede realizarse.
     */
    public int reservarHabitacion(int clienteId, String tipo, LocalDate fechaEntrada, LocalDate fechaSalida) {
        Cliente cliente = gestorClientes.obtenerCliente(clienteId);
        if (cliente == null) {
            System.out.println("No existe el cliente con ID " + clienteId);
            return -1;
        }
        if (!fechaEntrada.isBefore(fechaSalida)) {
            System.out.println("La fecha de entrada es posterior a la fecha de salida");
            return -2;
        }

        Habitacion habitacion = gestorHabitaciones.obtenerHabitacionPorTipoDisponible(tipo);
        if (habitacion == null) {
            System.out.println("No hay habitaciones disponibles del tipo " + tipo);
            return -3;
        }

        Reserva reserva = new Reserva(reservasPorHabitacion.size() + 1, habitacion, cliente, fechaEntrada, fechaSalida);
        reservasPorHabitacion.computeIfAbsent(habitacion.getNumero(), _ -> new ArrayList<>()).add(reserva);
        habitacion.reservar();

        actualizarEstadoVIP(cliente);

        System.out.println("Reserva realizada con éxito.");
        return habitacion.getNumero();
    }
    /**
     * Actualiza el estado VIP del cliente si ha realizado más de 3 reservas en el último año.
     * @param cliente Cliente cuya condición VIP será evaluada.
     */
    private void actualizarEstadoVIP(Cliente cliente) {
        long numReservas = reservasPorHabitacion.values().stream()
                .flatMap(List::stream)
                .filter(reserva -> reserva.getCliente().equals(cliente) &&
                        reserva.getFechaInicio().isAfter(LocalDate.now().minusYears(1)))
                .count();

        if (numReservas > 3 && !cliente.isEsVip()) {
            cliente.setEsVip(true);
            System.out.println("El cliente " + cliente.getNombre() + " ha pasado a ser VIP.");
        }
    }
    /**
     * Lista todas las reservas activas mostrando su información.
     */
    public void listarReservas() {
        reservasPorHabitacion.forEach((key, value) -> {
            System.out.println("Habitación #" + key);
            value.forEach(reserva -> System.out.println(
                    "Reserva #" + reserva.getId() + " - Cliente: " + reserva.getCliente().nombre
                            + " - Fecha de entrada: " + reserva.getFechaInicio()
                            + " - Fecha de salida: " + reserva.getFechaFin()));
        });
    }
}
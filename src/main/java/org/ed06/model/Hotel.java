package org.ed06.model;
/**
 * Clase que representa un hotel con sus datos básicos y gestores de clientes, habitaciones y reservas.
 */
public class Hotel {
    private final String NOMBRE;
    private final String DIRECCION;
    private final String TELEFONO;
    private final GestorClientes gestorClientes = new GestorClientes();
    private final GestorHabitaciones gestorHabitaciones = new GestorHabitaciones();
    private final GestorReservas gestorReservas = new GestorReservas(gestorClientes, gestorHabitaciones);
    /**
     * Constructor de la clase Hotel.
     * @param NOMBRE Nombre del hotel.
     * @param DIRECCION Dirección física del hotel.
     * @param TELEFONO Teléfono de contacto del hotel.
     */
    public Hotel(String NOMBRE, String DIRECCION, String TELEFONO) {
        this.NOMBRE = NOMBRE;
        this.DIRECCION = DIRECCION;
        this.TELEFONO = TELEFONO;
    }
    /** @return Gestor de clientes del hotel. */
    public GestorClientes getGestorClientes() {
        return gestorClientes;
    }
    /** @return Gestor de habitaciones del hotel. */
    public GestorHabitaciones getGestorHabitaciones() {
        return gestorHabitaciones;
    }
    /** @return Gestor de reservas del hotel. */
    public GestorReservas getGestorReservas() {
        return gestorReservas;
    }
}

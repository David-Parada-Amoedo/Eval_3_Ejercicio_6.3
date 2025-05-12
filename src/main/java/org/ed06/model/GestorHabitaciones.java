package org.ed06.model;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase que gestiona las habitaciones del hotel.
 * Permite registrar nuevas habitaciones, listar las disponibles y obtener habitaciones por número o tipo.
 */
public class GestorHabitaciones {
    /** Lista de habitaciones registradas en el hotel. */
    private final List<Habitacion> habitaciones = new ArrayList<>();
    /**
     * Registra una nueva habitación en el sistema.
     * @param tipo Tipo de habitación (SIMPLE, DOBLE, SUITE, LITERAS).
     * @param precioBase Precio base de la habitación por noche.
     */
    public void registrarHabitacion(String tipo, double precioBase) {
        Habitacion habitacion = new Habitacion(habitaciones.size() + 1, tipo, precioBase);
        habitaciones.add(habitacion);
    }
    /**
     * Lista todas las habitaciones disponibles en el hotel, mostrando sus detalles en la consola.
     */
    public void listarHabitacionesDisponibles() {
        habitaciones.stream()
                .filter(Habitacion::isDisponible)
                .forEach(h -> System.out.println("Habitación #" + h.getNumero() +
                        " - Tipo: " + h.getTipo() + " - Precio base: " + h.getPrecioBase()));
    }
    /**
     * Obtiene una habitación según su número de identificación.
     * @param numero Número de la habitación.
     * @return La habitación correspondiente al número, o null si no se encuentra.
     */
    public Habitacion obtenerHabitacion(int numero) {
        return habitaciones.stream()
                .filter(h -> h.getNumero() == numero)
                .findFirst().orElse(null);
    }
    /**
     * Obtiene una habitación disponible según el tipo especificado.
     * @param tipo Tipo de habitación buscada.
     * @return Una habitación disponible del tipo especificado, o null si no hay disponibilidad.
     */
    public Habitacion obtenerHabitacionPorTipoDisponible(String tipo) {
        return habitaciones.stream()
                .filter(h -> h.getTipo().equalsIgnoreCase(tipo) && h.isDisponible())
                .findFirst()
                .orElse(null);
    }
}


package org.ed06.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Clase que representa una reserva en el hotel.
 * Contiene información sobre el cliente, la habitación, las fechas de la reserva y el precio final.
 */
public class Reserva {
    private int id;
    private Habitacion habitacion;
    private Cliente cliente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double precioTotal;
    /**
     * Constructor que inicializa una reserva con los datos proporcionados.
     * @param id Identificador único de la reserva.
     * @param habitacion Habitación reservada.
     * @param cliente Cliente que realiza la reserva.
     * @param fechaInicio Fecha de inicio de la reserva.
     * @param fechaFin Fecha de fin de la reserva.
     */
    public Reserva(int id, Habitacion habitacion, Cliente cliente, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precioTotal = calcularPrecioFinal();
    }

    public int getId() {
        return id;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    /**
     * Calcula el precio total de la reserva.
     * El precio base de la habitación se multiplica por el número de noches reservadas.
     * Se aplican descuentos si el cliente es VIP (10%) o si la estancia supera los 7 días (5% adicional).
     * @return Precio total de la reserva.
     */

    public double calcularPrecioFinal() {
        //calculamos los días de la reserva
        double numeroDeDias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        // Calculamos el precio base de la habitación por el número de noches de la reserva
        double precioFinal = habitacion.getPrecioBase() * numeroDeDias;

        // Si el cliente es VIP, aplicamos un descuento del 10%
        if (cliente.esVip) {
            precioFinal *= 0.9;
        }

        // Si el intervalo de fechas es mayor a 7 días, aplicamos un descuento adicional del 5%
        if (numeroDeDias > 7) {
            precioFinal *= 0.95;
        }

        // Devolvemos el precio final
        return precioFinal;
    }
    /**
     * Muestra la información de la reserva en la consola.
     */
    public void mostrarReserva() {
        System.out.println("Reserva #" + id);
        System.out.println("Habitación #" + habitacion.getNumero() + " - Tipo: " + habitacion.getTipo() + " - Precio base: " + habitacion.getPrecioBase());
        System.out.println("Cliente: " + cliente.nombre);
        System.out.println("Fecha de inicio: " + fechaInicio.toString());
        System.out.println("Fecha de fin: " + fechaFin.toString());
        System.out.printf("Precio total: %.2f €\n", precioTotal);
    }
}

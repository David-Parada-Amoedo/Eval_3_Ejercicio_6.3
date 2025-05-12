package org.ed06.model;
/**
 * Clase que representa una habitación en el hotel.
 * Contiene información sobre el tipo de habitación, su precio y disponibilidad.
 */
public class Habitacion {
    private int numero;
    private String tipo; // "SIMPLE", "DOBLE", "SUITE"
    private double precioBase;

    //Todo pendiente cambiar la forma de gestionar la disponibilidad en base a las fechas de las reservas
    private boolean disponible;
    /**
     * Constructor que inicializa una habitación con los datos proporcionados.
     * @param numero Número identificador de la habitación.
     * @param tipo Tipo de habitación.
     * @param precioBase Precio base de la habitación por noche.
     */
    public Habitacion(int numero, String tipo, double precioBase) {
        this.numero = numero;
        this.tipo = tipo;
        this.precioBase = precioBase;
        this.disponible = true;
    }

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Determina el número máximo de huéspedes según el tipo de habitación.
     * @return Número máximo de huéspedes permitidos en la habitación.
     */
    public int obtenerNumMaxHuespedes() {
        return switch (tipo) {
            case "SIMPLE" -> 1;
            case "DOBLE" -> 3;
            case "SUITE" -> 4;
            case "LITERAS" -> 8;
            default -> 1;
        };
    }
    /**
     * Marca la habitación como reservada.
     * Si ya estaba reservada, muestra un mensaje en la consola.
     */
    public void reservar() {
        if (disponible) {
            System.out.println("Habitación #" + numero + " ya reservada");
        }
        disponible = true;
    }
}

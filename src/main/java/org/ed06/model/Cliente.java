package org.ed06.model;
/**
 * Representa un cliente en el sistema de reservas del hotel.
 * Contiene información personal y su estado VIP.
 */
public class Cliente {
    public int id;
    public String nombre;
    public String dni;
    public String email;
    public boolean esVip;
    /**
     * Obtiene el nombre del cliente.
     * @return Nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Verifica si el cliente es VIP.
     * @return `true` si el cliente es VIP, `false` en caso contrario.
     */
    public boolean isEsVip() {
        return esVip;
    }

    public void setEsVip(boolean esVip) {
        this.esVip = esVip;
    }
    /**
     * Constructor de Cliente.
     * @param id Identificador único del cliente.
     * @param nombre Nombre del cliente.
     * @param dni del cliente.
     * @param email Correo electrónico del cliente.
     * @param esVip Estado VIP del cliente.
     * @throws IllegalArgumentException Si los datos proporcionados no son válidos.
     */
    public Cliente(int id, String nombre, String dni, String email, boolean esVip) {
        this.id = id;
        if(validarNombre(nombre)) {
            this.nombre = nombre;
        }
        if(validarDni(dni)) {
            this.dni = dni;
        }
        if(validarEmail(email)) {
            this.email = email;
        }
        this.esVip = esVip;
    }
    /**
     * Verifica si el cliente es VIP.
     * @return `true` si el cliente es VIP, `false` en caso contrario.
     */
    public static boolean validarNombre(String nombre) {
        // Comprobamos que el nombre no sea nulo, esté vacio y tenga al menos 3 caracteres eliminando espacios inciales y finales
        if (nombre == null || nombre.trim().length() < 3) {
            throw new IllegalArgumentException("El nombre no es válido");
        }
        return true;
    }
    /**
     * Valida si un correo electrónico es correcto según el formato estándar.
     * @param email Correo electrónico a validar.
     * @return `true` si el correo es válido.
     * @throws IllegalArgumentException Si el correo no cumple el formato esperado.
     */
    public static boolean validarEmail(String email) {
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            throw new IllegalArgumentException("El email no es válido");
        }
        return true;
    }
    /**
     * Valida si un DNI es correcto según el formato esperado.
     * @param dni DNI a validar.
     * @return `true` si el DNI es válido.
     * @throws IllegalArgumentException Si el DNI no cumple el formato requerido.
     */
    public static boolean validarDni(String dni) {
        if (!dni.matches("[0-9]{8}[A-Z]")) {
            throw new IllegalArgumentException("El DNI no es válido");
        }
        return true;
    }

}
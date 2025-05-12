package org.ed06.model;

import java.util.HashMap;
import java.util.Map;
/**
 * Clase que gestiona los clientes del hotel, permitiendo su registro y consulta.
 */
public class GestorClientes {
    /**
     * Mapa que almacena los clientes registrados, con su ID como clave.
     */
    private static final Map<Integer, Cliente> clientes = new HashMap<>();
    /**
     * Registra un nuevo cliente en el sistema.
     * @param nombre Nombre del cliente.
     * @param email Correo electrónico del cliente.
     * @param dni Documento de identidad del cliente.
     * @param esVip Indica si el cliente tiene estatus VIP.
     */
    public static void registrarCliente(String nombre, String email, String dni, boolean esVip) {
        Cliente cliente = new Cliente(clientes.size() + 1, nombre, dni, email, esVip);
        clientes.put(cliente.id, cliente);
    }
    /**
     * Obtiene un cliente por su identificador único.
     * @param id Identificador del cliente.
     * @return Cliente correspondiente al ID o null si no existe.
     */
    public Cliente obtenerCliente(int id) {
        return clientes.get(id);
    }
    /**
     * Lista todos los clientes registrados mostrando su información en la consola.
     */
    public void listarClientes() {
        clientes.values().forEach(cliente ->
                System.out.println("Cliente #" + cliente.id + " - Nombre: " + cliente.nombre + " - DNI: " + cliente.dni + " - VIP: " + cliente.esVip));
    }
}

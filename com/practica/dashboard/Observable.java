package com.practica.dashboard;

/**
 * Interfaz del rol Sujeto (Subject) del patrón Observador.
 * <p>
 * Define el contrato que deben cumplir las entidades del sistema capaces de
 * emitir notificaciones a observadores registrados cuando se produce un
 * cambio en su estado (cadena de montaje, almacén, planificador, etc.). *
 * @author Shao Capilla Sanz
 */
public interface Observable {

    /**
     * Registra un nuevo observador para recibir notificaciones.
     *
     * @param observador observador a añadir
     */
    void addObservador(Observador observador);

    /**
     * Elimina un observador para que deje de recibir notificaciones.
     *
     * @param observador observador a quitar
     */
    void removeObservador(Observador observador);

    /**
     * Notifica un mensaje a todos los observadores registrados.
     *
     * @param message texto del mensaje a difundir
     */
    void notifyObservadores(String message);

}

package com.practica.dashboard;

/**
 * Interfaz del rol Observador del patrón Observador.
 * <p>
 * Implementada por aquellas entidades que desean ser notificadas cuando un
 * objeto {@link Observable} cambia su estado (por ejemplo, el dashboard
 * recibe avisos de la cadena de montaje y del almacén). *
 * @author Shao Capilla Sanz
 */
public interface Observador {

    /**
     * Método invocado por el observable para notificar un cambio.
     *
     * @param message texto descriptivo del evento ocurrido
     */
    void update(String message);

}

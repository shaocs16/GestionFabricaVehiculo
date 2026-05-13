package com.practica.montaje;

import com.practica.personal.Operario;
import com.practica.vehiculo.Coche;

/**
 * Robot de montaje controlado por un operario en una estación de la cadena.
 * <p>
 * Cada robot está especializado en un componente concreto (chasis, motor,
 * tapicería o ruedas) y trabaja sobre un único coche a la vez. El tiempo que
 * necesita para completar el montaje depende del perfil (eficiente o estándar)
 * del operario que lo controla. *
 * @author Shao Capilla Sanz
 */
public class Robot {
    /** Operario que controla el robot. */
    private Operario operario;
    /** Coche que se está ensamblando actualmente; {@code null} si está libre. */
    private Coche cocheActual;
    /** Segundos de simulación que restan para terminar el montaje en curso. */
    private int tiempoRestante;
    /** Nombre identificativo del robot. */
    private String nombre;

    /**
     * Crea un robot con un nombre identificativo y el operario que lo controla.
     *
     * @param nombre   nombre del robot
     * @param operario operario asignado al robot
     */
    public Robot(String nombre, Operario operario) {
        this.nombre = nombre;
        this.operario = operario;
        this.tiempoRestante = 0;
        this.cocheActual = null;
    }

    /**
     * @return {@code true} si el robot no está trabajando con ningún coche
     */
    public boolean estaLibre() {
        return cocheActual == null;
    }

    /**
     * Asigna un coche al robot para que comience el montaje. El tiempo de
     * trabajo se inicializa al tiempo de montaje del operario asignado.
     *
     * @param c coche a ensamblar
     */
    public void recibirCoche(Coche c) {
        this.cocheActual = c;
        this.tiempoRestante = operario.getTiempoMontaje();
    }

    /**
     * @return coche que el robot está ensamblando actualmente, o {@code null}
     */
    public Coche getCocheActual() {
        return cocheActual;
    }

    /**
     * Avanza un segundo de simulación el trabajo del robot. Si el coche en
     * curso es {@code null} no realiza ninguna acción. Cuando el tiempo
     * restante alcanza cero, registra el montaje completado en el operario y
     * comunica que ha finalizado.
     *
     * @return {@code true} si el montaje ha terminado en este tick;
     *         {@code false} en caso contrario
     */
    public boolean trabajar() {
        if (cocheActual == null) {
            return false;
        }

        tiempoRestante--;
        if (tiempoRestante <= 0) {
            operario.registrarMontajeCompletado();
            return true;
        }
        return false;
    }

    /**
     * Libera al robot del coche actual para que pueda recibir uno nuevo.
     */
    public void liberarCoche() {
        this.cocheActual = null;
    }

    /**
     * @return nombre identificativo del robot
     */
    public String getNombre() {
        return nombre;
    }
}

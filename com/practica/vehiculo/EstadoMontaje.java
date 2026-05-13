package com.practica.vehiculo;

/**
 * Estados por los que pasa un vehículo durante el proceso de ensamblaje en la
 * cadena de montaje.
 * <p>
 * El orden de los valores refleja la secuencia natural del montaje: primero se
 * coloca el chasis, después el motor, a continuación la tapicería y finalmente
 * las ruedas, quedando el vehículo en estado {@link #TERMINADO} una vez
 * completado. *
 * @author Shao Capilla Sanz
 */
public enum EstadoMontaje {
    /** Fase de montaje del chasis. */
    CHASIS,
    /** Fase de instalación del motor. */
    MOTOR,
    /** Fase de instalación de la tapicería. */
    TAPICERIA,
    /** Fase de instalación de las ruedas. */
    RUEDAS,
    /** Vehículo completamente ensamblado. */
    TERMINADO
}

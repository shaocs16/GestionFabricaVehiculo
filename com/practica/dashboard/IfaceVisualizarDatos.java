package com.practica.dashboard;

/**
 * Interfaz que define el subsistema de visualización utilizado por el
 * {@link Dashboard}.
 * <p>
 * Su existencia permite desacoplar el dashboard del mecanismo concreto de
 * presentación, de modo que se pueda cambiar fácilmente entre una salida
 * por consola, una interfaz gráfica, registro en fichero, etc., sin
 * modificar el resto del sistema. *
 * @author Shao Capilla Sanz
 */
public interface IfaceVisualizarDatos {
    /**
     * Muestra el mensaje indicado a través del medio de visualización
     * concreto.
     *
     * @param mensaje texto a mostrar
     */
    void mostrarDatos(String mensaje);
}

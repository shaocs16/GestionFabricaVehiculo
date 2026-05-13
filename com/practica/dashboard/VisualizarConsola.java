package com.practica.dashboard;

/**
 * Implementación de {@link IfaceVisualizarDatos} que muestra los mensajes
 * del dashboard por la consola estándar.
 * <p>
 * Es la implementación por defecto utilizada en la aplicación textual. *
 * @author Shao Capilla Sanz
 */
public class VisualizarConsola implements IfaceVisualizarDatos {
    /**
     * {@inheritDoc}
     * <p>
     * Imprime el mensaje en la consola enmarcado entre separadores para
     * destacarlo del resto de salida del programa.     */
    public void mostrarDatos(String mensaje) {
        System.out.println("-------------------------------------------------");
        System.out.println("[DASHBOARD] " + mensaje);
        System.out.println("-------------------------------------------------");
    }
}

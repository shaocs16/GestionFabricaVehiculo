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
    @Override
    public void mostrarDatos(String mensaje) {
        System.out.println("-------------------------------------------------");
        System.out.println("[NOTIFICACIÓN DASHBOARD]");
        System.out.println("Mensaje: " + mensaje);
        System.out.println("-------------------------------------------------");
    }

    /**
     * {@inheritDoc}
     * <p>
     * Imprime el mensaje en la consola enmarcado entre separadores con
     * la etiqueta {@code [CONFIRMACIÓN DASHBOARD]}.
     */
    @Override
    public void confirmacionDatos(String mensaje) {
        System.out.println("-------------------------------------------------");
        System.out.println("[CONFIRMACIÓN DASHBOARD]");
        System.out.println("Mensaje: " + mensaje);
        System.out.println("-------------------------------------------------");
    }

    /**
     * {@inheritDoc}
     * <p>
     * Imprime el mensaje en la consola enmarcado entre separadores con
     * la etiqueta {@code [ERROR DASHBOARD]}.
     */
    @Override
    public void errorDashboard(String mensaje) {
        System.out.println("-------------------------------------------------");
        System.out.println("[ERROR DASHBOARD]");
        System.out.println("Mensaje: " + mensaje);
        System.out.println("-------------------------------------------------");
    }
}

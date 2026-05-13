package com.practica.dashboard;

/**
 * Cuadro de mandos (dashboard) del sistema de gestión de la fábrica.
 * <p>
 * Permite al gestor de planta consultar el estado en tiempo real de las
 * cadenas de montaje y del almacén. Implementa el patrón Observador
 * registrándose como {@link Observador} sobre las entidades observables
 * (cadena de montaje, almacén, planificador) para recibir notificaciones
 * cada vez que se produce un cambio relevante. * <p>
 * El subsistema concreto de visualización está desacoplado a través de la
 * interfaz {@link IfaceVisualizarDatos}, lo que facilita cambiar la forma
 * de mostrar los datos (consola, gráfica, etc.) sin tocar la lógica. *
 * @author Shao Capilla Sanz
 */
public class Dashboard implements Observador {

    /** Subsistema concreto encargado de visualizar los datos. */
    private IfaceVisualizarDatos visualizador;

    /**
     * Crea un dashboard que delegará la visualización en el subsistema
     * indicado.
     *
     * @param visualizador subsistema de visualización a utilizar
     */
    public Dashboard(IfaceVisualizarDatos visualizador) {
        this.visualizador = visualizador;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Reenvía el mensaje recibido al subsistema de visualización configurado.     */
    @Override
    public void update(String message) {
        visualizador.mostrarDatos(message);
    }

}

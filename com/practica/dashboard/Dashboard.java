package com.practica.dashboard;

public class Dashboard implements Observador {

    private IfaceVisualizarDatos visualizador;

    public Dashboard(IfaceVisualizarDatos visualizador) {
        this.visualizador = visualizador;
    }

    @Override
    public void update(String message) {
        visualizador.mostrarDatos(message);
    }

}
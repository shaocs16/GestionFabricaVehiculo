package com.practica.dashboard;

/**
 * Write a description of class Dashboard here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Dashboard implements Observador {

    private IfaceVisualizarDatos visualizador;

    public Dashboard(IfaceVisualizarDatos visualizador) {
        this.visualizador = visualizador;
    }

    @Override
    public void update(String message) {
        visualizador.mostrarDatos();
    }

}
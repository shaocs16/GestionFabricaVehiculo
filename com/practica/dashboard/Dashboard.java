package com.practica.dashboard;

import com.practica.fabrica.AlmacenDatos;
import com.practica.montaje.CadenaMontaje;

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

    // Constructor opcional para suscribirse automáticamente
    public Dashboard(IfaceVisualizarDatos visualizador, AlmacenDatos almacen, CadenaMontaje cadena) {
        this.visualizador = visualizador;
        almacen.addObservador(this);
        cadena.addObservador(this);
    }

    @Override
    public void update(String message) {
        visualizador.mostrarDatos(message);
    }

}
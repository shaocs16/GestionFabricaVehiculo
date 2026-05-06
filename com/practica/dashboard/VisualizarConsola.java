package com.practica.dashboard;

/**
 * Write a description of class VisualizarConsola here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class VisualizarConsola implements IfaceVisualizarDatos {
    public void mostrarDatos(String mensaje) {
        System.out.println("-------------------------------------------------");
        System.out.println("[DASHBOARD] " + mensaje);
        System.out.println("-------------------------------------------------");
    }

    public void mostrarEstadoCadenaMontaje() {

    }
}
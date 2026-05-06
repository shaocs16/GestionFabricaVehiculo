package com.practica.dashboard;

/**
 * Write a description of class VisualizarConsola here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class VisualizarConsola implements IfaceVisualizarDatos {

    @Override
    public void mostrarDatos(String mensaje) {
        System.out.println("==========================================");
        System.out.println("   [DASHBOARD] - ACTUALIZACIÓN DE ESTADO  ");
        System.out.println("==========================================");
        System.out.println(" -> " + mensaje);
        System.out.println("==========================================");
    }
}
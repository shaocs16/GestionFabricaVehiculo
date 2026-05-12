package com.practica.dashboard;

public class VisualizarConsola implements IfaceVisualizarDatos {
    public void mostrarDatos(String mensaje) {
        System.out.println("-------------------------------------------------");
        System.out.println("[DASHBOARD] " + mensaje);
        System.out.println("-------------------------------------------------");
    }
}
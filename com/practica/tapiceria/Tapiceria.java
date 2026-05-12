package com.practica.tapiceria;

public abstract class Tapiceria {

    private String color;
    private double metrosCuadrados;

public Tapiceria() {

    }

public Tapiceria(String color, double metrosCuadrados) {
        this.color = color;
        this.metrosCuadrados = metrosCuadrados;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(double metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public abstract String tipoTapiceria();

    @Override
    public String toString() {
        return "Tapiceria [color=" + color + ", metrosCuadrados=" + metrosCuadrados + "]";
    }
}
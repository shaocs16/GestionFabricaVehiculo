package com.practica.tapiceria;

public class Cuero extends Tapiceria {

    public Cuero(String color, double metrosCuadrados) {
        super(color, metrosCuadrados);
    }

    @Override
    public String tipoTapiceria() {
        return "Cuero";
    }

    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipoTapiceria();
    }
}
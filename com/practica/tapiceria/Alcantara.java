package com.practica.tapiceria;

public class Alcantara extends Tapiceria {

    public Alcantara(String color, double metrosCuadrados) {
        super(color, metrosCuadrados);
    }

    @Override
    public String tipoTapiceria() {
        return "Alcantara";
    }

    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipoTapiceria();
    }
}
package com.practica.tapiceria;

public class Tela extends Tapiceria {

public Tela(String color, double metrosCuadrados) {
        super(color, metrosCuadrados);
    }

    @Override
    public String tipoTapiceria() {
        return "Tela";
    }

    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipoTapiceria();
    }
}
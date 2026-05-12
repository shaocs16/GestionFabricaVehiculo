package com.practica.rueda;

public class Todoterreno extends Rueda {

public Todoterreno(int ancho, int pulgadasLlanta, int indiceCarga, int codigoVelocidad) {
        super(ancho, pulgadasLlanta, indiceCarga, codigoVelocidad);
    }

    @Override
    public String tipoRueda() {
        return "Todoterreno";
    }

    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipoRueda() + "]";
    }
}
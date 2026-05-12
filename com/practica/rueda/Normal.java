package com.practica.rueda;

public class Normal extends Rueda {

    public Normal(int ancho, int pulgadasLlanta, int indiceCarga, int codigoVelocidad) {
        super(ancho, pulgadasLlanta, indiceCarga, codigoVelocidad);
    }

    @Override
    public String tipoRueda() {
        return "Normal";
    }

    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipoRueda() + "]";
    }
}
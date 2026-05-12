package com.practica.rueda;

public class Deportivo extends Rueda {

public Deportivo(int ancho, int pulgadasLlanta, int indiceCarga, int codigoVelocidad) {
        super(ancho, pulgadasLlanta, indiceCarga, codigoVelocidad);
    }

    @Override
    public String tipoRueda() {
        return "Deportivo";
    }

    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipoRueda() + "]";
    }
}
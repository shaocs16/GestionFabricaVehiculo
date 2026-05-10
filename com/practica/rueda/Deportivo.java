package com.practica.rueda;

/**
 * Write a description of class Deportivo here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Deportivo extends Rueda {
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class Deportivo
     */
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
package com.practica.rueda;

/**
 * Write a description of class Todoterreno here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Todoterreno extends Rueda {
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class Todoterreno
     */
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
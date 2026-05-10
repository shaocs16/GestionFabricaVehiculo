package com.practica.rueda;

/**
 * Write a description of class Normal here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Normal extends Rueda {
    /**
     * Constructor for objects of class Normal
     */
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
package com.practica.tapiceria;

/**
 * Write a description of class Cuero here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cuero extends Tapiceria {
    /**
     * Constructor for objects of class Cuero
     */
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
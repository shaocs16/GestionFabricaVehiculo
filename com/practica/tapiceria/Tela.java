package com.practica.tapiceria;

/**
 * Write a description of class Tela here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tela extends Tapiceria {
    /**
     * Constructor for objects of class Tela
     */
    /** Constructor con parámetros. */
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
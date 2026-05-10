package com.practica.tapiceria;

/**
 * Write a description of class Alcantara here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Alcantara extends Tapiceria {
    /**
     * Constructor for objects of class Alcantara
     */
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
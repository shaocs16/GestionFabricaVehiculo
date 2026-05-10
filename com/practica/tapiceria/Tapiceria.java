package com.practica.tapiceria;


/**
 * Write a description of class Tapiceria here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Tapiceria
{
    // instance variables - replace the example below with your own
    private String color;
    private double metrosCuadrados;

    /**
     * Constructor for objects of class Tapiceria
     */
    public Tapiceria(){

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(double metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }
}
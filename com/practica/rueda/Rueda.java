package com.practica.rueda;


/**
 * Write a description of class Rueda here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Rueda
{
    // instance variables - replace the example below with your own
    private int ancho;
    private int pulgadasLlanta;
    private int indiceCarga;
    private int codigoVelocidad;

    /**
     * Constructor for objects of class Rueda
     */
    public Rueda()
    {
        // initialise instance variables
    }
    
    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getPulgadasLlanta() {
        return pulgadasLlanta;
    }

    public void setPulgadasLlanta(int pulgadasLlanta) {
        this.pulgadasLlanta = pulgadasLlanta;
    }

    public int getIndiceCarga() {
        return indiceCarga;
    }

    public void setIndiceCarga(int indiceCarga) {
        this.indiceCarga = indiceCarga;
    }

    public int getVelocidad() {
        return codigoVelocidad;
    }

    public void setVelocidad(int codigoVelocidad) {
        this.codigoVelocidad = codigoVelocidad;
    }
}
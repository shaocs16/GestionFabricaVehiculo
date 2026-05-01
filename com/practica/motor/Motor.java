package com.practica.motor;


/**
 * Write a description of class Motor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Motor
{
    // instance variables - replace the example below with your own
    private double cilindrada;
    private int potencia;
    private int numeroCilindros;

    /**
     * Constructor for objects of class Motor
     */
    public Motor()
    {
        // initialise instance variables
        
    }
    
    public double getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(double cilindrada) {
        this.cilindrada = cilindrada;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public int getNumeroCilindros() {
        return numeroCilindros;
    }

    public void setNumeroCilindros(int numeroCilindros) {
        this.numeroCilindros = numeroCilindros;
    }
}
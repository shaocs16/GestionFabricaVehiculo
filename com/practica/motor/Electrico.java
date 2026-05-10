package com.practica.motor;

/**
 * Write a description of class Electrico here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Electrico extends Motor {
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class Electrico
     */
    public Electrico(double cilindrada, int potencia, int numeroCilindros) {
        super(cilindrada, potencia, numeroCilindros);
    }

    @Override
    public String tipoMotor() {
        return "Electrico";
    }

    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipoMotor();
    }
}
package com.practica.motor;

/**
 * Write a description of class Hibrido here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Hibrido extends Motor {
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class Hibrido
     */
    public Hibrido(double cilindrada, int potencia, int numeroCilindros) {
        super(cilindrada, potencia, numeroCilindros);
    }

    @Override
    public String tipoMotor() {
        return "Hibrido";
    }

    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipoMotor();
    }
}
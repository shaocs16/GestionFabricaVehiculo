package com.practica.motor;

/**
 * Write a description of class Gasolina here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Gasolina extends Motor {
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class Gasolina
     */
    public Gasolina(double cilindrada, int potencia, int numeroCilindros) {
        super(cilindrada, potencia, numeroCilindros);
    }

    @Override
    public String tipoMotor() {
        return "Gasolina";
    }

    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipoMotor();
    }
}
package com.practica.motor;

public class Hibrido extends Motor {

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
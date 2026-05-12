package com.practica.motor;

public class Electrico extends Motor {

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
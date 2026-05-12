package com.practica.motor;

public class Gasolina extends Motor {

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
package com.practica.motor;

public abstract class Motor {

    private double cilindrada;
    private int potencia;
    private int numeroCilindros;

public Motor() {

}

    public Motor(double cilindrada, int potencia, int numeroCilindros) {
        this.cilindrada = cilindrada;
        this.potencia = potencia;
        this.numeroCilindros = numeroCilindros;
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

    public abstract String tipoMotor();

    @Override
    public String toString() {
        return "Motor [cilindrada=" + cilindrada + ", potencia=" + potencia + ", numeroCilindros=" + numeroCilindros
                + "]";
    }
}
package com.practica.motor;

/**
 * Clase base abstracta que representa un motor de los que se pueden montar
 * en los vehículos de la fábrica.
 * <p>
 * Define las características técnicas comunes a todos los tipos de motor
 * (eléctrico, gasolina e híbrido): cilindrada, potencia y número de cilindros.
 * Las subclases concretan el tipo específico de motor mediante el método
 * {@link #tipoMotor()}. *
 * @author Shao Capilla Sanz
 */
public abstract class Motor {

    /** Cilindrada del motor (cc). */
    private double cilindrada;
    /** Potencia del motor en caballos de vapor (CV). */
    private int potencia;
    /** Número de cilindros del motor. */
    private int numeroCilindros;

    /**
     * Constructor por defecto.
     */
    public Motor() {

    }

    /**
     * Crea un motor con todas sus características técnicas.
     *
     * @param cilindrada      cilindrada en cc
     * @param potencia        potencia en CV
     * @param numeroCilindros número de cilindros
     */
    public Motor(double cilindrada, int potencia, int numeroCilindros) {
        this.cilindrada = cilindrada;
        this.potencia = potencia;
        this.numeroCilindros = numeroCilindros;
    }

    /**
     * @return cilindrada del motor en cc
     */
    public double getCilindrada() {
        return cilindrada;
    }

    /**
     * Establece la cilindrada del motor.
     *
     * @param cilindrada cilindrada en cc
     */
    public void setCilindrada(double cilindrada) {
        this.cilindrada = cilindrada;
    }

    /**
     * @return potencia del motor en CV
     */
    public int getPotencia() {
        return potencia;
    }

    /**
     * Establece la potencia del motor.
     *
     * @param potencia potencia en CV
     */
    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    /**
     * @return número de cilindros del motor
     */
    public int getNumeroCilindros() {
        return numeroCilindros;
    }

    /**
     * Establece el número de cilindros del motor.
     *
     * @param numeroCilindros número de cilindros
     */
    public void setNumeroCilindros(int numeroCilindros) {
        this.numeroCilindros = numeroCilindros;
    }

    /**
     * Devuelve la etiqueta textual con el tipo concreto de motor.
     *
     * @return tipo del motor (p.ej. "Gasolina", "Electrico", "Hibrido")
     */
    public abstract String tipoMotor();

    /**
     * @return representación textual del motor con sus características técnicas
     */
    @Override
    public String toString() {
        return "Motor [cilindrada=" + cilindrada + ", potencia=" + potencia + ", numeroCilindros=" + numeroCilindros
                + "]";
    }
}

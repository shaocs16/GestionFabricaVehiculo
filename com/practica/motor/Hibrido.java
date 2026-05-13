package com.practica.motor;

/**
 * Motor híbrido montable en los vehículos de la fábrica.
 *
 * @author Shao Capilla Sanz
 */
public class Hibrido extends Motor {

    /**
     * Crea un motor híbrido con sus características técnicas.
     *
     * @param cilindrada      cilindrada en cc
     * @param potencia        potencia en CV
     * @param numeroCilindros número de cilindros
     */
    public Hibrido(double cilindrada, int potencia, int numeroCilindros) {
        super(cilindrada, potencia, numeroCilindros);
    }

    /**
     * {@inheritDoc}
     *
     * @return la cadena "Hibrido"
     */
    @Override
    public String tipoMotor() {
        return "Hibrido";
    }

    /**
     * @return representación textual del motor incluyendo su tipo
     */
    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipoMotor();
    }
}

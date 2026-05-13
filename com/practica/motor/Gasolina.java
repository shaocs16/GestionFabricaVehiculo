package com.practica.motor;

/**
 * Motor de gasolina montable en los vehículos de la fábrica.
 *
 * @author Shao Capilla Sanz
 */
public class Gasolina extends Motor {

    /**
     * Crea un motor de gasolina con sus características técnicas.
     *
     * @param cilindrada      cilindrada en cc
     * @param potencia        potencia en CV
     * @param numeroCilindros número de cilindros
     */
    public Gasolina(double cilindrada, int potencia, int numeroCilindros) {
        super(cilindrada, potencia, numeroCilindros);
    }

    /**
     * {@inheritDoc}
     *
     * @return la cadena "Gasolina"
     */
    @Override
    public String tipoMotor() {
        return "Gasolina";
    }

    /**
     * @return representación textual del motor incluyendo su tipo
     */
    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipoMotor();
    }
}

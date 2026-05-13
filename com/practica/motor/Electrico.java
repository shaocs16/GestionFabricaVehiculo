package com.practica.motor;

/**
 * Motor eléctrico montable en los vehículos de la fábrica.
 *
 * @author Shao Capilla Sanz
 */
public class Electrico extends Motor {

    /**
     * Crea un motor eléctrico con sus características técnicas.
     *
     * @param cilindrada      cilindrada en cc
     * @param potencia        potencia en CV
     * @param numeroCilindros número de cilindros
     */
    public Electrico(double cilindrada, int potencia, int numeroCilindros) {
        super(cilindrada, potencia, numeroCilindros);
    }

    /**
     * {@inheritDoc}
     *
     * @return la cadena "Electrico"
     */
    @Override
    public String tipoMotor() {
        return "Electrico";
    }

    /**
     * @return representación textual del motor incluyendo su tipo
     */
    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipoMotor();
    }
}

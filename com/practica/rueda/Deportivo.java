package com.practica.rueda;

/**
 * Rueda de tipo Deportivo, orientada a vehículos de altas prestaciones.
 *
 * @author Shao Capilla Sanz
 */
public class Deportivo extends Rueda {

    /**
     * Crea una rueda deportiva con sus características técnicas.
     *
     * @param ancho           ancho en mm
     * @param pulgadasLlanta  diámetro de llanta en pulgadas
     * @param indiceCarga     índice de carga en kg
     * @param codigoVelocidad código de velocidad en km/h
     */
    public Deportivo(int ancho, int pulgadasLlanta, int indiceCarga, int codigoVelocidad) {
        super(ancho, pulgadasLlanta, indiceCarga, codigoVelocidad);
    }

    /**
     * {@inheritDoc}
     *
     * @return la cadena "Deportivo"
     */
    @Override
    public String tipoRueda() {
        return "Deportivo";
    }

    /**
     * @return representación textual de la rueda incluyendo su tipo
     */
    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipoRueda() + "]";
    }
}

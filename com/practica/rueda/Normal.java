package com.practica.rueda;

/**
 * Rueda de tipo Normal para uso general.
 *
 * @author Shao Capilla Sanz
 */
public class Normal extends Rueda {

    /**
     * Crea una rueda normal con sus características técnicas.
     *
     * @param ancho           ancho en mm
     * @param pulgadasLlanta  diámetro de llanta en pulgadas
     * @param indiceCarga     índice de carga en kg
     * @param codigoVelocidad código de velocidad en km/h
     */
    public Normal(int ancho, int pulgadasLlanta, int indiceCarga, int codigoVelocidad) {
        super(ancho, pulgadasLlanta, indiceCarga, codigoVelocidad);
    }

    /**
     * {@inheritDoc}
     *
     * @return la cadena "Normal"
     */
    @Override
    public String tipoRueda() {
        return "Normal";
    }

    /**
     * @return representación textual de la rueda incluyendo su tipo
     */
    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipoRueda() + "]";
    }
}

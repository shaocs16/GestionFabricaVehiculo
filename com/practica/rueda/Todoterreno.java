package com.practica.rueda;

/**
 * Rueda de tipo Todoterreno, apta para terrenos irregulares.
 *
 * @author Shao Capilla Sanz
 */
public class Todoterreno extends Rueda {

    /**
     * Crea una rueda todoterreno con sus características técnicas.
     *
     * @param ancho           ancho en mm
     * @param pulgadasLlanta  diámetro de llanta en pulgadas
     * @param indiceCarga     índice de carga en kg
     * @param codigoVelocidad código de velocidad en km/h
     */
    public Todoterreno(int ancho, int pulgadasLlanta, int indiceCarga, int codigoVelocidad) {
        super(ancho, pulgadasLlanta, indiceCarga, codigoVelocidad);
    }

    /**
     * {@inheritDoc}
     *
     * @return la cadena "Todoterreno"
     */
    @Override
    public String tipoRueda() {
        return "Todoterreno";
    }

    /**
     * @return representación textual de la rueda incluyendo su tipo
     */
    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipoRueda() + "]";
    }
}

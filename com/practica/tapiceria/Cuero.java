package com.practica.tapiceria;

/**
 * Tapicería de tipo Cuero.
 *
 * @author Shao Capilla Sanz
 */
public class Cuero extends Tapiceria {

    /**
     * Crea una tapicería de cuero.
     *
     * @param color           color de la tapicería
     * @param metrosCuadrados metros cuadrados de tela
     */
    public Cuero(String color, double metrosCuadrados) {
        super(color, metrosCuadrados);
    }

    /**
     * {@inheritDoc}
     *
     * @return la cadena "Cuero"
     */
    @Override
    public String tipoTapiceria() {
        return "Cuero";
    }

    /**
     * @return representación textual incluyendo el tipo de tapicería
     */
    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipoTapiceria();
    }
}

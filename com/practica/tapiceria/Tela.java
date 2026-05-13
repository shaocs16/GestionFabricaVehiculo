package com.practica.tapiceria;

/**
 * Tapicería de tipo Tela.
 *
 * @author Shao Capilla Sanz
 */
public class Tela extends Tapiceria {

    /**
     * Crea una tapicería de tela.
     *
     * @param color           color de la tapicería
     * @param metrosCuadrados metros cuadrados de tela
     */
    public Tela(String color, double metrosCuadrados) {
        super(color, metrosCuadrados);
    }

    /**
     * {@inheritDoc}
     *
     * @return la cadena "Tela"
     */
    @Override
    public String tipoTapiceria() {
        return "Tela";
    }

    /**
     * @return representación textual incluyendo el tipo de tapicería
     */
    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipoTapiceria();
    }
}

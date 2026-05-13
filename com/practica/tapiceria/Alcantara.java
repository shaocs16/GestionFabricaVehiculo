package com.practica.tapiceria;

/**
 * Tapicería de tipo Alcántara.
 *
 * @author Shao Capilla Sanz
 */
public class Alcantara extends Tapiceria {

    /**
     * Crea una tapicería de alcántara.
     *
     * @param color           color de la tapicería
     * @param metrosCuadrados metros cuadrados de tela
     */
    public Alcantara(String color, double metrosCuadrados) {
        super(color, metrosCuadrados);
    }

    /**
     * {@inheritDoc}
     *
     * @return la cadena "Alcantara"
     */
    @Override
    public String tipoTapiceria() {
        return "Alcantara";
    }

    /**
     * @return representación textual incluyendo el tipo de tapicería
     */
    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipoTapiceria();
    }
}

package com.practica.tapiceria;

/**
 * Clase base abstracta que representa la tapicería instalable en el interior
 * de los vehículos de la fábrica.
 * <p>
 * Define las características comunes a todos los tipos de tapicería (tela,
 * cuero y alcántara): color y metros cuadrados de tela. Las subclases
 * concretan el tipo específico mediante {@link #tipoTapiceria()}. *
 * @author Shao Capilla Sanz
 */
public abstract class Tapiceria {

    /** Color de la tapicería. */
    private String color;
    /** Metros cuadrados de tela empleados. */
    private double metrosCuadrados;

    /**
     * Constructor por defecto.
     */
    public Tapiceria() {

    }

    /**
     * Crea una tapicería con sus características.
     *
     * @param color           color de la tapicería
     * @param metrosCuadrados metros cuadrados de tela
     */
    public Tapiceria(String color, double metrosCuadrados) {
        this.color = color;
        this.metrosCuadrados = metrosCuadrados;
    }

    /**
     * @return color de la tapicería
     */
    public String getColor() {
        return color;
    }

    /**
     * Establece el color de la tapicería.
     *
     * @param color nuevo color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return metros cuadrados de tela de la tapicería
     */
    public double getMetrosCuadrados() {
        return metrosCuadrados;
    }

    /**
     * Establece los metros cuadrados de tela.
     *
     * @param metrosCuadrados metros cuadrados
     */
    public void setMetrosCuadrados(double metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    /**
     * Devuelve la etiqueta textual con el tipo concreto de tapicería.
     *
     * @return tipo de tapicería (p.ej. "Tela", "Cuero", "Alcantara")
     */
    public abstract String tipoTapiceria();

    /**
     * @return representación textual de la tapicería con sus características
     */
    @Override
    public String toString() {
        return "Tapiceria [color=" + color + ", metrosCuadrados=" + metrosCuadrados + "]";
    }
}

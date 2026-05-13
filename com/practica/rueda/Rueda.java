package com.practica.rueda;

/**
 * Clase base abstracta que representa una rueda montable en los vehículos
 * de la fábrica.
 * <p>
 * Define las características técnicas comunes a todos los tipos de rueda
 * (normal, deportiva y todoterreno): ancho en mm, diámetro de llanta en
 * pulgadas, índice de carga en kg y código de velocidad. Este último
 * indica la velocidad máxima permitida que el neumático puede soportar
 * con seguridad. *
 * @author Shao Capilla Sanz
 */
public abstract class Rueda {

    /** Ancho del neumático en milímetros. */
    private int ancho;
    /** Diámetro de la llanta en pulgadas. */
    private int pulgadasLlanta;
    /** Índice de carga del neumático en kg. */
    private int indiceCarga;
    /** Código de velocidad (km/h) que indica la velocidad máxima soportada. */
    private int codigoVelocidad;

    /**
     * Constructor por defecto.
     */
    public Rueda() {

    }

    /**
     * Crea una rueda con todas sus características técnicas.
     *
     * @param ancho           ancho en mm
     * @param pulgadasLlanta  diámetro de llanta en pulgadas
     * @param indiceCarga     índice de carga en kg
     * @param codigoVelocidad código de velocidad en km/h
     */
    public Rueda(int ancho, int pulgadasLlanta, int indiceCarga, int codigoVelocidad) {
        this.ancho = ancho;
        this.pulgadasLlanta = pulgadasLlanta;
        this.indiceCarga = indiceCarga;
        this.codigoVelocidad = codigoVelocidad;
    }

    /**
     * @return ancho del neumático en mm
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * Establece el ancho del neumático.
     *
     * @param ancho ancho en mm
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    /**
     * @return diámetro de llanta en pulgadas
     */
    public int getPulgadasLlanta() {
        return pulgadasLlanta;
    }

    /**
     * Establece el diámetro de la llanta.
     *
     * @param pulgadasLlanta diámetro en pulgadas
     */
    public void setPulgadasLlanta(int pulgadasLlanta) {
        this.pulgadasLlanta = pulgadasLlanta;
    }

    /**
     * @return índice de carga del neumático en kg
     */
    public int getIndiceCarga() {
        return indiceCarga;
    }

    /**
     * Establece el índice de carga del neumático.
     *
     * @param indiceCarga índice de carga en kg
     */
    public void setIndiceCarga(int indiceCarga) {
        this.indiceCarga = indiceCarga;
    }

    /**
     * @return código de velocidad del neumático (km/h)
     */
    public int getVelocidad() {
        return codigoVelocidad;
    }

    /**
     * Establece el código de velocidad del neumático.
     *
     * @param codigoVelocidad código de velocidad en km/h
     */
    public void setVelocidad(int codigoVelocidad) {
        this.codigoVelocidad = codigoVelocidad;
    }

    /**
     * Devuelve la etiqueta textual con el tipo concreto de rueda.
     *
     * @return tipo de rueda (p.ej. "Normal", "Deportivo", "Todoterreno")
     */
    public abstract String tipoRueda();

    /**
     * @return representación textual de la rueda con sus características
     */
    @Override
    public String toString() {
        return "Rueda [ancho=" + ancho + ", pulgadasLlanta=" + pulgadasLlanta + ", indiceCarga=" + indiceCarga + ", codigoVelocidad=" + codigoVelocidad + "]";
    }
}

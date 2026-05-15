package com.practica.vehiculo;

import com.practica.tapiceria.*;

import java.util.Arrays;

import com.practica.motor.*;
import com.practica.rueda.*;

/**
 * Clase base abstracta que representa un coche genérico fabricado en la factoría.
 * <p>
 * Encapsula las características comunes a todos los tipos de vehículos del catálogo
 * (Biplaza Deportivo, Turismo y Furgoneta): color, número de plazas, peso máximo
 * autorizado, tara y los componentes principales (motor, tapicería y ruedas).
 * Además, mantiene el estado actual del proceso de montaje y la información de
 * averías para que el planificador y los mecánicos puedan gestionarlas. *
 * @author Shao Capilla Sanz
 */
public abstract class Coche {

    /** Color exterior del vehículo. */
    private String color;
    /** Número de plazas del vehículo. */
    private int plazas;
    /** Peso máximo autorizado (MMA) del vehículo en kg. */
    private double pesoAutorizado;
    /** Tara (peso en vacío) del vehículo en kg. */
    private double taraVehiculo;

    /** Tapicería instalada en el interior del vehículo. */
    private Tapiceria tapiceria;
    /** Motor montado en el vehículo. */
    private Motor motor;
    /** Conjunto de cuatro ruedas instaladas en el vehículo. */
    private Rueda[] rueda = new Rueda[4];

    /** Estado actual del proceso de montaje del vehículo. */
    private EstadoMontaje estado;

    /** Indica si el vehículo está averiado y esperando reparación. */
    private boolean averiado = false;
    /** Tiempo (en segundos de simulación) que resta para finalizar la reparación. */
    private int tiempoReparacion = 0;

    /**
     * Crea un coche con todos sus atributos principales y componentes asignados.
     *
     * @param color          color exterior del vehículo
     * @param plazas         número de plazas
     * @param pesoAutorizado peso máximo autorizado (MMA) en kg
     * @param taraVehiculo   tara (peso en vacío) en kg
     * @param tapiceria      tapicería instalada
     * @param motor          motor instalado
     * @param rueda          array con las cuatro ruedas del vehículo
     */
    public Coche(String color, int plazas, double pesoAutorizado, double taraVehiculo, Tapiceria tapiceria, Motor motor, Rueda[] rueda) {
        this.color = color;
        this.plazas = plazas;
        this.pesoAutorizado = pesoAutorizado;
        this.taraVehiculo = taraVehiculo;
        this.tapiceria = tapiceria;
        this.motor = motor;
        this.rueda = rueda;
    }

    /**
     * @return color exterior del vehículo
     */
    public String getColor() {
        return color;
    }

    /**
     * Establece el color exterior del vehículo.
     *
     * @param color nuevo color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return número de plazas del vehículo
     */
    public int getPlazas() {
        return plazas;
    }

    /**
     * Establece el número de plazas del vehículo.
     *
     * @param plazas número de plazas
     */
    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

    /**
     * @return peso máximo autorizado (MMA) en kg
     */
    public double getPesoAutorizado() {
        return pesoAutorizado;
    }

    /**
     * Establece el peso máximo autorizado del vehículo.
     *
     * @param pesoAutorizado peso máximo en kg
     */
    public void setPesoAutorizado(double pesoAutorizado) {
        this.pesoAutorizado = pesoAutorizado;
    }

    /**
     * @return tara (peso en vacío) del vehículo en kg
     */
    public double getTaraVehiculo() {
        return taraVehiculo;
    }

    /**
     * Establece la tara del vehículo.
     *
     * @param taraVehiculo tara en kg
     */
    public void setTaraVehiculo(double taraVehiculo) {
        this.taraVehiculo = taraVehiculo;
    }

    /**
     * @return tapicería instalada en el vehículo
     */
    public Tapiceria getTapiceria() {
        return tapiceria;
    }

    /**
     * Asigna una tapicería al vehículo.
     *
     * @param tapiceria tapicería a instalar
     */
    public void setTapiceria(Tapiceria tapiceria) {
        this.tapiceria = tapiceria;
    }

    /**
     * @return array de ruedas instaladas en el vehículo
     */
    public Rueda[] getRueda() {
        return rueda;
    }

    /**
     * Asigna el conjunto de ruedas al vehículo.
     *
     * @param rueda array de ruedas a instalar
     */
    public void setRueda(Rueda[] rueda) {
        this.rueda = rueda;
    }

    /**
     * @return motor instalado en el vehículo
     */
    public Motor getMotor() {
        return motor;
    }

    /**
     * Asigna un motor al vehículo.
     *
     * @param motor motor a instalar
     */
    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    /**
     * @return estado actual del proceso de montaje
     * @see EstadoMontaje
     */
    public EstadoMontaje getEstadoMontaje() {
        return estado;
    }

    /**
     * Establece el estado del proceso de montaje del vehículo.
     *
     * @param estado nuevo estado de montaje
     */
    public void setEstadoMontaje(EstadoMontaje estado) {
        this.estado = estado;
    }

    /**
     * @return {@code true} si el coche está averiado y necesita reparación
     */
    public boolean isAveriado() {
        return averiado;
    }

    /**
     * Marca o limpia el estado de avería del vehículo.
     *
     * @param averiado {@code true} si está averiado
     */
    public void setAveriado(boolean averiado) {
        this.averiado = averiado;
    }

    /**
     * @return tiempo de reparación restante (en segundos de simulación)
     */
    public int getTiempoReparacion() {
        return tiempoReparacion;
    }

    /**
     * Establece el tiempo de reparación restante.
     *
     * @param tiempoReparacion tiempo en segundos
     */
    public void setTiempoReparacion(int tiempoReparacion) {
        this.tiempoReparacion = tiempoReparacion;
    }

    /**
     * Devuelve la etiqueta textual con el tipo concreto de coche.
     * Cada subclase devuelve su propia denominación.
     *
     * @return tipo del coche (p.ej. "Turismo", "Furgoneta", "Biplaza Deportivo")
     */
    public abstract String tipoCoche();

    /**
     * Representación textual del coche incluyendo todos sus atributos y componentes.
     *
     * @return cadena descriptiva del estado del coche
     */
    @Override
    public String toString() {
        return "Coche [color=" + color + ", plazas=" + plazas + ", pesoAutorizado=" + pesoAutorizado + ", taraVehiculo=" + taraVehiculo + ", tapiceria=" + tapiceria + ", motor=" + motor + ", rueda=" + Arrays.toString(rueda) + "]";
    }
}

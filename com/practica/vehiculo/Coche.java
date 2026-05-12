package com.practica.vehiculo;

import com.practica.tapiceria.*;

import java.util.Arrays;

import com.practica.motor.*;
import com.practica.rueda.*;

public abstract class Coche {

    private String color;
    private int plazas;
    private double pesoAutorizado;
    private double taraVehiculo;

    private Tapiceria tapiceria;
    private Motor motor;
    private Rueda[] rueda = new Rueda[4];

    private EstadoMontaje estado;

    private boolean averiado = false;
    private int tiempoReparacion = 0;

public Coche() {

    }

    public Coche(String color, int plazas, double pesoAutorizado, double taraVehiculo,
            Tapiceria tapiceria, Motor motor, Rueda[] rueda) {
        this.color = color;
        this.plazas = plazas;
        this.pesoAutorizado = pesoAutorizado;
        this.taraVehiculo = taraVehiculo;
        this.tapiceria = tapiceria;
        this.motor = motor;
        this.rueda = rueda;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

    public double getPesoAutorizado() {
        return pesoAutorizado;
    }

    public void setPesoAutorizado(double pesoAutorizado) {
        this.pesoAutorizado = pesoAutorizado;
    }

    public double getTaraVehiculo() {
        return taraVehiculo;
    }

    public void setTaraVehiculo(double taraVehiculo) {
        this.taraVehiculo = taraVehiculo;
    }

    public Tapiceria getTapiceria() {
        return tapiceria;
    }

    public void setTapiceria(Tapiceria tapiceria) {
        this.tapiceria = tapiceria;
    }

    public Rueda[] getRueda() {
        return rueda;
    }

    public void setRueda(Rueda[] rueda) {
        this.rueda = rueda;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public EstadoMontaje getEstadoMontaje() {
        return estado;
    }

    public void setEstadoMontaje(EstadoMontaje estado) {
        this.estado = estado;
    }

    public boolean isAveriado() {
        return averiado;
    }

    public void setAveriado(boolean averiado) {
        this.averiado = averiado;
    }

    public int getTiempoReparacion() {
        return tiempoReparacion;
    }

    public void setTiempoReparacion(int tiempoReparacion) {
        this.tiempoReparacion = tiempoReparacion;
    }

    public abstract String tipoCoche();

    @Override
    public String toString() {
        return "Coche [color=" + color + ", plazas=" + plazas + ", pesoAutorizado=" + pesoAutorizado + ", taraVehiculo="
                + taraVehiculo + ", tapiceria=" + tapiceria + ", motor=" + motor + ", rueda=" + Arrays.toString(rueda)
                + "]";
    }
}
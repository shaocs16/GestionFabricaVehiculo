package com.practica.vehiculo;

import com.practica.tapiceria.*;
import com.practica.motor.*;
import com.practica.rueda.*;

/**
 * Write a description of class Coche here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Coche
{
    // instance variables - replace the example below with your own
    private String color;
    private int plazas;
    private double pesoAutorizado;
    private double taraVehiculo;
    
    private Tapiceria tapiceria;
    private Motor motor;
    private Rueda[] rueda;

    /**
     * Constructor for objects of class Coche
     */
    public Coche(){
        
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
}
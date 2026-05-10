package com.practica.vehiculo;

import com.practica.motor.Motor;
import com.practica.rueda.Rueda;
import com.practica.tapiceria.Tapiceria;

/**
 * Write a description of class Turismo here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Turismo extends Coche {
    // Constructor
    public Turismo(String color, int plazas, double pesoAutorizado, double taraVehiculo,
            Tapiceria tapiceria, Motor motor, Rueda[] rueda) {
        super(color, plazas, pesoAutorizado, taraVehiculo, tapiceria, motor, rueda);
    }

    @Override
    public String tipoCoche() {
        return "Turismo";
    }

    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipoCoche();
    }
}
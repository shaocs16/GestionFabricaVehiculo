package com.practica.vehiculo;

import com.practica.motor.Motor;
import com.practica.rueda.Rueda;
import com.practica.tapiceria.Tapiceria;

/**
 * Write a description of class BiplazaDeportivo here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class BiplazaDeportivo extends Coche {
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class BiplazaDeportivo
     */
    public BiplazaDeportivo(String color, int plazas, double pesoAutorizado, double taraVehiculo,
            Tapiceria tapiceria, Motor motor, Rueda[] rueda) {
        super(color, plazas, pesoAutorizado, taraVehiculo, tapiceria, motor, rueda);
    }

    @Override
    public String tipoCoche() {
        return "Biplaza Deportivo";
    }

    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipoCoche();
    }
}
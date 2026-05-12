package com.practica.vehiculo;

import com.practica.motor.Motor;
import com.practica.rueda.Rueda;
import com.practica.tapiceria.Tapiceria;

public class BiplazaDeportivo extends Coche {

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
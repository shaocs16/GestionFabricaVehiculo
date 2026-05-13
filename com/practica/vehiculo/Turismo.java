package com.practica.vehiculo;

import com.practica.motor.Motor;
import com.practica.rueda.Rueda;
import com.practica.tapiceria.Tapiceria;

/**
 * Vehículo de tipo Turismo.
 * <p>
 * Subclase concreta de {@link Coche} que representa los turismos del catálogo
 * de la fábrica. Constituye uno de los tres tipos de vehículos ensamblados en
 * las cadenas de montaje. *
 * @author Shao Capilla Sanz
 */
public class Turismo extends Coche {

    /**
     * Crea un Turismo con todos sus atributos y componentes.
     *
     * @param color          color exterior del vehículo
     * @param plazas         número de plazas (típicamente 5)
     * @param pesoAutorizado peso máximo autorizado en kg
     * @param taraVehiculo   tara del vehículo en kg
     * @param tapiceria      tapicería instalada
     * @param motor          motor instalado
     * @param rueda          conjunto de ruedas instaladas
     */
    public Turismo(String color, int plazas, double pesoAutorizado, double taraVehiculo,
            Tapiceria tapiceria, Motor motor, Rueda[] rueda) {
        super(color, plazas, pesoAutorizado, taraVehiculo, tapiceria, motor, rueda);
    }

    /**
     * {@inheritDoc}
     *
     * @return la cadena "Turismo"
     */
    @Override
    public String tipoCoche() {
        return "Turismo";
    }

    /**
     * @return representación textual del coche incluyendo su tipo
     */
    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipoCoche();
    }
}

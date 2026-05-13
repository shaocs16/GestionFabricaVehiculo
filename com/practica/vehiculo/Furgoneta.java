package com.practica.vehiculo;

import com.practica.motor.Motor;
import com.practica.rueda.Rueda;
import com.practica.tapiceria.Tapiceria;

/**
 * Vehículo de tipo Furgoneta.
 * <p>
 * Subclase concreta de {@link Coche} que representa las furgonetas del catálogo
 * de la fábrica. Es uno de los tres tipos de vehículos ensamblados en las
 * cadenas de montaje de la factoría. *
 * @author Shao Capilla Sanz
 */
public class Furgoneta extends Coche {

    /**
     * Crea una Furgoneta con todos sus atributos y componentes.
     *
     * @param color          color exterior del vehículo
     * @param plazas         número de plazas
     * @param pesoAutorizado peso máximo autorizado en kg
     * @param taraVehiculo   tara del vehículo en kg
     * @param tapiceria      tapicería instalada
     * @param motor          motor instalado
     * @param rueda          conjunto de ruedas instaladas
     */
    public Furgoneta(String color, int plazas, double pesoAutorizado, double taraVehiculo, Tapiceria tapiceria, Motor motor, Rueda[] rueda) {
        super(color, plazas, pesoAutorizado, taraVehiculo, tapiceria, motor, rueda);
    }

    /**
     * {@inheritDoc}
     *
     * @return la cadena "Furgoneta"
     */
    @Override
    public String tipoCoche() {
        return "Furgoneta";
    }

    /**
     * @return representación textual del coche incluyendo su tipo
     */
    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipoCoche();
    }
}

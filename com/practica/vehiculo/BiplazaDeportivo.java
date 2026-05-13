package com.practica.vehiculo;

import com.practica.motor.Motor;
import com.practica.rueda.Rueda;
import com.practica.tapiceria.Tapiceria;

/**
 * Vehículo de tipo Biplaza Deportivo.
 * <p>
 * Subclase concreta de {@link Coche} que representa los coches deportivos de
 * dos plazas del catálogo de la fábrica. Hereda toda la funcionalidad de la
 * clase base y únicamente sobreescribe la identificación de tipo. *
 * @author Shao Capilla Sanz
 */
public class BiplazaDeportivo extends Coche {

    /**
     * Crea un Biplaza Deportivo con todos sus atributos y componentes.
     *
     * @param color          color exterior del vehículo
     * @param plazas         número de plazas (típicamente 2)
     * @param pesoAutorizado peso máximo autorizado en kg
     * @param taraVehiculo   tara del vehículo en kg
     * @param tapiceria      tapicería instalada
     * @param motor          motor instalado
     * @param rueda          conjunto de ruedas instaladas
     */
    public BiplazaDeportivo(String color, int plazas, double pesoAutorizado, double taraVehiculo,
            Tapiceria tapiceria, Motor motor, Rueda[] rueda) {
        super(color, plazas, pesoAutorizado, taraVehiculo, tapiceria, motor, rueda);
    }

    /**
     * {@inheritDoc}
     *
     * @return la cadena "Biplaza Deportivo"
     */
    @Override
    public String tipoCoche() {
        return "Biplaza Deportivo";
    }

    /**
     * @return representación textual del coche incluyendo su tipo
     */
    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipoCoche();
    }
}

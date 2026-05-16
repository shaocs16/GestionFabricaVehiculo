package com.practica.montaje;

import com.practica.vehiculo.*;
import com.practica.dashboard.*;
import java.util.ArrayList;

/**
 * Cadena de montaje de la fábrica de vehículos.
 * <p>
 * Aglutina las tres líneas de producción de la factoría, una por cada tipo de
 * vehículo del catálogo (Biplaza Deportivo, Turismo y Furgoneta), y mantiene
 * la cola de unidades pendientes o en curso para cada línea. * <p>
 * Implementa el patrón Observador como sujeto observable, de modo que cada
 * cambio relevante (incorporación de un vehículo, cambio de estado, etc.) se
 * notifica a los observadores registrados (típicamente el dashboard). *
 * @author Shao Capilla Sanz
 */
public class CadenaMontaje implements Observable {

    /** Cola de Biplazas Deportivos en montaje. */
    private ArrayList<BiplazaDeportivo> cadenaBiplaza;
    /** Cola de Turismos en montaje. */
    private ArrayList<Turismo> cadenaTurismo;
    /** Cola de Furgonetas en montaje. */
    private ArrayList<Furgoneta> cadenaFurgoneta;
    /** Lista de observadores registrados para recibir notificaciones. */
    private ArrayList<Observador> observadores;

    /**
     * Construye una cadena de montaje vacía, con las tres líneas y la lista
     * de observadores inicializadas.
     */
    public CadenaMontaje() {

        cadenaBiplaza = new ArrayList<BiplazaDeportivo>();
        cadenaTurismo = new ArrayList<Turismo>();
        cadenaFurgoneta = new ArrayList<Furgoneta>();
        observadores = new ArrayList<Observador>();
    }

    /**
     * Añade un Biplaza Deportivo a su línea de montaje y notifica a los
     * observadores.
     *
     * @param biplaza vehículo a encolar
     */
    public void agregarBiplaza(BiplazaDeportivo biplaza) {
        cadenaBiplaza.add(biplaza);
        notifyObservadores("Se ha agregado un Biplaza Deportivo a la cadena de montaje.");
    }

    /**
     * Añade un Turismo a su línea de montaje y notifica a los observadores.
     *
     * @param turismo vehículo a encolar
     */
    public void agregarTurismo(Turismo turismo) {
        cadenaTurismo.add(turismo);
        notifyObservadores("Se ha agregado un Turismo a la cadena de montaje.");
    }

    /**
     * Añade una Furgoneta a su línea de montaje y notifica a los observadores.
     *
     * @param furgoneta vehículo a encolar
     */
    public void agregarFurgoneta(Furgoneta furgoneta) {
        cadenaFurgoneta.add(furgoneta);
        notifyObservadores("Se ha agregado una Furgoneta a la cadena de montaje.");
    }

    /**
     * @return lista de Biplazas Deportivos en la cadena
     */
    public ArrayList<BiplazaDeportivo> getCadenaBiplaza() {
        return cadenaBiplaza;
    }

    /**
     * @return lista de Turismos en la cadena
     */
    public ArrayList<Turismo> getCadenaTurismo() {
        return cadenaTurismo;
    }

    /**
     * @return lista de Furgonetas en la cadena
     */
    public ArrayList<Furgoneta> getCadenaFurgoneta() {
        return cadenaFurgoneta;
    }

    /**
     * Elimina de las tres líneas todos los vehículos que ya se encuentran en
     * estado {@link EstadoMontaje#TERMINADO}, dejando únicamente los que
     * siguen pendientes de ensamblar.
     *
     * @return número total de vehículos purgados
     */
    public int purgarTerminados() {
        int antesB = cadenaBiplaza.size();
        int antesT = cadenaTurismo.size();
        int antesF = cadenaFurgoneta.size();
        cadenaBiplaza.removeIf(c -> c.getEstadoMontaje() == EstadoMontaje.TERMINADO);
        cadenaTurismo.removeIf(c -> c.getEstadoMontaje() == EstadoMontaje.TERMINADO);
        cadenaFurgoneta.removeIf(c -> c.getEstadoMontaje() == EstadoMontaje.TERMINADO);
        return (antesB - cadenaBiplaza.size()) + (antesT - cadenaTurismo.size()) + (antesF - cadenaFurgoneta.size());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObservador(Observador observador) {
        observadores.add(observador);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeObservador(Observador observador) {
        observadores.remove(observador);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyObservadores(String message) {
        for (Observador ob : observadores) {
            ob.update(message);
        }
    }
}

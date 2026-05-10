package com.practica.montaje;

import com.practica.vehiculo.*;
import com.practica.dashboard.*;
import java.util.ArrayList;

/**
 * Write a description of class CadenaMontaje here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class CadenaMontaje implements Observable {
    // instance variables - replace the example below with your own
    private ArrayList<BiplazaDeportivo> cadenaBiplaza;
    private ArrayList<Turismo> cadenaTurismo;
    private ArrayList<Furgoneta> cadenaFurgoneta;
    private ArrayList<Observador> observadores;

    /**
     * Constructor for objects of class CadenaMontaje
     */
    public CadenaMontaje() {
        // initialise instance variables
        cadenaBiplaza = new ArrayList<BiplazaDeportivo>();
        cadenaTurismo = new ArrayList<Turismo>();
        cadenaFurgoneta = new ArrayList<Furgoneta>();
        observadores = new ArrayList<Observador>();
    }

    public void agregarBiplaza(BiplazaDeportivo biplaza) {
        cadenaBiplaza.add(biplaza);
        notifyObservadores("Se ha agregado un Biplaza Deportivo a la cadena de montaje.");
    }

    public void agregarTurismo(Turismo turismo) {
        cadenaTurismo.add(turismo);
        notifyObservadores("Se ha agregado un Turismo a la cadena de montaje.");
    }

    public void agregarFurgoneta(Furgoneta furgoneta) {
        cadenaFurgoneta.add(furgoneta);
        notifyObservadores("Se ha agregado una Furgoneta a la cadena de montaje.");
    }

    @Override
    public void addObservador(Observador observador) {
        observadores.add(observador);
    }

    @Override
    public void removeObservador(Observador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notifyObservadores(String message) {
        for (Observador ob : observadores) {
            ob.update(message);
        }
    }
}
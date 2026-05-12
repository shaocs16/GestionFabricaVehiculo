package com.practica.montaje;

import com.practica.vehiculo.*;
import com.practica.dashboard.*;
import java.util.ArrayList;

public class CadenaMontaje implements Observable {
    private ArrayList<BiplazaDeportivo> cadenaBiplaza;
    private ArrayList<Turismo> cadenaTurismo;
    private ArrayList<Furgoneta> cadenaFurgoneta;
    private ArrayList<Observador> observadores;

public CadenaMontaje() {

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

    public ArrayList<BiplazaDeportivo> getCadenaBiplaza() {
        return cadenaBiplaza;
    }

    public ArrayList<Turismo> getCadenaTurismo() {
        return cadenaTurismo;
    }

    public ArrayList<Furgoneta> getCadenaFurgoneta() {
        return cadenaFurgoneta;
    }

    public void limpiar() {
        cadenaBiplaza.clear();
        cadenaTurismo.clear();
        cadenaFurgoneta.clear();
    }

public int purgarTerminados() {
        int antesB = cadenaBiplaza.size();
        int antesT = cadenaTurismo.size();
        int antesF = cadenaFurgoneta.size();
        cadenaBiplaza.removeIf(c -> c.getEstadoMontaje() == EstadoMontaje.TERMINADO);
        cadenaTurismo.removeIf(c -> c.getEstadoMontaje() == EstadoMontaje.TERMINADO);
        cadenaFurgoneta.removeIf(c -> c.getEstadoMontaje() == EstadoMontaje.TERMINADO);
        return (antesB - cadenaBiplaza.size())
             + (antesT - cadenaTurismo.size())
             + (antesF - cadenaFurgoneta.size());
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
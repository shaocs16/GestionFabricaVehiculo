package com.practica.dashboard;

public interface Observable {

    void addObservador(Observador observador);

    void removeObservador(Observador observador);

    void notifyObservadores(String message);

}
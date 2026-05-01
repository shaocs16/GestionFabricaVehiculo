package com.practica.dashboard;

/**
 * Write a description of interface Observable here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */

public interface Observable {

    void addObservador(Observador observador);

    void removeObservador(Observador observador);

    void notifyObservadores(String message);

}
package com.practica.fabrica;

import com.practica.vehiculo.*;
import com.practica.rueda.*;
import com.practica.motor.*;
import com.practica.tapiceria.*;
import com.practica.personal.*;
import com.practica.dashboard.Observable;
import com.practica.dashboard.Observador;

import java.util.*;

/**
 * Write a description of class Alamcen here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class AlmacenDatos implements IfaceAlmacen, Observable {
    // Vehiculo
    private ArrayList<BiplazaDeportivo> biplazasDeportivos;
    private ArrayList<Turismo> turismos;
    private ArrayList<Furgoneta> furgonetas;

    // Rueda
    private ArrayList<Rueda> ruedas;

    // Motor
    private ArrayList<Motor> motores;

    // Tapiceria
    private ArrayList<Tapiceria> tapicerias;

    // Personal
    private ArrayList<AdministradorSistema> administradoresSistema;
    private ArrayList<GestorPlanta> gestoresPlanta;
    private ArrayList<Mecanico> mecanicos;
    private ArrayList<Operario> operarios;

    private ArrayList<Observador> observadores;

    /**
     * Constructor for objects of class Alamcen
     */
    public AlmacenDatos() {
        // initialise instance variables
        biplazasDeportivos = new ArrayList<>();
        turismos = new ArrayList<>();
        furgonetas = new ArrayList<>();
        ruedas = new ArrayList<>();
        motores = new ArrayList<>();
        administradoresSistema = new ArrayList<>();
        gestoresPlanta = new ArrayList<>();
        mecanicos = new ArrayList<>();
        operarios = new ArrayList<>();
        observadores = new ArrayList<>();
    }

    @Override
    public void agregarBiplazaDeportivo(BiplazaDeportivo biplazaDeportivo) {
        biplazasDeportivos.add(biplazaDeportivo);
    }

    @Override
    public List<BiplazaDeportivo> getBiplazasDeportivos() {
        return biplazasDeportivos;
    }

    @Override
    public void agregarFurgoneta(Furgoneta furgoneta) {
        furgonetas.add(furgoneta);
    }

    @Override
    public List<Furgoneta> getFurgonetas() {
        return furgonetas;
    }

    @Override
    public void agregarTurismo(Turismo turismo) {
        turismos.add(turismo);
    }

    @Override
    public List<Turismo> getTurismos() {
        return turismos;
    }

    @Override
    public void agregarMotor(Motor motor) {
        motores.add(motor);
    }

    @Override
    public List<Motor> getMotores() {
        return motores;
    }

    @Override
    public void agregarTapiceria(Tapiceria tapiceria) {
        tapicerias.add(tapiceria);
    }

    @Override
    public List<Tapiceria> getTapicerias() {
        return tapicerias;
    }

    @Override
    public void agregarRueda(Rueda rueda) {
        ruedas.add(rueda);
    }

    @Override
    public List<Rueda> getRuedas() {
        return ruedas;
    }

    @Override
    public void agregarAdministradorSistema(AdministradorSistema administradorSistema) {
        administradoresSistema.add(administradorSistema);
    }

    @Override
    public List<AdministradorSistema> getAdministradoresSistema() {
        return administradoresSistema;
    }

    @Override
    public void agregarGestorPlanta(GestorPlanta gestorPlanta) {
        gestoresPlanta.add(gestorPlanta);
    }

    @Override
    public List<GestorPlanta> getGestoresPlanta() {
        return gestoresPlanta;
    }

    @Override
    public void agregarMecanico(Mecanico mecanico) {
        mecanicos.add(mecanico);
    }

    @Override
    public List<Mecanico> getMecanicos() {
        return mecanicos;
    }

    @Override
    public void agregarOperario(Operario operario) {
        operarios.add(operario);
    }

    @Override
    public List<Operario> getOperarios() {
        return operarios;
    }

    @Override
    public void vaciar() {
        biplazasDeportivos.clear();
        turismos.clear();
        furgonetas.clear();
        ruedas.clear();
        motores.clear();
        tapicerias.clear();
        administradoresSistema.clear();
        gestoresPlanta.clear();
        mecanicos.clear();
        operarios.clear();
    }

    @Override
    public int getTotalCoches() {
        return biplazasDeportivos.size() + turismos.size() + furgonetas.size();
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
        for (Observador observador : observadores) {
            observador.update(message);
        }
    }
}
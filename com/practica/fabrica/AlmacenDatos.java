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
    
    // Historial
    private ArrayList<RegistroMontaje> historial;

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
        tapicerias = new ArrayList<>();
        administradoresSistema = new ArrayList<>();
        gestoresPlanta = new ArrayList<>();
        mecanicos = new ArrayList<>();
        operarios = new ArrayList<>();
        observadores = new ArrayList<>();
        historial = new ArrayList<>();
    }

    @Override
    public void agregarBiplazaDeportivo(BiplazaDeportivo biplazaDeportivo) {
        biplazasDeportivos.add(biplazaDeportivo);
        registrarOperacion(new RegistroMontaje("BiplazaDeportivo", "Añadido al almacén"));
        notifyObservadores("Nuevo BiplazaDeportivo en almacén");
    }

    @Override
    public List<BiplazaDeportivo> getBiplazasDeportivos() {
        return biplazasDeportivos;
    }

    @Override
    public void agregarFurgoneta(Furgoneta furgoneta) {
        furgonetas.add(furgoneta);
        registrarOperacion(new RegistroMontaje("Furgoneta", "Añadida al almacén"));
        notifyObservadores("Nueva Furgoneta en almacén");
    }

    @Override
    public List<Furgoneta> getFurgonetas() {
        return furgonetas;
    }

    @Override
    public void agregarTurismo(Turismo turismo) {
        turismos.add(turismo);
        registrarOperacion(new RegistroMontaje("Turismo", "Añadido al almacén"));
        notifyObservadores("Nuevo Turismo en almacén");
    }

    @Override
    public List<Turismo> getTurismos() {
        return turismos;
    }

    @Override
    public void agregarMotor(Motor motor) {
        motores.add(motor);
        registrarOperacion(new RegistroMontaje("Motor", "Añadido al almacén"));
        notifyObservadores("Nuevo Motor en almacén");
    }

    @Override
    public List<Motor> getMotores() {
        return motores;
    }

    @Override
    public void agregarTapiceria(Tapiceria tapiceria) {
        tapicerias.add(tapiceria);
        registrarOperacion(new RegistroMontaje("Tapiceria", "Añadida al almacén"));
        notifyObservadores("Nueva Tapiceria en almacén");
    }

    @Override
    public List<Tapiceria> getTapicerias() {
        return tapicerias;
    }

    @Override
    public void agregarRueda(Rueda rueda) {
        ruedas.add(rueda);
        registrarOperacion(new RegistroMontaje("Rueda", "Añadida al almacén"));
        notifyObservadores("Nueva Rueda en almacén");
    }

    @Override
    public List<Rueda> getRuedas() {
        return ruedas;
    }

    @Override
    public void agregarAdministradorSistema(AdministradorSistema administradorSistema) {
        administradoresSistema.add(administradorSistema);
        registrarOperacion(new RegistroMontaje("AdministradorSistema", "Registrado en sistema"));
        notifyObservadores("Nuevo Administrador de Sistema");
    }

    @Override
    public List<AdministradorSistema> getAdministradoresSistema() {
        return administradoresSistema;
    }

    @Override
    public void agregarGestorPlanta(GestorPlanta gestorPlanta) {
        gestoresPlanta.add(gestorPlanta);
        registrarOperacion(new RegistroMontaje("GestorPlanta", "Registrado en sistema"));
        notifyObservadores("Nuevo Gestor de Planta");
    }

    @Override
    public List<GestorPlanta> getGestoresPlanta() {
        return gestoresPlanta;
    }

    @Override
    public void agregarMecanico(Mecanico mecanico) {
        mecanicos.add(mecanico);
        registrarOperacion(new RegistroMontaje("Mecanico", "Registrado en sistema"));
        notifyObservadores("Nuevo Mecanico");
    }

    @Override
    public List<Mecanico> getMecanicos() {
        return mecanicos;
    }

    @Override
    public void agregarOperario(Operario operario) {
        operarios.add(operario);
        registrarOperacion(new RegistroMontaje("Operario", "Registrado en sistema"));
        notifyObservadores("Nuevo Operario");
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
        historial.clear();
        notifyObservadores("Almacén vaciado");
    }

    @Override
    public int getTotalCoches() {
        return biplazasDeportivos.size() + turismos.size() + furgonetas.size();
    }

    @Override
    public void registrarOperacion(RegistroMontaje registro) {
        historial.add(registro);
    }

    @Override
    public List<RegistroMontaje> getRegistrosPorFecha(Date fecha) {
        List<RegistroMontaje> filtrado = new ArrayList<>();
        // Comparación simple por fecha (en un caso real podría comparar solo día/mes/año)
        for (RegistroMontaje r : historial) {
            // Usamos una simplificación: si es el mismo día
            if (r.getFecha().getYear() == fecha.getYear() &&
                r.getFecha().getMonth() == fecha.getMonth() &&
                r.getFecha().getDate() == fecha.getDate()) {
                filtrado.add(r);
            }
        }
        return filtrado;
    }

    @Override
    public List<RegistroMontaje> getTodosRegistros() {
        return historial;
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
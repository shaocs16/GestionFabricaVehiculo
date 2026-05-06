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
    private List<RegistroMontaje> historial;

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
        registrarOperacion(new RegistroMontaje(new Date(), "Biplaza Deportivo", "Añadido al almacén"));
        notifyObservadores("Nuevo componente en almacén: Biplaza Deportivo");
    }

    @Override
    public List<BiplazaDeportivo> getBiplazasDeportivos() {
        return biplazasDeportivos;
    }

    @Override
    public void agregarFurgoneta(Furgoneta furgoneta) {
        furgonetas.add(furgoneta);
        registrarOperacion(new RegistroMontaje(new Date(), "Furgoneta", "Añadida al almacén"));
        notifyObservadores("Nuevo componente en almacén: Furgoneta");
    }

    @Override
    public List<Furgoneta> getFurgonetas() {
        return furgonetas;
    }

    @Override
    public void agregarTurismo(Turismo turismo) {
        turismos.add(turismo);
        registrarOperacion(new RegistroMontaje(new Date(), "Turismo", "Añadido al almacén"));
        notifyObservadores("Nuevo componente en almacén: Turismo");
    }

    @Override
    public List<Turismo> getTurismos() {
        return turismos;
    }

    @Override
    public void agregarMotor(Motor motor) {
        motores.add(motor);
        registrarOperacion(new RegistroMontaje(new Date(), "Motor", "Añadido al almacén"));
        notifyObservadores("Nuevo componente en almacén: Motor");
    }

    @Override
    public List<Motor> getMotores() {
        return motores;
    }

    @Override
    public void agregarTapiceria(Tapiceria tapiceria) {
        tapicerias.add(tapiceria);
        registrarOperacion(new RegistroMontaje(new Date(), "Tapiceria", "Añadida al almacén"));
        notifyObservadores("Nuevo componente en almacén: Tapiceria");
    }

    @Override
    public List<Tapiceria> getTapicerias() {
        return tapicerias;
    }

    @Override
    public void agregarRueda(Rueda rueda) {
        ruedas.add(rueda);
        registrarOperacion(new RegistroMontaje(new Date(), "Rueda", "Añadida al almacén"));
        notifyObservadores("Nuevo componente en almacén: Rueda");
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
    
    @Override
    public List<RegistroMontaje> getRegistrosPorFecha(Date fecha) {
        List<RegistroMontaje> resultado = new ArrayList<>();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(fecha);
        
        for (RegistroMontaje registro : historial) {
            cal2.setTime(registro.getFecha());
            if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)) {
                resultado.add(registro);
            }
        }
        return resultado;
    }

    @Override
    public void registrarOperacion(RegistroMontaje registro) {
        historial.add(registro);
    }
}
package com.practica.fabrica;

import com.practica.vehiculo.*;
import com.practica.rueda.*;
import com.practica.motor.*;
import com.practica.tapiceria.*;
import com.practica.personal.*;
import java.util.*;

/**
 * Write a description of interface IAlmacen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public interface IfaceAlmacen
{
    
    void agregarBiplazaDeportivo(BiplazaDeportivo biplazaDeportivo);
    List<BiplazaDeportivo> getBiplazasDeportivos();
    
    void agregarFurgoneta(Furgoneta furgoneta);
    List<Furgoneta> getFurgonetas();
    
    void agregarTurismo(Turismo turismo);
    List<Turismo> getTurismos();
    
    void agregarMotor(Motor motor);
    List<Motor> getMotores();
    
    void agregarTapiceria(Tapiceria tapiceria);
    List<Tapiceria> getTapicerias();
    
    void agregarRueda(Rueda rueda);
    List<Rueda> getRuedas();

    void agregarAdministradorSistema(AdministradorSistema adminstradorSistema);
    List<AdministradorSistema> getAdministradoresSistema();

    void agregarGestorPlanta(GestorPlanta gestorPlanta);
    List<GestorPlanta> getGestoresPlanta();

    void agregarMecanico(Mecanico mecanico);
    List<Mecanico> getMecanicos();

    void agregarOperario(Operario operario);
    List<Operario> getOperarios();
    
    void vaciar();
    
    int getTotalCoches();

    void registrarOperacion(RegistroMontaje registro);
    List<RegistroMontaje> getRegistrosPorFecha(Date fecha);
    List<RegistroMontaje> getTodosRegistros();
}
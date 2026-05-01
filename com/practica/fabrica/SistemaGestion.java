package com.practica.fabrica;

import com.practica.vehiculo.*;
import com.practica.rueda.*;
import com.practica.motor.*;
import com.practica.tapiceria.*;
import com.practica.personal.*;
import java.util.List;

/**
 * Write a description of class SistemaGestion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SistemaGestion
{
    private IfaceAlmacen almacen;

    public SistemaGestion(IfaceAlmacen almacen){
        this.almacen = almacen;
    }

    public void registrarBiplazaDeportivo(BiplazaDeportivo biplazaDeportivo){
        almacen.agregarBiplazaDeportivo(biplazaDeportivo);
    }
    public List<BiplazaDeportivo> consultarBiplazasDeportivos(){
        return almacen.getBiplazasDeportivos();
    }

    public void registrarFurgoneta(Furgoneta furgoneta){
        almacen.agregarFurgoneta(furgoneta);
    }
    public List<Furgoneta> consultarFurgoneta(){
        return almacen.getFurgonetas();
    }

    public void registrarTurismo(Turismo turismo){
        almacen.agregarTurismo(turismo);
    }
    public List<Turismo> consultarTurismo(){
        return almacen.getTurismos();
    }

    public void registrarMotor(Motor motor){
        almacen.agregarMotor(motor);
    }
    public List<Motor> consultarMotor(){
        return almacen.getMotores();
    }

    public void registrarTapiceria(Tapiceria tapiceria){
        almacen.agregarTapiceria(tapiceria);
    }
    public List<Tapiceria> consultarTapiceria(){
        return almacen.getTapicerias();
    }

    public void registrarRueda(Rueda rueda){
        almacen.agregarRueda(rueda);
    }
    public List<Rueda> consultarRueda(){
        return almacen.getRuedas();
    }

    public void registrarAdministradorSistema(AdministradorSistema administradorSistema){
        almacen.agregarAdministradorSistema(administradorSistema);
    }
    public List<AdministradorSistema> consultarAdministradorSistema(){
        return almacen.getAdministradoresSistema();
    }

    public void registrarGestorPlanta(GestorPlanta gestorPlanta){
        almacen.agregarGestorPlanta(gestorPlanta);
    }
    public List<GestorPlanta> consultarGestorPlanta(){
        return almacen.getGestoresPlanta();
    }

    public void registrarMecanico(Mecanico mecanico){
        almacen.agregarMecanico(mecanico);
    }
    public List<Mecanico> consultarMecanico(){
        return almacen.getMecanicos();
    }

    public void registrarOperario(Operario operario){
        almacen.agregarOperario(operario);
    }
    public List<Operario> consultarOperario(){
        return almacen.getOperarios();
    }

    public void resetSistema(){
        almacen.vaciar();
    }

    public int consultarTotalCoches(){
        return almacen.getTotalCoches();
    }
    
}
package com.practica.personal;

import java.time.LocalDate;

/**
 * Write a description of class Operario here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Operario extends Trabajador {
    // instance variables - replace the example below with your own
    private int montajesRealizados;

    /**
     * Constructor for objects of class Operario
     */
    public Operario(String nombre, String apellidos, String dni,
            String direccion, String numSegSocial,
            double salario, LocalDate fechaIngreso) {
        super(nombre, apellidos, dni, direccion, numSegSocial, "Operario", salario, fechaIngreso);
        this.montajesRealizados = 0;
    }

    public boolean esEficiente(){
        return montajesRealizados > 10;
    }

    public int getTiempoMontaje(){
        return esEficiente() ? 1 : 3;
    }
    public void registrarMontajeCompletado() {
        this.montajesRealizados++;
    }


}
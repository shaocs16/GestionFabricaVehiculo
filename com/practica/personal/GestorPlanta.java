package com.practica.personal;

import java.time.LocalDate;

/**
 * Write a description of class GestorPlanta here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class GestorPlanta extends Trabajador {
    /**
     * Constructor for objects of class GestorPlanta
     */
    public GestorPlanta(String nombre, String apellidos, String dni,
            String direccion, String numSegSocial,
            double salario, LocalDate fechaIngreso) {
        super(nombre, apellidos, dni, direccion, numSegSocial, "Gestor de planta", salario, fechaIngreso);
    }
}
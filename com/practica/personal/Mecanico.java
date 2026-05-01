package com.practica.personal;

import java.time.LocalDate;

/**
 * Write a description of class Mecanico here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Mecanico extends Trabajador {
    /**
     * Constructor for objects of class Mecanico
     */
    public Mecanico(String nombre, String apellidos, String dni,
            String direccion, String numSegSocial,
            double salario, LocalDate fechaIngreso) {
        super(nombre, apellidos, dni, direccion, numSegSocial, "Mecánico de cinta", salario, fechaIngreso);
    }
}
package com.practica.personal;

import java.time.LocalDate;

/**
 * Write a description of class AdminstradorSistema here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class AdministradorSistema extends Trabajador {
    /**
     * Constructor for objects of class AdminstradorSistema
     */
    public AdministradorSistema(String nombre, String apellidos, String dni,
            String direccion, String numSegSocial,
            double salario, LocalDate fechaIngreso) {
        super(nombre, apellidos, dni, direccion, numSegSocial, "Administrador del sistema", salario, fechaIngreso);
    }

}
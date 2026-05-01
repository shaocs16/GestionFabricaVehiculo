package com.practica.personal;

import java.time.LocalDate;

/**
 * Write a description of class Trabajador here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Trabajador {
    // instance variables - replace the example below with your own
    private String nombre, apellidos, dni, direccion, numSegSocial, puestoTrabajo;
    private LocalDate fechaIngreso;
    private double salario;

    /**
     * Constructor for objects of class Trabajador
     */
    public Trabajador(String nombre, String apellidos, String dni,
            String direccion, String numSegSocial, String puestoTrabajo,
            double salario, LocalDate fechaIngreso) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion = direccion;
        this.numSegSocial = numSegSocial;
        this.puestoTrabajo = puestoTrabajo;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumSeguridadSocial() {
        return numSegSocial;
    }

    public void setNumSeguridadSocial(String numSegSocial) {
        this.numSegSocial = numSegSocial;
    }

    public String getPuestoTrabajo() {
        return puestoTrabajo;
    }

    public void setPuesto(String puestoTrabajo) {
        this.puestoTrabajo = puestoTrabajo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public LocalDate getfechaIngreso() {
        return fechaIngreso;
    }

    public void setfechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
package com.practica.personal;

import java.time.LocalDate;

/**
 * Clase base abstracta que representa a un trabajador de la fábrica.
 * <p>
 * Encapsula los datos personales y laborales comunes a todos los perfiles
 * (operario, mecánico de cinta, gestor de planta y administrador del
 * sistema): nombre, apellidos, DNI, dirección, número de seguridad social,
 * puesto de trabajo, salario y fecha de ingreso. *
 * @author Shao Capilla Sanz
 */
public abstract class Trabajador {

    /** Datos personales y laborales del trabajador. */
    private String nombre, apellidos, dni, direccion, numSegSocial, puestoTrabajo;
    /** Fecha de ingreso en la empresa. */
    private LocalDate fechaIngreso;
    /** Salario bruto del trabajador. */
    private double salario;

    /**
     * Crea un trabajador con todos sus datos personales y laborales.
     *
     * @param nombre        nombre del trabajador
     * @param apellidos     apellidos del trabajador
     * @param dni           DNI del trabajador
     * @param direccion     dirección postal
     * @param numSegSocial  número de la Seguridad Social
     * @param puestoTrabajo puesto que desempeña en la fábrica
     * @param salario       salario bruto
     * @param fechaIngreso  fecha de ingreso en la empresa
     */
    public Trabajador(String nombre, String apellidos, String dni, String direccion, String numSegSocial, String puestoTrabajo, double salario, LocalDate fechaIngreso) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion = direccion;
        this.numSegSocial = numSegSocial;
        this.puestoTrabajo = puestoTrabajo;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * @return nombre del trabajador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del trabajador.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return apellidos del trabajador
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del trabajador.
     *
     * @param apellidos nuevos apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return DNI del trabajador
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI del trabajador.
     *
     * @param dni nuevo DNI
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return dirección postal del trabajador
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección postal del trabajador.
     *
     * @param direccion nueva dirección
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return número de Seguridad Social del trabajador
     */
    public String getNumSeguridadSocial() {
        return numSegSocial;
    }

    /**
     * Establece el número de Seguridad Social del trabajador.
     *
     * @param numSegSocial nuevo número
     */
    public void setNumSeguridadSocial(String numSegSocial) {
        this.numSegSocial = numSegSocial;
    }

    /**
     * @return puesto de trabajo que desempeña
     */
    public String getPuestoTrabajo() {
        return puestoTrabajo;
    }

    /**
     * Establece el puesto de trabajo del trabajador.
     *
     * @param puestoTrabajo nuevo puesto
     */
    public void setPuesto(String puestoTrabajo) {
        this.puestoTrabajo = puestoTrabajo;
    }

    /**
     * @return salario bruto del trabajador
     */
    public double getSalario() {
        return salario;
    }

    /**
     * Establece el salario bruto del trabajador.
     *
     * @param salario nuevo salario
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }

    /**
     * @return fecha de ingreso en la empresa
     */
    public LocalDate getfechaIngreso() {
        return fechaIngreso;
    }

    /**
     * Establece la fecha de ingreso del trabajador.
     *
     * @param fechaIngreso nueva fecha de ingreso
     */
    public void setfechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}

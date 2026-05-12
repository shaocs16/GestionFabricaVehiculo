package com.practica.personal;

import java.time.LocalDate;

public class Operario extends Trabajador {

    private int montajesRealizados;

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

    public int getMontajesRealizados() {
        return montajesRealizados;
    }

    public void setMontajesRealizados(int montajesRealizados) {
        this.montajesRealizados = montajesRealizados;
    }
}
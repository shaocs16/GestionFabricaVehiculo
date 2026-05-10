package com.practica.personal;

import java.time.LocalDate;
import com.practica.vehiculo.Coche;

public class Mecanico extends Trabajador {

    /** Número total de reparaciones realizadas por este mecánico. */
    private int reparacionesRealizadas;

    /**
     * Constructor for objects of class Mecanico
     */
    public Mecanico(String nombre, String apellidos, String dni, String direccion, String numSegSocial,
            double salario, LocalDate fechaIngreso) {
        super(nombre, apellidos, dni, direccion, numSegSocial, "Mecánico de cinta", salario, fechaIngreso);
        this.reparacionesRealizadas = 0;
    }

    public void repararCoche(Coche c) {
        if (c != null && c.isAveriado()) {
            c.setTiempoReparacion(0);
            c.setAveriado(false);
            reparacionesRealizadas++;
            System.out.println("El mecánico " + getNombre() + " " + getApellidos()
                    + " ha reparado la avería de un coche.");
        }
    }

    public boolean esEficiente() {
        return reparacionesRealizadas > 20;
    }

    public int getTiempoReparacion() {
        if (esEficiente()) {
            return 1;
        } else {
            // entre 2 y 5 segundos
            return 2 + (int) (Math.random() * 4);
        }
    }

    public int getReparacionesRealizadas() {
        return reparacionesRealizadas;
    }
}
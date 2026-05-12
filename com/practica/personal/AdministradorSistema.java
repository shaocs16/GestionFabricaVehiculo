package com.practica.personal;

import java.time.LocalDate;

import com.practica.planificador.Planificador;

public class AdministradorSistema extends Trabajador {

private int restauracionesRealizadas;

public AdministradorSistema(String nombre, String apellidos, String dni,
            String direccion, String numSegSocial,
            double salario, LocalDate fechaIngreso) {
        super(nombre, apellidos, dni, direccion, numSegSocial,
                "Administrador del sistema", salario, fechaIngreso);
        this.restauracionesRealizadas = 0;
    }

    public void restaurarSistemaGestion(Planificador planificador) {
        if (planificador.isSistemaGestionBloqueado()) {
            planificador.setSistemaGestionBloqueado(false);
            restauracionesRealizadas++;
            String msg = "El administrador " + getNombre() + " " + getApellidos()
                    + " ha restaurado el sistema de gestión de la fábrica.";
            System.out.println(msg);
            planificador.notifyObservadores(msg);
        }
    }

    public void restaurarCadenasMontaje(Planificador planificador) {
        if (planificador.isCaidaDeLuz()) {
            planificador.setCaidaDeLuz(false);
            String msg = "El administrador " + getNombre() + " " + getApellidos()
                    + " ha reanudado las cadenas de montaje.";
            System.out.println(msg);
            planificador.notifyObservadores(msg);
        }
    }

    public int getRestauracionesRealizadas() {
        return restauracionesRealizadas;
    }
}
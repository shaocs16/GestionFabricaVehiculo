package com.practica.personal;

import java.time.LocalDate;

import com.practica.planificador.Planificador;

public class AdministradorSistema extends Trabajador {

    /** Número de caídas de luz que ha resuelto este administrador. */
    private int restauracionesRealizadas;

    /**
     * Constructor for objects of class AdministradorSistema
     */
    public AdministradorSistema(String nombre, String apellidos, String dni,
            String direccion, String numSegSocial,
            double salario, LocalDate fechaIngreso) {
        super(nombre, apellidos, dni, direccion, numSegSocial,
                "Administrador del sistema", salario, fechaIngreso);
        this.restauracionesRealizadas = 0;
    }

    public void restaurarLuz(Planificador planificador) {
        if (planificador.isCaidaDeLuz()) {
            planificador.setCaidaDeLuz(false);
            restauracionesRealizadas++;
            String msg = "El administrador " + getNombre() + " " + getApellidos()
                    + " ha restaurado el suministro eléctrico de la fábrica.";
            System.out.println(msg);
            planificador.notifyObservadores(msg);
        }
    }

    public int getRestauracionesRealizadas() {
        return restauracionesRealizadas;
    }
}
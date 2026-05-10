package com.practica.montaje;

import com.practica.personal.Operario;
import com.practica.vehiculo.Coche;

/**
 * Representa un Robot en la cadena de montaje controlado por un Operario.
 */
public class Robot {
    private Operario operario;
    private Coche cocheActual;
    private int tiempoRestante;
    private String nombre;

    public Robot(String nombre, Operario operario) {
        this.nombre = nombre;
        this.operario = operario;
        this.tiempoRestante = 0;
        this.cocheActual = null;
    }

    public boolean estaLibre() {
        return cocheActual == null;
    }

    public void recibirCoche(Coche c) {
        this.cocheActual = c;
        this.tiempoRestante = operario.getTiempoMontaje();
    }

    public Coche getCocheActual() {
        return cocheActual;
    }

    public boolean trabajar() {
        if (cocheActual == null) {
            return false;
        }

        tiempoRestante--;
        if (tiempoRestante <= 0) {
            operario.registrarMontajeCompletado();
            return true;
        }
        return false;
    }

    public void liberarCoche() {
        this.cocheActual = null;
    }

    public String getNombre() {
        return nombre;
    }
}
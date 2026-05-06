package com.practica.fabrica;

import java.util.Date;

/**
 * Clase para almacenar el registro histórico de operaciones (ensamblajes)
 * en la cadena de montaje y almacén.
 */
public class RegistroMontaje {
    private Date fecha;
    private String componente;
    private String accion;

    public RegistroMontaje(String componente, String accion) {
        this.fecha = new Date(); // Fecha actual por defecto
        this.componente = componente;
        this.accion = accion;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getComponente() {
        return componente;
    }

    public String getAccion() {
        return accion;
    }

    @Override
    public String toString() {
        return "[" + fecha.toString() + "] " + accion + " - Componente/Vehículo: " + componente;
    }
}

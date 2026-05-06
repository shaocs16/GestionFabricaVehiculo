package com.practica.fabrica;

import java.util.Date;

/**
 * Clase que representa un registro en el historial de montaje o almacén.
 */
public class RegistroMontaje {
    private Date fecha;
    private String tipoComponente;
    private String accion;

    public RegistroMontaje(Date fecha, String tipoComponente, String accion) {
        this.fecha = fecha;
        this.tipoComponente = tipoComponente;
        this.accion = accion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoComponente() {
        return tipoComponente;
    }

    public void setTipoComponente(String tipoComponente) {
        this.tipoComponente = tipoComponente;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    @Override
    public String toString() {
        return "RegistroMontaje{" +
                "fecha=" + fecha +
                ", tipoComponente='" + tipoComponente + '\'' +
                ", accion='" + accion + '\'' +
                '}';
    }
}

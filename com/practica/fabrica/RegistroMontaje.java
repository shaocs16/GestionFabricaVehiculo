package com.practica.fabrica;

import java.util.Date;

/**
 * Registro de una operación de montaje realizada en la fábrica.
 * <p>
 * Cada registro captura la fecha y hora en que ocurrió la operación, el tipo
 * de componente involucrado y una descripción de la acción realizada (alta
 * de un componente en el almacén, cambio de estado de un vehículo, etc.).
 * Los registros permiten reconstruir el histórico de actividad y realizar
 * consultas por fecha. *
 * @author Shao Capilla Sanz
 */
public class RegistroMontaje {
    /** Fecha y hora en que se produjo la operación. */
    private Date fecha;
    /** Tipo de componente o entidad afectada. */
    private String tipoComponente;
    /** Descripción de la acción registrada. */
    private String accion;

    /**
     * Crea un registro de operación de montaje.
     *
     * @param fecha          fecha y hora de la operación
     * @param tipoComponente tipo de componente o entidad afectada
     * @param accion         descripción de la acción
     */
    public RegistroMontaje(Date fecha, String tipoComponente, String accion) {
        this.fecha = fecha;
        this.tipoComponente = tipoComponente;
        this.accion = accion;
    }

    /**
     * @return fecha y hora en que se produjo la operación
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha del registro.
     *
     * @param fecha nueva fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return tipo de componente afectado
     */
    public String getTipoComponente() {
        return tipoComponente;
    }

    /**
     * Establece el tipo de componente del registro.
     *
     * @param tipoComponente nuevo tipo de componente
     */
    public void setTipoComponente(String tipoComponente) {
        this.tipoComponente = tipoComponente;
    }

    /**
     * @return descripción de la acción registrada
     */
    public String getAccion() {
        return accion;
    }

    /**
     * Establece la descripción de la acción.
     *
     * @param accion nueva descripción
     */
    public void setAccion(String accion) {
        this.accion = accion;
    }

    /**
     * @return representación textual del registro
     */
    @Override
    public String toString() {
        return "RegistroMontaje{" +
                "fecha=" + fecha +
                ", tipoComponente='" + tipoComponente + '\'' +
                ", accion='" + accion + '\'' +
                '}';
    }
}

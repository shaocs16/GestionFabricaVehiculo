package com.practica.personal;

import java.time.LocalDate;

import com.practica.dashboard.Dashboard;
import com.practica.vehiculo.Coche;

/**
 * Trabajador con perfil de Mecánico de cinta.
 * <p>
 * Los mecánicos se encargan de reparar las averías que se producen en la
 * cadena de montaje. Existen dos perfiles según la experiencia acumulada:
 * <ul>
 *   <li><b>Eficiente</b>: ha realizado más de 20 reparaciones y tarda 1
 *       segundo de simulación en reparar cualquier problema.</li>
 *   <li><b>Estándar</b>: requiere entre 2 y 5 segundos por reparación.</li>
 * </ul>
 *
 * @author Shao Capilla Sanz
 */
public class Mecanico extends Trabajador {

    /** Número de reparaciones realizadas por el mecánico. */
    private int reparacionesRealizadas;

    /** Dashboard al que el mecánico notifica el resultado de las reparaciones. */
    private Dashboard dashboard;

    /**
     * Crea un mecánico de cinta con sus datos personales y el dashboard
     * al que notificará el resultado de sus reparaciones.
     * El puesto queda fijado automáticamente como "Mecánico de cinta".
     *
     * @param nombre       nombre del mecánico
     * @param apellidos    apellidos del mecánico
     * @param dni          DNI del mecánico
     * @param direccion    dirección postal
     * @param numSegSocial número de la Seguridad Social
     * @param salario      salario bruto
     * @param fechaIngreso fecha de ingreso en la empresa
     * @param dashboard    dashboard al que se enviarán las notificaciones
     */
    public Mecanico(String nombre, String apellidos, String dni, String direccion, String numSegSocial, double salario, LocalDate fechaIngreso, Dashboard dashboard) {
        super(nombre, apellidos, dni, direccion, numSegSocial, "Mecánico de cinta", salario, fechaIngreso);
        this.reparacionesRealizadas = 0;
        this.dashboard = dashboard;
    }

    /**
     * Repara la avería de un coche, limpiando su estado de avería e
     * incrementando el contador de reparaciones del mecánico.
     *
     * @param c coche averiado a reparar; si es {@code null} o no está
     *          averiado, no se realiza ninguna acción
     */
    public void repararCoche(Coche c) {
        if (c != null && c.isAveriado()) {
            c.setTiempoReparacion(0);
            c.setAveriado(false);
            reparacionesRealizadas++;
            dashboard.update("El mecánico " + getNombre() + " " + getApellidos() + " ha reparado la avería de un coche.");
        }
    }

    /**
     * Indica si el mecánico es considerado eficiente, es decir, si ha
     * realizado más de 20 reparaciones.
     *
     * @return {@code true} si es eficiente; {@code false} si es estándar
     */
    public boolean esEficiente() {
        return reparacionesRealizadas > 20;
    }

    /**
     * Devuelve el tiempo (en segundos de simulación) que el mecánico tarda
     * en reparar una avería según su perfil.
     *
     * @return 1 si es eficiente; un valor aleatorio entre 2 y 5 si es estándar
     */
    public int getTiempoReparacion() {
        if (esEficiente()) {
            return 1;
        } else {
            return 2 + (int) (Math.random() * 4);
        }
    }

    /**
     * @return número total de reparaciones realizadas
     */
    public int getReparacionesRealizadas() {
        return reparacionesRealizadas;
    }
}

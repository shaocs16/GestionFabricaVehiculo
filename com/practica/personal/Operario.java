package com.practica.personal;

import java.time.LocalDate;

/**
 * Trabajador con perfil de Operario de la cadena de montaje.
 * <p>
 * Los operarios controlan los robots encargados del montaje de cada
 * componente (chasis, motor, tapicería, ruedas). Existen dos perfiles
 * según la experiencia acumulada:
 * <ul>
 *   <li><b>Eficiente</b>: ha realizado más de 10 montajes y completa su tarea
 *       en 1 segundo de simulación.</li>
 *   <li><b>Estándar</b>: requiere 3 segundos por tarea.</li>
 * </ul>
 *
 * @author Shao Capilla Sanz
 */
public class Operario extends Trabajador {

    /** Número de montajes de componentes realizados por el operario. */
    private int montajesRealizados;

    /**
     * Crea un operario con sus datos personales. El puesto queda fijado
     * automáticamente como "Operario" y el contador de montajes empieza en 0.
     *
     * @param nombre       nombre del operario
     * @param apellidos    apellidos del operario
     * @param dni          DNI del operario
     * @param direccion    dirección postal
     * @param numSegSocial número de la Seguridad Social
     * @param salario      salario bruto
     * @param fechaIngreso fecha de ingreso en la empresa
     */
    public Operario(String nombre, String apellidos, String dni,
            String direccion, String numSegSocial,
            double salario, LocalDate fechaIngreso) {
        super(nombre, apellidos, dni, direccion, numSegSocial, "Operario", salario, fechaIngreso);
        this.montajesRealizados = 0;
    }

    /**
     * Indica si el operario es considerado eficiente, es decir, si ha
     * realizado más de 10 montajes.
     *
     * @return {@code true} si es eficiente; {@code false} si es estándar
     */
    public boolean esEficiente(){
        return montajesRealizados > 10;
    }

    /**
     * Devuelve el tiempo en segundos que el operario tarda en realizar un
     * montaje según su perfil.
     *
     * @return 1 si es eficiente, 3 si es estándar
     */
    public int getTiempoMontaje(){
        return esEficiente() ? 1 : 3;
    }

    /**
     * Incrementa en uno el contador de montajes completados por el operario.
     * Se invoca cuando finaliza con éxito una tarea de ensamblaje.
     */
    public void registrarMontajeCompletado() {
        this.montajesRealizados++;
    }

    /**
     * @return número total de montajes realizados
     */
    public int getMontajesRealizados() {
        return montajesRealizados;
    }

    /**
     * Establece el número de montajes realizados (utilizable para inicializar
     * operarios con experiencia previa).
     *
     * @param montajesRealizados nuevo contador
     */
    public void setMontajesRealizados(int montajesRealizados) {
        this.montajesRealizados = montajesRealizados;
    }
}

package com.practica.personal;

import java.time.LocalDate;

import com.practica.planificador.Planificador;

/**
 * Trabajador con perfil de Administrador del Sistema.
 * <p>
 * Es el responsable de velar por el correcto funcionamiento del software de
 * la cadena de montaje y del gestor de fábrica. Cuando se produce un evento
 * crítico (por ejemplo, un apagón), todos los trabajadores quedan parados
 * hasta que el administrador restaura los sistemas: necesita 2 segundos para
 * reanudar el sistema de gestión y 3 segundos para reanudar las cadenas de
 * montaje. *
 * @author Shao Capilla Sanz
 */
public class AdministradorSistema extends Trabajador {

    /** Número de restauraciones del sistema realizadas. */
    private int restauracionesRealizadas;

    /**
     * Crea un Administrador del Sistema con sus datos personales. El puesto
     * queda fijado automáticamente como "Administrador del sistema".
     *
     * @param nombre       nombre del administrador
     * @param apellidos    apellidos del administrador
     * @param dni          DNI del administrador
     * @param direccion    dirección postal
     * @param numSegSocial número de la Seguridad Social
     * @param salario      salario bruto
     * @param fechaIngreso fecha de ingreso en la empresa
     */
    public AdministradorSistema(String nombre, String apellidos, String dni,
            String direccion, String numSegSocial,
            double salario, LocalDate fechaIngreso) {
        super(nombre, apellidos, dni, direccion, numSegSocial,
                "Administrador del sistema", salario, fechaIngreso);
        this.restauracionesRealizadas = 0;
    }

    /**
     * Restaura el sistema de gestión de la fábrica si se encuentra bloqueado,
     * notificando a los observadores del planificador y registrando la
     * restauración en el contador del administrador.
     *
     * @param planificador planificador cuyo sistema de gestión se desbloqueará
     */
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

    /**
     * Reanuda las cadenas de montaje si están detenidas por una caída de luz,
     * notificando a los observadores del planificador.
     *
     * @param planificador planificador cuyas cadenas se reanudarán
     */
    public void restaurarCadenasMontaje(Planificador planificador) {
        if (planificador.isCaidaDeLuz()) {
            planificador.setCaidaDeLuz(false);
            String msg = "El administrador " + getNombre() + " " + getApellidos()
                    + " ha reanudado las cadenas de montaje.";
            System.out.println(msg);
            planificador.notifyObservadores(msg);
        }
    }

    /**
     * @return número total de restauraciones del sistema realizadas
     */
    public int getRestauracionesRealizadas() {
        return restauracionesRealizadas;
    }
}

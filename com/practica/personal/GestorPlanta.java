package com.practica.personal;

import java.time.LocalDate;
import java.util.Date;

import com.practica.dashboard.Dashboard;
import com.practica.montaje.CadenaMontaje;
import com.practica.motor.Motor;
import com.practica.rueda.Rueda;
import com.practica.tapiceria.Tapiceria;
import com.practica.vehiculo.*;

/**
 * Trabajador con perfil de Gestor de Planta.
 * <p>
 * Es el encargado de configurar las cadenas de montaje y supervisar su
 * funcionamiento. Configura los componentes que utilizarán las cadenas
 * para ensamblar los vehículos, monitoriza el dashboard para detectar
 * incidencias y llama a los mecánicos cuando se produce una avería. *
 * @author Shao Capilla Sanz
 */
public class GestorPlanta extends Trabajador {

        /** Dashboard al que el gestor notifica confirmaciones e incidencias. */
        private Dashboard dashboard;

        /**
         * Crea un Gestor de Planta con sus datos personales y el dashboard
         * que utilizará para notificar el estado de las cadenas de montaje.
         * El puesto queda fijado automáticamente como "Gestor de planta".
         *
         * @param nombre       nombre del gestor
         * @param apellidos    apellidos del gestor
         * @param dni          DNI del gestor
         * @param direccion    dirección postal
         * @param numSegSocial número de la Seguridad Social
         * @param salario      salario bruto
         * @param fechaIngreso fecha de ingreso en la empresa
         * @param dashboard    dashboard al que se enviarán las notificaciones
        */
        public GestorPlanta(String nombre, String apellidos, String dni, String direccion, String numSegSocial, double salario, LocalDate fechaIngreso, Dashboard dashboard) {
                super(nombre, apellidos, dni, direccion, numSegSocial, "Gestor de planta", salario, fechaIngreso);
                this.dashboard = dashboard;
        }

        /**
         * Configura un lote de Biplazas Deportivos para su ensamblaje en la
         * cadena indicada, encolando las unidades en estado {@link EstadoMontaje#CHASIS}.
         * Notifica la acción al dashboard.
         *
         * @param cadena    cadena de montaje destino
         * @param cantidad  número de unidades a encolar
         * @param color     color de los vehículos
         * @param tara      tara en kg
         * @param pesoMax   peso máximo autorizado en kg
         * @param motor     motor a montar (puede ser {@code null} si se asignará después)
         * @param tapiceria tapicería a instalar (puede ser {@code null})
         * @param ruedas    ruedas a instalar (puede ser {@code null})
        */
        public void configurarBiplazas(CadenaMontaje cadena, int cantidad, String color, double tara, double pesoMax, Motor motor, Tapiceria tapiceria, Rueda[] ruedas) {
                dashboard.update("[GESTOR] " + getNombre() + " configura " + cantidad + " Biplaza(s) en la cadena de montaje.");
                for (int i = 0; i < cantidad; i++) {
                BiplazaDeportivo bd = new BiplazaDeportivo(color, 2, pesoMax, tara,tapiceria, motor, ruedas);
                bd.setEstadoMontaje(EstadoMontaje.CHASIS);
                cadena.agregarBiplaza(bd);
                }
        }

        /**
         * Configura un lote de Turismos para su ensamblaje en la cadena indicada,
         * encolando las unidades en estado {@link EstadoMontaje#CHASIS}.
         * Notifica la acción al dashboard.
         *
         * @param cadena    cadena de montaje destino
         * @param cantidad  número de unidades a encolar
         * @param color     color de los vehículos
         * @param tara      tara en kg
         * @param pesoMax   peso máximo autorizado en kg
         * @param motor     motor a montar
         * @param tapiceria tapicería a instalar
         * @param ruedas    ruedas a instalar
        */
        public void configurarTurismos(CadenaMontaje cadena, int cantidad, String color, double tara, double pesoMax, Motor motor, Tapiceria tapiceria, Rueda[] ruedas) {
                dashboard.update("[GESTOR] " + getNombre() + " configura " + cantidad + " Turismo(s) en la cadena de montaje.");
                for (int i = 0; i < cantidad; i++) {
                        Turismo t = new Turismo(color, 5, pesoMax, tara, tapiceria, motor, ruedas);
                        t.setEstadoMontaje(EstadoMontaje.CHASIS);
                        cadena.agregarTurismo(t);
                }
        }

        /**
         * Configura un lote de Furgonetas para su ensamblaje en la cadena indicada,
         * encolando las unidades en estado {@link EstadoMontaje#CHASIS}.
         * Notifica la acción al dashboard.
         *
         * @param cadena    cadena de montaje destino
         * @param cantidad  número de unidades a encolar
         * @param color     color de los vehículos
         * @param tara      tara en kg
         * @param pesoMax   peso máximo autorizado en kg
         * @param motor     motor a montar
         * @param tapiceria tapicería a instalar
         * @param ruedas    ruedas a instalar
        */
        public void configurarFurgonetas(CadenaMontaje cadena, int cantidad, String color, double tara, double pesoMax, Motor motor, Tapiceria tapiceria, Rueda[] ruedas) {
                dashboard.update("[GESTOR] " + getNombre() + " configura " + cantidad + " Furgoneta(s) en la cadena de montaje.");
                for (int i = 0; i < cantidad; i++) {
                Furgoneta f = new Furgoneta(color, 3, pesoMax, tara, tapiceria, motor, ruedas);
                f.setEstadoMontaje(EstadoMontaje.CHASIS);
                cadena.agregarFurgoneta(f);
                }
        }

        /**
         * Notifica al dashboard que el gestor está revisando el estado de las
         * cadenas de montaje en busca de incidencias.
        */
        public void consultarDashboard() {
                dashboard.update("[GESTOR] " + getNombre() + " " + getApellidos() + " revisa el dashboard para detectar incidencias.");
        }

        /**
         * Llama a un mecánico para que repare un coche averiado y notifica
         * la incidencia al dashboard.
         *
         * @param mecanico      mecánico al que se llama
         * @param cocheAveriado coche que requiere reparación
        */
        public void llamarMecanico(Mecanico mecanico, Coche cocheAveriado) {
                mecanico.repararCoche(cocheAveriado);
                dashboard.update("[GESTOR] " + getNombre() + " " + getApellidos() + " llama al mecánico " + mecanico.getNombre() + " " + mecanico.getApellidos() + " para reparar una avería.");
        }
}

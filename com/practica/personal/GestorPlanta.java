package com.practica.personal;

import java.time.LocalDate;
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

        /**
         * Crea un Gestor de Planta con sus datos personales. El puesto queda
         * fijado automáticamente como "Gestor de planta".
         *
         * @param nombre       nombre del gestor
         * @param apellidos    apellidos del gestor
         * @param dni          DNI del gestor
         * @param direccion    dirección postal
         * @param numSegSocial número de la Seguridad Social
         * @param salario      salario bruto
         * @param fechaIngreso fecha de ingreso en la empresa
        */
        public GestorPlanta(String nombre, String apellidos, String dni, String direccion, String numSegSocial, double salario, LocalDate fechaIngreso) {
                super(nombre, apellidos, dni, direccion, numSegSocial, "Gestor de planta", salario, fechaIngreso);
        }

        /**
         * Configura un lote de Biplazas Deportivos para su ensamblaje en la
         * cadena indicada, encolando las unidades en estado {@link EstadoMontaje#CHASIS}.
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
                System.out.println("[GESTOR] " + getNombre() + " configura " + cantidad + " Biplaza(s) en la cadena de montaje.");
                for (int i = 0; i < cantidad; i++) {
                BiplazaDeportivo b = new BiplazaDeportivo(color, 2, pesoMax, tara,
                        tapiceria, motor, ruedas);
                b.setEstadoMontaje(EstadoMontaje.CHASIS);
                cadena.agregarBiplaza(b);
                }
        }

        /**
         * Configura un lote de Turismos para su ensamblaje en la cadena indicada,
         * encolando las unidades en estado {@link EstadoMontaje#CHASIS}.
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
                System.out.println("[GESTOR] " + getNombre() + " configura " + cantidad + " Turismo(s) en la cadena de montaje.");
                for (int i = 0; i < cantidad; i++) {
                        Turismo t = new Turismo(color, 5, pesoMax, tara, tapiceria, motor, ruedas);
                        t.setEstadoMontaje(EstadoMontaje.CHASIS);
                        cadena.agregarTurismo(t);
                }
        }

        /**
         * Configura un lote de Furgonetas para su ensamblaje en la cadena indicada,
         * encolando las unidades en estado {@link EstadoMontaje#CHASIS}.
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
                System.out.println("[GESTOR] " + getNombre() + " configura " + cantidad + " Furgoneta(s) en la cadena de montaje.");
                for (int i = 0; i < cantidad; i++) {
                Furgoneta f = new Furgoneta(color, 3, pesoMax, tara, tapiceria, motor, ruedas);
                f.setEstadoMontaje(EstadoMontaje.CHASIS);
                cadena.agregarFurgoneta(f);
                }
        }

        /**
         * Genera un mensaje indicando que el gestor está revisando el dashboard
         * en busca de incidencias en el proceso de montaje.
         *
         * @param dashboard dashboard a consultar
         * @return cadena descriptiva de la acción realizada
        */
        public String consultarDashboard(Dashboard dashboard) {
                return "[GESTOR] " + getNombre() + " " + getApellidos() + " revisa el dashboard para detectar incidencias.";
        }

        /**
         * Llama a un mecánico para que repare un coche averiado y devuelve un
         * mensaje informativo de la acción.
         *
         * @param mecanico       mecánico al que se llama
         * @param cocheAveriado  coche que requiere reparación
         * @param dashboard      dashboard donde notificar la incidencia
         * @return cadena descriptiva de la llamada al mecánico
        */
        public String llamarMecanico(Mecanico mecanico, Coche cocheAveriado, Dashboard dashboard) {
                mecanico.repararCoche(cocheAveriado);
                return "[GESTOR] " + getNombre() + " " + getApellidos() + " llama al mecánico " + mecanico.getNombre() + " " + mecanico.getApellidos() + " para reparar una avería.";
        }
}

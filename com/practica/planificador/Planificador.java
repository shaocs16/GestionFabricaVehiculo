package com.practica.planificador;

import com.practica.dashboard.*;
import com.practica.fabrica.*;
import com.practica.montaje.*;
import com.practica.vehiculo.*;
import com.practica.motor.*;
import com.practica.tapiceria.*;
import com.practica.rueda.*;
import com.practica.personal.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Motor de simulación de la fábrica.
 * <p>
 * El planificador orquesta el avance del montaje en las tres cadenas
 * (Biplaza, Turismo y Furgoneta) tick a tick (1 segundo real), asignando
 * los robots de cada estación a los vehículos y consumiendo stock del
 * almacén a medida que se progresa por los estados de
 * {@link EstadoMontaje}. Es el único componente que avanza el tiempo de
 * la simulación. * <p>
 * Soporta tres niveles de simulación:
 * <ul>
 *   <li><b>Simple (1)</b>: sin incidencias.</li>
 *   <li><b>Compleja (2)</b>: averías de vehículos que requieren mecánico.</li>
 *   <li><b>Muy compleja (3)</b>: averías + apagón general que paraliza la
 *       fábrica hasta que el administrador restaure el sistema de gestión
 *       (2 s) y las cadenas (3 s).</li>
 * </ul> * <p>
 * Implementa {@link Observable} para poder notificar eventos relevantes
 * al {@link Dashboard} y a otros observadores. *
 * @author Shao Capilla Sanz
 */
public class Planificador implements Observable {

    /** Cadena de montaje sobre la que se ejecuta la simulación. */
    private CadenaMontaje cadenaMontaje;
    /** Sistema de gestión que mantiene almacén y registros. */
    private SistemaGestion sistemaGestion;
    /** Nivel de simulación: 1=Simple, 2=Compleja, 3=Muy compleja. */
    private int tipoSimulacion;

    /** Indica si la fábrica está en apagón. */
    private boolean caidaDeLuz = false;
    /** Indica si el sistema de gestión está bloqueado por el apagón. */
    private boolean sistemaGestionBloqueado = false;
    /** Segundos restantes para restaurar el sistema de gestión. */
    private int tiempoReparacionGestion = 0;
    /** Segundos restantes para restaurar las cadenas de montaje. */
    private int tiempoReparacionCadenas = 0;
    /** Marca si ya se ha generado el apagón en esta simulación. */
    private boolean apagonGenerado = false;

    /** Número de averías generadas en la cadena Biplaza. */
    private int averiasBiplaza = 0;
    /** Número de averías generadas en la cadena Turismo. */
    private int averiasTurismo = 0;
    /** Número de averías generadas en la cadena Furgoneta. */
    private int averiasFurgoneta = 0;

    /** Lista de observadores suscritos al planificador. */
    private ArrayList<Observador> observadores;

    /** Robots de las 4 estaciones de la cadena Biplaza. */
    private Robot[] robotsBiplaza = new Robot[4];
    /** Robots de las 4 estaciones de la cadena Turismo. */
    private Robot[] robotsTurismo = new Robot[4];
    /** Robots de las 4 estaciones de la cadena Furgoneta. */
    private Robot[] robotsFurgoneta = new Robot[4];

    /** Mecánicos disponibles para resolver averías (uno por cadena). */
    private Mecanico[] mecanicos;
    /** Administrador de sistema responsable de restaurar tras el apagón. */
    private AdministradorSistema admin;
    /** Gestor de planta encargado de avisos y llamadas al mecánico. */
    private GestorPlanta gestorPlanta;

    /**
     * Construye un planificador con los recursos necesarios para simular la
     * fábrica. Asocia operarios reales a los robots si los hay; en caso
     * contrario genera operarios sintéticos para cubrir las 4 estaciones de
     * las 3 cadenas (12 operarios).
     *
     * @param cadenaMontaje   cadena de montaje sobre la que operar
     * @param sistemaGestion  sistema de gestión para stock e historial
     * @param tipoSimulacion  nivel de simulación (1, 2 o 3)
     * @param mecanicos       mecánicos disponibles
     * @param admin           administrador del sistema (puede ser {@code null}
     *                        en niveles que no lo requieran)
     */
    public Planificador(CadenaMontaje cadenaMontaje, SistemaGestion sistemaGestion, int tipoSimulacion, Mecanico[] mecanicos, AdministradorSistema admin) {
        this.cadenaMontaje = cadenaMontaje;
        this.sistemaGestion = sistemaGestion;
        this.tipoSimulacion = tipoSimulacion;
        this.mecanicos = mecanicos;
        this.admin = admin;

        List<GestorPlanta> gestores = sistemaGestion.consultarGestorPlanta();
        this.gestorPlanta = gestores.isEmpty() ? null : gestores.get(0);
        this.observadores = new ArrayList<>();

        String[] componentes = { "Chasis", "Motor", "Tapicería", "Ruedas" };
        List<Operario> registrados = sistemaGestion.consultarOperario();
        int indice = 0;
        for (int i = 0; i < 4; i++) {
            robotsBiplaza[i]   = new Robot("Robot Biplaza"   + componentes[i],
                obtenerOperario(registrados, indice++, "Biplaza",   i));
            robotsTurismo[i]   = new Robot("Robot Turismo"   + componentes[i],
                obtenerOperario(registrados, indice++, "Turismo",   i));
            robotsFurgoneta[i] = new Robot("Robot Furgoneta" + componentes[i],
                obtenerOperario(registrados, indice++, "Furgoneta", i));
        }
    }

    /**
     * Devuelve un operario para la cadena/estación indicada. Si hay operarios
     * registrados, los recorre en orden circular; si no, genera uno sintético.
     *
     * @param registrados lista de operarios registrados
     * @param indice         índice de asignación
     * @param cadena      nombre de la cadena (Biplaza/Turismo/Furgoneta)
     * @param posicion    posición dentro de la cadena (0-3)
     * @return operario asignado
     */
    private Operario obtenerOperario(List<Operario> registrados, int indice, String cadena, int posicion) {
        if (!registrados.isEmpty()) {
            return registrados.get(indice % registrados.size());
        }
        return crearOperarioAleatorio(cadena, posicion);
    }

    /**
     * Crea un operario sintético con datos aleatorios cuando no hay
     * operarios registrados. La mitad de las veces el operario es eficiente
     * (más de 10 montajes ya realizados).
     *
     * @param nombreCadena nombre de la cadena para componer el nombre
     * @param indice       índice de la estación
     * @return operario generado
     */
    private Operario crearOperarioAleatorio(String nombreCadena, int indice) {

        String sufijo = nombreCadena + "_" + indice + "_" + System.nanoTime();
        String nombre = "Operario_" + nombreCadena + "_" + indice;
        String apellidos = "Apellido_" + sufijo;
        String dni = String.format("%08d", Math.abs(sufijo.hashCode()) % 100000000) + "A";
        String direccion = "Calle " + sufijo;
        String seguridadSocial = "Seguridad Social_" + sufijo;
        double salario = 25000;
        LocalDate fechaIngreso = LocalDate.now();

        Operario op = new Operario(nombre, apellidos, dni, direccion, seguridadSocial, salario, fechaIngreso);

        boolean esEficiente = Math.random() < 0.5;
        if (esEficiente) {
            for (int j = 0; j < 11; j++) {
                op.registrarMontajeCompletado();
            }
        }

        return op;
    }

    /**
     * Inicia la simulación y avanza segundo a segundo hasta que todas las
     * cadenas hayan terminado o se alcance el tope de 500 ticks. En cada
     * tick:
     * <ol>
     *   <li>Resuelve incidencias pendientes (apagón y averías).</li>
     *   <li>Avanza los vehículos por las estaciones libres.</li>
     *   <li>Genera nuevos eventos (averías y apagón).</li>
     *   <li>Comprueba si la fabricación ha terminado.</li>
     * </ol>
     * Al finalizar imprime un resumen de la actividad de los trabajadores.
     */
    public void iniciarSimulacion() {

        int purgados = cadenaMontaje.purgarTerminados();
        if (purgados > 0) {
            System.out.println("[INFO] Purgados " + purgados
                + " vehículos ya terminados de la cadena activa.");
        }

        int segundo = 1;
        int maxTicks = 500;
        boolean montajeTerminado = false;

        while (!montajeTerminado && segundo <= maxTicks) {
            System.out.println("\n--- T=" + segundo + " s ---");
            resolverIncidencias();
            trabajarEnEstaciones();
            generarEventos(segundo);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            segundo++;
            montajeTerminado = comprobarFinMontaje();
        }
        if (segundo > maxTicks) {
            System.out.println("\n[ABORTADA] Se alcanzó el tope de " + maxTicks
                + " segundos. Revise mecánicos / stock.");
        } else {
            System.out.println("\nSimulación terminada en " + (segundo - 1) + " segundos.");
        }
        imprimirResumenTrabajadores();
    }

    /**
     * Hace trabajar a todas las cadenas durante un tick. Si hay un apagón,
     * todas las cadenas quedan paradas.
     */
    private void trabajarEnEstaciones() {

        if (caidaDeLuz) {
            System.out.println("[APAGÓN] Sistema de gestión bloqueado: " + sistemaGestionBloqueado + ". Cadenas paradas.");
            return;
        }
        procesarCadenaConRobots(cadenaMontaje.getCadenaBiplaza(),robotsBiplaza,"Biplaza");
        procesarCadenaConRobots(cadenaMontaje.getCadenaTurismo(),robotsTurismo,"Turismo");
        procesarCadenaConRobots(cadenaMontaje.getCadenaFurgoneta(),robotsFurgoneta,"Furgoneta");
    }

    /**
     * Avanza una unidad de tiempo en una cadena concreta. Para cada coche:
     * <ul>
     *   <li>Si está terminado o averiado, lo salta.</li>
     *   <li>Selecciona el robot que corresponde a su estado actual.</li>
     *   <li>Si el robot está libre, le entrega el coche; si está ocupado con
     *       otro coche, espera al siguiente tick.</li>
     *   <li>Cuando el robot completa su trabajo, consume el componente
     *       correspondiente del stock, lo monta en el coche y avanza al
     *       siguiente estado. Si llega a TERMINADO, registra el vehículo
     *       en el almacén.</li>
     * </ul>
     *
     * @param lista        lista de coches de la cadena
     * @param robots       robots de las 4 estaciones de la cadena
     * @param nombreCadena nombre legible de la cadena (para logs)
     */
    private void procesarCadenaConRobots(ArrayList<? extends Coche> lista,Robot[] robots, String nombreCadena) {

        int i = 0;
        for (Coche c : lista) {
            if (c.getEstadoMontaje() == EstadoMontaje.TERMINADO) {
                i++;
                continue;
            }
            if (c.isAveriado()) {
                notifyObservadores("[AVISO] Coche " + nombreCadena + " #" + (i + 1) + " averiado - esperando mecánico.");
                i++;
                continue;
            }

            EstadoMontaje estadoActual = c.getEstadoMontaje();
            if (estadoActual == null) {
                estadoActual = EstadoMontaje.CHASIS;
                c.setEstadoMontaje(EstadoMontaje.CHASIS);
            }

            int indexRobot;
            EstadoMontaje estadoSiguiente;

            switch (estadoActual) {
                case CHASIS:
                    indexRobot = 0;
                    estadoSiguiente = EstadoMontaje.MOTOR;
                    break;
                case MOTOR:
                    indexRobot = 1;
                    estadoSiguiente = EstadoMontaje.TAPICERIA;
                    break;
                case TAPICERIA:
                    indexRobot = 2;
                    estadoSiguiente = EstadoMontaje.RUEDAS;
                    break;
                case RUEDAS:
                    indexRobot = 3;
                    estadoSiguiente = EstadoMontaje.TERMINADO;
                    break;
                default:
                    i++;
                    continue;
            }

            Robot robotAsignado = robots[indexRobot];

            if (robotAsignado.getCocheActual() != c) {
                if (robotAsignado.estaLibre()) {
                    robotAsignado.recibirCoche(c);
                } else {
                    i++;
                    continue;
                }
            }

            boolean terminado = robotAsignado.trabajar();
            if (terminado) {
                String detalleComponente = "";
                switch (estadoSiguiente) {
                    case MOTOR: {
                        Motor m = sistemaGestion.quitarStockMotor();
                        if (m != null) {
                            c.setMotor(m);
                            detalleComponente = " | Motor=" + m.tipoMotor() + " (" + m.getCilindrada() + "cc, " + m.getPotencia() + "CV)";
                        }
                        break;
                    }
                    case TAPICERIA: {
                        Tapiceria t = sistemaGestion.quitarStockTapiceria();
                        if (t != null) {
                            c.setTapiceria(t);
                            detalleComponente = " | Tapicería=" + t.tipoTapiceria() + " (" + t.getColor() + ")";
                        }
                        break;
                    }
                    case RUEDAS: {
                        Rueda[] r = sistemaGestion.quitarStockRuedas();
                        if (r != null) {
                            c.setRueda(r);
                            detalleComponente = " | Ruedas=" + r[0].tipoRueda() + " (" + r[0].getAncho() + "mm)";
                        }
                        break;
                    }
                    default:
                        break;
                }

                c.setEstadoMontaje(estadoSiguiente);
                robotAsignado.liberarCoche();

                if (estadoSiguiente == EstadoMontaje.TERMINADO) {
                    if (c instanceof BiplazaDeportivo) {
                        sistemaGestion.registrarBiplazaDeportivo((BiplazaDeportivo) c);
                    } else if (c instanceof Turismo) {
                        sistemaGestion.registrarTurismo((Turismo) c);
                    } else if (c instanceof Furgoneta) {
                        sistemaGestion.registrarFurgoneta((Furgoneta) c);
                    }
                }

                sistemaGestion.registrarHistorial(nombreCadena + " #" + (i + 1), estadoActual + " -> " + estadoSiguiente + detalleComponente);

                String msg = "[" + nombreCadena + " #" + (i + 1) + "] " + estadoActual + " -> " + estadoSiguiente + detalleComponente;
                cadenaMontaje.notifyObservadores(msg);
            }
            i++;
        }
    }

    /**
     * Genera eventos aleatorios (averías y apagón) según el nivel de
     * simulación. Las averías iniciales se producen de forma determinista
     * en los primeros segundos; a partir de la tercera avería pueden
     * surgir nuevas con probabilidad del 15% por tick (hasta un máximo de
     * 2 ó 3 según el nivel). En nivel 3, a partir del segundo 3 se genera
     * un apagón único que bloquea la fábrica.
     *
     * @param segundo tick actual de la simulación
     */
    private void generarEventos(int segundo) {
        if (tipoSimulacion == 1) return;

        if (averiasBiplaza < 2 && segundo == 1 + averiasBiplaza * 2) {
            if (generarAveriaLista(cadenaMontaje.getCadenaBiplaza(), "Biplaza")) {
                averiasBiplaza++;
            }
        }
        if (averiasTurismo < 2 && segundo == 1 + averiasTurismo * 2) {
            if (generarAveriaLista(cadenaMontaje.getCadenaTurismo(), "Turismo")) {
                averiasTurismo++;
            }
        }
        if (averiasFurgoneta < 2 && segundo == 1 + averiasFurgoneta * 2) {
            if (generarAveriaLista(cadenaMontaje.getCadenaFurgoneta(), "Furgoneta")) {
                averiasFurgoneta++;
            }
        }

        int maxAverias = (tipoSimulacion == 3) ? 3 : 2;
        if (averiasBiplaza < maxAverias && averiasBiplaza >= 2 && Math.random() < 0.15) {
            if (generarAveriaLista(cadenaMontaje.getCadenaBiplaza(), "Biplaza")) {
                averiasBiplaza++;
            }
        }
        if (averiasTurismo < maxAverias && averiasTurismo >= 2 && Math.random() < 0.15) {
            if (generarAveriaLista(cadenaMontaje.getCadenaTurismo(), "Turismo")) {
                averiasTurismo++;
            }
        }
        if (averiasFurgoneta < maxAverias && averiasFurgoneta >= 2 && Math.random() < 0.15) {
            if (generarAveriaLista(cadenaMontaje.getCadenaFurgoneta(), "Furgoneta")) {
                averiasFurgoneta++;
            }
        }

        if (tipoSimulacion == 3 && !apagonGenerado && segundo >= 3) {
            caidaDeLuz = true;
            sistemaGestionBloqueado = true;
            tiempoReparacionGestion = 2;
            tiempoReparacionCadenas = 3;
            apagonGenerado = true;
            String msg = "La luz se ha caído - El Administrador trabajará para restaurarla.";
            cadenaMontaje.notifyObservadores(msg);
        }
    }

    /**
     * Avería el primer coche no terminado y no averiado de la lista. Marca
     * el tiempo de reparación como pendiente de asignación (-1) y notifica
     * al gestor de planta.
     *
     * @param lista        lista de coches candidatos a averiarse
     * @param nombreCadena nombre legible de la cadena
     * @return {@code true} si se averió un coche; {@code false} si no había
     *         candidatos
     */
    private boolean generarAveriaLista(ArrayList<? extends Coche> lista, String nombreCadena) {
        for (Coche c : lista) {
            if (c.getEstadoMontaje() != EstadoMontaje.TERMINADO && !c.isAveriado()) {
                c.setAveriado(true);
                c.setTiempoReparacion(-1);

                if (gestorPlanta != null) {
                    gestorPlanta.consultarDashboard();
                }

                String quien = (gestorPlanta != null) ? "El Gestor de Planta " + gestorPlanta.getNombre() + " " + gestorPlanta.getApellidos() : "El Gestor de Planta";
                String msg = "Se ha detectado avería en cadena " + nombreCadena + " - " + quien + " llamará al mecánico.";
                cadenaMontaje.notifyObservadores(msg);
                return true;
            }
        }
        return false;
    }

    /**
     * Resuelve incidencias activas en el tick actual: avance del tiempo de
     * restauración del apagón y reparación progresiva de cada coche
     * averiado por su mecánico asignado.
     */
    private void resolverIncidencias() {
        if (tipoSimulacion == 1) {
            return;
        }

        if (tipoSimulacion == 3 && caidaDeLuz) {

            if (sistemaGestionBloqueado) {
                tiempoReparacionGestion--;
                if (tiempoReparacionGestion <= 0 && admin != null) {
                    admin.restaurarSistemaGestion(this);
                }
            }
            tiempoReparacionCadenas--;
            if (tiempoReparacionCadenas <= 0 && admin != null) {
                admin.restaurarCadenasMontaje(this);
            }
        }

        resolverAveriasConMecanico(cadenaMontaje.getCadenaBiplaza(), 0);
        resolverAveriasConMecanico(cadenaMontaje.getCadenaTurismo(), 1);
        resolverAveriasConMecanico(cadenaMontaje.getCadenaFurgoneta(), 2);
    }

    /**
     * Para cada coche averiado de la lista, decrementa su contador de
     * reparación según el mecánico asignado. Cuando el contador llega a 0,
     * el gestor llama al mecánico para que repare el coche y se emite el
     * aviso correspondiente.
     *
     * @param lista     lista de coches a revisar
     * @param indexMec  índice del mecánico asignado (módulo nº mecánicos)
     */
    private void resolverAveriasConMecanico(ArrayList<? extends Coche> lista, int indexMec) {
        if (mecanicos == null || mecanicos.length == 0) {
            return;
        }
        Mecanico mec = mecanicos[indexMec % mecanicos.length];

        for (Coche c : lista) {
            if (c.isAveriado()) {
                if (c.getTiempoReparacion() == -1) {
                    c.setTiempoReparacion(mec.getTiempoReparacion());
                }

                c.setTiempoReparacion(c.getTiempoReparacion() - 1);

                if (c.getTiempoReparacion() <= 0) {
                    if (gestorPlanta != null) {
                        gestorPlanta.llamarMecanico(mec, c);
                    } else {
                        mec.repararCoche(c);
                    }
                    String perfil = mec.esEficiente() ? "eficiente" : "estándar";
                    String msg = mec.getNombre() + " " + mec.getApellidos() + " (" + perfil + ") ha completado la reparación (" + mec.getReparacionesRealizadas() + " reparaciones totales).";
                    cadenaMontaje.notifyObservadores(msg);
                }
            }
        }
    }

    /**
     * @return {@code true} si las 3 cadenas tienen todos sus coches en
     *         estado TERMINADO
     */
    private boolean comprobarFinMontaje() {
        return todosTerminados(cadenaMontaje.getCadenaBiplaza())
                && todosTerminados(cadenaMontaje.getCadenaTurismo())
                && todosTerminados(cadenaMontaje.getCadenaFurgoneta());
    }

    /**
     * @param lista lista de coches a evaluar
     * @return {@code true} si la lista está vacía o todos sus coches están
     *         en estado TERMINADO
     */
    private boolean todosTerminados(ArrayList<? extends Coche> lista) {
        if (lista.isEmpty()) {
            return true;
        }
        for (Coche c : lista) {
            if (c.getEstadoMontaje() != EstadoMontaje.TERMINADO) {
                return false;
            }
        }
        return true;
    }

    /**
     * Imprime por consola un resumen final del trabajo realizado por
     * mecánicos y administrador, adaptado al nivel de simulación.
     */
    private void imprimirResumenTrabajadores() {
        notifyObservadores("RESUMEN");

        if (tipoSimulacion >= 2 && mecanicos != null) {
            for (Mecanico m : mecanicos) {
                System.out.println(" El mecánico " + m.getNombre() + " " + m.getApellidos() + " ha hecho " + m.getReparacionesRealizadas() + " reparaciones.");
            }
        }
        if (tipoSimulacion == 3 && admin != null) {
            System.out.println(" El administrador " + admin.getNombre() + " " + admin.getApellidos() + " ha restaurado la luz: " + admin.getRestauracionesRealizadas() + " veces.");
        }
        if (tipoSimulacion == 1) {
            System.out.println(" Simulación simple completada sin incidencias.");
        }
    }

    /** {@inheritDoc} */
    @Override
    public void notifyObservadores(String message) {
        for (Observador observador : observadores) {
            observador.update(message);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void addObservador(Observador observador) {
        observadores.add(observador);
    }

    /** {@inheritDoc} */
    @Override
    public void removeObservador(Observador observador) {
        observadores.remove(observador);
    }

    /** @return cadena de montaje asociada */
    public CadenaMontaje getCadenaMontaje() {
        return cadenaMontaje;
    }

    /** @param cadenaMontaje nueva cadena de montaje */
    public void setCadenaMontaje(CadenaMontaje cadenaMontaje) {
        this.cadenaMontaje = cadenaMontaje;
    }

    /** @return sistema de gestión asociado */
    public SistemaGestion getSistemaGestion() {
        return sistemaGestion;
    }

    /** @param sistemaGestion nuevo sistema de gestión */
    public void setSistemaGestion(SistemaGestion sistemaGestion) {
        this.sistemaGestion = sistemaGestion;
    }

    /** @return nivel de simulación (1, 2 ó 3) */
    public int getTipoSimulacion() {
        return tipoSimulacion;
    }

    /** @param tipoSimulacion nuevo nivel de simulación */
    public void setTipoSimulacion(int tipoSimulacion) {
        this.tipoSimulacion = tipoSimulacion;
    }

    /** @return {@code true} si actualmente hay apagón */
    public boolean isCaidaDeLuz() {
        return caidaDeLuz;
    }

    /** @param caidaDeLuz nuevo estado de apagón */
    public void setCaidaDeLuz(boolean caidaDeLuz) {
        this.caidaDeLuz = caidaDeLuz;
    }

    /** @return {@code true} si el sistema de gestión está bloqueado */
    public boolean isSistemaGestionBloqueado() {
        return sistemaGestionBloqueado;
    }

    /** @param sistemaGestionBloqueado nuevo estado de bloqueo */
    public void setSistemaGestionBloqueado(boolean sistemaGestionBloqueado) {
        this.sistemaGestionBloqueado = sistemaGestionBloqueado;
    }
}

package com.practica.planificador;

import com.practica.dashboard.Observable;
import com.practica.dashboard.Observador;
import com.practica.fabrica.SistemaGestion;
import com.practica.montaje.CadenaMontaje;
import com.practica.vehiculo.*;
import com.practica.montaje.Robot;
import com.practica.personal.Operario;
import java.time.LocalDate;

import java.util.ArrayList;

/**
 * Write a description of class Planificador here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Planificador implements Observable {
    // instance variables - replace the example below with your own
    private CadenaMontaje cadenaMontaje;
    private SistemaGestion sistemaGestion;
    private int tipoSimulacion;

    private boolean caidaDeLuz = false;
    private int tiempoReparacionLuz = 0;
    private boolean apagonGenerado = false;

    private int averiasBiplaza = 0;
    private int averiasTurismo = 0;
    private int averiasFurgoneta = 0;

    private ArrayList<Observador> observadores;
    
    private Robot[] robotsBiplaza = new Robot[4];
    private Robot[] robotsTurismo = new Robot[4];
    private Robot[] robotsFurgoneta = new Robot[4];

    /**
     * Constructor for objects of class Planificador
     */
    public Planificador(CadenaMontaje cadenaMontaje, SistemaGestion sistemaGestion, int tipoSimulacion) {
        this.cadenaMontaje = cadenaMontaje;
        this.sistemaGestion = sistemaGestion;
        this.tipoSimulacion = tipoSimulacion;
        observadores = new ArrayList<>();
        
        for(int i=0; i<4; i++) {
            robotsBiplaza[i] = new Robot("Robot Biplaza " + i, new Operario("Juan", "Perez Blanco"+i,
                    "1234567k", "Curros Enriquez",
                    "98", 30000, LocalDate.now()));
            robotsTurismo[i] = new Robot("Robot Turismo " + i, new Operario("Olga", "Jimenez Dia"+i,
                    "2345678v", "Gran Via",
                    "45", 30000, LocalDate.now()));
            robotsFurgoneta[i] = new Robot("Robot Furgoneta " + i, new Operario("Pablo", "Dominguez Ramos"+i,
                    "3456789m", "Valencia",
                    "12", 30000, LocalDate.now()));
        }
    }

    public void iniciarSimulacion() {
        int segundo = 1;
        boolean montajeTerminado = false;

        while (!montajeTerminado) {
            System.out.println("--- T=" + segundo + " Segundos ---");
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
        System.out.println("\nSimulación terminada en " + (segundo - 1) + " segundos.");
    }

    private void procesarCadenaConRobots(ArrayList<? extends Coche> lista, Robot[] robots, String nombreCadena) {
        if (caidaDeLuz) {
            System.out.println("[APAGON] La cadena " + nombreCadena + " está detenida por falta de luz.");
            return;
        }

        int i = 0;
        for (Coche c : lista) {
            if (c.getEstadoMontaje() == EstadoMontaje.TERMINADO) {
                i++;
                continue;
            }

            if (c.isAveriado()) {
                System.out.println("[AVISO] El coche " + nombreCadena + " #" + (i + 1) + " está averiado. No avanza.");
                i++;
                continue;
            }

            EstadoMontaje estadoActual = c.getEstadoMontaje();
            if (estadoActual == null) {
                estadoActual = EstadoMontaje.CHASIS;
                c.setEstadoMontaje(EstadoMontaje.CHASIS);
            }

            int indexRobot = 0;
            EstadoMontaje estadoSiguiente = EstadoMontaje.TERMINADO;

            if (estadoActual == EstadoMontaje.CHASIS) {
                indexRobot = 0;
                estadoSiguiente = EstadoMontaje.MOTOR;
            }
            else if (estadoActual == EstadoMontaje.MOTOR) {
                indexRobot = 1;
                estadoSiguiente = EstadoMontaje.TAPICERIA;
            }
            else if (estadoActual == EstadoMontaje.TAPICERIA) {
                indexRobot = 2;
                estadoSiguiente = EstadoMontaje.RUEDAS;
            }
            else if (estadoActual == EstadoMontaje.RUEDAS) {
                indexRobot = 3;
                estadoSiguiente = EstadoMontaje.TERMINADO;
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
                c.setEstadoMontaje(estadoSiguiente);
                robotAsignado.liberarCoche();
                String msg = "[" + nombreCadena + " #" + (i + 1) + "] "
                        + estadoActual + " -> " + estadoSiguiente;
                System.out.println(msg);
                cadenaMontaje.notifyObservadores(msg);
            }
            i++;
        }
    }

    private void generarEventos(int t) {
        if (tipoSimulacion == 1) {
            return;
        }

        int maxAverias = (tipoSimulacion == 3) ? 3 : 2;

        if (averiasBiplaza < maxAverias && Math.random() < 0.2) {
            if (generarAveriaLista(cadenaMontaje.getCadenaBiplaza()))
                averiasBiplaza++;
        }
        if (averiasTurismo < maxAverias && Math.random() < 0.2) {
            if (generarAveriaLista(cadenaMontaje.getCadenaTurismo()))
                averiasTurismo++;
        }
        if (averiasFurgoneta < maxAverias && Math.random() < 0.2) {
            if (generarAveriaLista(cadenaMontaje.getCadenaFurgoneta()))
                averiasFurgoneta++;
        }

        if (tipoSimulacion == 3 && !apagonGenerado && Math.random() < 0.1) {
            caidaDeLuz = true;
            tiempoReparacionLuz = 3;
            apagonGenerado = true;
            String msg = "CAÍDA DE LUZ DETECTADA";
            System.out.println(msg);
            cadenaMontaje.notifyObservadores(msg);
        }
    }

    private boolean generarAveriaLista(ArrayList<? extends Coche> lista) {
        for (Coche c : lista) {
            if (c.getEstadoMontaje() != EstadoMontaje.TERMINADO && !c.isAveriado()) {
                c.setAveriado(true);
                c.setTiempoReparacion(3);
                return true;
            }
        }
        return false;
    }

    private void resolverIncidencias() {
        if (tipoSimulacion == 1) {
            return;
        }

        if (tipoSimulacion == 3 && caidaDeLuz) {
            tiempoReparacionLuz--;
            if (tiempoReparacionLuz <= 0) {
                caidaDeLuz = false;
                String msg = "El Administrador ha devuelto la luz a la fábrica";
                System.out.println(msg);
                cadenaMontaje.notifyObservadores(msg);
            }
        }

        resolverAveriasLista(cadenaMontaje.getCadenaBiplaza());
        resolverAveriasLista(cadenaMontaje.getCadenaTurismo());
        resolverAveriasLista(cadenaMontaje.getCadenaFurgoneta());
    }

    private void resolverAveriasLista(ArrayList<? extends Coche> lista) {
        for (Coche c : lista) {
            if (c.isAveriado()) {
                c.setTiempoReparacion(c.getTiempoReparacion() - 1);
                if (c.getTiempoReparacion() <= 0) {
                    c.setAveriado(false);
                    String msg = "Un Mecánico ha arreglado la avería de un coche";
                    System.out.println(msg);
                    cadenaMontaje.notifyObservadores(msg);
                }
            }
        }
    }

    private void trabajarEnEstaciones() {
        procesarCadenaConRobots(cadenaMontaje.getCadenaBiplaza(), robotsBiplaza, "Biplaza");
        procesarCadenaConRobots(cadenaMontaje.getCadenaTurismo(), robotsTurismo, "Turismo");
        procesarCadenaConRobots(cadenaMontaje.getCadenaFurgoneta(), robotsFurgoneta, "Furgoneta");
    }

    private boolean comprobarFinMontaje() {
        return todosTerminados(cadenaMontaje.getCadenaBiplaza())
                && todosTerminados(cadenaMontaje.getCadenaTurismo())
                && todosTerminados(cadenaMontaje.getCadenaFurgoneta());
    }

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

    @Override
    public void notifyObservadores(String message) {
        for (Observador observador : observadores) {
            observador.update(message);
        }
    }

    @Override
    public void removeObservador(Observador observador) {
        observadores.remove(observador);
    }

    @Override
    public void addObservador(Observador observador) {
        observadores.add(observador);
    }

    public CadenaMontaje getCadenaMontaje() {
        return cadenaMontaje;
    }

    public void setCadenaMontaje(CadenaMontaje cadenaMontaje) {
        this.cadenaMontaje = cadenaMontaje;
    }

    public SistemaGestion getSistemaGestion() {
        return sistemaGestion;
    }

    public void setSistemaGestion(SistemaGestion sistemaGestion) {
        this.sistemaGestion = sistemaGestion;
    }

    public int getTipoSimulacion() {
        return tipoSimulacion;
    }

    public void setTipoSimulacion(int tipoSimulacion) {
        this.tipoSimulacion = tipoSimulacion;
    }
}
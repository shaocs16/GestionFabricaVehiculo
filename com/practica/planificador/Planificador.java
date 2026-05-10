package com.practica.planificador;

import com.practica.dashboard.Observable;
import com.practica.dashboard.Observador;
import com.practica.fabrica.SistemaGestion;
import com.practica.montaje.CadenaMontaje;
import com.practica.vehiculo.*;

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

    /**
     * Constructor for objects of class Planificador
     */
    public Planificador(CadenaMontaje cadenaMontaje, SistemaGestion sistemaGestion, int tipoSimulacion) {
        this.cadenaMontaje = cadenaMontaje;
        this.sistemaGestion = sistemaGestion;
        this.tipoSimulacion = tipoSimulacion;
        observadores = new ArrayList<>();
    }

    public void iniciarSimulacion() {
        int segundo = 1;
        boolean montajeTerminado = false;

        while (!montajeTerminado) {
            System.out.println("--- T=" + segundo + " Segundos ---");
            // 1. Resolver problemas actuales (si los hay)
            resolverIncidencias();

            // 2. Ejecutar acciones de los operarios en cada estación
            trabajarEnEstaciones();

            // 3. Avanzar vehículos a la siguiente estación (si la tarea terminó)
            avanzarCadenas();

            // 4. Generar eventos aleatorios según el tipo de simulación
            generarEventos(segundo);

            // 5. Esperar un segundo real para simular el reloj
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

    private void avanzarCadenas() {

        avanzarEstadosCadena(cadenaMontaje.getCadenaBiplaza(), "Biplaza");
        avanzarEstadosCadena(cadenaMontaje.getCadenaTurismo(), "Turismo");
        avanzarEstadosCadena(cadenaMontaje.getCadenaFurgoneta(), "Furgoneta");

    }

    private void avanzarEstadosCadena(ArrayList<? extends Coche> lista, String nombreCadena) {
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
            EstadoMontaje estadoSiguiente;

            switch (estadoActual) {
                case CHASIS:
                    estadoSiguiente = EstadoMontaje.MOTOR;
                    break;
                case MOTOR:
                    estadoSiguiente = EstadoMontaje.TAPICERIA;
                    break;
                case TAPICERIA:
                    estadoSiguiente = EstadoMontaje.RUEDAS;
                    break;
                case RUEDAS:
                    estadoSiguiente = EstadoMontaje.TERMINADO;
                    break;
                default:
                    estadoSiguiente = EstadoMontaje.TERMINADO;
                    break;
            }

            c.setEstadoMontaje(estadoSiguiente);
            String msg = "[" + nombreCadena + " #" + (i + 1) + "] "
                    + estadoActual + " -> " + estadoSiguiente;
            System.out.println(msg);
            cadenaMontaje.notifyObservadores(msg);
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
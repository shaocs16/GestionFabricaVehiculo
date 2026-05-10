package com.practica.planificador;

import com.practica.dashboard.Observable;
import com.practica.dashboard.Observador;
import com.practica.fabrica.SistemaGestion;
import com.practica.montaje.CadenaMontaje;
import com.practica.vehiculo.*;
import com.practica.montaje.Robot;
import com.practica.personal.AdministradorSistema;
import com.practica.personal.Mecanico;
import com.practica.personal.Operario;
import java.time.LocalDate;
import java.util.ArrayList;

public class Planificador implements Observable {

    private CadenaMontaje cadenaMontaje;
    private SistemaGestion sistemaGestion;
    private int tipoSimulacion;

    private boolean caidaDeLuz = false;
    private boolean sistemaGestionBloqueado = false;
    private int tiempoReparacionLuz = 0;
    private boolean apagonGenerado = false;

    private int averiasBiplaza = 0;
    private int averiasTurismo = 0;
    private int averiasFurgoneta = 0;

    private ArrayList<Observador> observadores;

    private Robot[] robotsBiplaza = new Robot[4];
    private Robot[] robotsTurismo = new Robot[4];
    private Robot[] robotsFurgoneta = new Robot[4];

    private Mecanico[] mecanicos;
    private AdministradorSistema admin;

    public Planificador(CadenaMontaje cadenaMontaje, SistemaGestion sistemaGestion,
            int tipoSimulacion,
            Mecanico[] mecanicos, AdministradorSistema admin) {
        this.cadenaMontaje = cadenaMontaje;
        this.sistemaGestion = sistemaGestion;
        this.tipoSimulacion = tipoSimulacion;
        this.mecanicos = mecanicos;
        this.admin = admin;
        this.observadores = new ArrayList<>();

        String[] componentes = { "Chasis", "Motor", "Tapicería", "Ruedas" };
        for (int i = 0; i < 4; i++) {
            robotsBiplaza[i] = new Robot(
                    "Robot Biplaza-" + componentes[i],
                    new Operario("Juan", "Pérez Blanco" + i, "1234567K",
                            "Calle Mayor 1", "98" + i, 30000, LocalDate.now()));
            robotsTurismo[i] = new Robot(
                    "Robot Turismo-" + componentes[i],
                    new Operario("Olga", "Jiménez Díaz" + i, "2345678V",
                            "Gran Vía 5", "45" + i, 30000, LocalDate.now()));
            robotsFurgoneta[i] = new Robot(
                    "Robot Furgoneta-" + componentes[i],
                    new Operario("Pablo", "Domínguez Ramos" + i, "3456789M",
                            "Av. Valencia 8", "12" + i, 30000, LocalDate.now()));
        }
    }

    public void iniciarSimulacion() {
        int segundo = 1;
        boolean montajeTerminado = false;

        while (!montajeTerminado) {
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
        System.out.println("\nSimulación terminada en " + (segundo - 1) + " segundos.");
        imprimirResumenTrabajadores();
    }

    private void trabajarEnEstaciones() {
        procesarCadenaConRobots(cadenaMontaje.getCadenaBiplaza(), robotsBiplaza, "Biplaza");
        procesarCadenaConRobots(cadenaMontaje.getCadenaTurismo(), robotsTurismo, "Turismo");
        procesarCadenaConRobots(cadenaMontaje.getCadenaFurgoneta(), robotsFurgoneta, "Furgoneta");
    }

    private void procesarCadenaConRobots(ArrayList<? extends Coche> lista,
            Robot[] robots, String nombreCadena) {
        if (caidaDeLuz) {
            System.out.println("[APAGÓN] La cadena " + nombreCadena
                    + " está detenida por falta de luz.");
            return;
        }

        int i = 0;
        for (Coche c : lista) {
            if (c.getEstadoMontaje() == EstadoMontaje.TERMINADO) {
                i++;
                continue;
            }
            if (c.isAveriado()) {
                System.out.println("[AVISO] Coche " + nombreCadena + " #" + (i + 1)
                        + " averiado — esperando mecánico.");
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
                c.setEstadoMontaje(estadoSiguiente);
                robotAsignado.liberarCoche();
                String msg = "[" + nombreCadena + " #" + (i + 1) + "] "
                        + estadoActual + " → " + estadoSiguiente;
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
            if (generarAveriaLista(cadenaMontaje.getCadenaBiplaza(), "Biplaza")) {
                averiasBiplaza++;
            }
        }
        if (averiasTurismo < maxAverias && Math.random() < 0.2) {
            if (generarAveriaLista(cadenaMontaje.getCadenaTurismo(), "Turismo")) {
                averiasTurismo++;
            }
        }
        if (averiasFurgoneta < maxAverias && Math.random() < 0.2) {
            if (generarAveriaLista(cadenaMontaje.getCadenaFurgoneta(), "Furgoneta")) {
                averiasFurgoneta++;
            }
        }

        if (tipoSimulacion == 3 && !apagonGenerado && Math.random() < 0.1) {
            caidaDeLuz = true;
            sistemaGestionBloqueado = true;
            tiempoReparacionLuz = 3;
            apagonGenerado = true;
            String msg = "La luz se ha caído — El Administrador trabajará para restaurarla.";
            System.out.println(msg);
            cadenaMontaje.notifyObservadores(msg);
        }
    }

    private boolean generarAveriaLista(ArrayList<? extends Coche> lista, String nombreCadena) {
        for (Coche c : lista) {
            if (c.getEstadoMontaje() != EstadoMontaje.TERMINADO && !c.isAveriado()) {
                c.setAveriado(true);
                c.setTiempoReparacion(3);
                String msg = "Se ha detectado avería en cadena " + nombreCadena
                        + " — El Gestor de Planta llamará al mecánico.";
                System.out.println(msg);
                cadenaMontaje.notifyObservadores(msg);
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
            
            if (tiempoReparacionLuz == 1 && admin != null) {
                admin.restaurarSistemaGestion(this);
            }
            if (tiempoReparacionLuz <= 0 && admin != null) {
                admin.restaurarCadenasMontaje(this);
            }
        }

        resolverAveriasConMecanico(cadenaMontaje.getCadenaBiplaza(), 0);
        resolverAveriasConMecanico(cadenaMontaje.getCadenaTurismo(), 1);
        resolverAveriasConMecanico(cadenaMontaje.getCadenaFurgoneta(), 2);
    }

    private void resolverAveriasConMecanico(ArrayList<? extends Coche> lista, int indexMec) {
        if (mecanicos == null || indexMec >= mecanicos.length) {
            return;
        }
        Mecanico mec = mecanicos[indexMec];

        for (Coche c : lista) {
            if (c.isAveriado()) {
                c.setTiempoReparacion(c.getTiempoReparacion() - 1);
                if (c.getTiempoReparacion() <= 0) {
                    mec.repararCoche(c); // método real del Mecánico
                    String msg = mec.getNombre() + " " + mec.getApellidos()
                            + " ha completado la reparación ("
                            + mec.getReparacionesRealizadas() + " reparaciones totales).";
                    System.out.println(msg);
                    cadenaMontaje.notifyObservadores(msg);
                }
            }
        }
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

    private void imprimirResumenTrabajadores() {
        System.out.println("\n── Resumen ──");
        if (mecanicos != null) {
            for (Mecanico m : mecanicos) {
                System.out.println("  " + m.getNombre() + " " + m.getApellidos()
                        + " (Mecánico) — reparaciones: " + m.getReparacionesRealizadas());
            }
        }
        if (admin != null) {
            System.out.println("  " + admin.getNombre() + " " + admin.getApellidos()
                    + " (Admin) — restauraciones de luz: "
                    + admin.getRestauracionesRealizadas());
        }
    }

    @Override
    public void notifyObservadores(String message) {
        for (Observador observador : observadores) {
            observador.update(message);
        }
    }

    @Override
    public void addObservador(Observador observador) {
        observadores.add(observador);
    }

    @Override
    public void removeObservador(Observador observador) {
        observadores.remove(observador);
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

    public boolean isCaidaDeLuz() {
        return caidaDeLuz;
    }

    public void setCaidaDeLuz(boolean caidaDeLuz) {
        this.caidaDeLuz = caidaDeLuz;
    }

    public boolean isSistemaGestionBloqueado() {
        return sistemaGestionBloqueado;
    }

    public void setSistemaGestionBloqueado(boolean sistemaGestionBloqueado) {
        this.sistemaGestionBloqueado = sistemaGestionBloqueado;
    }
}
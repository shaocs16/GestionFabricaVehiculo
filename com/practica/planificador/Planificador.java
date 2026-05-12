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

public class Planificador implements Observable {

    private CadenaMontaje cadenaMontaje;
    private SistemaGestion sistemaGestion;
    private int tipoSimulacion;

    private boolean caidaDeLuz = false;
    private boolean sistemaGestionBloqueado = false;
    private int tiempoReparacionGestion = 0;
    private int tiempoReparacionCadenas = 0;
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
    private GestorPlanta gestorPlanta;

    public Planificador(CadenaMontaje cadenaMontaje, SistemaGestion sistemaGestion,
            int tipoSimulacion,
            Mecanico[] mecanicos, AdministradorSistema admin) {
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
        int idx = 0;
        for (int i = 0; i < 4; i++) {
            robotsBiplaza[i]   = new Robot("Robot Biplaza"   + componentes[i],
                obtenerOperario(registrados, idx++, "Biplaza",   i));
            robotsTurismo[i]   = new Robot("Robot Turismo"   + componentes[i],
                obtenerOperario(registrados, idx++, "Turismo",   i));
            robotsFurgoneta[i] = new Robot("Robot Furgoneta" + componentes[i],
                obtenerOperario(registrados, idx++, "Furgoneta", i));
        }
    }

private Operario obtenerOperario(List<Operario> registrados, int idx,
                                     String cadena, int posicion) {
        if (!registrados.isEmpty()) {
            return registrados.get(idx % registrados.size());
        }
        return crearOperarioAleatorio(cadena, posicion);
    }

    private Operario crearOperarioAleatorio(String nombreCadena, int indice) {

String sufijo = nombreCadena + "_" + indice + "_" + System.nanoTime();
        String nombre = "Operario_" + nombreCadena + "_" + indice;
        String apellidos = "Apellido_" + sufijo;
        String dni = String.format("%08d", Math.abs(sufijo.hashCode()) % 100000000) + "A";
        String direccion = "Calle " + sufijo;
        String nss = "NSS_" + sufijo;
        double salario = 25000;
        LocalDate fechaIngreso = LocalDate.now();

        Operario op = new Operario(nombre, apellidos, dni, direccion, nss, salario, fechaIngreso);

        boolean esEficiente = Math.random() < 0.5;
        if (esEficiente) {
            for (int j = 0; j < 11; j++) {
                op.registrarMontajeCompletado();
            }
        }
        return op;
}

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

    private void trabajarEnEstaciones() {

        if (caidaDeLuz) {
            System.out.println("[APAGÓN] Sistema de gestión bloqueado: "
                + sistemaGestionBloqueado + ". Cadenas paradas.");
            return;
        }
        procesarCadenaConRobots(cadenaMontaje.getCadenaBiplaza(),   robotsBiplaza,   "Biplaza");
        procesarCadenaConRobots(cadenaMontaje.getCadenaTurismo(),   robotsTurismo,   "Turismo");
        procesarCadenaConRobots(cadenaMontaje.getCadenaFurgoneta(), robotsFurgoneta, "Furgoneta");
    }

    private void procesarCadenaConRobots(ArrayList<? extends Coche> lista,
            Robot[] robots, String nombreCadena) {

int i = 0;
        for (Coche c : lista) {
            if (c.getEstadoMontaje() == EstadoMontaje.TERMINADO) {
                i++;
                continue;
            }
            if (c.isAveriado()) {
                System.out.println("[AVISO] Coche " + nombreCadena + " #" + (i + 1)
                        + " averiado - esperando mecánico.");
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
                            detalleComponente = " | Motor=" + m.tipoMotor()
                                + " (" + m.getCilindrada() + "cc, " + m.getPotencia() + "CV)";
                        }
                        break;
                    }
                    case TAPICERIA: {
                        Tapiceria t = sistemaGestion.quitarStockTapiceria();
                        if (t != null) {
                            c.setTapiceria(t);
                            detalleComponente = " | Tapicería=" + t.tipoTapiceria()
                                + " (" + t.getColor() + ")";
                        }
                        break;
                    }
                    case RUEDAS: {
                        Rueda[] r = sistemaGestion.quitarStockRuedas();
                        if (r != null) {
                            c.setRueda(r);
                            detalleComponente = " | Ruedas=" + r[0].tipoRueda()
                                + " (" + r[0].getAncho() + "mm)";
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

                sistemaGestion.registrarHistorial(
                        nombreCadena + " #" + (i + 1),
                        estadoActual + " -> " + estadoSiguiente + detalleComponente);

                String msg = "[" + nombreCadena + " #" + (i + 1) + "] "
                        + estadoActual + " -> " + estadoSiguiente + detalleComponente;
                System.out.println(msg);
                cadenaMontaje.notifyObservadores(msg);
            }
            i++;
        }
    }

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
            System.out.println(msg);
            cadenaMontaje.notifyObservadores(msg);
        }
    }

    private boolean generarAveriaLista(ArrayList<? extends Coche> lista, String nombreCadena) {
        for (Coche c : lista) {
            if (c.getEstadoMontaje() != EstadoMontaje.TERMINADO && !c.isAveriado()) {
                c.setAveriado(true);
                c.setTiempoReparacion(-1);

if (gestorPlanta != null) {
                    String revision = gestorPlanta.consultarDashboard(null);
                    System.out.println(revision);
                    cadenaMontaje.notifyObservadores(revision);
                }

                String quien = (gestorPlanta != null)
                        ? "El Gestor de Planta " + gestorPlanta.getNombre() + " "
                          + gestorPlanta.getApellidos()
                        : "El Gestor de Planta";
                String msg = "Se ha detectado avería en cadena " + nombreCadena
                        + " - " + quien + " llamará al mecánico.";
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
                        String aviso = gestorPlanta.llamarMecanico(mec, c, null);
                        System.out.println(aviso);
                        cadenaMontaje.notifyObservadores(aviso);
                    } else {
                        mec.repararCoche(c);
                    }
                    String perfil = mec.esEficiente() ? "eficiente" : "estándar";
                    String msg = mec.getNombre() + " " + mec.getApellidos()
                            + " (" + perfil + ") ha completado la reparación ("
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
        System.out.println("\nRESUMEN");

if (tipoSimulacion >= 2 && mecanicos != null) {
            for (Mecanico m : mecanicos) {
                System.out.println(" El mecánico " + m.getNombre() + " " + m.getApellidos()
                        + " ha hecho " + m.getReparacionesRealizadas() + " reparaciones.");
            }
        }
        if (tipoSimulacion == 3 && admin != null) {
            System.out.println(" El administrador " + admin.getNombre() + " " + admin.getApellidos()
                    + " ha restaurado la luz: "
                    + admin.getRestauracionesRealizadas() + " veces.");
        }
        if (tipoSimulacion == 1) {
            System.out.println(" Simulación simple completada sin incidencias.");
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
package com.practica.fabrica;

import com.practica.vehiculo.*;
import com.practica.rueda.*;
import com.practica.motor.*;
import com.practica.tapiceria.*;
import com.practica.personal.*;
import com.practica.dashboard.Observable;
import com.practica.dashboard.Observador;

import java.util.*;

/**
 * Implementación basada en {@link ArrayList} del almacén de datos del sistema.
 * <p>
 * Mantiene en memoria todas las entidades del sistema (vehículos terminados,
 * stock de componentes, plantilla de trabajadores y registro histórico de
 * operaciones de montaje). Además, actúa como sujeto observable del patrón
 * Observador para notificar al dashboard cada vez que cambia el estado del
 * almacén. *
 * @author Shao Capilla Sanz
 */
public class AlmacenDatos implements IfaceAlmacen, Observable {

    /** Stock de Biplazas Deportivos terminados. */
    private ArrayList<BiplazaDeportivo> biplazasDeportivos;
    /** Stock de Turismos terminados. */
    private ArrayList<Turismo> turismos;
    /** Stock de Furgonetas terminadas. */
    private ArrayList<Furgoneta> furgonetas;

    /** Stock de ruedas disponibles para montaje. */
    private ArrayList<Rueda> ruedas;

    /** Stock de motores disponibles para montaje. */
    private ArrayList<Motor> motores;

    /** Stock de tapicerías disponibles para montaje. */
    private ArrayList<Tapiceria> tapicerias;

    /** Plantilla de administradores del sistema. */
    private ArrayList<AdministradorSistema> administradoresSistema;
    /** Plantilla de gestores de planta. */
    private ArrayList<GestorPlanta> gestoresPlanta;
    /** Plantilla de mecánicos de cinta. */
    private ArrayList<Mecanico> mecanicos;
    /** Plantilla de operarios. */
    private ArrayList<Operario> operarios;

    /** Observadores registrados para recibir notificaciones del almacén. */
    private ArrayList<Observador> observadores;

    /** Historial de operaciones de montaje registradas en el sistema. */
    private List<RegistroMontaje> historial;

    /**
     * Inicializa todas las colecciones del almacén vacías.
     */
    public AlmacenDatos() {

        biplazasDeportivos = new ArrayList<>();
        turismos = new ArrayList<>();
        furgonetas = new ArrayList<>();
        ruedas = new ArrayList<>();
        motores = new ArrayList<>();
        tapicerias = new ArrayList<>();
        administradoresSistema = new ArrayList<>();
        gestoresPlanta = new ArrayList<>();
        mecanicos = new ArrayList<>();
        operarios = new ArrayList<>();
        observadores = new ArrayList<>();
        historial = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     * <p>Registra la operación en el historial y notifica al dashboard.</p>
     */
    @Override
    public void agregarBiplazaDeportivo(BiplazaDeportivo biplazaDeportivo) {
        biplazasDeportivos.add(biplazaDeportivo);
        registrarOperacion(new RegistroMontaje(new Date(), "Biplaza Deportivo", "Vehículo terminado"));
        notifyObservadores("Vehículo terminado, Biplaza Deportivo");
    }

    /** {@inheritDoc} */
    @Override
    public List<BiplazaDeportivo> getBiplazasDeportivos() {
        return biplazasDeportivos;
    }

    /**
     * {@inheritDoc}
     * <p>Registra la operación en el historial y notifica al dashboard.</p>
     */
    @Override
    public void agregarFurgoneta(Furgoneta furgoneta) {
        furgonetas.add(furgoneta);
        registrarOperacion(new RegistroMontaje(new Date(), "Furgoneta", "Vehículo terminado"));
        notifyObservadores("Vehículo terminado, Furgoneta");
    }

    /** {@inheritDoc} */
    @Override
    public List<Furgoneta> getFurgonetas() {
        return furgonetas;
    }

    /**
     * {@inheritDoc}
     * <p>Registra la operación en el historial y notifica al dashboard.</p>
     */
    @Override
    public void agregarTurismo(Turismo turismo) {
        turismos.add(turismo);
        registrarOperacion(new RegistroMontaje(new Date(), "Turismo", "Vehículo terminado"));
        notifyObservadores("Vehículo terminado, Turismo");
    }

    /** {@inheritDoc} */
    @Override
    public List<Turismo> getTurismos() {
        return turismos;
    }

    /**
     * {@inheritDoc}
     * <p>Registra la operación en el historial y notifica al dashboard.</p>
     */
    @Override
    public void agregarMotor(Motor motor) {
        motores.add(motor);
        registrarOperacion(new RegistroMontaje(new Date(), "Motor", "Añadido al almacén"));
        notifyObservadores("Nuevo componente en almacén, Motor " + motor.tipoMotor());
    }

    /** {@inheritDoc} */
    @Override
    public List<Motor> getMotores() {
        return motores;
    }

    /**
     * {@inheritDoc}
     * <p>Registra la operación en el historial y notifica al dashboard.</p>
     */
    @Override
    public void agregarTapiceria(Tapiceria tapiceria) {
        tapicerias.add(tapiceria);
        registrarOperacion(new RegistroMontaje(new Date(), "Tapiceria", "Añadida al almacén"));
        notifyObservadores("Nuevo componente en almacén, Tapiceria " + tapiceria.tipoTapiceria());
    }

    /** {@inheritDoc} */
    @Override
    public List<Tapiceria> getTapicerias() {
        return tapicerias;
    }

    /**
     * {@inheritDoc}
     * <p>Registra la operación en el historial y notifica al dashboard.</p>
     */
    @Override
    public void agregarRueda(Rueda rueda) {
        ruedas.add(rueda);
        registrarOperacion(new RegistroMontaje(new Date(), "Rueda", "Añadida al almacén"));
        notifyObservadores("Nuevo componente en almacén, Rueda " + rueda.tipoRueda());
    }

    /** {@inheritDoc} */
    @Override
    public List<Rueda> getRuedas() {
        return ruedas;
    }

    /** {@inheritDoc} */
    @Override
    public void agregarAdministradorSistema(AdministradorSistema administradorSistema) {
        administradoresSistema.add(administradorSistema);
    }

    /** {@inheritDoc} */
    @Override
    public List<AdministradorSistema> getAdministradoresSistema() {
        return administradoresSistema;
    }

    /** {@inheritDoc} */
    @Override
    public void agregarGestorPlanta(GestorPlanta gestorPlanta) {
        gestoresPlanta.add(gestorPlanta);
    }

    /** {@inheritDoc} */
    @Override
    public List<GestorPlanta> getGestoresPlanta() {
        return gestoresPlanta;
    }

    /** {@inheritDoc} */
    @Override
    public void agregarMecanico(Mecanico mecanico) {
        mecanicos.add(mecanico);
    }

    /** {@inheritDoc} */
    @Override
    public List<Mecanico> getMecanicos() {
        return mecanicos;
    }

    /** {@inheritDoc} */
    @Override
    public void agregarOperario(Operario operario) {
        operarios.add(operario);
    }

    /** {@inheritDoc} */
    @Override
    public List<Operario> getOperarios() {
        return operarios;
    }

    /** {@inheritDoc} */
    @Override
    public void vaciar() {
        biplazasDeportivos.clear();
        turismos.clear();
        furgonetas.clear();
        ruedas.clear();
        motores.clear();
        tapicerias.clear();
        administradoresSistema.clear();
        gestoresPlanta.clear();
        mecanicos.clear();
        operarios.clear();
        historial.clear();
        observadores.clear();
    }

    /** {@inheritDoc} */
    @Override
    public int getTotalCoches() {
        return biplazasDeportivos.size() + turismos.size() + furgonetas.size();
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

    /** {@inheritDoc} */
    @Override
    public void notifyObservadores(String message) {
        for (Observador observador : observadores) {
            observador.update(message);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * La comparación de fechas se realiza con precisión de día (mismo año y
     * mismo día del año).     */
    @Override
    public List<RegistroMontaje> getRegistrosPorFecha(Date fecha) {
        List<RegistroMontaje> resultado = new ArrayList<>();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(fecha);

        for (RegistroMontaje registro : historial) {
            cal2.setTime(registro.getFecha());
            if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)) {
                resultado.add(registro);
            }
        }
        return resultado;
    }

    /** {@inheritDoc} */
    @Override
    public void registrarOperacion(RegistroMontaje registro) {
        historial.add(registro);
    }

    /**
     * {@inheritDoc}
     * <p>Si no hay stock, no realiza ninguna acción.</p>
     */
    @Override
    public void disminuirStockMotor() {

        if(!motores.isEmpty()) {
            motores.remove(0);
        }
    }

    /**
     * {@inheritDoc}
     * <p>Si no hay stock, no realiza ninguna acción.</p>
     */
    @Override
    public void disminuirStockRueda() {

        if(!ruedas.isEmpty()) {
            ruedas.remove(0);
        }
    }

    /**
     * {@inheritDoc}
     * <p>Si no hay stock, no realiza ninguna acción.</p>
     */
    @Override
    public void disminuirStockTapiceria() {

        if(!tapicerias.isEmpty()) {
            tapicerias.remove(0);
        }
    }

    /** {@inheritDoc} */
    @Override
    public int getStockMotores() {

        return motores.size();
    }

    /** {@inheritDoc} */
    @Override
    public int getStockRuedas() {

        return ruedas.size();
    }

    /** {@inheritDoc} */
    @Override
    public int getStockTapicerias() {

        return tapicerias.size();
    }

}

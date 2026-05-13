package com.practica.fabrica;

import com.practica.vehiculo.*;
import com.practica.rueda.*;
import com.practica.motor.*;
import com.practica.tapiceria.*;
import com.practica.personal.*;
import java.util.*;

/**
 * Contrato del almacén de datos del sistema de gestión de la fábrica.
 * <p>
 * Define las operaciones de alta, consulta y gestión de stock para todas las
 * entidades del sistema (vehículos, componentes, trabajadores y registros
 * históricos de montaje). Su existencia desacopla el sistema de gestión de
 * la estructura de datos concreta, de modo que se pueda sustituir la
 * implementación sin requerir cambios drásticos en el resto del diseño. *
 * @author Shao Capilla Sanz
 */
public interface IfaceAlmacen
{

    /**
     * Añade un Biplaza Deportivo terminado al almacén.
     *
     * @param biplazaDeportivo vehículo a almacenar
     */
    void agregarBiplazaDeportivo(BiplazaDeportivo biplazaDeportivo);

    /**
     * @return lista de Biplazas Deportivos terminados almacenados
     */
    List<BiplazaDeportivo> getBiplazasDeportivos();

    /**
     * Añade una Furgoneta terminada al almacén.
     *
     * @param furgoneta vehículo a almacenar
     */
    void agregarFurgoneta(Furgoneta furgoneta);

    /**
     * @return lista de Furgonetas terminadas almacenadas
     */
    List<Furgoneta> getFurgonetas();

    /**
     * Añade un Turismo terminado al almacén.
     *
     * @param turismo vehículo a almacenar
     */
    void agregarTurismo(Turismo turismo);

    /**
     * @return lista de Turismos terminados almacenados
     */
    List<Turismo> getTurismos();

    /**
     * Añade un motor al stock del almacén.
     *
     * @param motor motor a registrar
     */
    void agregarMotor(Motor motor);

    /**
     * @return lista de motores disponibles en stock
     */
    List<Motor> getMotores();

    /**
     * Añade una tapicería al stock del almacén.
     *
     * @param tapiceria tapicería a registrar
     */
    void agregarTapiceria(Tapiceria tapiceria);

    /**
     * @return lista de tapicerías disponibles en stock
     */
    List<Tapiceria> getTapicerias();

    /**
     * Añade una rueda al stock del almacén.
     *
     * @param rueda rueda a registrar
     */
    void agregarRueda(Rueda rueda);

    /**
     * @return lista de ruedas disponibles en stock
     */
    List<Rueda> getRuedas();

    /**
     * Da de alta a un administrador del sistema.
     *
     * @param adminstradorSistema administrador a registrar
     */
    void agregarAdministradorSistema(AdministradorSistema adminstradorSistema);

    /**
     * @return lista de administradores del sistema registrados
     */
    List<AdministradorSistema> getAdministradoresSistema();

    /**
     * Da de alta a un gestor de planta.
     *
     * @param gestorPlanta gestor a registrar
     */
    void agregarGestorPlanta(GestorPlanta gestorPlanta);

    /**
     * @return lista de gestores de planta registrados
     */
    List<GestorPlanta> getGestoresPlanta();

    /**
     * Da de alta a un mecánico de cinta.
     *
     * @param mecanico mecánico a registrar
     */
    void agregarMecanico(Mecanico mecanico);

    /**
     * @return lista de mecánicos registrados
     */
    List<Mecanico> getMecanicos();

    /**
     * Da de alta a un operario.
     *
     * @param operario operario a registrar
     */
    void agregarOperario(Operario operario);

    /**
     * @return lista de operarios registrados
     */
    List<Operario> getOperarios();

    /**
     * Vacía todos los datos del almacén (vehículos, stock, trabajadores e
     * historial).
     */
    void vaciar();

    /**
     * @return número total de coches terminados almacenados
     */
    int getTotalCoches();

    /**
     * Devuelve los registros de operaciones de montaje correspondientes a
     * una fecha concreta (precisión de día).
     *
     * @param fecha fecha a consultar
     * @return lista de registros de esa fecha
     */
    List<RegistroMontaje> getRegistrosPorFecha(Date fecha);

    /**
     * Añade una operación al historial del almacén.
     *
     * @param registro registro a incorporar
     */
    void registrarOperacion(RegistroMontaje registro);

    /**
     * @return número de motores actualmente en stock
     */
    int getStockMotores();

    /**
     * @return número de ruedas actualmente en stock
     */
    int getStockRuedas();

    /**
     * @return número de tapicerías actualmente en stock
     */
    int getStockTapicerias();

    /**
     * Disminuye en una unidad el stock de motores.
     */
    void disminuirStockMotor();

    /**
     * Disminuye en una unidad el stock de ruedas.
     */
    void disminuirStockRueda();

    /**
     * Disminuye en una unidad el stock de tapicerías.
     */
    void disminuirStockTapiceria();
}

package com.practica.fabrica;

import com.practica.vehiculo.*;
import com.practica.rueda.*;
import com.practica.montaje.CadenaMontaje;
import com.practica.motor.*;
import com.practica.tapiceria.*;
import com.practica.personal.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

/**
 * Fachada del sistema de gestión de la fábrica.
 * <p>
 * Coordina las operaciones de alta y consulta sobre el almacén de datos
 * (representado por {@link IfaceAlmacen}), exponiendo una API de alto nivel
 * para el resto de la aplicación (menús del usuario, planificador, gestor
 * de planta, etc.). Al trabajar contra la interfaz del almacén, su diseño
 * permanece desacoplado de la estructura de datos concreta utilizada. * <p>
 * Responsabilidades:
 * <ul>
 *   <li>Alta y consulta de vehículos, componentes y trabajadores.</li>
 *   <li>Gestión de stock (motores, tapicerías y ruedas).</li>
 *   <li>Búsquedas y listados sobre la plantilla.</li>
 *   <li>Estadísticas y consulta del historial de montaje.</li>
 * </ul>
 *
 * @author Shao Capilla Sanz
 */
public class SistemaGestion
{
    /** Almacén de datos contra el que opera el sistema de gestión. */
    private IfaceAlmacen almacen;

    /**
     * Crea un sistema de gestión asociado al almacén indicado.
     *
     * @param almacen implementación del almacén de datos a utilizar
     */
    public SistemaGestion(IfaceAlmacen almacen){
        this.almacen = almacen;
    }

    /**
     * Registra un Biplaza Deportivo terminado en el almacén.
     *
     * @param biplazaDeportivo vehículo a almacenar
     */
    public void registrarBiplazaDeportivo(BiplazaDeportivo biplazaDeportivo){
        almacen.agregarBiplazaDeportivo(biplazaDeportivo);
    }

    /**
     * @return lista de Biplazas Deportivos almacenados
     */
    public List<BiplazaDeportivo> consultarBiplazasDeportivos(){
        return almacen.getBiplazasDeportivos();
    }

    /**
     * Registra una Furgoneta terminada en el almacén.
     *
     * @param furgoneta vehículo a almacenar
     */
    public void registrarFurgoneta(Furgoneta furgoneta){
        almacen.agregarFurgoneta(furgoneta);
    }

    /**
     * @return lista de Furgonetas almacenadas
     */
    public List<Furgoneta> consultarFurgoneta(){
        return almacen.getFurgonetas();
    }

    /**
     * Registra un Turismo terminado en el almacén.
     *
     * @param turismo vehículo a almacenar
     */
    public void registrarTurismo(Turismo turismo){
        almacen.agregarTurismo(turismo);
    }

    /**
     * @return lista de Turismos almacenados
     */
    public List<Turismo> consultarTurismo(){
        return almacen.getTurismos();
    }

    /**
     * Añade un motor al stock del almacén.
     *
     * @param motor motor a registrar
     */
    public void registrarMotor(Motor motor){
        almacen.agregarMotor(motor);
    }

    /**
     * @return lista de motores en stock
     */
    public List<Motor> consultarMotor(){
        return almacen.getMotores();
    }

    /**
     * Añade una tapicería al stock del almacén.
     *
     * @param tapiceria tapicería a registrar
     */
    public void registrarTapiceria(Tapiceria tapiceria){
        almacen.agregarTapiceria(tapiceria);
    }

    /**
     * @return lista de tapicerías en stock
     */
    public List<Tapiceria> consultarTapiceria(){
        return almacen.getTapicerias();
    }

    /**
     * Añade una rueda al stock del almacén.
     *
     * @param rueda rueda a registrar
     */
    public void registrarRueda(Rueda rueda){
        almacen.agregarRueda(rueda);
    }

    /**
     * @return lista de ruedas en stock
     */
    public List<Rueda> consultarRueda(){
        return almacen.getRuedas();
    }

    /**
     * Da de alta a un administrador del sistema.
     *
     * @param administradorSistema administrador a registrar
     */
    public void registrarAdministradorSistema(AdministradorSistema administradorSistema){
        almacen.agregarAdministradorSistema(administradorSistema);
    }

    /**
     * @return lista de administradores del sistema
     */
    public List<AdministradorSistema> consultarAdministradorSistema(){
        return almacen.getAdministradoresSistema();
    }

    /**
     * Da de alta a un gestor de planta.
     *
     * @param gestorPlanta gestor a registrar
     */
    public void registrarGestorPlanta(GestorPlanta gestorPlanta){
        almacen.agregarGestorPlanta(gestorPlanta);
    }

    /**
     * @return lista de gestores de planta
     */
    public List<GestorPlanta> consultarGestorPlanta(){
        return almacen.getGestoresPlanta();
    }

    /**
     * Da de alta a un mecánico de cinta.
     *
     * @param mecanico mecánico a registrar
     */
    public void registrarMecanico(Mecanico mecanico){
        almacen.agregarMecanico(mecanico);
    }

    /**
     * @return lista de mecánicos registrados
     */
    public List<Mecanico> consultarMecanico(){
        return almacen.getMecanicos();
    }

    /**
     * Da de alta a un operario.
     *
     * @param operario operario a registrar
     */
    public void registrarOperario(Operario operario){
        almacen.agregarOperario(operario);
    }

    /**
     * @return lista de operarios registrados
     */
    public List<Operario> consultarOperario(){
        return almacen.getOperarios();
    }

    /**
     * Reinicia el sistema vaciando por completo el almacén.
     */
    public void resetSistema(){
        almacen.vaciar();
    }

    /**
     * @return número total de coches almacenados (terminados)
     */
    public int consultarTotalCoches(){
        return almacen.getTotalCoches();
    }

    /**
     * Busca operarios cuyo nombre contiene la cadena indicada (sin distinguir
     * mayúsculas/minúsculas).
     *
     * @param nombre fragmento de nombre a buscar
     * @return lista de operarios coincidentes
     */
    public List<Operario> buscarOperariosPorNombre(String nombre) {
        List<Operario> res = new ArrayList<>();
        String nombreBuscar = nombre.toLowerCase();
        for (Operario op : almacen.getOperarios()) {
            if (op.getNombre().toLowerCase().contains(nombreBuscar)) {
                res.add(op);
            }
        }
        return res;
    }

    /**
     * Devuelve la lista de operarios considerados eficientes (más de 10
     * montajes realizados).
     *
     * @return lista de operarios eficientes
     */
    public List<Operario> buscarOperariosEficientes(){
        List<Operario> res = new ArrayList<>();
        for(Operario op: almacen.getOperarios()){
            if(op.esEficiente()){
                res.add(op);
            }
        }
        return res;
    }

    /**
     * Busca mecánicos cuyo nombre contiene la cadena indicada (sin distinguir
     * mayúsculas/minúsculas).
     *
     * @param nombre fragmento de nombre a buscar
     * @return lista de mecánicos coincidentes
     */
    public List<Mecanico> buscarMecanicosPorNombre(String nombre){
        List<Mecanico> res = new ArrayList<>();
        String nombreBuscar = nombre.toLowerCase();
        for(Mecanico mec: almacen.getMecanicos()){
            if(mec.getNombre().toLowerCase().contains(nombreBuscar)){
                res.add(mec);
            }
        }
        return res;
    }

    /**
     * Busca un trabajador por su DNI entre todos los perfiles registrados.
     *
     * @param dni DNI a buscar
     * @return cadena descriptiva con el perfil y nombre del trabajador, o
     *         "No encontrado" si no existe
     */
    public String buscarTrabajadorPorDni(String dni) {
        for (Operario op : almacen.getOperarios()) {
        if (op.getDni().equals(dni)) return "Operario: " + op.getNombre() + " " + op.getApellidos();
    }
    for (Mecanico mec : almacen.getMecanicos()) {
        if (mec.getDni().equals(dni)) return "Mecánico: " + mec.getNombre() + " " + mec.getApellidos();
    }
    for (GestorPlanta gp : almacen.getGestoresPlanta()) {
        if (gp.getDni().equals(dni)) return "Gestor: " + gp.getNombre() + " " + gp.getApellidos();
    }
    for (AdministradorSistema as : almacen.getAdministradoresSistema()) {
        if (as.getDni().equals(dni)) return "Administrador: " + as.getNombre() + " " + as.getApellidos();
    }
    return "No encontrado";
    }

    /**
     * Comprueba si hay stock suficiente para fabricar el número de vehículos
     * indicado. Cada vehículo requiere un motor, una tapicería y cuatro
     * ruedas; si falta cualquier componente, muestra el motivo por consola.
     *
     * @param cantidad número de vehículos que se desean fabricar
     * @return {@code true} si hay stock suficiente; {@code false} en otro caso
     */
    public ComprobacionStock comprobarStock(int cantidad){
        int cantidadMotores = almacen.getStockMotores();
        int cantidadTapiceria = almacen.getStockTapicerias();
        int cantidadRueda = almacen.getStockRuedas();

        if (cantidadMotores < cantidad) {
            return ComprobacionStock.SIN_MOTORES;
        }
        if (cantidadTapiceria < cantidad) {
            return ComprobacionStock.SIN_TAPICERIAS;
        }
        if (cantidadRueda < cantidad * 4) {
            return ComprobacionStock.SIN_RUEDAS;
        }
        return ComprobacionStock.OK;
    }

    /**
     * Extrae un motor del stock, eliminándolo del almacén.
     *
     * @return motor extraído o {@code null} si no había stock
     */
    public Motor quitarStockMotor() {
        List<Motor> motores = almacen.getMotores();
        if (motores.isEmpty()) {
            return null;
        }
        Motor m = motores.get(0);
        almacen.disminuirStockMotor();
        return m;
    }

    /**
     * Extrae una tapicería del stock, eliminándola del almacén.
     *
     * @return tapicería extraída o {@code null} si no había stock
     */
    public Tapiceria quitarStockTapiceria() {
        List<Tapiceria> tapicerias = almacen.getTapicerias();
        if (tapicerias.isEmpty()) {
            return null;
        }
        Tapiceria t = tapicerias.get(0);
        almacen.disminuirStockTapiceria();
        return t;
    }

    /**
     * Extrae del stock un juego de 4 ruedas. Si es posible, las 4 ruedas
     * extraídas pertenecen al mismo tipo (Normal, Deportivo o Todoterreno);
     * en caso contrario se entregan las primeras 4 disponibles del stock.
     *
     * @return array de 4 ruedas o {@code null} si no había stock suficiente
     */
    public Rueda[] quitarStockRuedas() {
        List<Rueda> ruedas = almacen.getRuedas();
        if (ruedas.size() < 4) {
            return null;
        }

        String tipoBuscado = null;
        int contadas = 0;
        for (Rueda rTipo : ruedas) {
            if (tipoBuscado == null || !rTipo.tipoRueda().equals(tipoBuscado)) {
                tipoBuscado = rTipo.tipoRueda();
                contadas = 1;
            } else {
                contadas++;
            }
            if (contadas == 4) break;
        }

        Rueda[] r = new Rueda[4];
        if (contadas == 4 && tipoBuscado != null) {

            int asignadas = 0;
            int i = 0;
            while (asignadas < 4 && i < ruedas.size()) {
                if (ruedas.get(i).tipoRueda().equals(tipoBuscado)) {
                    r[asignadas++] = ruedas.remove(i);
                } else {
                    i++;
                }
            }
        } else {
            for (int i = 0; i < 4; i++) {
                r[i] = ruedas.get(0);
                almacen.disminuirStockRueda();
            }
        }
        return r;
    }

    /**
     * Crea y registra en el historial del almacén una nueva entrada con la
     * fecha actual.
     *
     * @param tipoComponente tipo de componente o entidad afectada
     * @param accion         descripción de la acción
     */
    public void registrarHistorial(String tipoComponente, String accion) {
        almacen.registrarOperacion(new RegistroMontaje(new Date(), tipoComponente, accion));
    }

    /**
     * Devuelve la lista de operarios ordenada alfabéticamente por nombre y,
     * en caso de empate, por apellidos.
     *
     * @return lista de operarios ordenada
     */
    public List<Operario> ordenarOperarios(){
        List<Operario> res = new ArrayList<>(almacen.getOperarios());
        res.sort(Comparator.comparing(Trabajador::getNombre).thenComparing(Trabajador::getApellidos));
        return res;
    }

    /**
     * Devuelve la lista de operarios cuya productividad (montajes realizados)
     * iguala o supera un umbral mínimo, ordenada de mayor a menor productividad.
     *
     * @param minMontajes umbral mínimo de montajes realizados
     * @return lista filtrada y ordenada por productividad descendente
     */
    public List<Operario> obtenerOperariosProductividad(int minMontajes) {
        List<Operario> res = new ArrayList<>();
        for (Operario op : almacen.getOperarios()) {
            if (op.getMontajesRealizados() >= minMontajes) {
                res.add(op);
            }
        }
        res.sort(Comparator.comparingInt(Operario::getMontajesRealizados).reversed());
        return res;
    }

    /**
     * Devuelve todos los coches terminados (Biplazas, Turismos y Furgonetas)
     * almacenados en el sistema.
     *
     * @param cadenaMontaje cadena de montaje (no consultada en esta
     *                      implementación; se mantiene por compatibilidad)
     * @return lista completa de coches terminados
     */
    public List<Coche> obtenerCochesTerminados(CadenaMontaje cadenaMontaje) {
        List<Coche> res = new ArrayList<>();
        res.addAll(consultarBiplazasDeportivos());
        res.addAll(consultarTurismo());
        res.addAll(consultarFurgoneta());
        return res;
    }

    /**
     * Filtra una lista de coches devolviendo únicamente los que tienen el
     * tipo de motor indicado (comparación sin distinguir mayúsculas).
     *
     * @param coches    lista de coches a filtrar
     * @param tipoMotor tipo de motor buscado (p.ej. "Gasolina")
     * @return lista filtrada
     */
    public List<Coche> filtrarTipoMotor(List<Coche> coches, String tipoMotor) {
        List<Coche> res = new ArrayList<>();
        for (Coche c: coches) {
            if(c.getMotor() != null && c.getMotor().tipoMotor().equalsIgnoreCase(tipoMotor)) {
                res.add(c);
            }
        }
        return res;
    }

    /**
     * Filtra una lista de coches devolviendo únicamente los que tienen el
     * tipo de tapicería indicado (comparación sin distinguir mayúsculas).
     *
     * @param coches        lista de coches a filtrar
     * @param tipoTapiceria tipo de tapicería buscado (p.ej. "Cuero")
     * @return lista filtrada
     */
    public List<Coche> filtrarTipoTapiceria(List<Coche> coches, String tipoTapiceria) {
        List<Coche> res = new ArrayList<>();
        for (Coche c: coches){
            if(c.getTapiceria() != null && c.getTapiceria().tipoTapiceria().equalsIgnoreCase(tipoTapiceria)) {
                res.add(c);
            }
        }
        return res;
    }

    /**
     * Devuelve los coches indicados ordenados alfabéticamente por tipo de
     * vehículo.
     *
     * @param coches lista de coches a ordenar
     * @return lista ordenada por tipo de coche
     */
    public List<Coche> obtenerCochesOrdenados(List<Coche> coches) {
        List<Coche> res = new ArrayList<>(coches);
        res.sort(Comparator.comparing(Coche::tipoCoche));
        return res;
    }

    /**
     * Calcula las configuraciones más ensambladas a partir de los coches
     * terminados. La configuración se identifica por la combinación de tipo
     * de vehículo, motor, tapicería y rueda.
     *
     * @param cadenaMontaje cadena de montaje (no consultada en esta
     *                      implementación; se mantiene por compatibilidad)
     * @return mapa cuya clave es la configuración y cuyo valor es el número
     *         de unidades ensambladas con esa configuración
     */
    public Map<String, Integer> getConfiguracionesMasEnsamblados(CadenaMontaje cadenaMontaje) {
        Map<String, Integer> res = new HashMap<>();
        List<Coche> cochesTerminados = obtenerCochesTerminados(cadenaMontaje);
        for (Coche c : cochesTerminados) {
            String config = c.tipoCoche()
                + " | Motor: " + (c.getMotor() != null ? c.getMotor().tipoMotor() : "N/A")
                + " | Tapicería: " + (c.getTapiceria() != null ? c.getTapiceria().tipoTapiceria() : "N/A")
                + " | Rueda: " + (c.getRueda() != null && c.getRueda().length > 0 ? c.getRueda()[0].tipoRueda() : "N/A");
            res.put(config, res.getOrDefault(config, 0) + 1);
        }
        return res;
    }

    /**
     * Devuelve los registros de operaciones de montaje correspondientes a la
     * fecha indicada (precisión de día).
     *
     * @param fecha fecha a consultar
     * @return lista de registros encontrados
     */
    public List<RegistroMontaje> consultarHistorialFecha(Date fecha) {
        return almacen.getRegistrosPorFecha(fecha);
    }
}

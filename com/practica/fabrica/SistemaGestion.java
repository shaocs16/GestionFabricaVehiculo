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

public class SistemaGestion
{
    private IfaceAlmacen almacen;

    public SistemaGestion(IfaceAlmacen almacen){
        this.almacen = almacen;
    }

    public void registrarBiplazaDeportivo(BiplazaDeportivo biplazaDeportivo){
        almacen.agregarBiplazaDeportivo(biplazaDeportivo);
    }
    public List<BiplazaDeportivo> consultarBiplazasDeportivos(){
        return almacen.getBiplazasDeportivos();
    }

    public void registrarFurgoneta(Furgoneta furgoneta){
        almacen.agregarFurgoneta(furgoneta);
    }
    public List<Furgoneta> consultarFurgoneta(){
        return almacen.getFurgonetas();
    }

    public void registrarTurismo(Turismo turismo){
        almacen.agregarTurismo(turismo);
    }
    public List<Turismo> consultarTurismo(){
        return almacen.getTurismos();
    }

    public void registrarMotor(Motor motor){
        almacen.agregarMotor(motor);
    }
    public List<Motor> consultarMotor(){
        return almacen.getMotores();
    }

    public void registrarTapiceria(Tapiceria tapiceria){
        almacen.agregarTapiceria(tapiceria);
    }
    public List<Tapiceria> consultarTapiceria(){
        return almacen.getTapicerias();
    }

    public void registrarRueda(Rueda rueda){
        almacen.agregarRueda(rueda);
    }
    public List<Rueda> consultarRueda(){
        return almacen.getRuedas();
    }

    public void registrarAdministradorSistema(AdministradorSistema administradorSistema){
        almacen.agregarAdministradorSistema(administradorSistema);
    }
    public List<AdministradorSistema> consultarAdministradorSistema(){
        return almacen.getAdministradoresSistema();
    }

    public void registrarGestorPlanta(GestorPlanta gestorPlanta){
        almacen.agregarGestorPlanta(gestorPlanta);
    }
    public List<GestorPlanta> consultarGestorPlanta(){
        return almacen.getGestoresPlanta();
    }

    public void registrarMecanico(Mecanico mecanico){
        almacen.agregarMecanico(mecanico);
    }
    public List<Mecanico> consultarMecanico(){
        return almacen.getMecanicos();
    }

    public void registrarOperario(Operario operario){
        almacen.agregarOperario(operario);
    }
    public List<Operario> consultarOperario(){
        return almacen.getOperarios();
    }

    public void resetSistema(){
        almacen.vaciar();
    }

    public int consultarTotalCoches(){
        return almacen.getTotalCoches();
    }

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

    public List<Operario> buscarOperariosEficientes(){
        List<Operario> res = new ArrayList<>();
        for(Operario op: almacen.getOperarios()){
            if(op.esEficiente()){
                res.add(op);
            }
        }
        return res;
    }

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

    public boolean hayStockSuficiente(int cantidad){
        int cantidadMotores = almacen.getStockMotores();
        int cantidadTapiceria = almacen.getStockTapicerias();
        int cantidadRueda = almacen.getStockRuedas();

        if (cantidadMotores < cantidad) {
            System.out.println("No hay stock suficiente de motores: hay " + cantidadMotores + " y se necesitan " + cantidad);
            return false;
        }
        if (cantidadTapiceria < cantidad) {
            System.out.println("No hay stock suficiente de tapicerías: hay " + cantidadTapiceria + " y se necesitan " + cantidad);
            return false;
        }
        if (cantidadRueda < cantidad * 4) {
            System.out.println("No hay stock suficiente de ruedas: hay " + cantidadRueda+ " y se necesitan " + (cantidad * 4));
            return false;
        }
        return true;
    }

    public Motor quitarStockMotor() {
        List<Motor> motores = almacen.getMotores();
        if (motores.isEmpty()) {
            return null;
        }
        Motor m = motores.get(0);
        almacen.disminuirStockMotor();
        return m;
    }

    public Tapiceria quitarStockTapiceria() {
        List<Tapiceria> tapicerias = almacen.getTapicerias();
        if (tapicerias.isEmpty()) {
            return null;
        }
        Tapiceria t = tapicerias.get(0);
        almacen.disminuirStockTapiceria();
        return t;
    }

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

    public void registrarHistorial(String tipoComponente, String accion) {
        almacen.registrarOperacion(new RegistroMontaje(new Date(), tipoComponente, accion));
    }

    public List<Operario> ordenarOperarios(){
        List<Operario> res = new ArrayList<>(almacen.getOperarios());
        res.sort(Comparator.comparing(Trabajador::getNombre).thenComparing(Trabajador::getApellidos));
        return res;
    }

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

public List<Coche> obtenerCochesTerminados(CadenaMontaje cadenaMontaje) {
        List<Coche> res = new ArrayList<>();
        res.addAll(consultarBiplazasDeportivos());
        res.addAll(consultarTurismo());
        res.addAll(consultarFurgoneta());
        return res;
    }

    public List<Coche> filtrarTipoMotor(List<Coche> coches, String tipoMotor) {
        List<Coche> res = new ArrayList<>();
        for (Coche c: coches) {
            if(c.getMotor() != null && c.getMotor().tipoMotor().equalsIgnoreCase(tipoMotor)) {
                res.add(c);
            }
        }
        return res;
    }

    public List<Coche> filtrarTipoTapiceria(List<Coche> coches, String tipoTapiceria) {
        List<Coche> res = new ArrayList<>();
        for (Coche c: coches){
            if(c.getTapiceria() != null && c.getTapiceria().tipoTapiceria().equalsIgnoreCase(tipoTapiceria)) {
                res.add(c);
            }
        }
        return res;
    }

    public List<Coche> obtenerCochesOrdenados(List<Coche> coches) {
        List<Coche> res = new ArrayList<>(coches);
        res.sort(Comparator.comparing(Coche::tipoCoche));
        return res;
    }

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

    public List<RegistroMontaje> consultarHistorialFecha(Date fecha) {
        return almacen.getRegistrosPorFecha(fecha);
    }
}
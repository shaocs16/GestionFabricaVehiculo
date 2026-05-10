package com.practica.personal;

import java.time.LocalDate;
import com.practica.dashboard.Dashboard;
import com.practica.montaje.CadenaMontaje;
import com.practica.motor.Motor;
import com.practica.rueda.Rueda;
import com.practica.tapiceria.Tapiceria;
import com.practica.vehiculo.*;

public class GestorPlanta extends Trabajador {

    public GestorPlanta(String nombre, String apellidos, String dni,
            String direccion, String numSegSocial,
            double salario, LocalDate fechaIngreso) {
        super(nombre, apellidos, dni, direccion, numSegSocial,
                "Gestor de planta", salario, fechaIngreso);
    }

    public void configurarBiplazas(CadenaMontaje cadena, int cantidad,
            Motor motor, Tapiceria tapiceria, Rueda[] ruedas) {
        System.out.println("[GESTOR] " + getNombre() + " configura " + cantidad
                + " Biplaza(s) en la cadena de montaje.");
        for (int i = 0; i < cantidad; i++) {
            BiplazaDeportivo b = new BiplazaDeportivo("Rojo", 2, 1200.0, 1800.0,
                    tapiceria, motor, ruedas);
            b.setEstadoMontaje(EstadoMontaje.CHASIS);
            cadena.agregarBiplaza(b);
        }
    }

    public void configurarTurismos(CadenaMontaje cadena, int cantidad,
            Motor motor, Tapiceria tapiceria, Rueda[] ruedas) {
        System.out.println("[GESTOR] " + getNombre() + " configura " + cantidad
                + " Turismo(s) en la cadena de montaje.");
        for (int i = 0; i < cantidad; i++) {
            Turismo t = new Turismo("Azul", 5, 1400.0, 2200.0,
                    tapiceria, motor, ruedas);
            t.setEstadoMontaje(EstadoMontaje.CHASIS);
            cadena.agregarTurismo(t);
        }
    }

    public void configurarFurgonetas(CadenaMontaje cadena, int cantidad,
            Motor motor, Tapiceria tapiceria, Rueda[] ruedas) {
        System.out.println("[GESTOR] " + getNombre() + " configura " + cantidad
                + " Furgoneta(s) en la cadena de montaje.");
        for (int i = 0; i < cantidad; i++) {
            Furgoneta f = new Furgoneta("Blanco", 3, 1800.0, 3500.0,
                    tapiceria, motor, ruedas);
            f.setEstadoMontaje(EstadoMontaje.CHASIS);
            cadena.agregarFurgoneta(f);
        }
    }

    public void consultarDashboard(Dashboard dashboard) {
        System.out.println("[GESTOR] " + getNombre()
                + " está revisando el dashboard para detectar posibles errores.");
    }

    public void llamarMecanico(Mecanico mecanico, Coche cocheAveriado, Dashboard dashboard) {
        String msg = "[GESTOR] " + getNombre()
                + " llama al mecánico " + mecanico.getNombre() + " "
                + mecanico.getApellidos() + " para reparar una avería.";
        System.out.println(msg);
        if (dashboard != null) {
            dashboard.update(msg);
        }
        mecanico.repararCoche(cocheAveriado);
    }
}
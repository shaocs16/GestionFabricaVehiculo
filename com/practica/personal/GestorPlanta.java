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
            String color, double tara, double pesoMax,
            Motor motor, Tapiceria tapiceria, Rueda[] ruedas) {
        System.out.println("[GESTOR] " + getNombre() + " configura " + cantidad
                + " Biplaza(s) en la cadena de montaje.");
        for (int i = 0; i < cantidad; i++) {
            BiplazaDeportivo b = new BiplazaDeportivo(color, 2, pesoMax, tara,
                    tapiceria, motor, ruedas);
            b.setEstadoMontaje(EstadoMontaje.CHASIS);
            cadena.agregarBiplaza(b);
        }
    }

    public void configurarTurismos(CadenaMontaje cadena, int cantidad,
            String color, double tara, double pesoMax,
            Motor motor, Tapiceria tapiceria, Rueda[] ruedas) {
        System.out.println("[GESTOR] " + getNombre() + " configura " + cantidad
                + " Turismo(s) en la cadena de montaje.");
        for (int i = 0; i < cantidad; i++) {
            Turismo t = new Turismo(color, 5, pesoMax, tara,
                    tapiceria, motor, ruedas);
            t.setEstadoMontaje(EstadoMontaje.CHASIS);
            cadena.agregarTurismo(t);
        }
    }

    public void configurarFurgonetas(CadenaMontaje cadena, int cantidad,
            String color, double tara, double pesoMax,
            Motor motor, Tapiceria tapiceria, Rueda[] ruedas) {
        System.out.println("[GESTOR] " + getNombre() + " configura " + cantidad
                + " Furgoneta(s) en la cadena de montaje.");
        for (int i = 0; i < cantidad; i++) {
            Furgoneta f = new Furgoneta(color, 3, pesoMax, tara,
                    tapiceria, motor, ruedas);
            f.setEstadoMontaje(EstadoMontaje.CHASIS);
            cadena.agregarFurgoneta(f);
        }
    }

public String consultarDashboard(Dashboard dashboard) {
        return "[GESTOR] " + getNombre() + " " + getApellidos()
                + " revisa el dashboard para detectar incidencias.";
    }

public String llamarMecanico(Mecanico mecanico, Coche cocheAveriado, Dashboard dashboard) {
        mecanico.repararCoche(cocheAveriado);
        return "[GESTOR] " + getNombre() + " " + getApellidos()
                + " llama al mecánico " + mecanico.getNombre() + " "
                + mecanico.getApellidos() + " para reparar una avería.";
    }
}
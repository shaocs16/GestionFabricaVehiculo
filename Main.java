
/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.practica.personal.*;
import com.practica.vehiculo.*;
import com.practica.rueda.*;
import com.practica.motor.*;
import com.practica.fabrica.*;
import com.practica.montaje.*;
import com.practica.dashboard.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== INICIANDO SIMULACIÓN DE LA FÁBRICA ===");

        // 1. Instanciar Almacén y Sistema de Gestión
        AlmacenDatos almacen = new AlmacenDatos();
        SistemaGestion gestionFabrica = new SistemaGestion(almacen);

        // 2. Instanciar Cadena de Montaje
        CadenaMontaje cadena = new CadenaMontaje();

        // 3. Instanciar Visualizador y Dashboard (El Dashboard se suscribe
        // automáticamente al Almacén y a la Cadena)
        IfaceVisualizarDatos visualizador = new VisualizarConsola();
        Dashboard dashboard = new Dashboard(visualizador, almacen, cadena);

        System.out.println("\n--- AÑADIENDO VEHÍCULOS A LA CADENA ---");
        // El dashboard debería reaccionar a estas operaciones
        cadena.agregarTurismo(new Turismo());
        cadena.agregarFurgoneta(new Furgoneta());

        System.out.println("\n--- AÑADIENDO COMPONENTES AL ALMACÉN ---");
        // El dashboard debería reaccionar a estas operaciones
        almacen.agregarMotor(new Electrico());
        almacen.agregarMotor(new Gasolina());
        almacen.agregarRueda(new Normal());

        System.out.println("\n--- VERIFICANDO HISTÓRICO DE OPERACIONES (POR FECHA) ---");
        // Comprobación manual de getRegistrosPorFecha()
        Date fechaHoy = new Date();
        List<RegistroMontaje> historialHoy = almacen.getRegistrosPorFecha(fechaHoy);

        System.out.println("Registros encontrados para hoy (" + historialHoy.size() + "):");
        for (RegistroMontaje registro : historialHoy) {
            System.out.println(registro.toString());
        }

        System.out.println("\n=== FIN DE LA SIMULACIÓN ===");
    }
}
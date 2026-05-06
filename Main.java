
/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.time.LocalDate;
import com.practica.personal.*;

public class Main
{
    // instance variables - replace the example below with your own
    public static void main(String[] args) {
        
        // Creamos un nuevo Operario
        Operario nuevoOperario = new Operario(
            "Juan",                       // nombre
            "Pérez García",               // apellidos
            "12345678A",                  // dni
            "Calle Falsa 123",            // direccion
            "28 12345678 12",             // numSegSocial
            1500.50,                      // salario
            LocalDate.of(2025, 4, 19)     // fechaPago (puedes usar LocalDate.now() para la fecha de hoy)
        );
        // Comprobación de que el puesto se asignó automáticamente gracias al super()
        System.out.println("Trabajador: " + nuevoOperario.getNombre());
        System.out.println("Puesto: " + nuevoOperario.getPuestoTrabajo()); // Imprimirá "Operario"
    }
}
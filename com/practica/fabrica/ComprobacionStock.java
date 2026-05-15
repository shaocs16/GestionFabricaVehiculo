package com.practica.fabrica;

/**
 * Resultado de la comprobación de stock antes de iniciar un ensamblaje.
 * <p>
 * Permite identificar con precisión qué componente tiene stock insuficiente,
 * evitando que {@link SistemaGestion} mezcle lógica de negocio con mensajes
 * de presentación.
 *
 * @author Shao Capilla Sanz
 */
public enum ComprobacionStock {
    /** Stock suficiente para fabricar los vehículos solicitados. */
    OK,
    /** No hay motores suficientes en el almacén. */
    SIN_MOTORES,
    /** No hay tapicerías suficientes en el almacén. */
    SIN_TAPICERIAS,
    /** No hay ruedas suficientes en el almacén. */
    SIN_RUEDAS
}

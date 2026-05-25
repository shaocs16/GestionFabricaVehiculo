# Preguntas nuevas de examen — basadas en el codigo real

Estas preguntas estan elaboradas a partir del codigo fuente real de la practica,
no solo de la descripcion general. Cada una apunta a un detalle concreto que
un profesor puede ver leyendo el codigo.

---

## P1. El metodo `comprobarStock` devuelve un enum, no un booleano. Por que?

**Por que puede caer:**
Es una decision de diseno visible en `SistemaGestion` y `factory_main` que un
alumno que no haya leido el codigo probablemente diria que devuelve `boolean`.

**Respuesta modelo:**

`comprobarStock(int cantidad)` devuelve un valor del enum `ComprobacionStock`
en lugar de un simple `boolean`. Los posibles valores son:

- `OK`: hay stock suficiente de todo.
- `SIN_MOTORES`: faltan motores.
- `SIN_TAPICERIAS`: faltan tapicerias.
- `SIN_RUEDAS`: faltan ruedas (se necesitan 4 por vehiculo).

La ventaja de usar un enum es que el codigo que llama puede saber exactamente
que falta, no solo que "algo falla". En `factory_main`, cuando el resultado no
es `OK`, se muestra al usuario el motivo concreto:

```java
if (res != ComprobacionStock.OK) {
    dashboard.mostrarError("Stock insuficiente de " + res);
}
```

Si hubiera devuelto `boolean`, ese mensaje no seria posible sin anadir logica
adicional. El enum hace el codigo mas expresivo y util.

---

## P2. Explique como el `Planificador` asigna operarios a los robots

**Por que puede caer:**
La logica de `obtenerOperario` y `crearOperarioAleatorio` es codigo propio y
detallado. Refleja un diseno pensado que el profesor puede preguntar para
comprobar que el alumno entiende su propia practica.

**Respuesta modelo:**

El `Planificador` necesita 12 robots en total: 4 por cada una de las 3 cadenas
(Biplaza, Turismo, Furgoneta). Cada robot necesita un operario.

En el constructor, se consultan los operarios registrados en el sistema:

- Si hay operarios registrados, se asignan en orden circular. Es decir, si hay
  menos de 12 operarios, se reutilizan empezando de nuevo desde el primero.
- Si no hay ningun operario registrado, el planificador genera operarios
  sinteticos con datos aleatorios. La mitad de las veces se les asignan mas
  de 10 montajes ya realizados para que sean eficientes.

Este diseno permite que la simulacion funcione siempre, aunque el usuario no
haya dado de alta ningun operario.

---

## P3. Explique la logica de extraccion de ruedas en `quitarStockRuedas`

**Por que puede caer:**
Es uno de los metodos mas elaborados de `SistemaGestion` y tiene una logica
no trivial que distingue dos casos.

**Respuesta modelo:**

El metodo `quitarStockRuedas()` extrae del stock un juego de 4 ruedas para
montarlo en un vehiculo. La logica tiene dos caminos:

1. **Caso normal**: intenta encontrar 4 ruedas del mismo tipo (Normal, Deportivo
   o Todoterreno). Recorre la lista buscando el primer tipo que tenga al menos
   4 unidades consecutivas, y extrae esas 4.

2. **Caso de reserva**: si no hay 4 ruedas del mismo tipo disponibles, extrae
   simplemente las 4 primeras del stock, aunque sean de tipos distintos.

Las ruedas se registran en el almacen siempre en juegos de 4 (en `factory_main`
se llama 4 veces a `registrarRueda` con un bucle `for`), por lo que el caso
normal es el habitual. El caso de reserva protege al sistema ante situaciones
de stock mezclado.

---

## P4. Que ocurre en la simulacion muy compleja cuando hay un apagon?

**Por que puede caer:**
Es la funcionalidad mas compleja de la practica y tiene un flujo detallado con
estados y tiempos concretos visibles en el codigo del `Planificador`.

**Respuesta modelo:**

Cuando el nivel de simulacion es 3 (muy compleja), a partir del segundo 3 se
genera un apagon unico. El `Planificador` activa dos flags:

- `caidaDeLuz = true`: paraliza toda la produccion. Mientras esta activo,
  `trabajarEnEstaciones()` no hace nada.
- `sistemaGestionBloqueado = true`: bloquea el sistema de gestion.

A continuacion se activan dos contadores de reparacion:

- `tiempoReparacionGestion = 2`: el administrador tarda 2 ticks en restaurar
  el sistema de gestion. Cuando llega a 0, se llama a
  `admin.restaurarSistemaGestion(planificador)`.
- `tiempoReparacionCadenas = 3`: tarda 3 ticks en restaurar las cadenas.
  Cuando llega a 0, se llama a `admin.restaurarCadenasMontaje(planificador)`.

Solo cuando ambas restauraciones terminan, la fabrica vuelve a funcionar con
normalidad. El apagon solo ocurre una vez por simulacion (`apagonGenerado`
evita que se repita).

---

## P5. Explique el papel del `GestorPlanta` en el flujo de averias

**Por que puede caer:**
El `GestorPlanta` es un intermediario que muchos alumnos pueden ignorar. Su
presencia o ausencia cambia el flujo de reparacion, lo que es un detalle de
diseno concreto.

**Respuesta modelo:**

Cuando un coche se averia en la simulacion compleja o muy compleja, el
`Planificador` comprueba si hay un `GestorPlanta` registrado:

- **Si hay gestor**: al detectar la averia, el gestor consulta el dashboard
  (`gestorPlanta.consultarDashboard()`) y emite el aviso. Cuando el mecanico
  termina la reparacion, es el gestor quien llama al mecanico mediante
  `gestorPlanta.llamarMecanico(mec, coche)`.

- **Si no hay gestor**: el mecanico repara directamente el coche con
  `mec.repararCoche(coche)`, sin intermediario.

Esta diferencia muestra el principio de delegacion: el gestor actua como
coordinador entre la deteccion del problema y la resolucion. No es
imprescindible para que la simulacion funcione, pero representa el rol
jerarquico real de un gestor de planta.

---

## P6. Explique como se ordena y filtra la lista de operarios en `SistemaGestion`

**Por que puede caer:**
El uso de `Comparator` y lambdas con referencias a metodos es un concepto de
Java que el examen puede evaluar directamente.

**Respuesta modelo:**

`SistemaGestion` ofrece dos metodos de listado de operarios:

**`ordenarOperarios()`**: devuelve los operarios ordenados alfabeticamente
por nombre y, en caso de empate, por apellidos. Usa `Comparator.comparing`
con referencias a metodos de la clase padre `Trabajador`:

```java
res.sort(Comparator.comparing(Trabajador::getNombre)
                   .thenComparing(Trabajador::getApellidos));
```

**`obtenerOperariosProductividad(int minMontajes)`**: filtra operarios que han
realizado al menos `minMontajes` montajes y los ordena de mayor a menor
productividad:

```java
res.sort(Comparator.comparingInt(Operario::getMontajesRealizados).reversed());
```

Estos metodos crean una lista auxiliar sin modificar la original del almacen,
lo que evita efectos secundarios sobre el estado interno del sistema.

---

## P7. Por que `SistemaGestion` trabaja con `IfaceAlmacen` en lugar de `AlmacenDatos`?

**Por que puede caer:**
Es el ejemplo mas directo de programacion contra interfaces en la practica, y
el profesor puede preguntar tanto el motivo como las consecuencias.

**Respuesta modelo:**

`SistemaGestion` declara su dependencia del almacen como:

```java
private IfaceAlmacen almacen;
```

Y recibe la implementacion concreta por el constructor:

```java
public SistemaGestion(IfaceAlmacen almacen) {
    this.almacen = almacen;
}
```

En `factory_main`, se crea asi:

```java
AlmacenDatos almacen = new AlmacenDatos();
sistemaGestion = new SistemaGestion(almacen);
```

Las ventajas de este diseno son:

1. **Intercambiabilidad**: si en el futuro se quisiera guardar los datos en
   una base de datos o en un fichero, bastaria con crear una nueva clase que
   implemente `IfaceAlmacen` y pasarla al constructor. No habria que tocar
   `SistemaGestion`.

2. **Bajo acoplamiento**: `SistemaGestion` no depende de los detalles internos
   de `AlmacenDatos`, solo del contrato definido por la interfaz.

3. **Facilidad de pruebas**: se podria crear un almacen simulado para tests
   sin necesidad de usar la implementacion real.

---

## P8. Explique que hace `delegarAGestor` y por que existe ese metodo

**Por que puede caer:**
Es un metodo de `factory_main` que muestra un patron de delegacion condicional.
El alumno que haya trabajado el codigo lo reconocera; el que no, no.

**Respuesta modelo:**

`delegarAGestor` centraliza la logica de anadir un vehiculo a la cadena
teniendo en cuenta si existe un gestor de planta o no.

Cuando el usuario decide anadir un vehiculo desde el menu, el programa:

1. Comprueba si hay algun `GestorPlanta` registrado.
2. **Si hay gestor**: llama a sus metodos `configurarBiplazas`,
   `configurarTurismos` o `configurarFurgonetas`, que son los responsables de
   crear y anadir el vehiculo a la cadena. El gestor actua como intermediario.
3. **Si no hay gestor**: crea directamente el objeto del vehiculo
   (`new Turismo(...)`, etc.) y lo anade a la cadena llamando a los metodos
   de `CadenaMontaje`.

Este diseno evita duplicar la comprobacion del gestor en cada opcion del menu
(casos 10, 11 y 12 del menu de almacen). Al extraerla a un metodo, el codigo
es mas limpio y facil de mantener.

---

## Tabla resumen de probabilidad

| # | Tema | Probabilidad |
|---|------|-------------|
| P1 | `ComprobacionStock` como enum en lugar de boolean | Alta |
| P4 | Flujo completo del apagon en simulacion muy compleja | Alta |
| P7 | Por que `SistemaGestion` usa `IfaceAlmacen` | Alta |
| P2 | Asignacion de operarios a robots (real vs sintetico) | Media |
| P5 | Papel del `GestorPlanta` en averias | Media |
| P6 | Uso de `Comparator` y lambdas para ordenar operarios | Media |
| P3 | Logica de extraccion de ruedas por tipo | Media-baja |
| P8 | Patron de delegacion en `delegarAGestor` | Baja |

# GestionFabricaVehiculo — POO 2025/2026

Práctica de Programación Orientada a Objetos. Modelado de una fábrica de vehículos.

---

## Estructura del proyecto

```
com/practica/
├── vehiculo/        → Clases de vehículos
├── motor/           → Clases de motores
├── tapiceria/       → Clases de tapicería
├── rueda/           → Clases de ruedas
├── personal/        → Trabajadores (Punto 2.B)
├── dashboard/       → Cuadro de mandos y visualización (Punto 2.C)
├── montaje/         → Cadena de montaje
└── fabrica/         → Sistema de gestión (Punto 2.A)
```

---

## Punto 1 — Cadena de montaje

### Enunciado original

> La cadena de montaje, como su nombre indica, tiene la misión de construir los
> distintos coches que se ofertan en el catálogo. Los tipos de coches que se
> construyen en esta factoría son: **biplaza deportivo, turismo y furgoneta**. Las
> características que se guardan de los vehículos son las siguientes: color, número de
> plazas, tara del vehículo y peso máximo autorizado. Cada tipo de vehículo tiene una
> cadena de montaje diferente. Por tanto, al existir tres tipos de vehículos, la factoría
> de coches dispone de tres tipos de cadenas de montaje diferentes. Los tipos de
> motores que se pueden montar en estos coches son: **eléctrico, gasolina e híbrido**.
> Las características técnicas de los motores que se tienen que almacenar son las
> siguientes: cilindrada, potencia y número de cilindros. El tipo de tapicería que se
> utiliza para los interiores son: **tela, cuero y alcántara**. Las características de la
> tapicería que se almacenarán serán: color y metros cuadrados de tela. Por último,
> las ruedas que se pueden combinar son: **normal, deportivo y todoterreno**. Las
> características que se tienen que almacenar de las ruedas son las siguientes: ancho
> en mm, diámetro de llanta en pulgadas, índice de carga en kg y código de velocidad
> en km/h. El código de velocidad indica la velocidad máxima permitida que el
> neumático puede soportar con seguridad durante un periodo determinado de tiempo.

---

### Implementación

### Vehículos (`com.practica.vehiculo`)

Clase abstracta base `Coche` con los atributos comunes:

| Atributo | Tipo | Descripción |
|---|---|---|
| `color` | `String` | Color del vehículo |
| `plazas` | `int` | Número de plazas |
| `taraVehiculo` | `double` | Tara del vehículo |
| `pesoAutorizado` | `double` | Peso máximo autorizado |
| `motor` | `Motor` | Motor montado |
| `tapiceria` | `Tapiceria` | Tapicería del interior |
| `rueda` | `Rueda[]` | Array de ruedas montadas |

Tres subclases concretas, una por cada tipo de vehículo:
- `BiplazaDeportivo extends Coche`
- `Turismo extends Coche`
- `Furgoneta extends Coche`

### Motores (`com.practica.motor`)

Clase abstracta base `Motor`:

| Atributo | Tipo | Descripción |
|---|---|---|
| `cilindrada` | `double` | Cilindrada del motor |
| `potencia` | `int` | Potencia en CV |
| `numeroCilindros` | `int` | Número de cilindros |

Tres subclases concretas:
- `Electrico extends Motor`
- `Gasolina extends Motor`
- `Hibrido extends Motor`

### Tapicería (`com.practica.tapiceria`)

Clase abstracta base `Tapiceria`:

| Atributo | Tipo | Descripción |
|---|---|---|
| `color` | `String` | Color de la tapicería |
| `metrosCuadrados` | `double` | Metros cuadrados de tela |

Tres subclases concretas:
- `Tela extends Tapiceria`
- `Cuero extends Tapiceria`
- `Alcantara extends Tapiceria`

### Ruedas (`com.practica.rueda`)

Clase abstracta base `Rueda`:

| Atributo | Tipo | Descripción |
|---|---|---|
| `ancho` | `int` | Ancho en mm |
| `pulgadasLlanta` | `int` | Diámetro de llanta en pulgadas |
| `indiceCarga` | `int` | Índice de carga en kg |
| `codigoVelocidad` | `int` | Velocidad máxima soportada en km/h |

Tres subclases concretas:
- `Normal extends Rueda`
- `Deportivo extends Rueda`
- `Todoterreno extends Rueda`

### Cadena de montaje (`com.practica.montaje`)

`CadenaMontaje` gestiona las tres líneas de producción independientes:

```java
private ArrayList<BiplazaDeportivo> cadenaBiplaza;
private ArrayList<Turismo>          cadenaTurismo;
private ArrayList<Furgoneta>        cadenaFurgoneta;
```

---

## Punto 2.A — Sistema de gestión y almacén desacoplado

### El problema que resuelve

El enunciado exige que el almacén de datos esté **desacoplado** del sistema de gestión. Esto significa que si en el futuro se cambia la estructura interna del almacén (de `ArrayList` a `HashMap`, a base de datos, etc.), el sistema de gestión **no necesita modificarse**.

### Solución: patrón Interfaz + Implementación

Se han creado 3 clases en `com.practica.fabrica`:

```
SistemaGestion
      │
      │ solo conoce (desacoplamiento)
      ▼
 <<interface>>
   IfaceAlmacen
      ▲
      │ implements
AlmacenDatos
```

---

## Punto 2.B — Gestión de trabajadores

### Enunciado original

> Gestión de trabajadores que se encarga de gestionar los datos personales (nombre, apellidos, DNI, dirección, número de seguridad social, puesto que desempeña, salario y fecha de ingreso) de los trabajadores de la factoría. Existen cuatro tipos de perfiles: operario, gestor de planta, administrador del sistema, mecánico de cinta.

### Implementación (Completado ✅)

Se ha creado la jerarquía inicial con la clase base abstracta `Trabajador` en el paquete `com.practica.personal` y sus cuatro subclases:
- `Operario`
- `GestorPlanta`
- `AdministradorSistema`
- `Mecanico`

**Claves de la implementación:**
- Cada subclase utiliza herencia (`extends Trabajador`).
- Sus constructores invocan a `super(...)` asignando automáticamente el nombre del puesto que les corresponde.
- Se ha actualizado la interfaz `IfaceAlmacen`, la implementación `AlmacenDatos` y el `SistemaGestion` para añadir la funcionalidad de lectura (get) y escritura (agregar) de todos estos trabajadores sin romper el desacoplamiento previo.

---

## Punto 2.C — Gestión de un dashboard (Cuadro de mandos)

### Enunciado original

> Gestión de un dashboard: el sistema de gestión de fábrica dispone de un
cuadro de mandos (en inglés, dashboard) que permite mostrar el balance de
los distintos componentes en el almacén y el estado de los vehículos en
construcción en las cadenas de montaje. Este cuadro de mandos será la
herramienta que utilizará el gestor de planta para consultar el estado en el
que se encuentran los vehículos que se están montando en cada una de las
cadenas de montaje. Por tanto, cada vez que se produzca un cambio en las
cadenas de montaje y en el almacén porque un componente ha sido
ensamblado, el dashboard tendrá que mostrar la modificación, mostrando la
situación actual del estado de las cadenas de montaje y del almacén. Todo el
proceso de montaje tiene que ser almacenado en la base de datos de
manera que pueda ser consultado por fecha a nivel de componente. Cabe
destacar que se espera que el diseño del dashboard esté desacoplado del
subsistema de visualización de datos para que, en un futuro, el diseño pueda
permitir fácilmente un cambio de subsistema de visualización de datos.

### Implementación (Completado ✅)

Se ha creado el paquete `com.practica.dashboard` con la arquitectura del Observador y Visualizador desacoplado:
1. **`RegistroMontaje.java`**: Guarda cada suceso en la fábrica asociado a una fecha/hora, para que la BD/Almacén conserve el registro histórico de operaciones.
2. **`IfaceVisualizarDatos.java`**: Interfaz de visualización para asegurar que el cuadro de mandos es independiente de la interfaz gráfica o consola de salida.
3. **`VisualizadorConsola.java`**: Implementación base del visualizador por terminal.
4. **`Dashboard.java`**: Clase orquestadora que guarda la lista de `RegistroMontaje` y usa el `VisualizadorConsola` para actualizar la salida gráfica.

Finalmente, la **`CadenaMontaje`** recibe el Dashboard e informa dinámicamente llamando a `notifyObservadores(...)` en cada evento de ensamblaje.

---

## Punto 2.D — Planificador (Scheduler)

### Enunciado original

Planificador (en inglés scheduler). Es el componente principal que hace
funcionar las cadenas de montaje. Para ello, se comporta como un reloj,
donde en cada segundo hace que se ejecute una acción dentro de la cadena
de montaje. De esta forma, el avance de los vehículos por la cadena de
montaje será simulado por el planificador. Por ejemplo, un caso de uso sería
el siguiente: el gestor de planta configura las cadenas de montaje para
construir coches. Cuando se inicia el proceso, en el segundo 1, los operarios
de las cadenas 1, 2 y 3 que controlan los robots de montaje de chasis en
cada una de las cadenas, respectivamente, utilizarán los robots para montar
los chasis en cada una de las cadenas de montaje, requiriendo el tiempo
necesario y aplicando todas las modificaciones necesarias en el estado del
vehículo, cadena de montaje y en el almacén de piezas; en el segundo 2, se
producirá un cambio de estado en el vehículo, el planificador avanzará las
cadenas de montaje hasta los segundos operarios para montar los motores
en cada uno de los chasis montados en el estado anterior. Así,
sucesivamente, a cada segundo, el planificador irá avanzando cada una de
las cadenas de montaje para ir ensamblando los vehículos, siempre y cuando
no se produzca ningún evento externo que lo retrase. Ejemplos de eventos
externos serían cualquier rotura que se produzca en la cadena de montaje
que requiera de mecánicos de cinta para repararlo o problemas en el sistema
que necesiten del administrador para restaurarlos. La Figura 1 muestra un
ejemplo visual del caso de uso descrito. Es importante remarcar que en este
caso de uso todos los operarios tardan un segundo en hacer su función
porque se han considerado sólo operarios eficientes. Si en lugar de eficientes
fueran usuarios estándar o hubiera distintos tipos de operarios en las
cadenas, habría que considerar los tiempos de dichos operarios para realizar
los ensamblajes de los vehículos.

El planificador implementa tres tipos de simulación:
i. Simple: en esta simulación no se produce ningún problema en las
cintas. Sólo hay que considerar los tipos de operarios seleccionados
en cada una de las cintas, cuya selección se realizará de manera
aleatoria.
ii. Compleja: en esta simulación entran en juego los mecánicos. Al
menos uno de cada perfil debe reparar al menos dos problemas en
cada una de las cintas.
iii. Muy compleja: en esta simulación entran en juego los perfiles de
operarios, mecánicos estándar y administrador del sistema. Se tienen
que dar de dos a tres problemas en cada una de las cadenas de
montaje para reparar y al menos un problema de caída de luz para
resolver por el administrador.

---

### Implementación (Completado ✅)

Para llevar a cabo la lógica del reloj y las simulaciones de la fábrica, se ha programado lo siguiente:

1. **`Planificador.java`**: Actúa como el motor del tiempo de la fábrica mediante un bucle `while` y `Thread.sleep(1000)`. En cada "segundo" que pasa:
   - Avanza el estado de montaje de los vehículos (`CHASIS` -> `MOTOR` -> etc.).
   - Genera eventos y averías aleatorias (`generarEventos`) dependiendo de la dificultad elegida (simulación tipo 1, 2 o 3).
   - Coordina las reparaciones (`resolverIncidencias`) simulando que el tiempo de avería disminuye.

2. **Adaptación de `Coche.java`**:
   - Se añadió el `EstadoMontaje` para registrar la posición en la cinta.
   - Se añadieron atributos de control `averiado` (boolean) y `tiempoReparacion` (int). Esto permite que el Planificador sepa qué vehículos deben detenerse temporalmente en la cinta.

3. **Manejo de Emergencias y Trabajadores**:
   - Para cumplir los requisitos de las simulaciones Compleja y Muy Compleja, el Planificador usa variables como `caidaDeLuz` para detener por completo todas las cadenas.
   - Los perfiles de trabajadores (Mecánicos y Administrador) entran en juego conceptualmente cuando se reducen los tiempos de reparación, notificando al Dashboard cuando logran devolver la normalidad.

4. **Notificaciones al Cuadro de Mandos**:
   - Manteniendo la arquitectura del Punto 2.C, el Planificador está totalmente conectado a la `CadenaMontaje` para que, en cada avance o avería, se llame a `notifyObservadores(msg)` y la salida gráfica mantenga al usuario informado en todo momento.

### Detalles del Funcionamiento y Perfiles de Trabajadores

En cuanto al funcionamiento de la factoría, las cadenas de montaje se encargan de
combinar los elementos de motores, tapicería y ruedas para construir vehículos. De esta
forma, el gestor de planta, mediante la aplicación, configura los tres tipos de vehículos que
quiere construir, selecciona los componentes, introduce las unidades y las cadenas de
montaje, comprobando previamente que tienen piezas suficientes para realizar el pedido, se
ponen a construir las unidades demandadas. En cuanto a los perfiles de los trabajadores,
son los siguientes:

a) **Operario**: En este tipo de trabajador existen dos perfiles de operarios: eficiente y
estándar. Ambos perfiles se encargan de trabajar en la cadena de montaje. La
diferencia entre ellos es el tiempo que dedican cada uno a realizar su tarea en la
cadena de montaje debido a la experiencia que tienen en su puesto de trabajo.
Esta experiencia se mide en cantidad de montajes de piezas. Así, un operario
eficiente será aquel que haya realizado > 10 montajes de piezas en su puesto de trabajo. En cuanto a la diferencia del tiempo que dedican, mientras que el primer
perfil, el operario eficiente, realiza la tarea que le ha sido encomendada en un
segundo; el segundo perfil, el operario estándar, necesita el triple de tiempo para
realizar la tarea que le han encomendado. La labor que el operario realiza es
controlar el robot que se encarga del montaje del componente. En cada cadena
de montaje hay un robot para montar cada componente y un operador por robot.
cada vez que un robot termine su labor de montaje, se cambiará el estado del
coche. Los estados en los que se puede encontrar un vehículo en la cadena de
montaje son: Chasis, Motor, Tapicería, Ruedas.

### Implementación del Operario y Robot (Completado ✅)

Para implementar el comportamiento de los perfiles del Operario y su vínculo con el Robot en la cadena de montaje, se ha programado lo siguiente:

1. **`Operario.java`** (paquete `com.practica.personal`):
   - Se añadió el atributo `private int montajesRealizados` inicializado a `0` en el constructor.
   - Se implementó `esEficiente()`: devuelve `true` si `montajesRealizados > 10`, distinguiendo así el perfil **eficiente** del **estándar**.
   - Se implementó `getTiempoMontaje()`: devuelve `1` (segundo) si es eficiente, o `3` (segundos) si es estándar.
   - Se implementó `registrarMontajeCompletado()`: incrementa el contador de montajes, permitiendo que el operario gane experiencia y eventualmente cambie de perfil.

2. **`Robot.java`** (paquete `com.practica.montaje`):
   - Clase nueva que representa una estación de trabajo en la cadena de montaje.
   - Cada Robot tiene asignado un `Operario` que controla su velocidad de trabajo.
   - `recibirCoche(Coche c)`: Asigna el coche al robot e inicializa `tiempoRestante` con el tiempo del operario (`getTiempoMontaje()`).
   - `trabajar()`: Decrementa `tiempoRestante` en cada tick de simulación. Cuando llega a 0, llama a `operario.registrarMontajeCompletado()` y devuelve `true`, señalizando que el estado del vehículo puede avanzar.
   - `liberarCoche()`: Libera el robot para que pueda recibir el siguiente vehículo.

3. **Integración en `Planificador.java`**:
   - Se instancian **12 Robots** en el constructor (4 por cada cadena: Biplaza, Turismo y Furgoneta), uno por cada componente a montar (Chasis, Motor, Tapicería, Ruedas), cada uno con su propio `Operario`.
   - Se reemplazó el avance automático de estado por el nuevo método `procesarCadenaConRobots()`. Ahora cada coche es asignado al robot de su estación correspondiente y sólo avanza de estado cuando el robot (y su operario) han completado el trabajo.
   - Si el robot de una estación está ocupado con otro coche, los coches que vienen detrás quedan retenidos en la cinta, simulando el comportamiento real de una cadena de montaje.
   - A medida que los operarios acumulan montajes, pasan de tardar 3 segundos a 1 segundo por estación de forma dinámica durante la propia simulación.

---

b) **Gestor de planta**: Es el encargado de monitorizar la planta para que las cadenas
de montaje y los operadores no dejen de trabajar. Su labor se centra en
configurar los componentes que utilizarán las cadenas de montaje para
ensamblar los vehículos. Además, tienen que estar pendiente del dashboard
para comprobar que no se ha producido ningún error en el proceso de montaje,
ya que él es el encargado de llamar a los mecánicos de cinta cuando tienen que
llevar a cabo una reparación.

### Implementación del Gestor de Planta (Completado ✅)

Para implementar el comportamiento del Gestor de Planta y conectarlo con el resto del sistema, se ha programado lo siguiente:

1. **`GestorPlanta.java`** (paquete `com.practica.personal`):
   - `configurarBiplazas(...)`, `configurarTurismos(...)`, `configurarFurgonetas(...)`: Crean las unidades de cada tipo de vehículo con los componentes elegidos y las añaden a la `CadenaMontaje`.
   - `consultarDashboard(Dashboard)`: Registra la acción del gestor al revisar el cuadro de mandos.
   - `llamarMecanico(Mecanico, Coche, Dashboard)`: Notifica al Dashboard y delega la reparación en el `Mecánico` correspondiente.

2. **`Mecanico.java`** (paquete `com.practica.personal`):
   - `repararCoche(Coche c)`: Elimina inmediatamente la avería del coche y actualiza el contador `reparacionesRealizadas`.
   - `getReparacionesRealizadas()`: Permite verificar al final de la simulación cuántas reparaciones ha llevado a cabo.

3. **`AdministradorSistema.java`** (paquete `com.practica.personal`):
   - `restaurarLuz(Planificador p)`: Desactiva la caída de luz en el Planificador, notifica al Dashboard e incrementa `restauracionesRealizadas`.
   - `getRestauracionesRealizadas()`: Permite verificar el número de restauraciones eléctricas al final de la simulación.

4. **Integración en `Planificador.java`**:
   - Recibe por inyección un array de `Mecanico[]` (uno por cadena) y un `AdministradorSistema`.
   - Reemplaza los mensajes hardcoded por llamadas reales a `mecanico.repararCoche(c)` y `admin.restaurarLuz(this)`.
   - Expone `isCaidaDeLuz()` / `setCaidaDeLuz()` para que el Administrador pueda operar sobre el estado de la fábrica.
   - Imprime un resumen de estadísticas de trabajadores al finalizar la simulación.

5. **`Main.java`**:
   - Instancia el `GestorPlanta`, tres `Mecánicos` y el `AdministradorSistema`.
   - Presenta un menú interactivo donde el gestor elige: número de vehículos de cada tipo, tipo de motor, tapicería, ruedas y modalidad de simulación.
   - Conecta el `Dashboard` como observador de la `CadenaMontaje` y del `Planificador` antes de iniciar la simulación.

---

c) **Administrador del sistema**: Es el encargado de velar que todo el software
funcione correctamente: cadena de montaje y gestor de fábrica. Por tanto,
cuando se produzca cualquier error en el sistema, como por ejemplo un apagón,
todas los trabajadores permanecerán quietos en su puesto de trabajo hasta que
el administrador del sistema restaure el funcionamiento de la fábrica,
necesitando dos segundos para reanudar el sistema de gestión de la fábrica y
tres segundos para reanudar las cadenas de montaje.

d) **Mecánico de cinta**: efectivo y estándar. Ambos perfiles se dedican a reparar la
cinta cuando se produzca un problema, la diferencia entre ambos es el tiempo
que tardan en reparar la cinta, debido a la experiencia que tienen en su puesto
de trabajo. Como ocurre con los operarios, la experiencia se mide en cantidad de
reparaciones realizadas. De esta forma, un mecánico eficiente será aquel que
haya realizado > 20 reparaciones. En lo relacionado a la diferencia de tiempo de
reparación entre ambos, el primero tarda un segundo en reparar cualquier
problema que se produzca en la cinta y el segundo tarda de 2 a 5 segundos en
repararla.

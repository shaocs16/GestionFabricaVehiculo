# Posibles preguntas de examen sobre la practica obligatoria

Este documento resume posibles preguntas de desarrollo sobre la practica de gestion de fabrica de vehiculos y propone respuestas modelo. La idea no es memorizar palabra por palabra, sino entender el razonamiento y saber explicarlo con ejemplos concretos de tu codigo.

## 1. Explique la estructura general de la practica

**Respuesta modelo:**

La practica implementa un sistema de gestion de una fabrica de vehiculos. La aplicacion se organiza en varios paquetes, cada uno con una responsabilidad concreta:

- `com.practica.vehiculo`: contiene la jerarquia de vehiculos, con `Coche` como clase abstracta y subclases como `Turismo`, `Furgoneta` y `BiplazaDeportivo`.
- `com.practica.motor`: contiene la jerarquia de motores, como `Gasolina`, `Electrico` e `Hibrido`.
- `com.practica.rueda`: contiene tipos de rueda como `Normal`, `Deportivo` y `Todoterreno`.
- `com.practica.tapiceria`: contiene tipos de tapiceria como `Tela`, `Cuero` y `Alcantara`.
- `com.practica.personal`: contiene los trabajadores de la fabrica: `Operario`, `Mecanico`, `GestorPlanta` y `AdministradorSistema`, todos derivados de `Trabajador`.
- `com.practica.fabrica`: contiene el almacen de datos, la interfaz del almacen y la clase `SistemaGestion`, que actua como capa de gestion.
- `com.practica.montaje`: contiene la cadena de montaje y los robots.
- `com.practica.planificador`: contiene la logica de simulacion del montaje.
- `com.practica.dashboard`: contiene el sistema de visualizacion y el patron observador.

La clase `Factory_Main` coordina el programa mediante menus de consola. Desde ella se registran componentes, trabajadores, vehiculos, se consulta stock y se lanza la simulacion.

## 2. Explique la jerarquia de vehiculos

**Respuesta modelo:**

La jerarquia de vehiculos se basa en la clase abstracta `Coche`. Esta clase representa los atributos comunes de cualquier vehiculo fabricado: color, numero de plazas, peso autorizado, tara, motor, tapiceria, ruedas y estado de montaje.

`Coche` es abstracta porque no representa un vehiculo concreto, sino una generalizacion. Define el metodo abstracto `tipoCoche()`, que obliga a cada subclase a indicar que tipo de coche representa.

Las subclases principales son:

- `Turismo`.
- `Furgoneta`.
- `BiplazaDeportivo`.

Cada una hereda los atributos y metodos comunes de `Coche` y especializa el comportamiento indicando su tipo concreto. Esto es un ejemplo de herencia y polimorfismo: se puede tratar un `Turismo`, una `Furgoneta` o un `BiplazaDeportivo` como un `Coche`, pero cada uno puede responder de forma distinta al metodo `tipoCoche()`.

## 3. Explique la diferencia entre herencia y composicion usando la practica

**Respuesta modelo:**

En la practica aparecen tanto herencia como composicion.

La herencia se usa cuando una clase es una especializacion de otra. Por ejemplo:

- `Turismo extends Coche`.
- `Furgoneta extends Coche`.
- `BiplazaDeportivo extends Coche`.
- `Operario extends Trabajador`.
- `Mecanico extends Trabajador`.
- `Gasolina extends Motor`.

En estos casos se cumple una relacion "es un": un turismo es un coche, un operario es un trabajador, un motor de gasolina es un motor.

La composicion se usa cuando una clase contiene objetos de otras clases. Por ejemplo, un `Coche` tiene un `Motor`, una `Tapiceria` y un array de `Rueda`. En este caso la relacion es "tiene un": un coche tiene un motor, tiene una tapiceria y tiene ruedas.

La herencia permite reutilizar atributos y comportamientos comunes. La composicion permite construir objetos complejos a partir de otros objetos.

## 4. Explique el uso de clases abstractas en la practica

**Respuesta modelo:**

En la practica se usan clases abstractas para representar conceptos generales que no deberian instanciarse directamente.

Un ejemplo es `Coche`. No tiene sentido crear un coche generico sin saber si es turismo, furgoneta o biplaza. Por eso `Coche` contiene los datos comunes y declara el metodo abstracto `tipoCoche()`.

Otro ejemplo es `Trabajador`, que contiene datos comunes como nombre, apellidos, DNI, direccion, numero de seguridad social, puesto, salario y fecha de ingreso. Sin embargo, en la aplicacion se crean trabajadores concretos como `Operario`, `Mecanico`, `GestorPlanta` o `AdministradorSistema`.

Este diseno permite evitar duplicacion de codigo y obliga a las subclases a concretar el comportamiento especifico cuando sea necesario.

## 5. Explique el uso de interfaces en la practica

**Respuesta modelo:**

La practica usa interfaces para desacoplar partes del sistema y permitir que el codigo dependa de contratos, no de implementaciones concretas.

Un ejemplo importante es `IfaceAlmacen`. Esta interfaz define las operaciones que debe ofrecer un almacen: agregar vehiculos, consultar listas, registrar componentes, gestionar stock y consultar historial. La clase `AlmacenDatos` implementa esa interfaz usando `ArrayList`.

La clase `SistemaGestion` trabaja con un atributo de tipo `IfaceAlmacen`, no directamente con `AlmacenDatos`. Esto permite cambiar la implementacion del almacen en el futuro sin modificar toda la logica de gestion.

Tambien aparecen interfaces en el patron observador:

- `Observable`: define metodos para anadir, eliminar y notificar observadores.
- `Observador`: define el metodo `update(String message)`.

Ademas, `IfaceVisualizarDatos` desacopla el `Dashboard` de la forma concreta de mostrar la informacion. Actualmente se usa consola, pero podria cambiarse por otra visualizacion.

## 6. Explique el patron Observador en la practica

**Respuesta modelo:**

El patron Observador permite que ciertos objetos reciban notificaciones cuando cambia el estado de otros objetos, sin acoplar directamente las clases entre si.

En la practica, `Dashboard` implementa la interfaz `Observador`. Esto significa que puede recibir mensajes mediante el metodo `update(String message)`.

Por otro lado, clases como `AlmacenDatos`, `CadenaMontaje` y `Planificador` implementan o usan el comportamiento de `Observable`, es decir, pueden tener una lista de observadores y notificarles eventos.

Ejemplos de eventos notificados:

- Se ha agregado un vehiculo a la cadena.
- Se ha anadido un componente al almacen.
- Un vehiculo ha terminado el montaje.
- Se ha producido una averia.
- Ha finalizado la simulacion.

La ventaja es que la logica de negocio no necesita saber exactamente como se muestra el mensaje. Solo notifica el evento, y el `Dashboard` se encarga de visualizarlo.

## 7. Explique la funcion de `SistemaGestion`

**Respuesta modelo:**

`SistemaGestion` actua como una fachada o capa intermedia entre la interfaz de usuario, el almacen y la simulacion.

Sus responsabilidades principales son:

- Registrar vehiculos terminados.
- Registrar motores, tapicerias y ruedas.
- Registrar trabajadores.
- Consultar stock.
- Buscar trabajadores por nombre o DNI.
- Ordenar operarios.
- Filtrar vehiculos terminados por motor o tapiceria.
- Calcular configuraciones mas ensambladas.
- Consultar el historial de operaciones por fecha.

En lugar de que `Factory_Main` acceda directamente a las listas de `AlmacenDatos`, llama a metodos de `SistemaGestion`. Esto mejora la organizacion, centraliza la logica y reduce el acoplamiento.

Ademas, `SistemaGestion` depende de la interfaz `IfaceAlmacen`, por lo que no queda ligado a una implementacion concreta del almacen.

## 8. Explique como se gestiona el stock de componentes

**Respuesta modelo:**

El stock se gestiona principalmente mediante `AlmacenDatos` y `SistemaGestion`.

`AlmacenDatos` almacena en listas los componentes disponibles:

- Lista de `Motor`.
- Lista de `Tapiceria`.
- Lista de `Rueda`.

Desde `Factory_Main`, el usuario puede anadir motores, tapicerias y ruedas. Las ruedas se registran en juegos de 4, porque cada vehiculo necesita cuatro ruedas.

Antes de anadir o simular vehiculos, se comprueba el stock mediante `SistemaGestion.comprobarStock(int cantidad)`. Este metodo verifica que haya:

- Al menos un motor por vehiculo.
- Al menos una tapiceria por vehiculo.
- Al menos cuatro ruedas por vehiculo.

Durante el montaje, el `Planificador` consume componentes mediante:

- `quitarStockMotor()`.
- `quitarStockTapiceria()`.
- `quitarStockRuedas()`.

Cuando el vehiculo avanza al estado correspondiente, se le asigna el componente consumido del almacen.

## 9. Explique el funcionamiento de la cadena de montaje

**Respuesta modelo:**

La cadena de montaje esta representada por la clase `CadenaMontaje`. Esta clase contiene tres listas, una por cada tipo de vehiculo:

- `cadenaBiplaza`.
- `cadenaTurismo`.
- `cadenaFurgoneta`.

Cuando el usuario crea un vehiculo desde el menu, este se anade a una de esas listas. La cadena tambien implementa notificaciones al `Dashboard` mediante el patron Observador.

Los vehiculos avanzan por distintos estados de montaje definidos en `EstadoMontaje`:

- `CHASIS`.
- `MOTOR`.
- `TAPICERIA`.
- `RUEDAS`.
- `TERMINADO`.

La clase `Planificador` es la encargada de hacer avanzar la simulacion. En cada segundo de simulacion recorre las cadenas y usa robots para trabajar en cada estacion. Cuando un coche termina una fase, pasa al siguiente estado. Cuando llega a `TERMINADO`, se registra en el almacen como vehiculo terminado.

## 10. Explique la funcion del `Planificador`

**Respuesta modelo:**

`Planificador` es el motor de la simulacion. Su funcion es coordinar el montaje de los vehiculos que estan en la cadena.

Trabaja segundo a segundo y realiza varias tareas:

- Recorre las tres cadenas de montaje.
- Asigna coches a robots segun su estado actual.
- Hace avanzar los coches por las fases de montaje.
- Consume stock de motores, tapicerias y ruedas.
- Registra los vehiculos terminados en `SistemaGestion`.
- Registra operaciones en el historial.
- Genera y resuelve averias en simulaciones complejas.
- Gestiona apagones en la simulacion muy compleja.
- Notifica eventos al `Dashboard`.

El `Planificador` permite tres niveles de simulacion:

- Simple: sin averias.
- Compleja: con averias y mecanicos.
- Muy compleja: con averias, mecanicos y apagones gestionados por un administrador.

## 11. Explique los estados de montaje de un coche

**Respuesta modelo:**

Cada coche tiene un estado de montaje almacenado en el atributo `estado` de la clase `Coche`. Ese estado indica en que fase del proceso se encuentra el vehiculo.

Los estados estan definidos en el enum `EstadoMontaje` y representan el avance del vehiculo por la cadena:

- `CHASIS`: fase inicial.
- `MOTOR`: se monta el motor.
- `TAPICERIA`: se monta la tapiceria.
- `RUEDAS`: se montan las ruedas.
- `TERMINADO`: el vehiculo ya esta finalizado.

El `Planificador` consulta el estado actual del coche y decide que robot debe trabajar sobre el. Cuando el robot termina, el coche pasa al siguiente estado.

Este uso de un enum evita trabajar con cadenas de texto sueltas y hace que el codigo sea mas claro y seguro.

## 12. Explique como se registran los vehiculos terminados

**Respuesta modelo:**

Cuando un vehiculo llega al estado `TERMINADO`, el `Planificador` lo registra mediante `SistemaGestion`.

El codigo comprueba el tipo real del coche usando `instanceof`:

- Si es `BiplazaDeportivo`, llama a `registrarBiplazaDeportivo()`.
- Si es `Turismo`, llama a `registrarTurismo()`.
- Si es `Furgoneta`, llama a `registrarFurgoneta()`.

Estos metodos delegan en `AlmacenDatos`, que guarda el vehiculo en la lista correspondiente y registra una operacion en el historial.

Asi se separan los vehiculos pendientes en la cadena de los vehiculos ya terminados y almacenados historicamente.

## 13. Explique la gestion de trabajadores

**Respuesta modelo:**

Los trabajadores se modelan mediante la clase abstracta `Trabajador`, que contiene los datos comunes: nombre, apellidos, DNI, direccion, numero de seguridad social, puesto, salario y fecha de ingreso.

Las subclases representan perfiles concretos:

- `Operario`: trabaja en el montaje mediante robots.
- `Mecanico`: repara averias en simulaciones complejas.
- `GestorPlanta`: consulta el dashboard y llama a mecanicos.
- `AdministradorSistema`: restaura el sistema y las cadenas en caso de apagon.

Este diseno evita duplicar atributos personales y permite que cada tipo de trabajador tenga comportamiento especifico.

Por ejemplo, `Operario` tiene contador de montajes realizados y puede ser eficiente si supera cierto numero de montajes. `Mecanico` tiene contador de reparaciones y puede tardar menos si es eficiente.

## 14. Explique como se determina si un operario o mecanico es eficiente

**Respuesta modelo:**

En la practica se define la eficiencia en funcion de la experiencia acumulada.

En `Operario`, el metodo `esEficiente()` devuelve verdadero si el operario ha realizado mas de 10 montajes. Si es eficiente, su tiempo de montaje es 1 segundo; si no, tarda 3 segundos.

En `Mecanico`, el metodo `esEficiente()` devuelve verdadero si ha realizado mas de 20 reparaciones. Si es eficiente, tarda 1 segundo en reparar; si no, tarda un tiempo aleatorio entre 2 y 5 segundos.

Esto permite que el comportamiento de la simulacion dependa del estado interno de los objetos, que es una idea propia de la programacion orientada a objetos.

## 15. Explique la simulacion simple, compleja y muy compleja

**Respuesta modelo:**

La practica permite tres tipos de simulacion:

La simulacion simple no genera incidencias. Los vehiculos avanzan por la cadena hasta llegar a `TERMINADO`, consumiendo componentes del stock.

La simulacion compleja introduce averias. Algunos coches pueden marcarse como averiados y quedan detenidos hasta que un mecanico los repara. Para iniciar esta simulacion se requiere al menos un mecanico registrado.

La simulacion muy compleja incluye averias y tambien apagones. En caso de apagon, la fabrica queda parada y el sistema de gestion queda bloqueado. El administrador de sistema debe restaurar el sistema y las cadenas de montaje. Para iniciar esta simulacion se necesita al menos un administrador de sistema.

Este planteamiento permite comprobar la interaccion entre vehiculos, mecanicos, gestor de planta, administrador, cadena de montaje y dashboard.

## 16. Explique el papel del `Dashboard`

**Respuesta modelo:**

El `Dashboard` representa el cuadro de mandos de la fabrica. Su funcion es mostrar al usuario informacion sobre eventos relevantes del sistema.

Implementa la interfaz `Observador`, por lo que puede recibir mensajes mediante el metodo `update(String message)`. Estos mensajes proceden de objetos observables como el almacen, la cadena de montaje o el planificador.

El `Dashboard` no imprime directamente de una forma fija, sino que delega en una interfaz llamada `IfaceVisualizarDatos`. En la practica se usa `VisualizarConsola`, pero podria cambiarse por otra implementacion.

Esto aplica el principio de bajo acoplamiento, porque la logica del sistema no depende directamente de la forma de visualizacion.

## 17. Explique el uso de colecciones en la practica

**Respuesta modelo:**

La practica usa principalmente `ArrayList` para almacenar conjuntos dinamicos de objetos.

Ejemplos:

- `AlmacenDatos` usa listas para motores, ruedas, tapicerias, vehiculos terminados y trabajadores.
- `CadenaMontaje` usa listas para los vehiculos pendientes de cada tipo.
- `Planificador` recorre listas de coches para avanzar el montaje.
- `SistemaGestion` devuelve listas filtradas u ordenadas en busquedas y estadisticas.

Tambien se usa `Map<String, Integer>` para contar configuraciones de vehiculos ensamblados. La clave representa una configuracion concreta y el valor representa cuantas veces aparece.

Las colecciones permiten gestionar cantidades variables de objetos sin usar arrays de tamano fijo.

## 18. Explique como se realizan busquedas y listados

**Respuesta modelo:**

Las busquedas y listados estan centralizados en `SistemaGestion`.

Ejemplos:

- `buscarOperariosPorNombre(String nombre)`: recorre la lista de operarios y devuelve los que contienen el nombre buscado.
- `buscarMecanicosPorNombre(String nombre)`: hace lo mismo con mecanicos.
- `buscarTrabajadorPorDni(String dni)`: busca el DNI en operarios, mecanicos, gestores y administradores.
- `ordenarOperarios()`: devuelve operarios ordenados por nombre y apellidos.
- `obtenerOperariosProductividad(int minMontajes)`: filtra operarios que alcanzan un minimo de montajes y los ordena por productividad.
- `filtrarTipoMotor(...)`: filtra vehiculos terminados por tipo de motor.
- `filtrarTipoTapiceria(...)`: filtra vehiculos terminados por tipo de tapiceria.

Esto muestra uso de recorridos, comparaciones, filtros, ordenacion con `Comparator` y listas auxiliares.

## 19. Explique el historial de operaciones

**Respuesta modelo:**

El historial de operaciones permite guardar acciones relevantes del sistema, como anadir componentes o terminar vehiculos.

Cada entrada se representa mediante `RegistroMontaje`, que incluye una fecha, un tipo de componente o entidad y una descripcion de la accion.

`AlmacenDatos` mantiene una lista llamada `historial`. Cuando ocurre una operacion importante, se registra una nueva entrada.

`SistemaGestion.consultarHistorialFecha(Date fecha)` permite consultar operaciones de una fecha concreta. En `AlmacenDatos.getRegistrosPorFecha(Date fecha)`, la comparacion se realiza por dia y ano, no por hora exacta.

Esto permite mostrar desde el menu las operaciones realizadas en una fecha determinada.

## 20. Si hubiera que anadir un nuevo tipo de motor, explique que cambios haria

**Respuesta modelo:**

Para anadir un nuevo tipo de motor, crearia una nueva clase en el paquete `com.practica.motor` que heredase de `Motor`.

Por ejemplo, si se quisiera anadir un motor Diesel:

```java
public class Diesel extends Motor {
    public Diesel(double cilindrada, int potencia, int numeroCilindros) {
        super(cilindrada, potencia, numeroCilindros);
    }

    @Override
    public String tipoMotor() {
        return "Diesel";
    }
}
```

Despues habria que anadir una opcion en el menu de almacen de `Factory_Main` para registrar ese nuevo motor mediante `sistemaGestion.registrarMotor(new Diesel(...))`.

No seria necesario modificar `Coche`, porque los coches trabajan con la clase base `Motor`. Esto demuestra la ventaja del polimorfismo.

## 21. Si hubiera que anadir un nuevo tipo de vehiculo, explique que cambios haria

**Respuesta modelo:**

Para anadir un nuevo tipo de vehiculo, crearia una clase que heredase de `Coche` e implementase el metodo `tipoCoche()`.

Por ejemplo, para anadir un `Camioneta`, se podria crear:

```java
public class Camioneta extends Coche {
    public Camioneta(String color, int plazas, double pesoAutorizado,
                     double taraVehiculo, Tapiceria tapiceria,
                     Motor motor, Rueda[] rueda) {
        super(color, plazas, pesoAutorizado, taraVehiculo, tapiceria, motor, rueda);
    }

    @Override
    public String tipoCoche() {
        return "Camioneta";
    }
}
```

Despues habria que decidir si la cadena de montaje va a tener una nueva lista especifica para ese tipo de vehiculo. Si se mantiene el mismo diseno actual, habria que anadir una lista en `CadenaMontaje`, metodos para agregar y consultar, opciones en el menu, y registro en `SistemaGestion` y `AlmacenDatos`.

La respuesta importante es indicar que crear la clase es facil gracias a la herencia, pero integrarla completamente en el sistema requiere modificar la cadena, el almacen y los menus.

## 22. Explique ventajas e inconvenientes del diseno actual

**Respuesta modelo:**

Ventajas:

- La practica esta organizada en paquetes con responsabilidades diferenciadas.
- Usa herencia para reutilizar atributos comunes en vehiculos, componentes y trabajadores.
- Usa composicion para construir coches a partir de motor, tapiceria y ruedas.
- Usa interfaces para reducir acoplamiento, como `IfaceAlmacen` e `IfaceVisualizarDatos`.
- Usa el patron Observador para notificar eventos al dashboard.
- Centraliza la logica de gestion en `SistemaGestion`.

Inconvenientes o mejoras posibles:

- `Factory_Main` concentra mucho codigo de menus y podria dividirse en clases mas pequenas.
- Algunas partes dependen de listas separadas por tipo de vehiculo, lo que dificulta anadir nuevos tipos.
- La simulacion usa tiempos reales con `Thread.sleep(1000)`, lo que puede hacer lenta la ejecucion.
- Algunas operaciones usan `instanceof`, lo que podria reducirse con mas polimorfismo.
- El almacenamiento esta en memoria, por lo que los datos se pierden al cerrar el programa.

## 23. Explique donde aparece el polimorfismo en la practica

**Respuesta modelo:**

El polimorfismo aparece cuando se trabaja con referencias de una clase base o interfaz, pero el objeto real pertenece a una subclase concreta.

Ejemplos:

- Una lista de `Motor` puede contener objetos `Gasolina`, `Electrico` o `Hibrido`.
- Una lista de `Coche` puede contener `Turismo`, `Furgoneta` o `BiplazaDeportivo`.
- Un metodo puede llamar a `tipoMotor()` sin saber exactamente que tipo concreto de motor esta usando.
- `Dashboard` se trata como `Observador`, y los objetos observables solo necesitan llamar a `update()`.
- `SistemaGestion` trabaja con `IfaceAlmacen`, aunque la implementacion concreta sea `AlmacenDatos`.

El polimorfismo facilita extender el sistema y reduce dependencias con clases concretas.

## 24. Explique como se aplica la encapsulacion

**Respuesta modelo:**

La encapsulacion consiste en ocultar los atributos internos de una clase y acceder a ellos mediante metodos.

En la practica, muchas clases tienen atributos `private`. Por ejemplo, `Coche` tiene como privados `color`, `plazas`, `pesoAutorizado`, `taraVehiculo`, `tapiceria`, `motor`, `rueda`, `estado`, `averiado` y `tiempoReparacion`.

Para acceder o modificar esos atributos se usan getters y setters, como `getColor()`, `setColor()`, `getMotor()`, `setMotor()` o `getEstadoMontaje()`.

Esto protege el estado interno de los objetos y permite controlar como se modifican sus datos.

## 25. Pregunta general: diferencia entre clase abstracta e interfaz

**Respuesta modelo:**

Una clase abstracta puede tener atributos, constructores, metodos implementados y metodos abstractos. Se usa cuando varias clases comparten estado y comportamiento comun. En la practica, `Coche` y `Trabajador` son buenos ejemplos.

Una interfaz define un contrato de metodos que una clase debe implementar. Se usa para desacoplar componentes y permitir varias implementaciones. En la practica, `IfaceAlmacen`, `Observable`, `Observador` e `IfaceVisualizarDatos` son ejemplos de interfaces.

La diferencia principal es que una clase solo puede heredar de una clase abstracta, pero puede implementar varias interfaces. La clase abstracta representa una relacion mas fuerte de tipo "es un", mientras que la interfaz expresa una capacidad o contrato.

## 26. Pregunta general: diferencia entre sobrecarga y sobrescritura

**Respuesta modelo:**

La sobrecarga consiste en tener varios metodos con el mismo nombre pero distinta lista de parametros dentro de una clase. Se resuelve en tiempo de compilacion.

La sobrescritura consiste en que una subclase redefine un metodo heredado de la superclase. Se usa con `@Override` y se resuelve en tiempo de ejecucion mediante polimorfismo.

En la practica aparece sobrescritura, por ejemplo, cuando las subclases de `Coche` implementan `tipoCoche()`, o cuando las subclases de `Motor` implementan `tipoMotor()`.

## 27. Pregunta general: explique que es un constructor

**Respuesta modelo:**

Un constructor es un metodo especial que se ejecuta al crear un objeto con `new`. Sirve para inicializar los atributos del objeto.

En la practica, por ejemplo, el constructor de `Coche` recibe color, plazas, peso autorizado, tara, tapiceria, motor y ruedas. Las subclases llaman al constructor de la superclase mediante `super(...)`.

Otro ejemplo es `SistemaGestion`, cuyo constructor recibe un `IfaceAlmacen`. Asi queda asociado a un almacen concreto desde el momento de su creacion.

## 28. Pregunta general: explique que es `ArrayList`

**Respuesta modelo:**

`ArrayList` es una coleccion dinamica de Java que permite almacenar objetos en una lista cuyo tamano puede crecer o disminuir durante la ejecucion.

En la practica se usa para almacenar motores, ruedas, tapicerias, vehiculos terminados, trabajadores y vehiculos pendientes en la cadena.

Frente a un array normal, `ArrayList` es mas flexible porque permite anadir y eliminar elementos facilmente con metodos como `add()`, `remove()` o `clear()`.

## 29. Pregunta general: explique que significa `static`

**Respuesta modelo:**

`static` indica que un atributo o metodo pertenece a la clase y no a una instancia concreta.

En `Factory_Main`, algunos atributos como `sistemaGestion`, `cadenaMontaje`, `dashboard` y `sc` son `static` porque se usan desde metodos tambien estaticos del menu. No es necesario crear un objeto de `Factory_Main` para acceder a ellos dentro de la propia clase.

El metodo `main` tambien es `static` porque la maquina virtual de Java debe poder ejecutarlo sin crear previamente un objeto.

## 30. Consejos para responder en el examen

**Respuesta modelo breve para estructurar cualquier pregunta:**

Si preguntan por una clase, responde en este orden:

- Que representa.
- Que atributos importantes tiene.
- Con que otras clases se relaciona.
- Que metodos importantes ofrece.
- Que concepto de POO demuestra.

Si preguntan por un flujo, responde en este orden:

- Donde empieza.
- Que clases participan.
- Que datos se modifican.
- Donde termina.
- Que ventaja tiene ese diseno.

Si preguntan por una mejora o ampliacion, responde en este orden:

- Que clase nueva crearias.
- De que clase heredaria o que interfaz implementaria.
- Que metodos tendrias que sobrescribir.
- Que menus, almacen o simulacion habria que adaptar.
- Que partes no haria falta tocar gracias al polimorfismo.

# Sistema de Gestión de Fábrica de Vehículos

## Práctica de la asignatura Programación Orientada a Objetos

## Escenario para el curso 2025-2026 - Febrero de 2026 - Versión 1

## Departamento de Lenguajes y Sistemas Informáticos

## Escuela Técnica Superior de Ingeniería Informática - UNED

## 1. Introducción

Los objetivos que se plantean en la realización de esta práctica son los siguientes:
● Familiarización con la Programación Orientada a Objetos (POO): definición de
clases e instancias, uso de la herencia, definición/uso de métodos estáticos y
abstractos.
● Realización del diseño orientado a objetos de un problema.
● Implementación de un programa sencillo donde se manejen conceptos relacionados
con POO.
La práctica se va a implementar en Java 2 Estándar Edition (J2SE). El compilador de Java

### que se usará será BlueJ, tal y como se define en el programa de la asignatura.

## 2. Programación Orientada a Objetos en Java

El paradigma de programación orientada a objetos define un programa como una colección
de entidades que se relacionan para resolver un problema. Estas entidades, que se
conocen genéricamente como objetos, están definidas por un conjunto de propiedades y
métodos, y están organizadas en torno a una jerarquía de clases.
En Java, cada objeto puede tener variables y métodos privados y públicos. Se puede
modificar dicha visibilidad de una clase usando los modificadores de acceso a miembros.

### Las dos maneras más habituales de especificar la accesibilidad son:

private – la variable o método está disponible solamente para esta clase.
public – la variable o método está disponible para todas las clases.
Una clase puede heredar los variables y métodos públicos de otra clase a través del

### mecanismo de herencia y la palabra clave extends. Por ejemplo:


//clase base que va a contener información sobre vehículos de nuestra
empresa:
**public** vehiculo {
**private int** noPuertas;
**private int** noRuedas;
**private** String modelo;
**public** vehiculo(){}
**public void** setNoPuertas( **int** np) {
noPuertas = np;
}
//etc.
}
//una clase para tratar a los coches en general...
**public** coche **extends** vehiculo {
**private boolean** airbags;
**public** coche(){}
**public void** setAirbags(Boolean a) {
airbags = a;
}
//etc.
}
//y, por fin, una clase para tratar a los coches deportivos
**public final** cocheDeportivo **extends** vehículo {
**private** String capacidadMotor;
**private int** maxVelocidad;
**public** cocheDeportivo(){}
**public void** setCapacidadMotor(String cm) {
capacidadMotor = cm;
}
//etc.
//se puede llamar a cualquier método en las superclases como
//si estuvieran dentro
//de esta misma clase, p.ej.:
setNoPuertas(2);
}
**Notas:** Las clases que extienden otras clases tienen el nombre de subclases y las clases
que son extendidas por otras clases tienen el nombre de superclases.
Hay que tener cuidado a la hora de planificar las relaciones de herencia entre clases en
Java, porque una clase solamente puede heredar variables y métodos de otra (y sus
superclases). En Java, no hay herencia múltiple como hay en lenguajes como C++, aunque
se puede reproducir la técnica de herencia múltiple usando interfaces.


## 3. Descripción de la práctica - Curso 2025-

La práctica de Programación Orientada a Objetos para el curso 2025/26 consistirá en el
modelado de una fábrica de vehículos. Este modelado tendrá dos unidades operativas
principales que serán: la cadena de montaje y el sistema de gestión de la fábrica. A
continuación, se describen las funciones que cada unidad operativa tiene dentro de la
fábrica de vehículos.

1. La cadena de montaje, como su nombre indica, tiene la misión de construir los
    distintos coches que se ofertan en el catálogo. Los tipos de coches que se
    construyen en esta factoría son: biplaza deportivo, turismo y furgoneta. Las
    características que se guardan de los vehículos son las siguientes: color, número de
    plazas, tara del vehículo y peso máximo autorizado. Cada tipo de vehículo tiene una
    cadena de montaje diferente. Por tanto, al existir tres tipos de vehículos, la factoría
    de coches dispone de tres tipos de cadenas de montaje diferentes. Los tipos de
    motores que se pueden montar en estos coches son: eléctrico, gasolina e híbrido.
    Las características técnicas de los motores que se tienen que almacenar son las
    siguientes: cilindrada, potencia y número de cilindros. El tipo de tapicería que se
    utiliza para los interiores son: tela, cuero y alcántara. Las características de la
    tapicería que se almacenarán serán: color y metros cuadrados de tela. Por último,
    las ruedas que se pueden combinar son: normal, deportivo y todoterreno. Las
    características que se tienen que almacenar de las ruedas son las siguientes: ancho
    en mm, diámetro de llanta en pulgadas, índice de carga en kg y código de velocidad
    en km/h. El código de velocidad indica la velocidad máxima permitida que el
    neumático puede soportar con seguridad durante un periodo determinado de tiempo.
2. El sistema de gestión de fábrica se encarga de gestionar el funcionamiento de la
    factoría. Entre las funciones que desempeña se destacan:
       a. Gestión de almacén de datos donde se guardan todos los datos del sistema.
          Se debe diseñar una estructura de datos que almacene toda la información
          indicada de cada una de las entidades. Cabe destacar que se espera que se
          realice un diseño que se encuentre desacoplado del sistema de gestión y que
          facilite el cambio de estructura de datos sin que requiera de modificaciones
          severas en el diseño original.
       b. Gestión de trabajadores que se encarga de gestionar los datos personales
          (nombre, apellidos, DNI, dirección, número de seguridad social, puesto que
          desempeña, salario y fecha de ingreso) de los trabajadores de la factoría.
          Existen cuatro tipos de perfiles: operario, gestor de planta, administrador del
          sistema, mecánico de cinta.
       c. Gestión de un dashboard: el sistema de gestión de fábrica dispone de un
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
d. Planificador (en inglés _scheduler_ ). Es el componente principal que hace
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


Figura 1. Representación del caso de uso descrito anteriormente.
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
En cuanto al funcionamiento de la factoría, las cadenas de montaje se encargan de
combinar los elementos de motores, tapicería y ruedas para construir vehículos. De esta
forma, el gestor de planta, mediante la aplicación, configura los tres tipos de vehículos que
quiere construir, selecciona los componentes, introduce las unidades y las cadenas de
montaje, comprobando previamente que tienen piezas suficientes para realizar el pedido, se
ponen a construir las unidades demandadas. En cuanto a los perfiles de los trabajadores,
son los siguientes:
a) Operario: En este tipo de trabajador existen dos perfiles de operarios: eficiente y
estándar. Ambos perfiles se encargan de trabajar en la cadena de montaje. La
diferencia entre ellos es el tiempo que dedican cada uno a realizar su tarea en la
cadena de montaje debido a la experiencia que tienen en su puesto de trabajo.
Esta experiencia se mide en cantidad de montajes de piezas. Así, un operario
eficiente será aquel que haya realizado > 10 montajes de piezas en su puesto de


```
trabajo. En cuanto a la diferencia del tiempo que dedican, mientras que el primer
perfil, el operario eficiente, realiza la tarea que le ha sido encomendada en un
segundo; el segundo perfil, el operario estándar, necesita el triple de tiempo para
realizar la tarea que le han encomendado. La labor que el operario realiza es
controlar el robot que se encarga del montaje del componente. En cada cadena
de montaje hay un robot para montar cada componente y un operador por robot.
Cada vez que un robot termine su labor de montaje, se cambiará el estado del
coche. Los estados en los que se puede encontrar un vehículo en la cadena de
montaje son: Chasis, Motor, Tapicería, Ruedas.
b) Gestor de planta: Es el encargado de monitorizar la planta para que las cadenas
de montaje y los operadores no dejen de trabajar. Su labor se centra en
configurar los componentes que utilizarán las cadenas de montaje para
ensamblar los vehículos. Además, tienen que estar pendiente del dashboard
para comprobar que no se ha producido ningún error en el proceso de montaje,
ya que él es el encargado de llamar a los mecánicos de cinta cuando tienen que
llevar a cabo una reparación.
c) Administrador del sistema: Es el encargado de velar que todo el software
funcione correctamente: cadena de montaje y gestor de fábrica. Por tanto,
cuando se produzca cualquier error en el sistema, como por ejemplo un apagón,
todas los trabajadores permanecerán quietos en su puesto de trabajo hasta que
el administrador del sistema restaure el funcionamiento de la fábrica,
necesitando dos segundos para reanudar el sistema de gestión de la fábrica y
tres segundos para reanudar las cadenas de montaje.
d) Mecánico de cinta: efectivo y estándar. Ambos perfiles se dedican a reparar la
cinta cuando se produzca un problema, la diferencia entre ambos es el tiempo
que tardan en reparar la cinta, debido a la experiencia que tienen en su puesto
de trabajo. Como ocurre con los operarios, la experiencia se mide en cantidad de
reparaciones realizadas. De esta forma, un mecánico eficiente será aquel que
haya realizado > 20 reparaciones. En lo relacionado a la diferencia de tiempo de
reparación entre ambos, el primero tarda un segundo en reparar cualquier
problema que se produzca en la cinta y el segundo tarda de 2 a 5 segundos en
repararla.
```
## 4. Desarrollo de la práctica

En esta práctica se propondrán diferentes funcionalidades en función de la calificación a la
que aspire el estudiante. De este modo, una mayor complejidad a desarrollar implica una
calificación mayor en la evaluación de la práctica. Hay que tener en cuenta que **la nota
mínima para aprobar** la práctica es 5.0.
Es importante considerar que para optar a la calificación de un nivel superior han de
cumplirse todas y cada una de las funcionalidades especificadas en el nivel inmediatamente
anterior. En caso de no ser así (no cumplir con todos los requerimientos de un nivel), no se
podrá obtener una calificación superior a la marcada por el nivel cuyas restricciones no se


cumplen en su totalidad. Del mismo modo, los niveles han de implementarse en el orden
que se indican, no siendo posible implementar niveles no consecutivos para obtener
calificaciones superiores.
Para cada uno de los niveles se van a indicar unos requisitos mínimos de cumplimiento.
Esto quiere decir que para cualquier otro detalle de diseño que no se encuentre descrito
expresamente en lo indicado en este enunciado, el alumno tiene libertad para tomar cuantas
decisiones considere oportunas.
Para obtener la nota mínima para aprobar hay que desarrollar los primeros **dos niveles** de
la práctica.
**Nivel 1 - Puntuación total máxima a obtener: 3 puntos.**
El objetivo en este nivel es que el alumno desarrolle las relaciones de clase, herencia y
demás que van asociadas al desarrollo de la práctica. Así, se pide realizar las siguientes
tareas:
● Planteamiento del problema: actores participantes, relaciones entre actores,
funcionalidad a cumplir por la práctica a desarrollar.
● Establecimiento de diferentes clases a intervenir en la práctica, relaciones de
dependencia entre clases, identificar diferentes jerarquías de clases, etc.
● Elaboración de un documento escrito (memoria de la práctica) que contenga el primer
punto y los correspondientes ficheros para BlueJ que implementen el segundo.
Es necesario contemplar las herramientas que proporciona el paradigma de Programación
Orientada a Objetos necesarias para la implementación de lo solicitado en la práctica
(abstracción, encapsulamiento, herencia y polimorfismo).
**Nivel 2 - Puntuación total máxima a obtener: 7 puntos.**
El objetivo en este nivel es que el alumno desarrolle la parte de gestión de datos del sistema
usando la estructura de clases y métodos que se ha diseñado en el nivel anterior.
Sólo se podrá optar a la calificación de este nivel si se han implementado de manera
satisfactoria todos los puntos del nivel 1. En este nivel el sistema debe permitir lo siguiente:
● Gestión de almacén: Añadir y actualizar los datos de vehículos a producir en el sistema
y todos los componentes asociados: motores, tapicería y ruedas.
● Gestión de trabajadores: Dar de alta a los distintos tipos de empleados del sistema con
sus datos personales.
● Implementar la funcionalidad relacionada con la opción “Simple” del planificador.
● Realizar búsquedas sencillas sobre los empleados del sistema.
● Realizar consultas y actualizaciones del stock de vehículos del sistema.
**Nivel 3 - Puntuación total máxima a obtener: 10 puntos.**
El objetivo en este nivel es que el alumno desarrolle una interfaz textual del sistema para
que las funciones identificadas en el nivel 2 funcionen correctamente.


● Implementar las funciones identificadas en el nivel 2.
● Implementación completa del planificador y las cadenas de montaje.
● Implementación completa del almacén de datos, almacenando todas las entidades del
sistema y la información relacionada con las cadenas de montaje. Además, su diseño
debe de estar desacoplado del sistema de gestión para facilitar el cambio de estructura
de datos sin requerir modificaciones enormes en el diseño original.
● Implementación completa del dashboard, incluyendo gestión de vehículos, cadenas de
montaje y almacén. Además, su diseño tiene que permitir el cambio de subsistema de
visualización de datos sin que suponga un cambio desmedido en el diseño original.
● Implementar todas las funciones relacionadas con el planificador, opciones: “Simple”,
“Compleja”, y “Muy compleja”.
● Producir diferentes listados y estadísticas del funcionamiento del sistema:
o Listado de operarios con opciones de filtrado por productividad y opciones de
ordenación alfabética.
o Listado de vehículos ensamblados, con opciones de filtrado por componentes y
opciones de ordenación alfabética.
o Listado de configuraciones de vehículos con mayor tasa de ensamblaje.
o Listado de cadenas de montaje con filtrado de fecha, donde se muestran en esas
fechas los vehículos producidos con sus componentes.

## 5. Plan de Trabajo

Para realizar la práctica se seguirá el siguiente método de trabajo:
● En primer lugar, se leerá detenidamente el enunciado de esta práctica.
● A continuación, hay que diseñar, utilizando un paradigma orientado a objetos, los
elementos necesarios para cada nivel de la aplicación explicada en el apartado
anterior. Debe hacerse uso de los mecanismos de herencia siempre que sea posible.
Se valorará un buen diseño que favorezca la reutilización de código y facilite su
mantenimiento.
● El código estará debidamente comentado.
● La clase principal que abre la aplicación deberá llamarse “factory_main **.class** ”.

## 6. Control de plagio en las prácticas

Como se ha indicado en el apartado 10 de este documento, las prácticas son esenciales en
las titulaciones de Informática porque permiten a los alumnos adquirir conocimientos
importantes sobre los aspectos más aplicados de una asignatura. Por lo tanto, dado el
hecho de que la práctica de esta asignatura es un trabajo individual y obligatorio que cuenta
para la nota final de la asignatura y que implica un esfuerzo por parte de los alumnos, es
necesario garantizar la originalidad de dicho trabajo. Para evitar este problema, una vez
terminado el plazo de entrega de la práctica (indicado en el curso virtual), el equipo docente
usará un software de control de plagio para revisar las prácticas. En los casos donde haya


plagio se informará al Servicio de Inspección de la UNED para que tome las medidas

### disciplinarias apropiadas.

## 7. Normas de realización de la práctica

A continuación, se enumeran las normas de realización de la práctica.

1. La realización de la práctica es obligatoria. Sólo se evaluará el examen si la práctica
    ha sido previamente aprobada.
2. Si bien el desarrollo de aplicaciones Orientadas a Objetos usando el lenguaje de
    programación Java no requiere el uso concreto de ningún entorno de desarrollo, está
    práctica ha de desarrollarse íntegramente empleando el entorno de desarrollo BlueJ,
    que es el que se muestra en el libro de texto básico de la asignatura.
3. La práctica es un trabajo individual. Las prácticas cuyo código coincida total o
    parcialmente con el de otro alumno serán motivo de suspenso para todos los
    implicados (copiadores y copiados), no pudiéndose examinar ninguno de ellos en el
    presente curso académico (además de cualquier medida disciplinaria que aplicará el
    Servicio de Inspección).
4. Cada tutor será responsable de organizar las sesiones de control presenciales o
    virtuales de la realización de la práctica. Al menos una de dichas sesiones de control
    deberá ser obligatoria:
       a. Es el tutor el que marca la fecha de dicha sesión y no el equipo docente.
       b. El tutor puede organizar la sesión hacia el final del cuatrimestre para poder
          comprobar que los alumnos han hecho bien el trabajo y para ayudar al tutor a
          calificar el trabajo.
5. La única vía de entrega de la práctica es a través de la plataforma Ágora siguiendo
    las indicaciones del apartado 8.
6. El equipo docente tendrá en cuenta prácticas con notas altas para aquellos alumnos
    cuyo examen esté cercano al aprobado.
7. El alumno debería dirigirse a su tutor para cualquier duda que tenga sobre su
    práctica y solamente al equipo docente (por correo electrónico) en el caso de que su
    tutor no pueda resolver su problema. En este caso, pediremos al alumno que,
    además de sus datos personales, nos envíe el nombre del centro asociado en el que
    está matriculado y el de su tutor.
8. Evidentemente se pueden usar los foros para realizar consultas a los compañeros,
    pero nunca para intercambiar código.
9. La fecha límite de entrega de la práctica la establecerá **el tutor** , junto con el
    **procedimiento de seguimiento y entrega** que el tutor quiere seguir. En ningun
    caso, un tutor podrá fijar como fecha de entrega una fecha posterior al último
    domingo antes del inicio de la primera semana de pruebas presenciales de junio, es
    decir, posterior al 24 de mayo. Por lo tanto, es responsabilidad del **alumno**
    informarse de dicho procedimiento y de las fechas asociadas, que se comunican al
    inicio del curso.
    La fecha que figura en ÁGORA para la práctica, el **último domingo antes del inicio**
    **de la primera semana de pruebas presenciales de junio** , sirve exclusivamente
    para que **el tutor** pueda subir las notas y **no** para que un alumno entregue su
    práctica con la esperanza que sea corregida por el tutor o el equipo docente.
    Cualquier práctica que se suba a ÁGORA fuera del plazo **no será corregida** , y el

### alumno no podría aprobar el examen en la convocatoria de junio.


```
Para la convocatoria de septiembre , la nota de la práctica tiene que estar incluida
```
### en el curso virtual por los tutores antes del 31 de agosto de 2025.

```
Si el tutor en el centro asociado está dispuesto a corregir la práctica para la
convocatoria de septiembre, el alumno debe seguir las indicaciones del tutor al
respecto. En caso contrario, el alumno deberá enviar su práctica al tutor Antonio
Sernandez (asernandez@ponferrada.uned.es), quien se encargará de su corrección.
Asimismo, el alumno deberá ponerse en contacto con él para gestionar la fecha de
entrega de la práctica al mismo.
```
## 8. Entrega de la práctica

La práctica se entrega a través de la plataforma Ágora en el apartado “Entrega de trabajos”.
El archivo que hay que subir a Ágora debe ser un archivo comprimido (rar o zip), que se
puede preparar con el software de compresión que traen la mayoría de los sistemas
operativos hoy en día o usando un software libre como 7zip (www.7-zip.org). **No se deben
usar acentos** en los nombres de los archivos ni las carpetas. El archivo comprimido debe
estar compuesto por una carpeta con el nombre del alumno que contendrá dos elementos:

1. **Memoria** : La memoria constará de los siguientes apartados:
    ○ Portada con título “Práctica de Programación Orientada a Objetos – Curso
       2025-2026” y los datos del alumno: Nombre, Apellidos, dirección de correo
       electrónico y teléfono de contacto.
    ○ Análisis de la aplicación realizada, mostrando el funcionamiento del
       programa, estrategias implementadas, decisiones de diseño establecidas y,
       en general, toda aquella información que haga referencia a las diferentes
       decisiones tomadas a lo largo del desarrollo de la práctica, junto a una
       justificación de dichas decisiones.
    ○ Diagrama de clases, detallando claramente el tipo de relación entre ellas
       (uso, agregación, herencia, ...).
    ○ Un texto en el que se describa cada clase/objeto, justificación de su
       existencia, métodos públicos que contiene y funcionalidad que realizan.
    ○ Anexo con el código fuente de las clases implementadas.
2. **Una carpeta con el código** : incluyendo todos los ficheros *.java y *.class, así como
    la memoria en formato electrónico (preferiblemente html o pdf).
**NOTAS:**
● Al hacer la entrega del trabajo se acepta que tanto el código fuente Java como la
memoria de la práctica es original. Aquellos aportes intelectuales de otros autores
(como, por ejemplo, el tutor) deben estar referenciados debidamente en el texto de
dicho trabajo.
**●** Si el archivo subido a ÁGORA por parte del alumno no sigue estas indicaciones,
está infectado con algún virus, o no se puede descomprimir, el equipo docente no
aceptará la práctica y se calificará con una nota de 0.


## 9. Normas para los tutores

Como se puede apreciar, el papel del tutor es fundamental en todos los aspectos de la
práctica, tanto el planteamiento del problema, el diseño orientado a objetos del programa,
su desarrollo y su depuración. Tratándose de una asignatura obligatoria, cada alumno
debería tener acceso a un tutor. Los tutores deben seguir los siguientes pasos:

1. Ayudar a los alumnos al principio del curso con el planteamiento de la práctica y las
    normas que tienen que seguir.
2. Para explicar ciertos conceptos relacionados con la solución de la práctica, el tutor
    puede dar fragmentos de código fuente a los alumnos. Los pequeños fragmentos no
    tendrán importancia a la hora de llevar a cabo el control de plagio por parte del
    equipo docente. No obstante, si un alumno va a incluir un fragmento de código en su
    práctica, debe incluir un comentario al respecto directamente anterior al código y
    también una nota al respecto en su memoria.
3. Informar a los alumnos acerca de las sesiones de control presenciales o virtuales de
    la práctica. Al menos una de dichas sesiones de control deberá ser obligatoria.
4. Informar a los alumnos acerca de la fecha límite de entrega de la práctica
5. Comunicar la calificación a sus alumnos.

## 10. Centros Asociados vs. Prácticas en Asignaturas Obligatorias

Las prácticas son esenciales en las titulaciones de Informática porque, entre otras cosas,
permiten a los alumnos adquirir conocimientos importantes sobre los aspectos más
aplicados de ciertas asignaturas, lo cual resulta de gran relevancia e interés a la hora de
acceder a un puesto laboral relacionado con la Informática. Para orientar y ayudar a los
alumnos, así como para comprobar que realmente un alumno ha realizado su práctica de
forma satisfactoria, ésta se debe realizar en un Centro Asociado bajo la supervisión de un
tutor, quien decide, en última instancia, la forma en la cual se organiza el desarrollo de la
misma en su Centro Asociado (existencia o no de sesiones presenciales obligatorias, forma
de entrega, etc.).
De vez en cuando sucede que un alumno se pone en contacto con un Equipo Docente del
Departamento de Lenguajes y Sistemas Informáticos (L.S.I.) porque se ha matriculado en
una asignatura obligatoria en un Centro Asociado que no le proporciona un tutor para
supervisar la práctica, aún cuando se le ha permitido matricularse. El alumno busca en el
Equipo Docente que se le proporcione una solución a este problema, como por ejemplo, la
posibilidad de asistir a unas sesiones extraordinarias de prácticas en la Sede Central de la
U.N.E.D. en Madrid o la posibilidad de realizar la práctica por su cuenta en casa, enviándola
a continuación al Equipo Docente para su corrección. Sin embargo, los Equipos Docentes
de L.S.I. no disponen de recursos para poder llevar a cabo ninguna de estas dos
alternativas.


Un Centro Asociado que ha permitido a un alumno matricularse en una asignatura
obligatoria de una carrera de Informática debería ayudarle a encontrar una solución al
problema de la realización de las prácticas. Si se trata de una asignatura donde no se han
matriculado muchos alumnos, quizás el centro no cuente con recursos para proporcionar un
tutor específicamente para la asignatura. Si hay otro Centro Asociado cerca que dispone de
tutor, quizás el alumno pueda realizar la práctica allí. Pero si no es así, el Centro Asociado
debería proporcionar un tutor para supervisar y corregir las prácticas de sus alumnos. Lo
más razonable sería que fuera un tutor de otra asignatura de Informática en el mismo
Centro el que hiciera la sesión de prácticas para los alumnos de la asignatura en cuestión, y
al final de la sesión evaluará los trabajos de los alumnos, según las pautas marcadas por el
Equipo Docente, haciendo llegar a éste las calificaciones otorgadas.
Por lo tanto, un alumno que, tras haberse matriculado en una asignatura obligatoria en un
Centro Asociado, se encuentre con que el centro no tiene tutor para dicha asignatura,
debería dirigirse al Director del Centro Asociado, para solicitar de él una solución, tal como
se ha presentado aquí, es decir, alguien que pueda supervisar y corregir su práctica con
plenas garantías. En el caso de que el Director no le proporcione una solución, el alumno
debería comunicárselo, por escrito, lo antes posible, al Director del Departamento de L.S.I.,
Dr. Anselmo Peñas Padilla.

import com.practica.fabrica.*;
import com.practica.montaje.*;
import com.practica.dashboard.*;
import com.practica.planificador.*;
import com.practica.personal.*;
import com.practica.motor.*;
import com.practica.tapiceria.*;
import com.practica.rueda.*;
import com.practica.vehiculo.*;

import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Clase principal de la aplicación de gestión de la fábrica de vehículos.
 * <p>
 * Expone un menú textual por consola que permite al usuario acceder a las
 * funciones de la fábrica: gestión de almacén (componentes y vehículos en
 * cadena), gestión de trabajadores, consulta de stock, búsqueda de
 * empleados, simulación del montaje y listados/estadísticas. * <p>
 * Coordina las instancias singleton de {@link SistemaGestion},
 * {@link CadenaMontaje} y {@link Dashboard} que se comparten entre todas
 * las opciones del menú. *
 * @author Shao Capilla Sanz
 */
public class factory_main {

    /** Fachada del sistema de gestión sobre el almacén de datos. */
    public static SistemaGestion sistemaGestion;
    /** Cadena de montaje activa con los vehículos pendientes. */
    public static CadenaMontaje cadenaMontaje;
    /** Dashboard observador que muestra los eventos en consola. */
    public static Dashboard dashboard;
    /** Scanner único para leer entrada del usuario. */
    public static Scanner sc = new Scanner(System.in);

    /**
     * Punto de entrada del programa. Inicializa el almacén, el sistema de
     * gestión, la cadena de montaje y el dashboard, conecta el dashboard
     * como observador del almacén y de la cadena, y entra en el bucle del
     * menú principal hasta que el usuario elige salir.
     *
     * @param args argumentos de línea de comandos (no se utilizan)
     */
    public static void main(String[] args) {

        AlmacenDatos almacen = new AlmacenDatos();
        sistemaGestion = new SistemaGestion(almacen);
        cadenaMontaje = new CadenaMontaje();
        dashboard = new Dashboard(new VisualizarConsola());

        almacen.addObservador(dashboard);
        cadenaMontaje.addObservador(dashboard);

        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\nGESTIÓN FÁBRICA DE VEHÍCULOS");
            System.out.println("1. Gestión de Almacén");
            System.out.println("2. Gestión de Trabajadores");
            System.out.println("3. Consultar stock");
            System.out.println("4. Buscar empleados");
            System.out.println("5. Iniciar Simulación");
            System.out.println("6. Listados y estadísticas");
            System.out.println("7. Reiniciar Sistema");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    menuAlmacen();
                    break;
                case 2:
                    menuTrabajadores();
                    break;
                case 3:
                    mostrarStock();
                    break;
                case 4:
                    menuBusqueda();
                    break;
                case 5:
                    menuSimulacion();
                    break;
                case 6:
                    menuListados();
                    break;
                case 7:
                    sistemaGestion.resetSistema();
                    System.out.println("Sistema reiniciado correctamente.");
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Comprueba que hay stock suficiente para fabricar un vehículo más,
     * teniendo en cuenta los vehículos ya pendientes en la cadena. Si no
     * hay stock muestra un mensaje y devuelve {@code false}.
     *
     * @return {@code true} si se puede añadir un vehículo más;
     *         {@code false} en caso contrario
     */
    private static boolean comprobarStockParaNuevoVehiculo() {
        cadenaMontaje.purgarTerminados();
        int yaEnCadena = cadenaMontaje.getCadenaBiplaza().size() + cadenaMontaje.getCadenaTurismo().size() + cadenaMontaje.getCadenaFurgoneta().size();
        ComprobacionStock res = sistemaGestion.comprobarStock(yaEnCadena + 1);
        if (res != ComprobacionStock.OK) {
            System.out.println("No se añade el vehículo: stock insuficiente de " + res);
            return false;
        }
        return true;
    }

    /**
     * Delega la creación de un vehículo en el gestor de planta si existe;
     * en caso contrario añade el vehículo directamente a la cadena.
     *
     * @param tipo    tipo de vehículo ("Biplaza", "Turismo" o "Furgoneta")
     * @param color   color del vehículo
     * @param plazas  número de plazas
     * @param pesoMax peso máximo autorizado en kg
     * @param tara    tara del vehículo en kg
     */
    private static void delegarAGestor(String tipo, String color, int plazas,
            double pesoMax, double tara) {
        List<GestorPlanta> gestores = sistemaGestion.consultarGestorPlanta();
        if (!gestores.isEmpty()) {
            GestorPlanta g = gestores.get(0);
            switch (tipo) {
                case "Biplaza":
                    g.configurarBiplazas(cadenaMontaje, 1, color, tara, pesoMax, null, null, null);
                    break;
                case "Turismo":
                    g.configurarTurismos(cadenaMontaje, 1, color, tara, pesoMax, null, null, null);
                    cadenaMontaje.getCadenaTurismo()
                        .get(cadenaMontaje.getCadenaTurismo().size() - 1)
                        .setPlazas(plazas);
                    break;
                case "Furgoneta":
                    g.configurarFurgonetas(cadenaMontaje, 1, color, tara, pesoMax, null, null, null);
                    cadenaMontaje.getCadenaFurgoneta()
                        .get(cadenaMontaje.getCadenaFurgoneta().size() - 1)
                        .setPlazas(plazas);
                    break;
            }
        } else {

            switch (tipo) {
                case "Biplaza":
                    cadenaMontaje.agregarBiplaza(new BiplazaDeportivo(color, plazas, pesoMax, tara, null, null, null));
                    System.out.println("Biplaza Deportivo añadido a la cadena.");
                    break;
                case "Turismo":
                    cadenaMontaje.agregarTurismo(new Turismo(color, plazas, pesoMax, tara, null, null, null));
                    System.out.println("Turismo añadido a la cadena.");
                    break;
                case "Furgoneta":
                    cadenaMontaje.agregarFurgoneta(new Furgoneta(color, plazas, pesoMax, tara, null, null, null));
                    System.out.println("Furgoneta añadida a la cadena.");
                    break;
            }
        }
    }

    /**
     * Solicita al usuario un componente del almacén (motor, tapicería o
     * rueda) y permite modificar sus atributos. Muestra la lista numerada
     * de los elementos disponibles para que el usuario elija por índice.
     */
    private static void actualizarComponenteAlmacen() {
        System.out.println("Actualizar componente:");
        System.out.println("  1) Motor   2) Tapicería   3) Rueda");
        System.out.print("Tipo: ");
        int tipo = sc.nextInt();
        List<?> lista;
        switch (tipo) {
            case 1:
                lista = sistemaGestion.consultarMotor();
                break;
            case 2:
                lista = sistemaGestion.consultarTapiceria();
                break;
            case 3:
                lista = sistemaGestion.consultarRueda();
                break;
            default:
                System.out.println("Tipo no válido.");
                return;
        }

        if (lista.isEmpty()) {
            System.out.println("No hay elementos para actualizar.");
            return;
        }

        for (int i = 0; i < lista.size(); i++) {
            System.out.println("  [" + i + "] " + lista.get(i));
        }

        System.out.print("Índice a actualizar: ");
        int indice = sc.nextInt();

        if (indice < 0 || indice >= lista.size()) {
            System.out.println("Índice fuera de rango.");
            return;
        }

        switch (tipo) {
            case 1: {
                Motor m = (Motor) lista.get(indice);
                System.out.print("Nueva cilindrada: ");
                m.setCilindrada(sc.nextDouble());
                System.out.print("Nueva potencia: ");
                m.setPotencia(sc.nextInt());
                System.out.print("Nuevo número cilindros: ");
                m.setNumeroCilindros(sc.nextInt());
                System.out.println("Motor actualizado.");
                break;
            }
            case 2: {
                Tapiceria t = (Tapiceria) lista.get(indice);
                sc.nextLine();
                System.out.print("Nuevo color: ");
                t.setColor(sc.nextLine());
                System.out.print("Nuevos m²: ");
                t.setMetrosCuadrados(sc.nextDouble());
                System.out.println("Tapicería actualizada.");
                break;
            }
            case 3: {
                Rueda r = (Rueda) lista.get(indice);
                System.out.print("Nuevo ancho: ");
                r.setAncho(sc.nextInt());
                System.out.print("Nueva pulgadas llanta: ");
                r.setPulgadasLlanta(sc.nextInt());
                System.out.print("Nuevo índice carga: ");
                r.setIndiceCarga(sc.nextInt());
                System.out.print("Nuevo código velocidad: ");
                r.setVelocidad(sc.nextInt());
                System.out.println("Rueda actualizada.");
                break;
            }
        }
    }

    /**
     * Submenú de gestión de almacén. Permite registrar componentes
     * (motores Gasolina/Eléctrico/Híbrido, tapicerías Tela/Cuero/Alcántara,
     * ruedas Normal/Deportiva/Todoterreno), añadir vehículos a la cadena
     * (Biplaza, Turismo, Furgoneta) y actualizar componentes existentes.
     * Las ruedas se registran siempre en juegos de 4.
     */
    public static void menuAlmacen() {
        int op = -1;
        while (op != 0) {
            System.out.println("\nMENÚ GESTIÓN DE ALMACÉN");
            System.out.println("1. Añadir Motor Gasolina");
            System.out.println("2. Añadir Motor Eléctrico");
            System.out.println("3. Añadir Motor Híbrido");
            System.out.println("4. Añadir Tapicería Tela");
            System.out.println("5. Añadir Tapicería Cuero");
            System.out.println("6. Añadir Tapicería Alcántara");
            System.out.println("7. Añadir Rueda Normal");
            System.out.println("8. Añadir Rueda Deportiva");
            System.out.println("9. Añadir Rueda Todoterreno");
            System.out.println("-- VEHÍCULOS A ENSAMBLAR --");
            System.out.println("10. Añadir Biplaza Deportivo a la cadena");
            System.out.println("11. Añadir Turismo a la cadena");
            System.out.println("12. Añadir Furgoneta a la cadena");
            System.out.println("13. Actualizar componente del almacén");
            System.out.println("0. Volver al menú principal");
            System.out.print("Opción: ");
            op = sc.nextInt();
            switch (op) {
                case 1:
                    System.out.print("Cilindrada: ");
                    double cGasolina = sc.nextDouble();
                    System.out.print("Potencia: ");
                    int pGasolina = sc.nextInt();
                    System.out.print("Cilindros: ");
                    int numCilGasolina = sc.nextInt();
                    sistemaGestion.registrarMotor(new Gasolina(cGasolina, pGasolina, numCilGasolina));
                    System.out.println("Motor Gasolina registrado.");
                    break;
                case 2:
                    System.out.print("Cilindrada: ");
                    double cElectrico = sc.nextDouble();
                    System.out.print("Potencia: ");
                    int pcElectrico = sc.nextInt();
                    System.out.print("Cilindros: ");
                    int numCilcElectrico = sc.nextInt();
                    sistemaGestion.registrarMotor(new Electrico(cElectrico, pcElectrico, numCilcElectrico));
                    System.out.println("Motor Electrico registrado.");
                    break;
                case 3:
                    System.out.print("Cilindrada: ");
                    double cHibrido = sc.nextDouble();
                    System.out.print("Potencia: ");
                    int pcHibrido = sc.nextInt();
                    System.out.print("Cilindros: ");
                    int numCilcHibrido = sc.nextInt();
                    sistemaGestion.registrarMotor(new Hibrido(cHibrido, pcHibrido, numCilcHibrido));
                    System.out.println("Motor Hibrido registrado.");
                    break;
                case 4:
                    System.out.print("Color: ");
                    sc.nextLine();
                    String cTela = sc.nextLine();
                    System.out.print("Metros Cuadrados: ");
                    double mcTela = sc.nextDouble();
                    sistemaGestion.registrarTapiceria(new Tela(cTela, mcTela));
                    System.out.println("Tapiceria Tela registrado.");
                    break;
                case 5:
                    System.out.print("Color: ");
                    sc.nextLine();
                    String cCuero = sc.nextLine();
                    System.out.print("Metros Cuadrados: ");
                    double mcCuero = sc.nextDouble();
                    sistemaGestion.registrarTapiceria(new Cuero(cCuero, mcCuero));
                    System.out.println("Tapiceria Cuero registrado.");
                    break;
                case 6:
                    System.out.print("Color: ");
                    sc.nextLine();
                    String cAlcantara = sc.nextLine();
                    System.out.print("Metros Cuadrados: ");
                    double mcAlcantara = sc.nextDouble();
                    sistemaGestion.registrarTapiceria(new Alcantara(cAlcantara, mcAlcantara));
                    System.out.println("Tapiceria Alcantara registrado.");
                    break;
                case 7:
                    System.out.print("Ancho: ");
                    int aNormal = sc.nextInt();
                    System.out.print("Pulgadas Llanta: ");
                    int pLlantaNormal = sc.nextInt();
                    System.out.print("Indice Carga: ");
                    int indiCargaNormal = sc.nextInt();
                    System.out.print("Codigo Velocial: ");
                    int codVelNormal = sc.nextInt();
                    for (int i = 0; i < 4; i++) {
                        sistemaGestion.registrarRueda(new Normal(aNormal, pLlantaNormal, indiCargaNormal,codVelNormal));
                    }
                    System.out.println("4 Ruedas Normales registradas.");
                    break;
                case 8:
                    System.out.print("Ancho: ");
                    int aDeportivo = sc.nextInt();
                    System.out.print("Pulgadas Llanta: ");
                    int pLlantaDeportivo = sc.nextInt();
                    System.out.print("Indice Carga: ");
                    int indiCargaDeportivo = sc.nextInt();
                    System.out.print("Codigo Velocial: ");
                    int codVelDeportivo = sc.nextInt();
                    for (int i = 0; i < 4; i++) {
                        sistemaGestion.registrarRueda(new Deportivo(aDeportivo, pLlantaDeportivo, indiCargaDeportivo,codVelDeportivo));
                    }
                    System.out.println("4 Ruedas Deportivas registradas.");
                    break;
                case 9:
                    System.out.print("Ancho: ");
                    int aTodoterreno = sc.nextInt();
                    System.out.print("Pulgadas Llanta: ");
                    int pLlantaTodoterreno = sc.nextInt();
                    System.out.print("Indice Carga: ");
                    int indiCargaTodoterreno = sc.nextInt();
                    System.out.print("Codigo Velocial: ");
                    int codVelTodoterreno = sc.nextInt();
                    for (int i = 0; i < 4; i++) {
                        sistemaGestion.registrarRueda(new Todoterreno(aTodoterreno, pLlantaTodoterreno, indiCargaTodoterreno,codVelTodoterreno));
                    }
                    System.out.println("4 Ruedas Todoterrenos registradas.");
                    break;
                case 10:
                    System.out.print("Color: ");
                    sc.nextLine();
                    String colorBiplaza = sc.nextLine();
                    System.out.print("Nº plazas: ");
                    int plazasBiplaza = sc.nextInt();
                    System.out.print("Peso Autorizado: ");
                    double pesoAutorBiplaza = sc.nextDouble();
                    System.out.print("Tara Vehiculo: ");
                    double taraVehiBiplaza = sc.nextDouble();
                    if (!comprobarStockParaNuevoVehiculo()) break;
                    delegarAGestor("Biplaza", colorBiplaza, plazasBiplaza, pesoAutorBiplaza, taraVehiBiplaza);
                    break;
                case 11:
                    System.out.print("Color: ");
                    sc.nextLine();
                    String colorTurismo = sc.nextLine();
                    System.out.print("Nº plazas: ");
                    int plazasTurismo = sc.nextInt();
                    System.out.print("Peso Autorizado: ");
                    double pesoAutorTurismo = sc.nextDouble();
                    System.out.print("Tara Vehiculo: ");
                    double taraVehiTurismo = sc.nextDouble();
                    if (!comprobarStockParaNuevoVehiculo()) break;
                    delegarAGestor("Turismo", colorTurismo, plazasTurismo, pesoAutorTurismo, taraVehiTurismo);
                    break;
                case 12:
                    System.out.print("Color: ");
                    sc.nextLine();
                    String colorFurgoneta = sc.nextLine();
                    System.out.print("Nº plazas: ");
                    int plazasFurgoneta = sc.nextInt();
                    System.out.print("Peso Autorizado: ");
                    double pesoAutorFurgoneta = sc.nextDouble();
                    System.out.print("Tara Vehiculo: ");
                    double taraVehiFurgoneta = sc.nextDouble();
                    if (!comprobarStockParaNuevoVehiculo()) break;
                    delegarAGestor("Furgoneta", colorFurgoneta, plazasFurgoneta, pesoAutorFurgoneta, taraVehiFurgoneta);
                    break;
                case 13:
                    actualizarComponenteAlmacen();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Submenú de gestión de trabajadores. Permite dar de alta operarios,
     * mecánicos, gestores de planta y administradores de sistema. Todos
     * comparten los mismos datos básicos (nombre, apellidos, DNI, dirección,
     * NSS y salario) con fecha de ingreso igual a la actual.
     */
    public static void menuTrabajadores() {
        int op = -1;
        while (op != 0) {
            System.out.println("\nMENÚ GESTIÓN DE TRABAJADORES");
            System.out.println("1. Añadir Operario");
            System.out.println("2. Añadir Mecánico");
            System.out.println("3. Añadir Gestor de Planta");
            System.out.println("4. Añadir Administrador de Sistema");
            System.out.println("0. Volver al menú principal");
            System.out.print("Opción: ");
            op = sc.nextInt();
            switch (op) {
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                case 1: case 2: case 3: case 4:
                    sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Apellidos: ");
                    String apellidos = sc.nextLine();
                    System.out.print("DNI: ");
                    String dni = sc.nextLine();
                    System.out.print("Dirección: ");
                    String dir = sc.nextLine();
                    System.out.print("Nº Seg. Social: ");
                    String segSocial = sc.nextLine();
                    System.out.print("Salario: ");
                    double salario = sc.nextDouble();
                    switch (op) {
                        case 1:
                            sistemaGestion.registrarOperario(
                                new Operario(nombre, apellidos, dni, dir, segSocial, salario, LocalDate.now()));
                            System.out.println("Operario registrado.");
                            break;
                        case 2:
                            sistemaGestion.registrarMecanico(
                                new Mecanico(nombre, apellidos, dni, dir, segSocial, salario, LocalDate.now()));
                            System.out.println("Mecánico registrado.");
                            break;
                        case 3:
                            sistemaGestion.registrarGestorPlanta(
                                new GestorPlanta(nombre, apellidos, dni, dir, segSocial, salario, LocalDate.now()));
                            System.out.println("Gestor de Planta registrado.");
                            break;
                        case 4:
                            sistemaGestion.registrarAdministradorSistema(
                                new AdministradorSistema(nombre, apellidos, dni, dir, segSocial, salario, LocalDate.now()));
                            System.out.println("Administrador de Sistema registrado.");
                            break;
                    }
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Submenú de búsqueda de empleados. Permite buscar operarios por
     * nombre, listar operarios eficientes (>10 montajes), buscar a un
     * trabajador por DNI en cualquier perfil y listar todos los operarios
     * registrados con sus métricas.
     */
    public static void menuBusqueda() {
        int op = -1;
        while (op != 0) {
            System.out.println("\nMENÚ BUSCAR EMPLEADOS");
            System.out.println("1. Buscar operario por nombre");
            System.out.println("2. Listar operarios eficientes");
            System.out.println("3. Buscar trabajador por DNI");
            System.out.println("4. Listar todos los operarios");
            System.out.println("5. Buscar mecánico por nombre");
            System.out.println("0. Volver al menú principal");
            System.out.print("Opción: ");
            op = sc.nextInt(); sc.nextLine();
            switch (op) {
                case 1:
                    System.out.print("Nombre a buscar: ");
                    String nombre = sc.nextLine();
                    for (Operario o : sistemaGestion.buscarOperariosPorNombre(nombre)) {
                        System.out.println("  - " + o.getNombre() + " " + o.getApellidos());
                    }
                    break;
                case 2:
                    for (Operario o : sistemaGestion.buscarOperariosEficientes()) {
                        System.out.println("  - " + o.getNombre() + " (montajes: " + o.getMontajesRealizados() + ")");
                    }
                    break;
                case 3:
                    System.out.print("DNI: ");
                    String dni = sc.nextLine();
                    System.out.println(sistemaGestion.buscarTrabajadorPorDni(dni));
                    break;
                case 4: {

                    List<Operario> todos = sistemaGestion.consultarOperario();
                    if (todos.isEmpty()) {
                        System.out.println("No hay operarios registrados.");
                    } else {
                        for (Operario o : todos) {
                            String perfil = o.esEficiente() ? "eficiente" : "estándar";
                            System.out.println("  - " + o.getNombre() + " " + o.getApellidos() + " | DNI: " + o.getDni() + " | montajes: " + o.getMontajesRealizados() + " (" + perfil + ")");
                        }
                    }
                    break;
                }
                case 5:
                    System.out.print("Nombre del mecánico a buscar: ");
                    String nombreMecanico = sc.nextLine();
                    List<Mecanico> mecanicos = sistemaGestion.buscarMecanicosPorNombre(nombreMecanico);
                    if (mecanicos.isEmpty()) {
                        System.out.println("No se encontraron mecánicos con ese nombre.");
                    } else {
                        for (Mecanico m : mecanicos) {
                            System.out.println("  - " + m.getNombre() + " " + m.getApellidos());
                        }
                    }
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Muestra por consola el stock actual del almacén (motores, tapicerías
     * y ruedas) y el número de vehículos pendientes en la cadena y ya
     * ensamblados, separados por tipo.
     */
    public static void mostrarStock() {
        System.out.println("\nSTOCK DEL ALMACÉN");
        System.out.println("Motores disponibles:    " + sistemaGestion.consultarMotor().size());
        System.out.println("Tapicerías disponibles: " + sistemaGestion.consultarTapiceria().size());
        System.out.println("Ruedas disponibles:     " + sistemaGestion.consultarRueda().size());

        int pendBip = contarPendientes(cadenaMontaje.getCadenaBiplaza());
        int pendTur = contarPendientes(cadenaMontaje.getCadenaTurismo());
        int pendFur = contarPendientes(cadenaMontaje.getCadenaFurgoneta());
        int ensBip  = sistemaGestion.consultarBiplazasDeportivos().size();
        int ensTur  = sistemaGestion.consultarTurismo().size();
        int ensFur  = sistemaGestion.consultarFurgoneta().size();

        System.out.println("\nVEHÍCULOS EN CADENA (pendientes de ensamblar)");
        System.out.println("  Biplazas: " + pendBip);
        System.out.println("  Turismos: " + pendTur);
        System.out.println("  Furgonetas: " + pendFur);
        System.out.println("VEHÍCULOS ENSAMBLADOS (histórico)");
        System.out.println("  Biplazas: " + ensBip);
        System.out.println("  Turismos: " + ensTur);
        System.out.println("  Furgonetas: " + ensFur);
        System.out.println("  TOTAL coches: " + sistemaGestion.consultarTotalCoches());
    }

    /**
     * Cuenta los coches de la lista que aún no han alcanzado el estado
     * TERMINADO.
     *
     * @param lista lista de coches a evaluar
     * @return número de coches pendientes
     */
    private static int contarPendientes(java.util.List<? extends com.practica.vehiculo.Coche> lista) {
        int n = 0;
        for (com.practica.vehiculo.Coche c : lista) {
            if (c.getEstadoMontaje() != com.practica.vehiculo.EstadoMontaje.TERMINADO) {
                n++;
            }
        }
        return n;
    }

    /**
     * Submenú de simulación. Permite elegir entre simulación simple,
     * compleja (con averías y mecánicos) o muy compleja (averías + apagones
     * con administrador). Antes de iniciar la simulación valida los
     * requisitos: vehículos en cadena, stock suficiente, presencia de
     * mecánicos y/o administrador según el nivel.
     */
    public static void menuSimulacion() {
        int op = -1;
        while (op != 0) {
            System.out.println("\nMENÚ INICIAR SIMULACIÓN");
            System.out.println("1. Simple (sin averías)");
            System.out.println("2. Compleja (con mecánicos)");
            System.out.println("3. Muy Compleja (mecánicos + apagones)");
            System.out.println("0. Volver al menú principal");
            System.out.print("Tipo de simulación: ");
            op = sc.nextInt();

            if (op == 0) {
                System.out.println("Volviendo al menú principal...");
                break;
            }

            if (op < 1 || op > 3) {
                System.out.println("Opción no válida.");
                continue;
            }

            cadenaMontaje.purgarTerminados();
            int totalCoches = cadenaMontaje.getCadenaBiplaza().size() + cadenaMontaje.getCadenaTurismo().size() + cadenaMontaje.getCadenaFurgoneta().size();
            if (totalCoches == 0) {
                System.out.println("No hay vehículos en la cadena. Añade vehículos desde Gestión de Almacén (opciones 10-12).");
                break;
            }

            ComprobacionStock res = sistemaGestion.comprobarStock(totalCoches);
            if (res != ComprobacionStock.OK) {
                System.out.println("Simulación cancelada: stock insuficiente de " + res);
                break;
            }

            if (op >= 2 && sistemaGestion.consultarMecanico().isEmpty()) {
                System.out.println("Simulación cancelada: se requiere al menos un mecánico.");
                break;
            }
            if (op == 2) {
                boolean hayEficiente = false, hayEstandar = false;
                for (Mecanico m : sistemaGestion.consultarMecanico()) {
                    if (m.esEficiente()) hayEficiente = true; else hayEstandar = true;
                }
                if (!hayEficiente && hayEstandar) {
                    System.out.println("[AVISO] Aún no hay mecánicos eficientes (>20 reparaciones). " + "La simulación continuará sólo con mecánicos estándar.");
                }
            }
            if (op == 3 && sistemaGestion.consultarAdministradorSistema().isEmpty()) {
                System.out.println("Simulación cancelada: se requiere un Administrador de Sistema para gestionar apagones.");
                break;
            }

            Mecanico[] mecs = (op == 1) ? new Mecanico[0]
                : sistemaGestion.consultarMecanico().toArray(new Mecanico[0]);

            AdministradorSistema admin = (op == 3 && !sistemaGestion.consultarAdministradorSistema().isEmpty()) ? sistemaGestion.consultarAdministradorSistema().get(0) : null;

            System.out.println("Iniciando simulación con " + totalCoches + " vehículos...");

            Planificador planificador = new Planificador(cadenaMontaje, sistemaGestion, op, mecs, admin);
            planificador.addObservador(dashboard);

            planificador.iniciarSimulacion();
            break;
        }
    }

    /**
     * Submenú de listados y estadísticas. Permite obtener listados
     * ordenados de operarios, filtros sobre vehículos terminados por
     * motor o tapicería, ranking de configuraciones más ensambladas y la
     * consulta del historial de operaciones por fecha.
     */
    public static void menuListados() {
        int op = -1;
        while (op != 0) {
            System.out.println("\nMENÚ LISTADOS Y ESTADÍSTICAS");
            System.out.println("1. Operarios ordenados (nombre, apellidos)");
            System.out.println("2. Operarios por productividad (mínimo de montajes)");
            System.out.println("3. Vehículos terminados");
            System.out.println("4. Vehículos terminados por tipo de motor");
            System.out.println("5. Vehículos terminados por tipo de tapicería");
            System.out.println("6. Vehículos terminados ordenados por tipo");
            System.out.println("7. Configuraciones más ensambladas");
            System.out.println("8. Consultar historial por fecha");
            System.out.println("0. Volver al menú principal");
            System.out.print("Opción: ");
            op = sc.nextInt();
            switch (op) {
                case 1: {
                    List<Operario> lista = sistemaGestion.ordenarOperarios();
                    if (lista.isEmpty()) {
                        System.out.println("No hay operarios registrados.");
                    } else {
                        for (Operario o : lista) {
                            System.out.println("  - " + o.getNombre() + " " + o.getApellidos() + " (montajes: " + o.getMontajesRealizados() + ")");
                        }
                    }
                    break;
                }
                case 2: {
                    System.out.print("Mínimo de montajes: ");
                    int min = sc.nextInt();
                    List<Operario> lista = sistemaGestion.obtenerOperariosProductividad(min);
                    if (lista.isEmpty()) {
                        System.out.println("Ningún operario alcanza ese umbral.");
                    } else {
                        for (Operario o : lista) {
                            System.out.println("  - " + o.getNombre() + " " + o.getApellidos() + " (montajes: " + o.getMontajesRealizados() + ")");
                        }
                    }
                    break;
                }
                case 3: {
                    List<Coche> coches = sistemaGestion.obtenerCochesTerminados(cadenaMontaje);
                    if (coches.isEmpty()) {
                        System.out.println("No hay vehículos terminados todavía.");
                    } else {
                        for (Coche c : coches) {
                            System.out.println("  - " + c.tipoCoche() + " (" + c.getColor() + ")");
                        }
                    }
                    break;
                }
                case 4: {
                    sc.nextLine();
                    System.out.print("Tipo de motor (Gasolina/Electrico/Hibrido): ");
                    String tipo = sc.nextLine();
                    List<Coche> terminados = sistemaGestion.obtenerCochesTerminados(cadenaMontaje);
                    List<Coche> filtrados = sistemaGestion.filtrarTipoMotor(terminados, tipo);
                    if (filtrados.isEmpty()) {
                        System.out.println("Sin coincidencias.");
                    } else {
                        for (Coche c : filtrados) {
                            System.out.println("  - " + c.tipoCoche() + " (" + c.getColor() + ")");
                        }
                    }
                    break;
                }
                case 5: {
                    sc.nextLine();
                    System.out.print("Tipo de tapicería (Tela/Cuero/Alcantara): ");
                    String tipo = sc.nextLine();
                    List<Coche> terminados = sistemaGestion.obtenerCochesTerminados(cadenaMontaje);
                    List<Coche> filtrados = sistemaGestion.filtrarTipoTapiceria(terminados, tipo);
                    if (filtrados.isEmpty()) {
                        System.out.println("Sin coincidencias.");
                    } else {
                        for (Coche c : filtrados) {
                            System.out.println("  - " + c.tipoCoche() + " (" + c.getColor() + ")");
                        }
                    }
                    break;
                }
                case 6: {
                    List<Coche> terminados = sistemaGestion.obtenerCochesTerminados(cadenaMontaje);
                    List<Coche> ordenados = sistemaGestion.obtenerCochesOrdenados(terminados);
                    if (ordenados.isEmpty()) {
                        System.out.println("No hay vehículos terminados.");
                    } else {
                        for (Coche c : ordenados) {
                            System.out.println("  - " + c.tipoCoche() + " (" + c.getColor() + ")");
                        }
                    }
                    break;
                }
                case 7: {
                    Map<String, Integer> conf = sistemaGestion.getConfiguracionesMasEnsamblados(cadenaMontaje);
                    if (conf.isEmpty()) {
                        System.out.println("Aún no hay configuraciones ensambladas.");
                    } else {
                        for (Map.Entry<String, Integer> e : conf.entrySet()) {
                            System.out.println("  - " + e.getKey() + "  => " + e.getValue() + " uds.");
                        }
                    }
                    break;
                }
                case 8: {
                    sc.nextLine();
                    System.out.print("Fecha (dd/MM/yyyy) — vacío = hoy: ");
                    String fechaStr = sc.nextLine().trim();
                    Date fecha;
                    try {
                        fecha = fechaStr.isEmpty()
                                ? new Date()
                                : new SimpleDateFormat("dd/MM/yyyy").parse(fechaStr);
                    } catch (Exception ex) {
                        System.out.println("Fecha no válida. Usa el formato dd/MM/yyyy.");
                        break;
                    }
                    List<RegistroMontaje> regs = sistemaGestion.consultarHistorialFecha(fecha);
                    if (regs.isEmpty()) {
                        System.out.println("Sin registros para esa fecha.");
                    } else {
                        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
                        for (RegistroMontaje r : regs) {
                            System.out.println("  - " + fmt.format(r.getFecha())
                                    + " | " + r.getTipoComponente()
                                    + " | " + r.getAccion());
                        }
                    }
                    break;
                }
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
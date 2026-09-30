import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            Caso casoActual = crearCaso(scanner);
            int opcion;

            do {
                mostrarMenu();

                opcion = leerEntero(
                        scanner,
                        "Seleccione una opcion: "
                );

                switch (opcion) {
                    case 1:
                        casoActual = crearCaso(scanner);

                        System.out.println(
                                "El caso actual fue reemplazado."
                        );
                        break;

                    case 2:
                        registrarUbicacion(
                                scanner, casoActual
                        );
                        break;

                    case 3:
                        System.out.println(
                                "\nUBICACIONES REGISTRADAS"
                        );

                        System.out.println(
                                casoActual.consultarUbicaciones()
                        );
                        break;

                    case 4:
                        consultarUnaUbicacion(
                                scanner, casoActual
                        );
                        break;

                    case 5:
                        modificarUbicacion(
                                scanner, casoActual
                        );
                        break;

                    case 6:
                        descartarUbicacion(
                                scanner, casoActual
                        );
                        break;

                    case 7:
                        registrarPista(
                                scanner, casoActual
                        );
                        break;

                    case 8:
                        System.out.println(
                                "\nPISTAS REGISTRADAS"
                        );

                        System.out.println(
                                casoActual.consultarPistas()
                        );
                        break;

                    case 9:
                        buscarPista(scanner, casoActual);
                        break;

                    case 10:
                        modificarPista(
                                scanner, casoActual
                        );
                        break;

                    case 11:
                        eliminarPista(
                                scanner, casoActual
                        );
                        break;

                    case 12:
                        System.out.println(
                                "\n"
                                + casoActual.generarReporte()
                        );
                        break;

                    case 13:
                        System.out.println(
                                "Programa finalizado."
                        );
                        break;

                    default:
                        System.out.println(
                                "Opcion no valida."
                        );
                }

            } while (opcion != 13);

        } finally {
            scanner.close();

            System.out.println(
                    "Scanner cerrado correctamente."
            );
        }
    }

    private static Caso crearCaso(Scanner scanner) {
        System.out.println("\nDATOS DEL CASO");

        String nombre = leerTexto(
                scanner,
                "Nombre del caso: "
        );

        String codigo = leerTexto(
                scanner,
                "Codigo de identificacion: "
        );

        String detective = leerTexto(
                scanner,
                "Detective responsable: "
        );

        return new Caso(
                nombre,
                codigo,
                detective
        );
    }

    private static void mostrarMenu() {
        System.out.println(
                "\n===== AGENCIA DE DETECTIVES ====="
        );

        System.out.println("1. Nuevo caso");
        System.out.println("2. Registrar ubicacion");
        System.out.println("3. Consultar ubicaciones");
        System.out.println("4. Consultar una ubicacion");
        System.out.println("5. Modificar ubicacion");
        System.out.println("6. Descartar ubicacion");
        System.out.println("7. Registrar pista");
        System.out.println("8. Consultar pistas");
        System.out.println("9. Buscar pista");
        System.out.println("10. Modificar pista");
        System.out.println("11. Eliminar pista");

        System.out.println(
                "12. Mostrar reporte de investigacion"
        );

        System.out.println("13. Salir");
    }

    private static void registrarUbicacion(
            Scanner scanner, Caso caso) {

        int posicion = leerEntero(
                scanner,
                "Posicion del arreglo (1-5): "
        );

        if (posicion < 1 || posicion > 5) {
            System.out.println(
                    "La posicion debe estar entre 1 y 5."
            );
            return;
        }

        if (caso.obtenerUbicacion(posicion) != null) {
            System.out.println(
                    "La posicion seleccionada esta ocupada."
            );
            return;
        }

        String codigo = leerTexto(
                scanner,
                "Codigo: "
        );

        String nombre = leerTexto(
                scanner,
                "Nombre: "
        );

        String direccion = leerTexto(
                scanner,
                "Direccion o descripcion: "
        );

        int riesgo = leerEntero(
                scanner,
                "Nivel de riesgo (1-10): "
        );

        String estado = leerTexto(
                scanner,
                "Estado: "
        );

        try {
            Ubicacion nuevaUbicacion = new Ubicacion(
                    codigo,
                    nombre,
                    direccion,
                    riesgo,
                    estado
            );

            if (caso.registrarUbicacion(
                    posicion, nuevaUbicacion)) {

                System.out.println(
                        "Ubicacion registrada correctamente."
                );
            } else {
                System.out.println(
                        "No se pudo registrar la ubicacion."
                );
            }

        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    private static void consultarUnaUbicacion(
            Scanner scanner, Caso caso) {

        int posicion = leerEntero(
                scanner,
                "Posicion que desea consultar (1-5): "
        );

        if (posicion < 1 || posicion > 5) {
            System.out.println(
                    "La posicion debe estar entre 1 y 5."
            );
            return;
        }

        Ubicacion encontrada =
                caso.obtenerUbicacion(posicion);

        if (encontrada == null) {
            System.out.println(
                    "La posicion se encuentra vacia."
            );
        } else {
            System.out.println(
                    encontrada.toString()
            );
        }
    }

    private static void modificarUbicacion(
            Scanner scanner, Caso caso) {

        int posicion = leerEntero(
                scanner,
                "Posicion que desea modificar (1-5): "
        );

        if (posicion < 1 || posicion > 5) {
            System.out.println(
                    "La posicion debe estar entre 1 y 5."
            );
            return;
        }

        if (caso.obtenerUbicacion(posicion) == null) {
            System.out.println(
                    "La posicion se encuentra vacia."
            );
            return;
        }

        int nuevoRiesgo = leerEntero(
                scanner,
                "Nuevo nivel de riesgo (1-10): "
        );

        String nuevoEstado = leerTexto(
                scanner,
                "Nuevo estado: "
        );

        try {
            if (caso.modificarUbicacion(
                    posicion,
                    nuevoRiesgo,
                    nuevoEstado)) {

                System.out.println(
                        "Ubicacion modificada correctamente."
                );
            }

        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    private static void descartarUbicacion(
            Scanner scanner, Caso caso) {

        int posicion = leerEntero(
                scanner,
                "Posicion que desea descartar (1-5): "
        );

        if (posicion < 1 || posicion > 5) {
            System.out.println(
                    "La posicion debe estar entre 1 y 5."
            );
            return;
        }

        if (caso.descartarUbicacion(posicion)) {
            System.out.println(
                    "Ubicacion descartada correctamente."
            );
        } else {
            System.out.println(
                    "La posicion se encuentra vacia."
            );
        }
    }

    private static void registrarPista(
            Scanner scanner, Caso caso) {

        String codigo = leerTexto(
                scanner,
                "Codigo: "
        );

        if (caso.buscarPista(codigo) != null) {
            System.out.println(
                    "Ya existe una pista con ese codigo."
            );
            return;
        }

        String descripcion = leerTexto(
                scanner,
                "Descripcion: "
        );

        String tipo = leerTexto(
                scanner,
                "Tipo de evidencia: "
        );

        int importancia = leerEntero(
                scanner,
                "Nivel de importancia (1-10): "
        );

        int confiabilidad = leerEntero(
                scanner,
                "Nivel de confiabilidad (0-100): "
        );

        try {
            Pista nuevaPista = new Pista(
                    codigo,
                    descripcion,
                    tipo,
                    importancia,
                    confiabilidad
            );

            if (caso.registrarPista(nuevaPista)) {
                System.out.println(
                        "Pista registrada correctamente."
                );
            } else {
                System.out.println(
                        "No se pudo registrar la pista."
                );
            }

        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    private static void buscarPista(
            Scanner scanner, Caso caso) {

        String codigo = leerTexto(
                scanner,
                "Codigo de la pista: "
        );

        Pista encontrada =
                caso.buscarPista(codigo);

        if (encontrada == null) {
            System.out.println(
                    "No existe una pista con ese codigo."
            );
        } else {
            System.out.println(
                    encontrada.toString()
            );
        }
    }

    private static void modificarPista(
            Scanner scanner, Caso caso) {

        String codigo = leerTexto(
                scanner,
                "Codigo de la pista: "
        );

        if (caso.buscarPista(codigo) == null) {
            System.out.println(
                    "No existe una pista con ese codigo."
            );
            return;
        }

        String descripcion = leerTexto(
                scanner,
                "Nueva descripcion: "
        );

        String tipo = leerTexto(
                scanner,
                "Nuevo tipo de evidencia: "
        );

        int importancia = leerEntero(
                scanner,
                "Nuevo nivel de importancia (1-10): "
        );

        int confiabilidad = leerEntero(
                scanner,
                "Nuevo nivel de confiabilidad (0-100): "
        );

        try {
            if (caso.modificarPista(
                    codigo,
                    descripcion,
                    tipo,
                    importancia,
                    confiabilidad)) {

                System.out.println(
                        "Pista modificada correctamente."
                );
            }

        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    private static void eliminarPista(
            Scanner scanner, Caso caso) {

        String codigo = leerTexto(
                scanner,
                "Codigo de la pista: "
        );

        if (caso.eliminarPista(codigo)) {
            System.out.println(
                    "Pista eliminada correctamente."
            );
        } else {
            System.out.println(
                    "No existe una pista con ese codigo."
            );
        }
    }

    private static int leerEntero(
            Scanner scanner, String mensaje) {

        while (true) {
            try {
                System.out.print(mensaje);

                int numero = scanner.nextInt();
                scanner.nextLine();

                return numero;

            } catch (InputMismatchException e) {
                System.out.println(
                        "Debe ingresar un numero entero."
                );

                scanner.nextLine();
            }
        }
    }

    private static String leerTexto(
            Scanner scanner, String mensaje) {

        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }
}
import java.util.ArrayList;

public class Caso {
    private String nombre;
    private String codigo;
    private String detectiveResponsable;
    private Ubicacion[] ubicaciones;
    private ArrayList<Pista> pistas;

    public Caso(String nombre, String codigo,
            String detectiveResponsable) {

        this.nombre = nombre;
        this.codigo = codigo;
        this.detectiveResponsable = detectiveResponsable;

        ubicaciones = new Ubicacion[5];
        pistas = new ArrayList<Pista>();
    }

    private boolean esPosicionValida(int posicion) {
        return posicion >= 1 && posicion <= 5;
    }

    public boolean registrarUbicacion(
            int posicion, Ubicacion nuevaUbicacion) {

        if (!esPosicionValida(posicion)) {
            return false;
        }

        int indice = posicion - 1;

        if (ubicaciones[indice] != null) {
            return false;
        }

        ubicaciones[indice] = nuevaUbicacion;
        return true;
    }

    public Ubicacion obtenerUbicacion(int posicion) {
        if (!esPosicionValida(posicion)) {
            return null;
        }

        return ubicaciones[posicion - 1];
    }

    public String consultarUbicaciones() {
        String resultado = "";

        for (int i = 0; i < ubicaciones.length; i++) {
            if (ubicaciones[i] != null) {
                resultado = resultado
                        + "Posicion " + (i + 1) + ": "
                        + ubicaciones[i].toString() + "\n";
            }
        }

        if (resultado.isEmpty()) {
            return "No hay ubicaciones registradas.";
        }

        return resultado;
    }

    public boolean modificarUbicacion(
            int posicion, int nuevoRiesgo,
            String nuevoEstado) {

        Ubicacion encontrada = obtenerUbicacion(posicion);

        if (encontrada == null) {
            return false;
        }

        encontrada.modificar(nuevoRiesgo, nuevoEstado);
        return true;
    }

    public boolean descartarUbicacion(int posicion) {
        if (!esPosicionValida(posicion)) {
            return false;
        }

        int indice = posicion - 1;

        if (ubicaciones[indice] == null) {
            return false;
        }

        ubicaciones[indice] = null;
        return true;
    }

    public int contarUbicaciones() {
        int cantidad = 0;

        for (int i = 0; i < ubicaciones.length; i++) {
            if (ubicaciones[i] != null) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public int contarEspaciosDisponibles() {
        return 5 - contarUbicaciones();
    }

    public Ubicacion ubicacionMayorRiesgo() {
        Ubicacion mayor = null;

        for (int i = 0; i < ubicaciones.length; i++) {
            if (ubicaciones[i] != null) {
                if (mayor == null
                        || ubicaciones[i].getNivelRiesgo()
                        > mayor.getNivelRiesgo()) {

                    mayor = ubicaciones[i];
                }
            }
        }

        return mayor;
    }

    public boolean registrarPista(Pista nuevaPista) {
        if (buscarPista(nuevaPista.getCodigo()) != null) {
            return false;
        }

        pistas.add(nuevaPista);
        return true;
    }

    public Pista buscarPista(String codigoPista) {
        for (int i = 0; i < pistas.size(); i++) {
            Pista actual = pistas.get(i);

            if (actual.getCodigo().equalsIgnoreCase(codigoPista)) {
                return actual;
            }
        }

        return null;
    }

    public String consultarPistas() {
        if (pistas.isEmpty()) {
            return "No hay pistas registradas.";
        }

        String resultado = "";

        for (int i = 0; i < pistas.size(); i++) {
            resultado = resultado
                    + pistas.get(i).toString() + "\n";
        }

        return resultado;
    }

    public boolean modificarPista(
            String codigoPista,
            String nuevaDescripcion,
            String nuevoTipoEvidencia,
            int nuevaImportancia,
            int nuevaConfiabilidad) {

        Pista encontrada = buscarPista(codigoPista);

        if (encontrada == null) {
            return false;
        }

        encontrada.modificar(
                nuevaDescripcion,
                nuevoTipoEvidencia,
                nuevaImportancia,
                nuevaConfiabilidad
        );

        return true;
    }

    public boolean eliminarPista(String codigoPista) {
        for (int i = 0; i < pistas.size(); i++) {
            Pista actual = pistas.get(i);

            if (actual.getCodigo().equalsIgnoreCase(codigoPista)) {
                pistas.remove(i);
                return true;
            }
        }

        return false;
    }

    public int contarPistas() {
        return pistas.size();
    }

    public Pista pistaMayorImportancia() {
        if (pistas.isEmpty()) {
            return null;
        }

        Pista mayor = pistas.get(0);

        for (int i = 1; i < pistas.size(); i++) {
            if (pistas.get(i).getNivelImportancia()
                    > mayor.getNivelImportancia()) {

                mayor = pistas.get(i);
            }
        }

        return mayor;
    }

    public Pista pistaMayorConfiabilidad() {
        if (pistas.isEmpty()) {
            return null;
        }

        Pista mayor = pistas.get(0);

        for (int i = 1; i < pistas.size(); i++) {
            if (pistas.get(i).getNivelConfiabilidad()
                    > mayor.getNivelConfiabilidad()) {

                mayor = pistas.get(i);
            }
        }

        return mayor;
    }

    public double promedioImportancia() {
        if (pistas.isEmpty()) {
            return 0;
        }

        int suma = 0;

        for (int i = 0; i < pistas.size(); i++) {
            suma = suma
                    + pistas.get(i).getNivelImportancia();
        }

        return (double) suma / pistas.size();
    }

    public String generarReporte() {
        String reporte = "REPORTE DE INVESTIGACION\n";

        reporte = reporte
                + "Caso: " + nombre + "\n"
                + "Codigo: " + codigo + "\n"
                + "Detective responsable: "
                + detectiveResponsable + "\n";

        reporte = reporte
                + "Ubicaciones registradas: "
                + contarUbicaciones() + "\n"
                + "Espacios disponibles: "
                + contarEspaciosDisponibles() + "\n";

        Ubicacion mayorRiesgo = ubicacionMayorRiesgo();

        if (mayorRiesgo == null) {
            reporte = reporte
                    + "Ubicacion con mayor riesgo: "
                    + "No disponible\n";
        } else {
            reporte = reporte
                    + "Ubicacion con mayor riesgo: "
                    + mayorRiesgo.toString() + "\n";
        }

        reporte = reporte
                + "Pistas registradas: "
                + contarPistas() + "\n";

        if (pistas.isEmpty()) {
            reporte = reporte
                    + "Pista con mayor importancia: "
                    + "No disponible\n"
                    + "Pista con mayor confiabilidad: "
                    + "No disponible\n"
                    + "Promedio de importancia: "
                    + "No disponible";
        } else {
            reporte = reporte
                    + "Pista con mayor importancia: "
                    + pistaMayorImportancia().toString() + "\n";

            reporte = reporte
                    + "Pista con mayor confiabilidad: "
                    + pistaMayorConfiabilidad().toString() + "\n";

            reporte = reporte
                    + "Promedio de importancia: "
                    + String.format(
                            "%.2f", promedioImportancia()
                    );
        }

        return reporte;
    }
}
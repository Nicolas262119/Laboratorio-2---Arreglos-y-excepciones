public class Ubicacion {
    private String codigo;
    private String nombre;
    private String direccion;
    private int nivelRiesgo;
    private String estado;

    public Ubicacion(String codigo, String nombre, String direccion,
            int nivelRiesgo, String estado) {

        validarNivelRiesgo(nivelRiesgo);

        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.nivelRiesgo = nivelRiesgo;
        this.estado = estado;
    }

    public int getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void modificar(int nuevoNivelRiesgo, String nuevoEstado) {
        validarNivelRiesgo(nuevoNivelRiesgo);

        nivelRiesgo = nuevoNivelRiesgo;
        estado = nuevoEstado;
    }

    private void validarNivelRiesgo(int nivelRiesgo) {
        if (nivelRiesgo < 1 || nivelRiesgo > 10) {
            throw new IllegalArgumentException(
                    "El nivel de riesgo debe estar entre 1 y 10."
            );
        }
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo
                + " | Nombre: " + nombre
                + " | Direccion: " + direccion
                + " | Riesgo: " + nivelRiesgo
                + " | Estado: " + estado;
    }
}
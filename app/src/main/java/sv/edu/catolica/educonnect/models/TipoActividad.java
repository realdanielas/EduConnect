package sv.edu.catolica.educonnect.models;

public enum TipoActividad {
    EXAMEN("Examen"),
    TAREA("Tarea");

    private final String descripcion;

    TipoActividad(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
    @Override
    public String toString() {
        return descripcion;
    }
}
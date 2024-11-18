package sv.edu.catolica.educonnect;

public enum ActividadEstado {
    PENDIENTE("Pendiente"),
    EN_PROGRESO("En Progreso"),
    COMPLETADO("Completado");

    private final String descripcion;

    ActividadEstado(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
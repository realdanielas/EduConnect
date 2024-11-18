package sv.edu.catolica.educonnect;

import java.util.Date;

public class Tarea extends Actividad{
    private double calificacion;
    private String enlaceRecursos;
    private String comentarios;

    public Tarea(String titulo, String descripcion, Date fechaInicio, Date fechaFin, ActividadEstado actividadEstado, double calificacion, String comentarios, String enlaceRecursos) {
        // Llamada al constructor de la clase base (Actividad) y pasando null para las listas
        super(titulo, descripcion, fechaInicio, fechaFin, actividadEstado, TipoActividad.TAREA, null);

        // Validacion de Calificacion
        if (calificacion < 0 || calificacion > 10) {
            throw new IllegalArgumentException("La calificación debe estar entre 0 y 10.");
        }

        // Inicializar los nuevos campos específicos de Tarea
        this.calificacion = calificacion;
        this.comentarios = comentarios != null ? comentarios : ""; // Default en caso de null
        this.enlaceRecursos = enlaceRecursos != null ? enlaceRecursos : ""; // Default en caso de null
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public String getEnlaceRecursos() {
        return enlaceRecursos;
    }

    public void setEnlaceRecursos(String enlaceRecursos) {
        this.enlaceRecursos = enlaceRecursos;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    //Por motivos de testeo y debugging
    @Override
    public String toString() {
        return "Tarea{" +
                "titulo='" + getTitulo() + '\'' +
                ", descripcion='" + getDescripcion() + '\'' +
                ", fechaInicio=" + getFechaInicio() +
                ", fechaFin=" + getFechaFin() +
                ", actividadEstado=" + getEstadoDescripcion() +
                ", calificacion=" + calificacion +
                ", enlaceRecursos='" + enlaceRecursos + '\'' +
                ", comentarios='" + comentarios + '\'' +
                '}';
    }
}

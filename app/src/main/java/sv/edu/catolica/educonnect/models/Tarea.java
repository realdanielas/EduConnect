package sv.edu.catolica.educonnect.models;

import java.util.Date;

public class Tarea extends Actividad{
    private double calificacion;
    private String enlaceRecursos;
    private String comentarios;

    public Tarea(String titulo, String descripcion, Date fechaInicio, Date fechaFin, double calificacion, String comentarios, String enlaceRecursos) {
        super(titulo, descripcion, fechaInicio, fechaFin, TipoActividad.TAREA, null); // Pass TipoActividad.TAREA

        // Validation and initialization
        if (calificacion < 0 || calificacion > 10) {
            throw new IllegalArgumentException("La calificación debe estar entre 0 y 10.");
        }
        this.calificacion = calificacion;
        this.comentarios = comentarios != null ? comentarios : ""; // Default in case of null
        this.enlaceRecursos = enlaceRecursos != null ? enlaceRecursos : ""; // Default in case of null

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

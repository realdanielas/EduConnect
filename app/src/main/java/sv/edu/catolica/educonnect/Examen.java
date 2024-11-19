package sv.edu.catolica.educonnect;

import java.util.Date;

public class Examen extends Actividad{
    private double calificacion;
    private String comentarios;

    public Examen(String titulo, String descripcion, Date fechaInicio, Date fechaFin, double calificacion, String comentarios) {
        super(titulo, descripcion, fechaInicio, fechaFin, TipoActividad.EXAMEN, null); // Pass TipoActividad.EXAMEN

        // Validation and initialization
        if (calificacion < 0 || calificacion > 10) {
            throw new IllegalArgumentException("La calificación debe estar entre 0 y 10.");
        }
        this.calificacion = calificacion;
        this.comentarios = comentarios != null ? comentarios : ""; // Default in case of null
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    
    //Por motivos de debugging y testeo
    @Override

    public String toString() {
        return "Examen{" +
                "titulo='" + getTitulo() + '\'' +
                ", descripcion='" + getDescripcion() + '\'' +
                ", fechaInicio=" + getFechaInicio() +
                ", fechaFin=" + getFechaFin() +
                ", actividadEstado=" + getEstadoDescripcion() +
                ", calificacion=" + calificacion +
                ", comentarios='" + comentarios + '\'' +
                '}';
    }
}

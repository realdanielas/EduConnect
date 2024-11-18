package sv.edu.catolica.educonnect;

import java.util.Date;

public class Examen extends Actividad{
    private double calificacion;
    private String comentarios;

    public Examen(String titulo, String descripcion, Date fechaInicio, Date fechaFin, ActividadEstado actividadEstado, double calificacion, String comentarios) {
        // Llamada al constructor de la clase base (Actividad) y pasando null para las listas
        super(titulo, descripcion, fechaInicio, fechaFin, actividadEstado, TipoActividad.EXAMEN, null);

        // Validacion de Calificacion
        if (calificacion < 0 || calificacion > 10) {
            throw new IllegalArgumentException("La calificación debe estar entre 0 y 10.");
        }

        // Inicializar los nuevos campos específicos de Examen
        this.calificacion = calificacion;
        this.comentarios = comentarios != null ? comentarios : ""; // Default en caso de null
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

package sv.edu.catolica.educonnect.models;

import java.util.Date;

public class Actividad {
    private String titulo;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private ActividadEstado actividadEstado;

    private TipoActividad tipoActividad; //Tipo de Actividad (Examen o Tarea)
    private Object actividad; //El objeto especifico (Examen o Tarea)

    // Constructor
    public Actividad(String titulo, String descripcion, Date fechaInicio, Date fechaFin, TipoActividad tipoActividad, Object actividad) {
        if (fechaInicio.after(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser después de la fecha de fin.");
        }
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.actividadEstado = ActividadEstado.PENDIENTE; // Valor por defecto
        this.tipoActividad = tipoActividad;
        this.actividad = actividad;

        /* Validación del tipo de actividad
        if (tipoActividad == TipoActividad.EXAMEN && !(actividad instanceof Examen)) {
            throw new IllegalArgumentException("El tipo de actividad debe ser un Examen.");
        }
        if (tipoActividad == TipoActividad.TAREA && !(actividad instanceof Tarea)) {
            throw new IllegalArgumentException("El tipo de actividad debe ser una Tarea.");
        }*/
    }
     public String getTitulo() {
        return titulo;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getDescripcion() {
        return descripcion;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public Date getFechaInicio() {
        return fechaInicio;
    }


    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    public Date getFechaFin() {
        return fechaFin;
    }


    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }


    public ActividadEstado getEstado() {
        return actividadEstado;
    }


    public void setEstado(ActividadEstado actividadEstado) {
        this.actividadEstado = actividadEstado;
    }


    public String getEstadoDescripcion() {
        return actividadEstado.getDescripcion(); // Retorna la descripcion del estado
    }


    public TipoActividad getTipoActividad() {
        return tipoActividad;
    }


    public void setTipoActividad(TipoActividad tipoActividad) {
        this.tipoActividad = tipoActividad;
    }


    public Object getActividad() {
        return actividad;
    }


    public void setActividad(Object actividad) {
        this.actividad = actividad;
    }

    //Por motivos de testeo y debugging
    @Override
    public String toString() {
        return "Actividad{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", actividadEstado=" + getEstadoDescripcion() +
                ", tipoActividad=" + tipoActividad +
                ", actividad=" + actividad +
                '}';
    }
}

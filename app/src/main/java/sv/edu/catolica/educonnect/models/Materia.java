package sv.edu.catolica.educonnect.models;

import java.util.List;

import sv.edu.catolica.educonnect.roles.Usuario;

public class Materia {
    private String nombre;
    private Integer  unidadesValorativas; //acepta valores negativos
    private List<Usuario> docentes;
    private List<Usuario> alumnos;
    private List<Actividad> actividades;

    public Materia(String nombre, Integer unidadesValorativas, List<Usuario> docentes, List<Usuario> alumnos, List<Actividad> actividades) {
        this.nombre = nombre;
        this.unidadesValorativas = unidadesValorativas;
        this.docentes = docentes;
        this.alumnos = alumnos;
        this.actividades = actividades;
    }

    public List<Usuario> getAlumnos() {
        return alumnos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getUnidadesValorativas() {
        return unidadesValorativas;
    }

    public void setUnidadesValorativas(Integer unidadesValorativas) {
        if (unidadesValorativas == null || unidadesValorativas < 0) {
            throw new IllegalArgumentException("Las unidades valorativas deben ser un número positivo o cero.");
        }
        this.unidadesValorativas = unidadesValorativas;
    }

    public List<Usuario> getDocentes() {
        return docentes;
    }

    public void setDocentes(List<Usuario> docentes) {
        this.docentes = docentes;
    }

    public void setAlumnos(List<Usuario> alumnos) {
        this.alumnos = alumnos;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }
}

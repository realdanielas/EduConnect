package sv.edu.catolica.educonnect;

import java.util.List;

import sv.edu.catolica.educonnect.models.Materia;
import sv.edu.catolica.educonnect.roles.Usuario;

public class GestionMateria {
    public void agregarAlumno(Materia materia, Usuario alumno){
        if (alumno != null && !materia.getAlumnos().contains(alumno)){
            materia.getAlumnos().add(alumno);
        }
    }

    public void eliminarAlumno(Materia materia, Usuario alumno){
        if (alumno != null ){
            materia.getAlumnos().remove(alumno);
        }
    }

    public List<Usuario> obtenerAlumnos(Materia materia){
        return materia.getAlumnos();
    }
}

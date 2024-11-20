package sv.edu.catolica.educonnect.roles;

import java.util.List;
import java.util.Date;

import sv.edu.catolica.educonnect.models.Materia;


public class UsuarioFactory {

    private static void validarCamposObligatorios(String... campos){
        for (String campo : campos){
            if (campo == null || campo.isEmpty()){
                throw new IllegalArgumentException("Todos los campos son requeridos.");
            }
        }
    }

    //Metodo factory para crear Admin
    public static Admin crearAdmin(String uid, String nombre, String email, String rol, String telefonoContacto, String areaResponsable, Boolean superAdmin){
        validarCamposObligatorios(uid, nombre, email, rol, telefonoContacto, areaResponsable);

        return new Admin(uid, nombre, email, rol, telefonoContacto, areaResponsable, superAdmin);
    }

    //Metodo factory para crear Docente
    public static Docente crearDocente(String uid, String nombre, String email, String rol, String telefonoContacto, List<Materia> materiasAsignadas, String titulo, String departamento){
        validarCamposObligatorios(uid, nombre, email, rol, telefonoContacto, departamento);
        return new Docente(uid, nombre, email, rol, telefonoContacto, materiasAsignadas, titulo, departamento);
    }

    //Metodo factory para crear Estudiante
    public static Estudiante crearEstudiante(String uid, String nombre, String email, String rol, String telefonoContacto, String carnet, List<Materia> materiasInscritas, Date fechaIngreso, Double promedioGeneral){
        validarCamposObligatorios(uid, nombre, email, rol, telefonoContacto);
        if (fechaIngreso == null){
            throw new IllegalArgumentException("La fecha de ingreso es requerida.");
        }
        return new Estudiante(uid, nombre, email, rol, telefonoContacto, carnet, materiasInscritas, fechaIngreso, promedioGeneral);
    }

    //Metodo factory para crear Padre
    public static Padre crearPadre(String uid, String nombre, String email, String rol, String telefonoContacto, List<Estudiante> hijos, String direccion, String parentesco){
        validarCamposObligatorios(uid, nombre, email, rol, telefonoContacto, direccion, parentesco);
        return new Padre(uid, nombre, email, rol, telefonoContacto, hijos, direccion, parentesco);
    }
}

package sv.edu.catolica.educonnect;

import java.util.List;
import java.util.Date;


public class UsuarioFactory{
    public static Usuario crearUsuario(RolUsuario rol, String uid, String nombre, String email, String telefonoContacto, Object args){
        switch (rol){
            case ADMIN:
                String areaResponsable = (String) args;
                Boolean superAdmin = (Boolean) args;
                return new Admin(uid, nombre, email, rol.toString(), telefonoContacto, areaResponsable, superAdmin);
            case DOCENTE:
                return new Docente(uid, nombre, email, rol.toString(), telefonoContacto, (List<Materia>) args, (String) args, (String) args);
            case ESTUDIANTE:
                return new Estudiante(uid, nombre, email, rol.toString(), telefonoContacto, (String) args, (List<Materia>) args, (Date) args, (Double) args);
            case PADRE:
                return new Padre(uid, nombre, email, rol.toString(), telefonoContacto, (List<Estudiante>) args, (String) args, (String) args);
            default:
                throw new IllegalArgumentException("Rol de usuario no válido: " + rol);
        }
    }
    private static Admin crearAdmin(String uid, String nombre, String email, String telefonoContacto, String areaResponsable, Boolean superAdmin){
        return new Admin(uid, nombre, email, RolUsuario.ADMIN.toString(), telefonoContacto, areaResponsable, superAdmin);
    }
    private static Docente crearDocente(String uid, String nombre, String email, String telefonoContacto, List<Materia> materiasAsignadas, String titulo, String departamento){
        return new Docente(uid, nombre, email, RolUsuario.DOCENTE.toString(), telefonoContacto, materiasAsignadas, titulo, departamento);
    }
    private static Estudiante crearEstudiante(String uid, String nombre, String email, String telefonoContacto, String carnet, List<Materia> materiasInscritas, Date fechaIngreso, Double promedioGeneral){
        return new Estudiante(uid, nombre, email, RolUsuario.ESTUDIANTE.toString(), telefonoContacto, carnet, materiasInscritas, fechaIngreso, promedioGeneral);
    }
    private static Padre crearPadre(String uid, String nombre, String email, String telefonoContacto, List<Estudiante> hijos, String direccion, String parentesco){
        return new Padre(uid, nombre, email, RolUsuario.PADRE.toString(), telefonoContacto, hijos, direccion, parentesco);
    }
}

package sv.edu.catolica.educonnect;

import java.util.List;

public class Padre extends Usuario{
    private List<Estudiante> hijos;
    private String direccion;
    private String parentesco;

    public Padre(String uid, String nombre, String email, String rol, String telefonoContacto, List<Estudiante> hijos, String direccion, String parentesco) {
        super(uid, nombre, email, rol, telefonoContacto);
        this.hijos = hijos;
        this.direccion = direccion;
        this.parentesco = parentesco;
    }

    public List<Estudiante> getHijos() {
        return hijos;
    }

    public void setHijos(List<Estudiante> hijos) {
        this.hijos = hijos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }
}

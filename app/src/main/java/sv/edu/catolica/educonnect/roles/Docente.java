package sv.edu.catolica.educonnect.roles;

import java.util.List;

import sv.edu.catolica.educonnect.models.Materia;

public class Docente extends Usuario{
    private List<Materia> materiasAsignadas;
    private String titulo;
    private String departamento;

    public Docente(String uid, String nombre, String email, String rol, String telefonoContacto, List<Materia> materiasAsignadas, String titulo, String departamento){
        super(uid, nombre, email, rol, telefonoContacto);
        this.materiasAsignadas = materiasAsignadas;
        this.titulo = titulo;
        this.departamento = departamento;
    }

    public List<Materia> getMateriasAsignadas() {
        return materiasAsignadas;
    }

    public void setMateriasAsignadas(List<Materia> materiasAsignadas) {
        this.materiasAsignadas = materiasAsignadas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}

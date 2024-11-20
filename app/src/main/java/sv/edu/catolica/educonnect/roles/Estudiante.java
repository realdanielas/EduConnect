package sv.edu.catolica.educonnect.roles;

import java.util.Date;
import java.util.List;

import sv.edu.catolica.educonnect.models.Materia;

public class Estudiante extends Usuario{
    private String carnet;
    private List<Materia> materiasInscritas;
    private Date fechaIngreso;
    private Double promedioGeneral;

    public Estudiante(String uid, String nombre, String email, String rol, String telefonoContacto, String carnet, List<Materia> materiasInscritas, Date fechaIngreso, Double promedioGeneral) {
        super(uid, nombre, email, rol, telefonoContacto);
        this.carnet = carnet;
        this.materiasInscritas = materiasInscritas;
        this.fechaIngreso = fechaIngreso;
        this.promedioGeneral = promedioGeneral;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public List<Materia> getMateriasInscritas() {
        return materiasInscritas;
    }

    public void setMateriasInscritas(List<Materia> materiasInscritas) {
        this.materiasInscritas = materiasInscritas;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Double getPromedioGeneral() {
        return promedioGeneral;
    }

    public void setPromedioGeneral(Double promedioGeneral) {
        this.promedioGeneral = promedioGeneral;
    }


}

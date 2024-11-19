package sv.edu.catolica.educonnect;

public class Admin extends Usuario{
    private String areaResponsable;
    private Boolean superAdmin;

    public Admin(String uid, String nombre, String email, String rol, String telefonoContacto, String areaResponsable, Boolean superAdmin) {
        super(uid, nombre, email, rol, telefonoContacto);
        this.areaResponsable = areaResponsable;
        this.superAdmin = superAdmin;
    }

    public String getAreaResponsable() {
        return areaResponsable;
    }

    public void setAreaResponsable(String areaResponsable) {
        this.areaResponsable = areaResponsable;
    }

    public Boolean getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(Boolean superAdmin) {
        this.superAdmin = superAdmin;
    }
}

package sv.edu.catolica.educonnect.roles;

public class Usuario {
    private String uid;
    private String nombre;
    private String email;
    private String rol;
    private String telefonoContacto;

    public Usuario(String uid, String nombre, String email, String rol){
        this.uid = uid;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
    }
    public Usuario(String uid, String nombre, String email, String rol, String telefonoContacto){
        this.uid = uid;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
        this.telefonoContacto = telefonoContacto;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

package sv.edu.catolica.educonnect;

public class Usuario {
    private String uid;
    private String email;
    private String rol;

    public Usuario(){    }

    public Usuario(String uid, String email, String rol){
        this.uid = uid;
        this.email = email;
        this.rol = rol;
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
}

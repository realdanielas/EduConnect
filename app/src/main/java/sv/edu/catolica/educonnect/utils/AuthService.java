package sv.edu.catolica.educonnect.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.List;

import sv.edu.catolica.educonnect.roles.Estudiante;
import sv.edu.catolica.educonnect.roles.RolUsuario;
import sv.edu.catolica.educonnect.roles.Usuario;
import sv.edu.catolica.educonnect.roles.UsuarioFactory;

public class AuthService {
    private final FirebaseAuth mAuth;

    public AuthService() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void signIn(String email, String password, AuthCallback callback) {
        if (email.isEmpty() || password.isEmpty()) {
            callback.onFailure("Por favor llenar todos los campos");
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callback.onSuccess(mAuth.getCurrentUser());
            } else {
                String errorMessage = task.getException() != null ? task.getException().getMessage() : "Error desconocido";
                callback.onFailure("Inicio de Sesión fallido: " + errorMessage);
            }
        });
    }

    public void signUp(String email, String password, String nombre, String rol, String telefonoContacto, String areaResponsable, Boolean superAdmin, String departamento, Date fechaIngreso, String direccion, String parentesco, AuthCallback callback) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    try{
                        //Usar Factory para crear el usuario apropiado
                        Usuario newUser = createUserByRole(user.getUid(), nombre, email, rol, telefonoContacto, areaResponsable, superAdmin, departamento, fechaIngreso, direccion, parentesco);

                        //Guarda el usuario en FireStore
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        db.collection("users").document(user.getUid()).set(newUser)
                                .addOnSuccessListener(aVoid -> callback.onSuccess(user))
                                .addOnFailureListener(e -> callback.onFailure("Error al guardar datos del usuario: " + e.getMessage()));
                    } catch (IllegalArgumentException e) {
                        callback.onFailure(e.getMessage());
                    }
                } else {
                    callback.onFailure("Error: usuario no autenticado.");
                }
            } else {
                String errorMessage = task.getException() != null ? task.getException().getMessage() : "Error desconocido";
                callback.onFailure("Error al crear el usuario: " + errorMessage);
            }
        });
    }

    private Usuario createUserByRole(String uid, String nombre, String email, String rol, String telefonoContacto, String areaResponsable, Boolean superAdmin, String departamento, Date fechaIngreso, String direccion, String parentesco) {
        switch (rol) {
            case "Admin":
                return UsuarioFactory.crearAdmin(uid, nombre, email, rol, telefonoContacto, areaResponsable, superAdmin);
            case "Docente":
                return UsuarioFactory.crearDocente(uid, nombre, email, rol, telefonoContacto, null, null, departamento);
            case "Estudiante":
                return UsuarioFactory.crearEstudiante(uid, nombre, email, rol, telefonoContacto, null, null, fechaIngreso, null);
            case "Padre":
                return UsuarioFactory.crearPadre(uid, nombre, email, rol, telefonoContacto, null, direccion, parentesco);
            default:
                throw new IllegalArgumentException("Rol de usuario no válido: " + rol);
        }
    }

    public void getUser(String uid, UserCallback callback) {
       FirebaseFirestore db = FirebaseFirestore.getInstance();
       db.collection("users").document(uid).get()
               .addOnSuccessListener(documentSnapshot -> {
                   if (documentSnapshot.exists()){
                       String nombre = documentSnapshot.getString("nombre");
                       String email = documentSnapshot.getString("email");
                       String rol = documentSnapshot.getString("rol");
                       String telefonoContacto = documentSnapshot.getString("telefonoContacto");

                       try{
                           Usuario user = createUserFromFirestore(uid, nombre, email, rol, telefonoContacto);
                           callback.onSuccess(user);
                       } catch (IllegalArgumentException e){
                           callback.onFailure(e.getMessage());
                       }
                   } else {
                       callback.onFailure("Usuario no encontrado");
                   }
               }).addOnFailureListener(e -> callback.onFailure("Error al obtener usuario: " + e.getMessage()));
    }

    private Usuario createUserFromFirestore(String uid, String nombre, String email, String rol, String telefonoContacto){
        switch (rol){
            case "Admin":
                return UsuarioFactory.crearAdmin(uid, nombre, email, rol, telefonoContacto, "", null);
            case "Docente":
                return UsuarioFactory.crearDocente(uid, nombre, email, rol, telefonoContacto, null, null, "");
            case "Estudiante":
                return UsuarioFactory.crearEstudiante(uid, nombre, email, rol, telefonoContacto, null, null, null, null);
            case "Padre":
                return UsuarioFactory.crearPadre(uid, nombre, email, rol, telefonoContacto, null, null, null);
            default:
                throw new IllegalArgumentException("Rol de usuario no válido: " + rol);
        }
    }

    public interface AuthCallback {
        void onSuccess(FirebaseUser user);
        void onFailure(String errorMessage);
    }

    public interface UserCallback {
        void onSuccess(Usuario user);
        void onFailure(String errorMessage);
    }
}

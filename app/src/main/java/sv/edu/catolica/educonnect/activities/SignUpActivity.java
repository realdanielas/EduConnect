package sv.edu.catolica.educonnect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private EditText nombreET, correoET, passwordET;
    private String rolSeleccionado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        nombreET = findViewById(R.id.NombreET);
        correoET = findViewById(R.id.CorreoET);
        passwordET = findViewById(R.id.PasswordET);

        // Botones de seleccion de rol
        findViewById(R.id.docente_btn).setOnClickListener(view -> selectRole("Docente"));
        findViewById(R.id.estudiante_btn).setOnClickListener(view -> selectRole("Estudiante"));
        findViewById(R.id.padre_btn).setOnClickListener(view -> selectRole("Padre"));

        // Boton para regresar al sign in
        findViewById(R.id.regresarInicio_btn).setOnClickListener(view -> {
            Toast.makeText(SignUpActivity.this, "Regresando al inicio...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
            // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        // Boton para crear cuenta
        findViewById(R.id.crearUsu_btn).setOnClickListener(view -> createAccount());
    }

    private void selectRole(String role) {
        rolSeleccionado = role;
        Toast.makeText(this, "Rol seleccionado: " + role, Toast.LENGTH_SHORT).show();
    }

    private void createAccount() {
        String nombre = nombreET.getText().toString();
        String email = correoET.getText().toString();
        String pass = passwordET.getText().toString();

        if (validateInputs(nombre, email, pass)) {
            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user != null) {
                        // Crea el objeto usuario
                        Usuario newUser = new Usuario(user.getUid(), nombre, email, rolSeleccionado);

                        // guarda la data en firestore
                        db.collection("users").document(user.getUid()).set(newUser)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(SignUpActivity.this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show();
                                    // Redirect to sign-in
                                    startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                                    finish();
                                })
                                .addOnFailureListener(e -> Toast.makeText(SignUpActivity.this, "Error al guardar datos del usuario: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                    } else {
                        Toast.makeText(SignUpActivity.this, "Error: usuario no autenticado.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignUpActivity.this, "Error al crear el usuario: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean validateInputs(String nombre, String email, String pass) {
        if (nombre.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Por favor llenar todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (rolSeleccionado.isEmpty()) {
            Toast.makeText(this, "Por favor seleccionar un rol", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}

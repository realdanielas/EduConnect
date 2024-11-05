package sv.edu.catolica.educonnect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText nombreET, correoET, passwordET;
    private String rolSeleccionado = "";

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        mAuth = FirebaseAuth.getInstance();
        nombreET = findViewById(R.id.NombreET);
        correoET = findViewById(R.id.CorreoET);
        passwordET = findViewById(R.id.PasswordET);

        //Botones para seleccion de rol
        findViewById(R.id.docente_btn).setOnClickListener(view -> selectRole("Docente"));
        findViewById(R.id.estudiante_btn).setOnClickListener(view -> selectRole("Estudiante"));
        findViewById(R.id.padre_btn).setOnClickListener(view -> selectRole("Padre"));

        //boton regresar a inicio
        findViewById(R.id.regresarInicio_btn).setOnClickListener(view -> {
            startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            finish();
        });

        //boton crear
        findViewById(R.id.crearUsu_btn).setOnClickListener(view -> createAccount());
    }

    private void selectRole(String role){
        rolSeleccionado = role;
        Toast.makeText(this, "Rol seleccionado: " + role, Toast.LENGTH_SHORT).show();
    }

    private void createAccount(){
        String nombre = nombreET.getText().toString();
        String email = correoET.getText().toString();
        String pass = passwordET.getText().toString();

        if (validateInputs(nombre, email, pass)){
            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(SignUpActivity.this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignUpActivity.this, "Error al crear usuario: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean validateInputs(String nombre, String email, String pass){
        if (nombre.isEmpty() || email.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "Por favor llenar todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (rolSeleccionado.isEmpty()){
            Toast.makeText(this, "Por favor seleccionar un rol", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
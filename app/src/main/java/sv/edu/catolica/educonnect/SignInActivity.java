package sv.edu.catolica.educonnect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText emailET, passET;
    private TextView registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        mAuth = FirebaseAuth.getInstance();
        emailET = findViewById(R.id.CorreoET);
        passET = findViewById(R.id.PasswordET);
        registrar = findViewById(R.id.registrarseTextView);

        registrar.setOnClickListener(view -> {
            startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            finish();
        });
        findViewById(R.id.LoginButton).setOnClickListener(view -> signInUser());
    }

    private void signInUser(){
        String email = emailET.getText().toString();
        String pass = passET.getText().toString();

        if (email.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "Por favor llenar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()){
                FirebaseUser user = mAuth.getCurrentUser();
                Toast.makeText(SignInActivity.this, "Inicio de Sesión correcto", Toast.LENGTH_SHORT).show();
                //Redirigir a main
            } else {
                Toast.makeText(SignInActivity.this, "Inicio de Sesión fallido: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

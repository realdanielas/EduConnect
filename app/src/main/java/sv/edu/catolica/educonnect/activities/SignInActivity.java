package sv.edu.catolica.educonnect.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import sv.edu.catolica.educonnect.R;
import sv.edu.catolica.educonnect.utils.AuthService;

public class SignInActivity extends AppCompatActivity {
    private EditText emailET, passET;
    private ProgressBar progressBar;
    private AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        //Inicializa Firebase Auth y AuthService
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        authService = new AuthService();

        //Inicializa componentes UI
        emailET = findViewById(R.id.CorreoET);
        passET = findViewById(R.id.PasswordET);
        progressBar = findViewById(R.id.progressBar);
        TextView registrar = findViewById(R.id.registrarseTextView);

        //Revisa si el usuario ya está autenticado
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            //Si el usuario ya está autenticado, redirige a la actividad HomeActivity
            startActivity(new Intent(SignInActivity.this, HomeActivity.class));
            finish();
        }

        //Configurar el boton para registrarse
        registrar.setOnClickListener(view -> {
            startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            finish();
        });

        //Configurar el boton para iniciar sesión
        findViewById(R.id.LoginButton).setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            signInUser();
        });
    }

    private void signInUser() {
        String email = emailET.getText().toString();
        String password = passET.getText().toString();

        //Iniciar sesión con AuthService
        authService.signIn(email, password, new AuthService.AuthCallback() {
            @Override
            public void onSuccess(FirebaseUser user) {
                //Si el inicio de sesión es exitoso, redirige a la actividad HomeActivity
                progressBar.setVisibility(View.GONE);
                Toast.makeText(SignInActivity.this, "Inicio de Sesión correcto", Toast.LENGTH_SHORT).show();
                //Redirecciona a HomeActivity
                startActivity(new Intent(SignInActivity.this, HomeActivity.class));
                finish();
            }

            @Override
            public void onFailure(String errorMessage) {
                //Si el inicio de sesión falla, muestra un mensaje de error
                progressBar.setVisibility(View.GONE);
                Toast.makeText(SignInActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

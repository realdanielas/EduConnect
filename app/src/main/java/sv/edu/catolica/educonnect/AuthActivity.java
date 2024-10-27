package sv.edu.catolica.educonnect;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Inicializa Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        email = ((EditText) findViewById(R.id.editTextEmailAddress)).getText().toString();
        pass = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Revisa si el usuario ya esta logueado
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    public void crearCuenta(View view){
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //Logueo exitoso
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    //falta crear metodo Update UI
                    //updateUI(user);
                } else {
                    //Logueo fallido
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(AuthActivity.this, "Autenticacion Fallida.", Toast.LENGTH_SHORT).show();
                    //falta crear metodo Update UI
                    //updateUI(null);
                }
            }
        });
    }

    public void iniciarSesion(View view){
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Inicio de sesion exitoso
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //falta crear metodo Update UI
                            // updateUI(user);
                        } else {
                            // Inicio de sesion fallido
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(AuthActivity.this, "Autenticacion Fallida.",
                                    Toast.LENGTH_SHORT).show();
                            //falta crear metodo Update UI
                            // updateUI(null);
                        }
                    }
                });
    }


}

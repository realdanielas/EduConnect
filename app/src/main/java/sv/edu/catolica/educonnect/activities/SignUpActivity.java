package sv.edu.catolica.educonnect.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import sv.edu.catolica.educonnect.R;
import sv.edu.catolica.educonnect.utils.AuthService;

public class SignUpActivity extends AppCompatActivity {
    private AuthService authService;
    private String rolSeleccionado = "";

    //Campos comunes
    private EditText nombreET, correoET, passwordET, telefonoContactoET;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up); //Layout inicial de seleccion de roles

        //Inicializa Firebase Auth y AuthService
        authService = new AuthService();

        //Inicializar botones
        Button docenteBtn = findViewById(R.id.docente_btn);
        Button estudianteBtn = findViewById(R.id.estudiante_btn);
        Button padreBtn = findViewById(R.id.padre_btn);
        Button regresarInicioBtn = findViewById(R.id.regresarInicio_btn);

        //Logica de seleccion de rol
        docenteBtn.setOnClickListener(v -> {
            rolSeleccionado = "Docente";
            setContentView(R.layout.sign_up_docente);
            initializeDocenteUI();
        });
        estudianteBtn.setOnClickListener(v -> {
            rolSeleccionado = "Estudiante";
            setContentView(R.layout.sign_up_estudiante);
            initializeEstudianteUI();
        });
        padreBtn.setOnClickListener(v -> {
            rolSeleccionado = "Padre";
            setContentView(R.layout.sign_up_padre);
            initializePadreUI();
        });
        regresarInicioBtn.setOnClickListener(v -> setContentView(R.layout.sign_in));
    }

    private void initializeDocenteUI(){
        //inicializa campos comunes
        initializeCamposComunes();
        Button crearUsuBtn = findViewById(R.id.crearUsu_btn);
        crearUsuBtn.setOnClickListener(v -> createAccount("Docente"));
        Button regresarInicioBtn = findViewById(R.id.regresarInicio_btn);
        regresarInicioBtn.setOnClickListener(v -> setContentView(R.layout.sign_up));
    }

    private void initializeEstudianteUI(){
        //inicializa campos comunes
        initializeCamposComunes();
        Button crearUsuBtn = findViewById(R.id.crearUsu_btn);
        crearUsuBtn.setOnClickListener(v -> createAccount("Estudiante"));
        Button regresarInicioBtn = findViewById(R.id.regresarInicio_btn);
        regresarInicioBtn.setOnClickListener(v -> setContentView(R.layout.sign_up));
    }

    private void initializePadreUI(){
        //inicializa campos comunes
        initializeCamposComunes();
        Button crearUsuBtn = findViewById(R.id.crearUsu_btn);
        crearUsuBtn.setOnClickListener(v -> createAccount("Padre"));
        Button regresarInicioBtn = findViewById(R.id.regresarInicio_btn);
        regresarInicioBtn.setOnClickListener(v -> setContentView(R.layout.sign_up));
    }

    private void initializeCamposComunes(){
        nombreET = findViewById(R.id.NombreET);
        correoET = findViewById(R.id.CorreoET);
        passwordET = findViewById(R.id.PasswordET);
        telefonoContactoET = findViewById(R.id.telefonoContactoET);
    }

    private void createAccount(String rol){
        String nombre = nombreET.getText().toString();
        String correo = correoET.getText().toString();
        String pass = passwordET.getText().toString();
        String telefonoContacto = telefonoContactoET.getText().toString();

        if(validarInputs(nombre, correo, pass, telefonoContacto)){
            //Recolectar inputs adicionales segun rol
            Boolean superAdmin = null;
            String areaResponsable = null;
            String departamento = null;
            String direccion = null;
            String parentesco = null;
            Date fechaIngreso = null;

            switch(rol){
                case "Docente":
                    departamento = ((EditText) findViewById(R.id.departamentoET)).getText().toString();
                    break;
                case "Estudiante":
                    String fechaIngresoStr = ((EditText) findViewById(R.id.fechaIngresoET)).getText().toString();
                    try{
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        fechaIngreso = sdf.parse(fechaIngresoStr);
                    } catch (ParseException e){
                        Toast.makeText(this, "Fecha Invalida. Formato: dd/MM/yyyy", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    break;
                case "Padre":
                    direccion = ((EditText) findViewById(R.id.direccionET)).getText().toString();
                    parentesco = ((EditText) findViewById(R.id.parentescoET)).getText().toString();
                    break;
            }

            //Llamar al metodo Sign-Up de AuthService
            authService.signUp(correo, pass, nombre, rolSeleccionado, telefonoContacto, areaResponsable, superAdmin, departamento, fechaIngreso, direccion, parentesco, new AuthService.AuthCallback() {
                @Override
                public void onSuccess(FirebaseUser user) {
                    Toast.makeText(SignUpActivity.this, "Usuario creado exitosamente.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
                    finish();
                }

                @Override
                public void onFailure(String errorMessage) {
                    Toast.makeText(SignUpActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
    private boolean validarInputs(String nombre, String email, String pass, String telefonoContacto){
        if(nombre.isEmpty() || email.isEmpty() || pass.isEmpty() || telefonoContacto.isEmpty()){
            Toast.makeText(this, "Por favor llenar todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (rolSeleccionado.isEmpty()){
            Toast.makeText(this, "Por favor seleccionar un rol.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}


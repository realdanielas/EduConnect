package sv.edu.catolica.educonnect;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private TextView saludo;
    private CalendarView calendario;
    private List<Actividad> actividades;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        saludo = findViewById(R.id.saludo);
        calendario = findViewById(R.id.calendario);

        //Usuario ejemplo (QUITAR)
        String nombreUsuario = "Usuario";
        saludo.setText("Hola, " + nombreUsuario);

        //inicializacion de lista
        actividades = new ArrayList<>();
        cargarActividades();

        // Listener para calentario
        calendario.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            Date fechaSeleccionada = new Date(year - 1900, month, dayOfMonth); // Adjust year for Date
            //mostrarActividadesParaFecha(fechaSeleccionada);
        });
    }

    private void cargarActividades(){
        //Actividad Ejemplo
        actividades.add(new Tarea("Tarea 1", "Descripcion de la Tarea", new Date(124, 1, 5), new Date(124, 9, 5), ActividadEstado.COMPLETADO, 8.5, null, null));

        actividades.add(new Examen("Primer Examen", "Primer examen corto", new Date(124, 1, 5), new Date(124, 9, 5), ActividadEstado.PENDIENTE, 9.5, null));
    }

    /*private void mostrarActividadesParaFecha(){
        //Limpiar campos
        TextView materia = findViewById(R.id.materia);
        TextView titulo = findViewById(R.id.titulo);
        TextView fechaFin = findViewById(R.id.fechaEntrega);
        TextView descripcion = findViewById(R.id.descripcion);

        materia.setText("");
        titulo.setText("");
        fechaFin.setText("");
        descripcion.setText("");
        //falty
        for(Actividad actividad : actividades){
            if(isSameDay(actividad.getFechaInicio(), date) || isSameDay(actividad.getFechaFin(), date)){
                //acttualizar CardView con detalles de actividad
                if(actividad instanceof Tarea){
                    materia.setText("Tarea: " + materia.getNombre());


                }
            }
        }
    }*/

}

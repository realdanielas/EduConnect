package sv.edu.catolica.educonnect;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private TextView saludoTV;
    private RecyclerView actividadesRV;
    private ActividadAdaptador actividadAdaptador;
    private List<Actividad> actividades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        // Inicializar los componentes de la interfaz
        saludoTV = findViewById(R.id.saludoTV);
        CalendarView calendario = findViewById(R.id.calendario);
        actividadesRV = findViewById(R.id.actividadesRV);

        //Seteando el RecyclerView
        actividadesRV.setLayoutManager(new LinearLayoutManager(this));
        actividades = new ArrayList<>();
        actividadAdaptador = new ActividadAdaptador(actividades);
        actividadesRV.setAdapter(actividadAdaptador);

        // Usuario de ejemplo (QUITAR EN PRODUCCIÓN)
        String nombreUsuario = "Usuario";
        saludoTV.setText("Hola, " + nombreUsuario);

        // Cargar Actividades
        cargarActividades();

        // Listener para el calendario
        calendario.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            Date fechaSeleccionada = calendar.getTime();
            mostrarActividadesParaFecha(fechaSeleccionada);
        });
    }

    private void cargarActividades() {

        // Actividades de ejemplo
        actividades.add(new Tarea("Tarea 1", "Descripción de la Tarea",
                new Date(124, 9, 1), // 1 de octubre de 2024
                new Date(124, 9, 5), // 5 de octubre de 2024
                8.5, null, null));

        actividades.add(new Examen("Primer Examen", "Primer examen corto",
                new Date(124, 9, 5), // 5 de octubre de 2024
                new Date(124, 9, 5), // 5 de octubre de 2024
                9.5, null));

        //Notificar al adaptador del cambio de data
        actividadAdaptador.notifyDataSetChanged();
    }

    private void mostrarActividadesParaFecha(Date fecha) {
        //Limpia la lista actual de actividades
        List<Actividad> actividadesFiltradas = new ArrayList<>();

        for (Actividad actividad : actividades) {
            if (esMismoDia(actividad.getFechaInicio(), fecha) || esMismoDia(actividad.getFechaFin(), fecha)) {
                actividadesFiltradas.add(actividad); // Agregar actividades matching
            }
        }

        //Actualizar el adaptador con la fecha filtrada
        actividadAdaptador.setActividades(actividadesFiltradas);
        actividadAdaptador.notifyDataSetChanged();
    }

    private boolean esMismoDia(Date fecha1, Date fecha2) {
        // Comparar dos objetos Date para ver si son el mismo día
        return fecha1.getYear() == fecha2.getYear() && fecha1.getMonth() == fecha2.getMonth() && fecha1.getDate() == fecha2.getDate();
    }
}
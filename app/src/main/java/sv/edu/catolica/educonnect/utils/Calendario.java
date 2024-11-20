package sv.edu.catolica.educonnect.utils;

import android.os.Bundle;
import android.widget.CalendarView;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.catolica.educonnect.R;

public class Calendario extends AppCompatActivity {
    CalendarView calendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        calendario = findViewById(R.id.calendario);

        /*calendario.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String Fecha = dayOfMonth + "-" + (month + 1) + "-" + year;
        });*/
        calendario.getDate();


    }
}

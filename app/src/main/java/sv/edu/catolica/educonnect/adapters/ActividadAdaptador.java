package sv.edu.catolica.educonnect;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ActividadAdaptador extends RecyclerView.Adapter<ActividadAdaptador.ActividadViewHolder> {
    private List<Actividad> actividades;

    public ActividadAdaptador(List<Actividad> actividades){
        this.actividades = actividades;
    }

    public void setActividades(List<Actividad> actividades){
        this.actividades = actividades;
    }

    @NonNull
    @Override
    public ActividadAdaptador.ActividadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.actividad_item, parent, false);
        return new ActividadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActividadViewHolder holder, int position) {
        Actividad actividad = actividades.get(position);
        holder.tituloTV.setText(actividad.getTitulo());
        holder.descripcionTV.setText(actividad.getDescripcion());
        holder.fechaEntregaTV.setText(String.format("Fecha: %s", actividad.getFechaFin()));
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM YY", Locale.getDefault());
        holder.fechaEntregaTV.setText(String.format("Fecha: %s", sdf.format(actividad.getFechaFin())));
    }

    @Override
    public int getItemCount() {
        return actividades.size();
    }

    static class ActividadViewHolder extends RecyclerView.ViewHolder{
        TextView tituloTV, descripcionTV, fechaEntregaTV;

        public ActividadViewHolder(@NonNull View itemView){
            super(itemView);
            tituloTV = itemView.findViewById(R.id.tituloTV);
            descripcionTV = itemView.findViewById(R.id.descripcionTV);
            fechaEntregaTV = itemView.findViewById(R.id.fechaEntregaTV);
        }
    }
}

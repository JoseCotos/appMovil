package com.upc.miappposta.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.upc.miappposta.R;
import com.upc.miappposta.SeleccionHorarioActivity;
import com.upc.miappposta.entidad.EspecialidadJava;

import java.util.List;

public class EspecialidadesAdapter extends RecyclerView.Adapter<EspecialidadesAdapter.ViewHolder> {

    private final List<EspecialidadJava> especialidadList;
    private final Context context;

    public EspecialidadesAdapter(Context context, List<EspecialidadJava> especialidadList) {
        this.context = context;
        this.especialidadList = especialidadList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_especialidad, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EspecialidadJava especialidad = especialidadList.get(position);

        holder.tvNombre.setText(especialidad.getNombre());

        holder.ivIcono.setImageResource(especialidad.getIconoResId());


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, SeleccionHorarioActivity.class);
            intent.putExtra("NOMBRE_ESPECIALIDAD", especialidad.getNombre());
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return especialidadList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        ImageView ivIcono;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombre = itemView.findViewById(R.id.tvEspecialidadNombre);

            ivIcono = itemView.findViewById(R.id.ivEspecialidadIcono);
        }
    }
}
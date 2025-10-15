package com.upc.miappposta.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.upc.miappposta.R;
import com.upc.miappposta.entidad.Horario;

import java.util.List;

public class HorariosAdapter extends RecyclerView.Adapter<HorariosAdapter.ViewHolder> {

    private final List<Horario> horarioList;
    private final Context context;
    private final OnHorarioSelectedListener listener;

    // Colores definidos para los estados visuales (opcional, pero ayuda a la legibilidad)
    private static final int COLOR_SELECTED = Color.parseColor("#A9A9A9");
    private static final int COLOR_AVAILABLE = Color.parseColor("#ECECEC");
    private static final int COLOR_NOT_AVAILABLE = Color.parseColor("#F0F0F0");

    public HorariosAdapter(Context context, List<Horario> horarioList, OnHorarioSelectedListener listener) {
        this.context = context;
        this.horarioList = horarioList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_horario, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Horario horario = horarioList.get(position);
        holder.tvHora.setText(horario.getHora());

        if (!horario.isDisponible()) {

            holder.tvDisponibilidad.setVisibility(View.VISIBLE);
            holder.cardHorario.setCardBackgroundColor(COLOR_NOT_AVAILABLE);
            holder.itemView.setClickable(false);
            holder.tvHora.setTextColor(Color.DKGRAY);
        } else {

            holder.tvDisponibilidad.setVisibility(View.GONE);
            holder.itemView.setClickable(true);

            if (horario.isSelected()) {
                holder.cardHorario.setCardBackgroundColor(COLOR_SELECTED);
                holder.tvHora.setTextColor(Color.WHITE);
            } else {

                holder.cardHorario.setCardBackgroundColor(COLOR_AVAILABLE);
                holder.tvHora.setTextColor(Color.BLACK);
            }

            holder.itemView.setOnClickListener(v -> listener.onHorarioSelected(position));
        }
    }

    @Override
    public int getItemCount() {
        return horarioList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardHorario;
        TextView tvHora;
        TextView tvDisponibilidad;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardHorario = itemView.findViewById(R.id.cardHorario);
            tvHora = itemView.findViewById(R.id.tvHora);
            tvDisponibilidad = itemView.findViewById(R.id.tvDisponibilidad);
        }
    }
}
package com.upc.miappposta;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.upc.miappposta.adapters.HorariosAdapter;
import com.upc.miappposta.adapters.OnHorarioSelectedListener;
import com.upc.miappposta.entidad.Horario;

import java.util.ArrayList;
import java.util.List;

public class SeleccionHorarioActivity extends AppCompatActivity implements OnHorarioSelectedListener {

    private RecyclerView rvHorarios;
    private HorariosAdapter adapter;
    private List<Horario> horarioList;
    private Button btnSeleccionar;
    private int selectedPosition = RecyclerView.NO_POSITION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_horario);

        rvHorarios = findViewById(R.id.rvHorarios);
        btnSeleccionar = findViewById(R.id.btnSeleccionar);
        TextView btnRegresar = findViewById(R.id.btnRegresar);

        cargarHorarios();

        rvHorarios.setLayoutManager(new GridLayoutManager(this, 3));

        adapter = new HorariosAdapter(this, horarioList, this);
        rvHorarios.setAdapter(adapter);

        btnRegresar.setOnClickListener(v -> finish());

        btnSeleccionar.setOnClickListener(v -> {
            if (selectedPosition != RecyclerView.NO_POSITION) {
                Horario horarioSeleccionado = horarioList.get(selectedPosition);
                Toast.makeText(this, "Cita seleccionada: " + horarioSeleccionado.getHora(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onHorarioSelected(int position) {
        if (selectedPosition != RecyclerView.NO_POSITION) {
            horarioList.get(selectedPosition).setSelected(false);
            adapter.notifyItemChanged(selectedPosition);
        }

        horarioList.get(position).setSelected(true);
        selectedPosition = position;
        adapter.notifyItemChanged(selectedPosition);

        btnSeleccionar.setVisibility(View.VISIBLE);
    }

    private void cargarHorarios() {
        horarioList = new ArrayList<>();

        horarioList.add(new Horario("8 AM", true));
        horarioList.add(new Horario("9 AM", true));
        horarioList.add(new Horario("10 AM", true));

        horarioList.add(new Horario("11 AM", true));
        horarioList.add(new Horario("12 PM", true));
        horarioList.add(new Horario("2 PM", false));

        horarioList.add(new Horario("3 PM", false));
        horarioList.add(new Horario("4 PM", true));
        horarioList.add(new Horario("5 PM", true));
    }
}
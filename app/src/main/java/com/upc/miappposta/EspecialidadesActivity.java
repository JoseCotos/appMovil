package com.upc.miappposta;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.upc.miappposta.adapters.EspecialidadesAdapter;
import com.upc.miappposta.entidad.EspecialidadJava;

import java.util.ArrayList;
import java.util.List;

public class EspecialidadesActivity extends AppCompatActivity {

    private RecyclerView rvEspecialidades;
    private EspecialidadesAdapter adapter;
    private List<EspecialidadJava> especialidadList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_especialidades);

        rvEspecialidades = findViewById(R.id.rvEspecialidades);

        cargarEspecialidades();

        rvEspecialidades.setLayoutManager(new GridLayoutManager(this, 2));


        adapter = new EspecialidadesAdapter(this, especialidadList);
        rvEspecialidades.setAdapter(adapter);
    }

    private void cargarEspecialidades() {
        especialidadList = new ArrayList<>();
        especialidadList.add(new EspecialidadJava("Medicina General", 1, R.drawable.mgeneral));
        especialidadList.add(new EspecialidadJava("Pediatría", 2, R.drawable.pediatria));
        especialidadList.add(new EspecialidadJava("Cardiología", 3, R.drawable.cardiologia));

        especialidadList.add(new EspecialidadJava("Neumología", 4, R.drawable.neumologia));
        especialidadList.add(new EspecialidadJava("Nefrología", 5, R.drawable.nefrologia));
        especialidadList.add(new EspecialidadJava("Endocrinología", 6, R.drawable.endocrinologia));

    }
}
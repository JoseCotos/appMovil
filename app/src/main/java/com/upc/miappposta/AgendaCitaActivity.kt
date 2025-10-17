package com.upc.miappposta

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AgendaCitaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agenda_cita)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnMedico = findViewById<android.widget.ImageButton>(R.id.btnMedico)
        val btnEspecialidad = findViewById<android.widget.ImageButton>(R.id.btnEspecialidad)

        btnMedico.setOnClickListener {
            Toast.makeText(this, "Seleccionaste Médico", Toast.LENGTH_SHORT).show()
            // Aquí podrías abrir otra pantalla:
            // startActivity(Intent(this, MedicoActivity::class.java))
        }

        btnEspecialidad.setOnClickListener {
            Toast.makeText(this, "Seleccionaste Especialidad", Toast.LENGTH_SHORT).show()
             startActivity(Intent(this, PacienteActivity::class.java))
        }
    }
}

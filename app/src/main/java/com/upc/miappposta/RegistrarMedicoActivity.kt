package com.upc.miappposta

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.upc.miappposta.util.SQLiteHelper

class RegistrarMedicoActivity : AppCompatActivity() {

    private lateinit var db: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_medico)

        db = SQLiteHelper(this)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etApellido = findViewById<EditText>(R.id.etApellido)
        val etTelefono = findViewById<EditText>(R.id.etTelefono)
        val etIdEspecialidad = findViewById<EditText>(R.id.etIdEspecialidad)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarMedico)

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val apellido = etApellido.text.toString()
            val telefono = etTelefono.text.toString()
            val idEspecialidad = etIdEspecialidad.text.toString().toIntOrNull() ?: 1

            db.insertarMedico(nombre, apellido, telefono, idEspecialidad)
            Toast.makeText(this, "Médico registrado", Toast.LENGTH_SHORT).show()
            finish() // cerrar Activity y volver a la lista
        }
    }
}

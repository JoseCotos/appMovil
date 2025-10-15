package com.upc.miappposta

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.upc.miappposta.databinding.ActivityPacienteBinding
import com.upc.miappposta.entidad.Paciente
import com.upc.miappposta.service.PacienteService

class PacienteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPacienteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        grabarPaciente()
    }

    private fun grabarPaciente(){
        binding.btnRegistrarPaciente.setOnClickListener {
            val tipoDocumento = binding.txtTipoDoc.text.toString()
            val numeroDocumento = binding.txtNroDocumento.text.toString()
            val nombre = binding.txtNombre.text.toString()
            val apellido = binding.txtApellido.text.toString()
            val correo = binding.txtCorreo.text.toString()
            val telefono = binding.txtTelefono.text.toString()
            val direccion = binding.txtDireccion.text.toString()

            if (tipoDocumento.isNotEmpty() && numeroDocumento.isNotEmpty() && nombre.isNotEmpty() &&
                apellido.isNotEmpty() && correo.isNotEmpty() && telefono.isNotEmpty() && direccion.isNotEmpty()) {
                val paciente = Paciente(0,tipoDocumento, numeroDocumento,
                    nombre, apellido, correo, telefono, direccion)
                mostrarMensaje(PacienteService(this).registrarPaciente(paciente))
            } else {
                mostrarMensaje("Por favor, complete todos los campos")
            }
        }
    }

    private fun mostrarMensaje(mensaje: String) {
        val ventana = AlertDialog.Builder(this)
        ventana.setTitle("Mensaje")
        ventana.setMessage(mensaje)
        ventana.setPositiveButton("Aceptar", null)
        ventana.create().show()
    }
}
package com.upc.miappposta

import android.os.Bundle
import android.util.Patterns
import android.widget.ArrayAdapter
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

        // 🔹 Inicializa el binding correctamente
        binding = ActivityPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ajusta márgenes del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 🔹 Carga los valores del Spinner
        configurarSpinner()

        // 🔹 Configura la acción del botón
        grabarPaciente()
    }

    private fun configurarSpinner() {
        val tiposDocumento = resources.getStringArray(R.array.tipos_documento)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tiposDocumento)
        binding.spTipoDocumento.adapter = adapter
    }

    private fun grabarPaciente() {
        binding.btnRegistrarPaciente.setOnClickListener {
            val tipoDocumento = binding.spTipoDocumento.selectedItem.toString()
            val numeroDocumento = binding.txtNroDocumento.text.toString().trim()
            val nombre = binding.txtNombre.text.toString().trim()
            val apellido = binding.txtApellido.text.toString().trim()
            val correo = binding.txtCorreo.text.toString().trim()
            val telefono = binding.txtTelefono.text.toString().trim()
            val direccion = binding.txtDireccion.text.toString().trim()

            // 🔹 Validaciones
            when {
                tipoDocumento.isEmpty() -> mostrarMensaje("Seleccione un tipo de documento")
                numeroDocumento.isEmpty() -> mostrarMensaje("Ingrese el número de documento")
                nombre.isEmpty() -> mostrarMensaje("Ingrese el nombre del paciente")
                apellido.isEmpty() -> mostrarMensaje("Ingrese el apellido del paciente")
                correo.isEmpty() -> mostrarMensaje("Ingrese el correo electrónico")
                !Patterns.EMAIL_ADDRESS.matcher(correo).matches() -> mostrarMensaje("Correo no válido")
                telefono.isEmpty() -> mostrarMensaje("Ingrese el teléfono")
                !telefono.all { it.isDigit() } -> mostrarMensaje("El teléfono solo debe contener números")
                direccion.isEmpty() -> mostrarMensaje("Ingrese la dirección")
                else -> {
                    val paciente = Paciente(
                        0,
                        tipoDocumento,
                        numeroDocumento,
                        nombre,
                        apellido,
                        correo,
                        telefono,
                        direccion
                    )
                    val resultado = PacienteService(this).registrarPaciente(paciente)
                    mostrarMensaje(resultado)
                    limpiarCampos()
                }
            }
        }
    }

    private fun limpiarCampos() {
        binding.txtNroDocumento.setText("")
        binding.txtNombre.setText("")
        binding.txtApellido.setText("")
        binding.txtCorreo.setText("")
        binding.txtTelefono.setText("")
        binding.txtDireccion.setText("")
        binding.spTipoDocumento.setSelection(0)
    }

    private fun mostrarMensaje(mensaje: String) {
        AlertDialog.Builder(this)
            .setTitle("Mensaje")
            .setMessage(mensaje)
            .setPositiveButton("Aceptar", null)
            .show()
    }
}

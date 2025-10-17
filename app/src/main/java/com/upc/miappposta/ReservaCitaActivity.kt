package com.upc.miappposta

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.upc.miappposta.databinding.ActivityReservaCitaBinding
import com.upc.miappposta.entidad.Cita
import com.upc.miappposta.entidad.Especialidad
import com.upc.miappposta.service.CitaService
import com.upc.miappposta.service.EspecialidadService
import com.upc.miappposta.service.MedicoService
import kotlin.properties.Delegates

class ReservaCitaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReservaCitaBinding
    private var idPacienteReg by Delegates.notNull<Int>()
    private var idEspecialidadReg by Delegates.notNull<Int>()
    private var idMedicoReg by Delegates.notNull<Int>()
    private var fechaReg by Delegates.notNull<String>()
    private var horaReg by Delegates.notNull<String>()
    private var estadoReg by Delegates.notNull<String>()
    private var motivoConsultaReg by Delegates.notNull<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityReservaCitaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        llenarEspecialidad()
        grabarCitaMedica()

        val objEspecialidad = findViewById<Spinner>(R.id.spEspecialidad)
        objEspecialidad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                val idEspecialidad = selectedItem.split("::")[0].toInt()
                val nombre = selectedItem.split("::")[1]
                idEspecialidadReg = idEspecialidad
                llenarMedico(idEspecialidad)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        val objMedico = findViewById<Spinner>(R.id.spMedico)
        objMedico.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                val idMedico = selectedItem.split("::")[0].toInt()
                val nombre = selectedItem.split("::")[1]
                idMedicoReg = idMedico
                llenarDisponibilidadFecha(idMedico)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        val objFecha = findViewById<Spinner>(R.id.spFecha)
        objFecha.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                val idMedico = selectedItem.split("::")[0].toInt()
                val fecha = selectedItem.split("::")[1]
                fechaReg = fecha
                llenarDisponibilidadHora(idMedico, fecha)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        val objHora = findViewById<Spinner>(R.id.spHora)
        objHora.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                val idDisponibilidad = selectedItem.split("::")[0].toInt()
                val hora = selectedItem.split("::")[1]
                horaReg = hora
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    fun grabarCitaMedica(){
        binding.btnReservarCita.setOnClickListener{
            idPacienteReg=1
            estadoReg = "Pendiente"
            motivoConsultaReg = binding.txtMotivoConsulta.text.toString()

            if(idPacienteReg.toString().isNotEmpty() && idEspecialidadReg.toString().isNotEmpty() &&
                idMedicoReg.toString().isNotEmpty() && fechaReg.isNotEmpty() && horaReg.isNotEmpty() &&
                estadoReg.isNotEmpty() && motivoConsultaReg.isNotEmpty()){

                val cita = Cita(0,idPacienteReg,idMedicoReg,idEspecialidadReg,fechaReg,horaReg,estadoReg,motivoConsultaReg)
                val rpta = CitaService(this).registrarCitaMedica(cita)
                mostrarMensaje(rpta)
                finish()
            }else{
                mostrarMensaje("Debe llenar todos los campos")
            }
        }
    }

    fun mostrarMensaje(mensaje: String){
        val ventana = AlertDialog.Builder(this)
        ventana.setTitle("Mensaje")
        ventana.setMessage(mensaje)
        ventana.setPositiveButton("Aceptar", null)
        ventana.create().show()
    }
    fun llenarDisponibilidadHora(idMedico: Int, fecha: String){
        val DisponibilidadHora = MedicoService(this).listarDisponibilidadHora(idMedico, fecha)
        binding.spHora.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, DisponibilidadHora)
    }

    fun llenarDisponibilidadFecha(idMedico: Int){
        val DisponibilidadFecha = MedicoService(this).listarDisponibilidadFecha(idMedico)
        binding.spFecha.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, DisponibilidadFecha)
    }
    fun llenarEspecialidad(){
        val listaEspecialidades = EspecialidadService(this).listarEspecialidadesTexto()
        binding.spEspecialidad.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaEspecialidades)
    }

    fun llenarMedico(idEspecialidad: Int){
        val listarMedicos = MedicoService(this).listarMedicosTexto(idEspecialidad)
        binding.spMedico.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listarMedicos)
    }


}
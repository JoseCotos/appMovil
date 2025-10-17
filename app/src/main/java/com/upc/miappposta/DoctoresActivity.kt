package com.upc.miappposta

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.upc.miappposta.databinding.ActivityDoctoresBinding
import com.upc.miappposta.util.SQLiteHelper

class DoctoresActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDoctoresBinding
    private lateinit var db: SQLiteHelper
    private lateinit var listaMedicos: MutableList<MedicoConEspecialidad>
    private lateinit var adapter: MedicoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctoresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = SQLiteHelper(this)

        // Obtener lista de médicos
        listaMedicos = obtenerMedicos()

        // Configurar RecyclerView
        adapter = MedicoAdapter(listaMedicos)
        binding.recyclerMedicos.layoutManager = LinearLayoutManager(this)
        binding.recyclerMedicos.adapter = adapter

        // Botón Agregar Médico
        binding.btnAgregarMedico.setOnClickListener {
            startActivity(Intent(this, RegistrarMedicoActivity::class.java))
        }

        // Buscador en tiempo real
        binding.edtBuscarMedico.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filtrarMedicos(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun onResume() {
        super.onResume()
        // Refrescar lista al volver a la Activity
        listaMedicos = obtenerMedicos()
        adapter.actualizarLista(listaMedicos)
    }

    private fun filtrarMedicos(texto: String) {
        val listaFiltrada = listaMedicos.filter {
            it.nombre.contains(texto, ignoreCase = true) ||
                    it.apellido.contains(texto, ignoreCase = true) ||
                    it.especialidad.contains(texto, ignoreCase = true)
        }
        adapter.actualizarLista(listaFiltrada)
    }

    // Clase de datos
    data class MedicoConEspecialidad(
        val idMedico: Int,
        val nombre: String,
        val apellido: String,
        val telefono: String,
        val especialidad: String
    )

    private fun obtenerMedicos(): MutableList<MedicoConEspecialidad> {
        val lista = mutableListOf<MedicoConEspecialidad>()
        val cursor = db.readableDatabase.rawQuery(
            """
            SELECT m.id_medico, m.nombre, m.apellido, m.telefono, e.nombre AS especialidad
            FROM medico m
            LEFT JOIN especialidad e ON m.id_especialidad = e.id_especialidad
            """.trimIndent(), null
        )

        if (cursor.moveToFirst()) {
            do {
                val medico = MedicoConEspecialidad(
                    idMedico = cursor.getInt(cursor.getColumnIndexOrThrow("id_medico")),
                    nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                    apellido = cursor.getString(cursor.getColumnIndexOrThrow("apellido")),
                    telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono")),
                    especialidad = cursor.getString(cursor.getColumnIndexOrThrow("especialidad")) ?: "Sin especialidad"
                )
                lista.add(medico)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return lista
    }

    // Adapter
    inner class MedicoAdapter(private var lista: List<MedicoConEspecialidad>) :
        RecyclerView.Adapter<MedicoAdapter.MedicoViewHolder>() {

        inner class MedicoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val txtNombre: TextView = view.findViewById(R.id.txtNombreMedico)
            val txtEspecialidad: TextView = view.findViewById(R.id.txtEspecialidadMedico)
            val txtTelefono: TextView = view.findViewById(R.id.txtTelefonoMedico)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicoViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_medico, parent, false)
            return MedicoViewHolder(view)
        }

        override fun onBindViewHolder(holder: MedicoViewHolder, position: Int) {
            val medico = lista[position]
            holder.txtNombre.text = "${medico.nombre} ${medico.apellido}"
            holder.txtEspecialidad.text = medico.especialidad
            holder.txtTelefono.text = medico.telefono
        }

        override fun getItemCount(): Int = lista.size

        fun actualizarLista(nuevaLista: List<MedicoConEspecialidad>) {
            lista = nuevaLista
            notifyDataSetChanged()
        }
    }
}

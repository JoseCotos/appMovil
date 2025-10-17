package com.upc.miappposta

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HistoricoActivity : AppCompatActivity() {

    private lateinit var adapter: SimpleAdapter
    private lateinit var data: List<Map<String, String>>
    private lateinit var filteredData: MutableList<Map<String, String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historico)

        val listView = findViewById<ListView>(R.id.listHistorial)
        val searchEditText = findViewById<EditText>(R.id.editTextText4)

        // Datos base
        data = listOf(
            mapOf("titulo" to "Pediatría", "subtitulo" to "Realizada el 10/01/2024", "comentario" to "Control de crecimiento y desarrollo."),
            mapOf("titulo" to "Neumología", "subtitulo" to "Realizada el 15/02/2024", "comentario" to "Seguimiento por asma bronquial."),
            mapOf("titulo" to "Cardiología", "subtitulo" to "Realizada el 22/02/2024", "comentario" to "Chequeo anual, resultados normales."),
            mapOf("titulo" to "Dermatología", "subtitulo" to "Realizada el 05/03/2024", "comentario" to "Tratamiento para acné juvenil."),
            mapOf("titulo" to "Oftalmología", "subtitulo" to "Realizada el 12/03/2024", "comentario" to "Medida de la vista y cambio de lentes."),
            mapOf("titulo" to "Ginecología", "subtitulo" to "Realizada el 18/04/2024", "comentario" to "Control ginecológico anual de rutina."),
            mapOf("titulo" to "Traumatología", "subtitulo" to "Realizada el 25/04/2024", "comentario" to "Seguimiento de fractura de muñeca."),
            mapOf("titulo" to "Urología", "subtitulo" to "Realizada el 03/05/2024", "comentario" to "Exámenes de próstata, todo en orden."),
            mapOf("titulo" to "Neurología", "subtitulo" to "Realizada el 10/05/2024", "comentario" to "Consulta por migrañas recurrentes."),
            mapOf("titulo" to "Gastroenterología", "subtitulo" to "Realizada el 21/05/2024", "comentario" to "Tratamiento para gastritis crónica.")
        )

        // Copia inicial de los datos
        filteredData = data.toMutableList()

        val from = arrayOf("titulo", "subtitulo", "comentario")
        val to = intArrayOf(R.id.item_titulo, R.id.item_subtitulo, R.id.item_comentario)

        adapter = SimpleAdapter(this, filteredData, R.layout.list_item_historico, from, to)
        listView.adapter = adapter

        // Listener para el campo de búsqueda
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().lowercase()
                filteredData.clear()
                filteredData.addAll(
                    data.filter {
                        it["titulo"]!!.lowercase().contains(query) ||
                                it["comentario"]!!.lowercase().contains(query) ||
                                it["subtitulo"]!!.lowercase().contains(query)
                    }
                )
                adapter.notifyDataSetChanged()
            }
        })

        // Ajuste de insets para pantalla completa
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

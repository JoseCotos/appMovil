package com.upc.miappposta

import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.upc.miappposta.databinding.ActivityHistoricoBinding
import com.upc.miappposta.databinding.ActivityLoginBinding

class HistoricoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoricoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historico)

        val listView = findViewById<ListView>(R.id.listHistorial)

        // Lista de mapas, donde cada mapa representa un item de la lista
        val data = listOf(
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

        // Las llaves del mapa que queremos mostrar
        val from = arrayOf("titulo", "subtitulo", "comentario")
        // Los IDs de los TextViews en list_item_historico.xml donde irán los datos
        val to = intArrayOf(R.id.item_titulo, R.id.item_subtitulo, R.id.item_comentario)

        // Crea el SimpleAdapter
        val adapter = SimpleAdapter(
            this,
            data,
            R.layout.list_item_historico,
            from,
            to
        )

        // Asigna el adaptador al ListView
        listView.adapter = adapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

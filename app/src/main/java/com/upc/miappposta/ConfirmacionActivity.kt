package com.upc.miappposta

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.upc.miappposta.databinding.ActivityConfirmacionBinding

class ConfirmacionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmacionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // ✅ Inicializas el binding correctamente
        binding = ActivityConfirmacionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mantienes el ajuste de los bordes
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ✅ Ahora sí puedes usar binding sin error
        binding.btnInicio.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}

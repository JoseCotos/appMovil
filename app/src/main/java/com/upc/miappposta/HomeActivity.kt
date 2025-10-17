package com.upc.miappposta

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.upc.miappposta.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtMensaje.text = "Bienvenido al sistema 🎉"

        binding.btnHistorial.setOnClickListener {
            startActivity(Intent(this, HistoricoActivity::class.java))
        }

        binding.btnConfirmado.setOnClickListener {
            startActivity(Intent(this, ConfirmacionActivity::class.java))
        }

        binding.btnAgendar.setOnClickListener {
            startActivity(Intent(this, AgendaCitaActivity::class.java))
        }
    }
}

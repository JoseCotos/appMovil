package com.upc.miappposta

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.upc.miappposta.databinding.ActivityHomeAdminBinding

class HomeAdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mensaje de bienvenida
        binding.txtMensaje.text = "Bienvenido al Sistema Administrativo"

        // Acciones de los botones
        binding.btnMedicos.setOnClickListener {
            startActivity(Intent(this, DoctoresActivity::class.java))
        }

        binding.btnPacientes.setOnClickListener {
            startActivity(Intent(this, ConfirmacionActivity::class.java))
        }


    }
}

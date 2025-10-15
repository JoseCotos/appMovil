package com.upc.miappposta

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.upc.miappposta.databinding.ActivityConfirmacionBinding
import com.upc.miappposta.databinding.ActivityHistoricoBinding

class ConfirmacionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmacionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirmacion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
        binding.btnInicio.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}
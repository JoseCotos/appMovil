package com.upc.miappposta

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.upc.miappposta.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Botón que lleva al Login
        binding.btnIniciar.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

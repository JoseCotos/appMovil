package com.upc.miappposta

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.upc.miappposta.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        binding.btnLogin.setOnClickListener {
            val correo = binding.txtCorreo.text.toString().trim()
            val password = binding.txtPassword.text.toString().trim()

            if (correo.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Ingrese correo y contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (db.validarLogin(correo, password)) {
                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnRegistrar.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}

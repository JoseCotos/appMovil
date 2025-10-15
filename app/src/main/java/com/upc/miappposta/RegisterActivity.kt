package com.upc.miappposta

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.upc.miappposta.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        binding.btnRegistrar.setOnClickListener {
            val nombre = binding.txtNombre.text.toString()
            val correo = binding.txtCorreo.text.toString()
            val password = binding.txtPassword.text.toString()

            if (nombre.isEmpty() || correo.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                if (db.insertarUsuario(nombre, correo, password)) {
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "El correo ya existe", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

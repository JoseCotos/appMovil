package com.upc.miappposta

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.upc.miappposta.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val CHANNEL_ID = "mi_canal_de_citas"
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // El usuario concedió el permiso. ¡Genial!
            } else {
                // El usuario denegó el permiso. No podremos mostrar notificaciones.
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createNotificationChannel()
        askForNotificationPermission()

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
        binding.btnNotificar.setOnClickListener {
            showNotification(
                "Estado de la Cita",
                "Su cita ha sido actualizada. Revise los detalles.",
                1 // ID único para esta notificación
            )
        }
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notificaciones de Citas"
            val descriptionText = "Canal para notificar sobre el estado y confirmación de citas."
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    private fun askForNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
    private fun showNotification(title: String, text: String, notificationId: Int) {
        // Verificamos si tenemos permiso.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // Si no tenemos permiso, no hacemos nada. La pedimos al inicio.
            return
        }

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher) // Ícono que se mostrará.
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }
    }
}

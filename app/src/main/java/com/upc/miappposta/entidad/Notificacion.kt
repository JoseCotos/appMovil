package com.upc.miappposta.entidad

data class Notificacion(
    val idNotificacion: Int,
    val idCita: Int,
    val fecha: String,
    val mensaje: String
)

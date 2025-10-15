package com.upc.miappposta.entidad

data class Disponibilidad(
    val id_disponibilidad: Int,
    val id_medico: Int,
    val fecha: String,
    val hora: String,
    val estado: String
)

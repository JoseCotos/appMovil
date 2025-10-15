package com.upc.miappposta.entidad

data class Medico(
    val id_medico: Int,
    val id_especialidad: Int,
    val nombre: String,
    val apellido: String,
    val telefono: String
)

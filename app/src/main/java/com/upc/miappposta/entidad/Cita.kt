package com.upc.miappposta.entidad

data class Cita(
    val id_cita: Int,
    val id_paciente: Int,
    val id_medico: Int,
    val fecha: String,
    val hora: String,
    val estado: String,
    val motivo_consulta: String
)

package com.upc.miappposta.entidad

data class CitaMedica(
    val idCita: Int,
    val idPaciente: Int,
    val idMedico: Int,
    val fecha: String,
    val hora: String,
    val estado: String,
    val motivoConsulta: String
)

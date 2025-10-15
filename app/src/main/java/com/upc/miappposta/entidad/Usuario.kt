package com.upc.miappposta.entidad

data class Usuario(
    var id_usuario: Int,
    var id_paciente: Int,
    var correo: String,
    var password: String
)

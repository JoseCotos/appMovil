package com.upc.miappposta.entidad

data class Paciente(
    var id_paciente: Int,
    var tipo_documento: String,
    var numero_documento: String,
    var nombre: String,
    var apellido: String,
    var correo: String,
    var telefono: String,
    var direccion: String
)

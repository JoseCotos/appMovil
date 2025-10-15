package com.upc.miappposta.service

import android.content.ContentValues
import android.content.Context
import com.upc.miappposta.entidad.Paciente
import com.upc.miappposta.util.SQLiteHelper

class PacienteService(context: Context) {
    private var sqliteHelper: SQLiteHelper = SQLiteHelper(context)

    fun registrarPaciente(paciente: Paciente): String {
        var rpta = ""
        val db = sqliteHelper.writableDatabase
        try {
            val valores = ContentValues()
            valores.put("tipo_documento", paciente.tipo_documento)
            valores.put("numero_documento", paciente.numero_documento)
            valores.put("nombre", paciente.nombre)
            valores.put("apellido", paciente.apellido)
            valores.put("correo", paciente.correo)
            valores.put("telefono", paciente.telefono)
            valores.put("direccion", paciente.direccion)

            val resultado = db.insert("paciente", null, valores)
            if (resultado == -1L) {
                rpta = "Error al registrar el paciente"
            } else {
                rpta = "Paciente registrado exitosamente"
            }

        }catch (e: Exception){
            rpta = "Error: ${e.message}"
        }finally {
            db.close()
        }

        return rpta
    }
}
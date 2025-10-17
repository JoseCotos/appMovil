package com.upc.miappposta.service

import android.content.ContentValues
import android.content.Context
import com.upc.miappposta.entidad.Cita
import com.upc.miappposta.util.SQLiteHelper

class CitaService(context: Context) {
    private val sqliteHelper: SQLiteHelper = SQLiteHelper(context)

    fun registrarCitaMedica(cita: Cita): String{
        var rpta = ""
        val db = sqliteHelper.writableDatabase

        try {
            val valores = ContentValues()
            valores.put("id_paciente", cita.id_paciente)
            valores.put("id_medico", cita.id_medico)
            valores.put("id_especialidad", cita.id_especialidad)
            valores.put("fecha", cita.fecha)
            valores.put("hora", cita.hora)
            valores.put("estado", cita.estado)
            valores.put("motivo_consulta", cita.motivo_consulta)

            val resultado = db.insert("cita", null, valores)
            if (resultado != -1L) {
                rpta = "Cita registrada con éxito"
            } else {
                rpta = "Error al registrar la cita"
            }

        }catch (e: Exception){
            rpta = "Error ${e.message}"
        }finally {
            db.close()
        }

        return rpta
    }
}
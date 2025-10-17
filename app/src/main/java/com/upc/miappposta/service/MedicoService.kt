package com.upc.miappposta.service

import android.content.Context
import android.util.Log
import com.upc.miappposta.util.SQLiteHelper

class MedicoService(context: Context) {
    private val sqLiteHelper: SQLiteHelper = SQLiteHelper(context)

    fun listarMedicosTexto(idEspecialidad: Int): ArrayList<String> {
        var listaMedicos: ArrayList<String> = arrayListOf()
        val db = sqLiteHelper.readableDatabase

        try {
            val cursor = db.rawQuery("SELECT * FROM medico where id_especialidad = " + idEspecialidad, null)
            if (cursor.moveToFirst()) {
                do {
                    listaMedicos.add(cursor.getString(0) + "::" + cursor.getString(2) + " " + cursor.getString(3))
                } while (cursor.moveToNext())
                cursor.close()
            }else{
                if (cursor != null) {
                    cursor.close()
                }
            }
        }catch (e: Exception){
            Log.d("Error", e.message.toString())
        }finally {
            db.close()
        }

        return listaMedicos
    }

    fun listarDisponibilidadFecha(idMedico: Int): ArrayList<String>{
        var listaDisponibilidad: ArrayList<String> = arrayListOf()
        val db = sqLiteHelper.readableDatabase

        try {
            val cursor = db.rawQuery("SELECT DISTINCT fecha FROM disponibilidad where id_medico =" + idMedico, null)
            if (cursor.moveToFirst()) {
                do {
                    listaDisponibilidad.add(idMedico.toString() + "::" + cursor.getString(0))
                } while (cursor.moveToNext())
                cursor.close()
            }else{
                if (cursor != null) {
                    cursor.close()
                }
            }

        }catch (e: Exception){
            Log.d("Error", e.message.toString())
        }finally {
            db.close()
        }

        return listaDisponibilidad
    }

    fun listarDisponibilidadHora(idMedico: Int, fecha: String): ArrayList<String>{
        var listaDisponibilidad: ArrayList<String> = arrayListOf()
        val db = sqLiteHelper.readableDatabase

        try {
            val cursor = db.rawQuery("SELECT * FROM disponibilidad where id_medico =" + idMedico + " and fecha = '" + fecha + "'", null)
            if (cursor.moveToFirst()) {
                do {
                    listaDisponibilidad.add(cursor.getString(0) + "::" + cursor.getString(3))
                } while (cursor.moveToNext())
                cursor.close()
            }else{
                if (cursor != null) {
                    cursor.close()
                }
            }

        }catch (e: Exception){
            Log.d("Error", e.message.toString())
        }finally {
            db.close()
        }

        return listaDisponibilidad
    }
}
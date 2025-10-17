package com.upc.miappposta.service

import android.content.Context
import android.util.Log
import com.upc.miappposta.entidad.Especialidad
import com.upc.miappposta.util.SQLiteHelper

class EspecialidadService(context: Context) {
    private val sqliteHelper: SQLiteHelper = SQLiteHelper(context)

    fun listarEspecialidades(): List<Especialidad> {
        var listaEspecialidades: MutableList<Especialidad> = mutableListOf()
        val db = sqliteHelper.readableDatabase

        try {
            val cursor = db.rawQuery("SELECT * FROM especialidad", null)
            if (cursor.moveToFirst()) {
                do {
                    var esp : Especialidad = Especialidad(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                    )
                    listaEspecialidades.add(esp)
                } while (cursor.moveToNext())
                cursor.close()
            } else {
                if (cursor != null) {
                    cursor.close()
                }
            }
        }catch (e: Exception){
            Log.d("Error", e.message.toString())
        }finally {
            db.close()
        }

        return listaEspecialidades
    }

    fun listarEspecialidadesTexto(): ArrayList<String> {
        var listaEspecialidades: ArrayList<String> = arrayListOf()
        val db = sqliteHelper.readableDatabase

        try {
            val cursor = db.rawQuery("SELECT * FROM especialidad", null)
            if (cursor.moveToFirst()) {
                do {
                    listaEspecialidades.add(cursor.getString(0) + "::" + cursor.getString(1))

                } while (cursor.moveToNext())
                cursor.close()
            } else {
                if (cursor != null) {
                    cursor.close()
                }
            }
        }catch (e: Exception){
            Log.d("Error", e.message.toString())
        }finally {
            db.close()
        }

        return listaEspecialidades
    }

}
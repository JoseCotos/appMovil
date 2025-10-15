package com.upc.miappposta.util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSON){
    companion object{
        private const val DATABASE_NAME = "myposta.db"
        private const val DATABASE_VERSON = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        var query = "CREATE TABLE IF NOT EXISTS paciente(" +
                "id_paciente INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tipo_documento TEXT," +
                "numero_documento TEXT," +
                "nombre TEXT," +
                "apellido TEXT," +
                "correo TEXT," +
                "telefono TEXT," +
                "direccion TEXT" +
                ")"
        db.execSQL(query)

        query = "CREATE TABLE IF NOT EXISTS usuario(" +
                "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_paciente INTEGER," +
                "correo TEXT," +
                "password TEXT" +
                ")"
        db.execSQL(query)

        query = "CREATE TABLE IF NOT EXISTS medico(" +
                "id_medico INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_especialidad INTEGER," +
                "nombre TEXT," +
                "apellido TEXT," +
                "telefono TEXT" +
                ")"
        db.execSQL(query)

        query = "CREATE TABLE IF NOT EXISTS especialidad(" +
                "id_especialidad INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "descripcion TEXT" +
                ")"
        db.execSQL(query)

        query = "CREATE TABLE IF NOT EXISTS disponibilidad(" +
                "id_disponibilidad INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_medico INTEGER," +
                "fecha TEXT," +
                "hora TEXT," +
                "estado TEXT" +
                ")"
        db.execSQL(query)

        query = "CREATE TABLE IF NOT EXISTS cita(" +
                "id_cita INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_paciente INTEGER," +
                "id_medico INTEGER," +
                "fecha TEXT," +
                "hora TEXT," +
                "estado TEXT," +
                "motivo_consulta TEXT" +
                ")"
        db.execSQL(query)

        query = "CREATE TABLE IF NOT EXISTS notificacion(" +
                "id_notificacion INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_cita INTEGER," +
                "fecha TEXT," +
                "mensaje TEXT" +
                ")"
        db.execSQL(query)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS paciente")
        db.execSQL("DROP TABLE IF EXISTS usuario")
        db.execSQL("DROP TABLE IF EXISTS medico")
        db.execSQL("DROP TABLE IF EXISTS especialidad")
        db.execSQL("DROP TABLE IF EXISTS disponibilidad")
        db.execSQL("DROP TABLE IF EXISTS cita")
        db.execSQL("DROP TABLE IF EXISTS notificacion")
        onCreate(db)

    }

}
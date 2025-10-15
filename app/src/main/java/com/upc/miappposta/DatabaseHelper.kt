package com.upc.miappposta

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "usuarios.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE usuarios (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT, " +
                    "correo TEXT UNIQUE, " +
                    "password TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        onCreate(db)
    }

    fun insertarUsuario(nombre: String, correo: String, password: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("nombre", nombre)
            put("correo", correo)
            put("password", password)
        }
        val result = db.insert("usuarios", null, values)
        return result != -1L
    }

    fun validarLogin(correo: String, password: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM usuarios WHERE correo=? AND password=?",
            arrayOf(correo, password)
        )
        val existe = cursor.count > 0
        cursor.close()
        return existe
    }
}

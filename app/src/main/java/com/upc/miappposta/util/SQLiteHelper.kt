package com.upc.miappposta.util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSON){
    companion object{
        private const val DATABASE_NAME = "myposta.db"
        private const val DATABASE_VERSON = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        TODO("Not yet implemented")
    }

}
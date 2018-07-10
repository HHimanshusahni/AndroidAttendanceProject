package com.online.attendencehelper.db.tables

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.online.attendencehelper.db.DB_NAME
import com.online.attendencehelper.db.DB_VER
val DB_NAME = "attendance.db"
val DB_VER = 4
class SubjectTableHelper (context:Context):SQLiteOpenHelper(context, DB_NAME,null, DB_VER){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.let{
            it.execSQL(SubjectTable.CMD_CREATE_TABLE)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}
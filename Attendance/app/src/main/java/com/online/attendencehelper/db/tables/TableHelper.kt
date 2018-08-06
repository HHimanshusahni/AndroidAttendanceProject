package com.online.attendencehelper.db.tables

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
val DB_NAME = "AttendanceDatabase.db"
val DB_VER = 4
class TableHelper (context:Context):SQLiteOpenHelper(context, DB_NAME,null, DB_VER){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.let{
            it.execSQL(SubjectTable.CMD_CREATE_TABLE)
            it.execSQL(AttendanceTable.CMD_CREATE_TABLE)
            it.execSQL(AttendanceRecordTable.CMD_CREATE_TABLE)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}
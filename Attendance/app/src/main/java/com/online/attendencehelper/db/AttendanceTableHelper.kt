package com.online.attendencehelper.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.online.attendencehelper.db.tables.AttendanceTable
import com.online.attendencehelper.db.tables.SubjectTable

val DB_NAME = "attendance.db"
val DB_VER = 4
class AttendanceTableHelper(context: Context): SQLiteOpenHelper(context, DB_NAME,null,DB_VER) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.let {
            it.execSQL(AttendanceTable.CMD_CREATE_TABLE)
            it.execSQL(SubjectTable.CMD_CREATE_TABLE)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}
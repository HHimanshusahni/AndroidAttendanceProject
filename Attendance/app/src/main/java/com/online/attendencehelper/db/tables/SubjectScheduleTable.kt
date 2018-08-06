package com.online.attendencehelper.db.tables

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.online.attendencehelper.models.SubjectSchedule

class SubjectScheduleTable {
    companion object {
        val TABLE_NAME = "SubjectScheduleTable"
        val CMD_CREATE_TABLE = """
            CREATE TABLE IF NOT EXITS ${TABLE_NAME}(
            ${Columns.SUBJECTID} INTEGER,
            ${Columns.TIMINGS} TEXT,
            ${Columns.DAYS} TEXT
            );
        """.trimIndent()
        fun addSchedule(db:SQLiteDatabase,subjectSchedule: SubjectSchedule):Long{
            val row = ContentValues()
            row.put(Columns.SUBJECTID,subjectSchedule.subjectid)
            row.put(Columns.TIMINGS,subjectSchedule.timings)
            row.put(Columns.DAYS,subjectSchedule.days)
            return db.insert(TABLE_NAME,null,row)
        }
        object Columns{
            val SUBJECTID  = "subjectid"
            val TIMINGS = "timings"
            val DAYS = "days"
        }
    }
}
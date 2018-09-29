package com.online.attendencehelper.db.tables

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.online.attendencehelper.models.SubjectSchedule

class SubjectScheduleTable {
    companion object {
        val TABLE_NAME = "subjectschedule"
        val CMD_CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS ${TABLE_NAME}(
            ${Columns.SUBJECTID} INT,
            ${Columns.TIMINGS} TEXT,
            ${Columns.DAYS} TEXT
            );
        """.trimIndent()


        fun addSchedule(db:SQLiteDatabase,subjectSchedule: SubjectSchedule):Long{
            val row = ContentValues()
            row.put(Columns.SUBJECTID,subjectSchedule.subjectId)
            row.put(Columns.TIMINGS,subjectSchedule.timings)
            row.put(Columns.DAYS,subjectSchedule.days)
            return db.insert(TABLE_NAME,null,row)
        }

        fun getScheduleFromSubjectId(db:SQLiteDatabase,subjectId:Int):ArrayList<SubjectSchedule> {
            val subjectSchedulelist = ArrayList<SubjectSchedule>()
            val cursor = db.query(
                    TABLE_NAME,
                    arrayOf(Columns.SUBJECTID,
                            Columns.TIMINGS,
                            Columns.DAYS),
                    Columns.SUBJECTID+"=?",
                    arrayOf(subjectId.toString()),

                    null, null,
                    null

            )
            val subjectIdCol = cursor.getColumnIndex(Columns.SUBJECTID)
            val timingsIdCol = cursor.getColumnIndex(Columns.DAYS)
            val daysIdCol = cursor.getColumnIndex(Columns.TIMINGS)
            while(cursor.moveToNext()){
                val rowTask = SubjectSchedule(
                        cursor.getInt(subjectIdCol),
                        cursor.getString(timingsIdCol),
                        cursor.getString(daysIdCol)
                )
                subjectSchedulelist.add(rowTask)
            }
            cursor.close()
            return  subjectSchedulelist
        }
        object Columns{
            val SUBJECTID  = "subjectId"
            val TIMINGS = "timings"
            val DAYS = "days"
        }
    }
}
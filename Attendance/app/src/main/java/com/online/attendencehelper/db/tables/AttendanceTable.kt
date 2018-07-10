package com.online.attendencehelper.db.tables

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.online.attendencehelper.models.Attendance

class AttendanceTable {
    companion object {
        val TABLE_NAME = "attendances"
        val CMD_CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS ${TABLE_NAME}(

            ${Columns.SUBJECTID} INTEGER,
            ${Columns.STUDENTID} INTEGER ,
            ${Columns.STUDENTNAME} TEXT,
            ${Columns.PRESENT} BOOLEAN,
            ${Columns.TIME} TEXT,
            ${Columns.DATE} TEXT
            );
        """.trimIndent()


        fun addAttendance(db: SQLiteDatabase, attendance: Attendance): Long {

            val row = ContentValues()
            row.put(Columns.SUBJECTID, attendance.subjectid)
            row.put(Columns.STUDENTID, attendance.studentid)
            row.put(Columns.STUDENTNAME, attendance.studentName)
            row.put(Columns.PRESENT, attendance.present)
            row.put(Columns.TIME, attendance.time)
            row.put(Columns.DATE, attendance.date)

            return db.insert(TABLE_NAME, null, row)
        }


        fun getAllAttendance(db: SQLiteDatabase): ArrayList<Attendance> {
            val attendances = ArrayList<Attendance>()
            val cursor = db.query(
                    TABLE_NAME,
                    arrayOf(Columns.SUBJECTID,
                            Columns.STUDENTID,
                            Columns.STUDENTNAME,
                            Columns.PRESENT,
                            Columns.TIME,
                            Columns.DATE),
                    null, null,
                    null, null,
                    null
            )
            val subjectidCol = cursor.getColumnIndex(Columns.SUBJECTID)
            val studentidCol = cursor.getColumnIndex(Columns.STUDENTID)
            val studentnameCol = cursor.getColumnIndex(Columns.STUDENTNAME)
            val presentCol = cursor.getColumnIndex(Columns.PRESENT)
            val timeCol = cursor.getColumnIndex(Columns.TIME)
            val dateCol = cursor.getColumnIndex(Columns.DATE)

            while (cursor.moveToNext()) {
                val rowTask = Attendance(
                        cursor.getInt(subjectidCol),
                        cursor.getInt(studentidCol),
                        cursor.getString(studentnameCol),
                        cursor.getInt(presentCol) == 1,
                        cursor.getString(timeCol),
                        cursor.getString(dateCol).toString()
                )
                attendances.add(rowTask)
            }
            return attendances
        }

        object Columns {
            val SUBJECTID = "subjectid"
            val STUDENTID = "studentid"
            val STUDENTNAME = "studentname"
            val PRESENT = "present"
            val TIME = "time"
            val DATE = "date"
        }
    }
}
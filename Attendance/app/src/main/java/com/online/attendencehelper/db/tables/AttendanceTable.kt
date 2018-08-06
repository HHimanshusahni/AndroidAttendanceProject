package com.online.attendencehelper.db.tables

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.online.attendencehelper.models.Attendance

class AttendanceTable {
    companion object {
        val TABLE_NAME = "attendances"
        val CMD_CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS ${TABLE_NAME}(
            ${Columns.ATTENDANCEID} INTEGER ,
            ${Columns.STUDENTID} INTEGER ,
            ${Columns.PRESENT} BOOLEAN
            );
        """.trimIndent()


        fun addAttendance(db: SQLiteDatabase, attendance: Attendance): Long {

            val row = ContentValues()
            row.put(Columns.ATTENDANCEID, attendance.attendanceid)
            row.put(Columns.STUDENTID, attendance.studentid)
            row.put(Columns.PRESENT, attendance.present)

            return db.insert(TABLE_NAME, null, row)
        }


        fun getAllAttendance(db: SQLiteDatabase): ArrayList<Attendance> {
            val attendances = ArrayList<Attendance>()
            val cursor = db.query(
                    TABLE_NAME,
                    arrayOf(Columns.ATTENDANCEID,
                            Columns.STUDENTID,
                            Columns.PRESENT
                    ),
                    null, null,
                    null, null,
                    null
            )
            val attendanceidCol = cursor.getColumnIndex(Columns.ATTENDANCEID)
            val studentidCol = cursor.getColumnIndex(Columns.STUDENTID)
            val presentCol = cursor.getColumnIndex(Columns.PRESENT)

            while (cursor.moveToNext()) {
                val rowTask = Attendance(
                        cursor.getInt(attendanceidCol),
                        cursor.getInt(studentidCol),
                        cursor.getInt(presentCol) == 1
                )
                attendances.add(rowTask)
            }
            return attendances
        }

        object Columns {
            val ATTENDANCEID = "attendanceid"
            val STUDENTID = "studentid"
            val PRESENT = "present"
        }
    }
}
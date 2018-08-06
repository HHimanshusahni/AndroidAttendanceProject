package com.online.attendencehelper.db.tables

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.online.attendencehelper.models.AttendanceRecord

class AttendanceRecordTable {
    companion object {
        val TABLE_NAME = "AttendanceRecordTable"
        val CMD_CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS ${TABLE_NAME}(
            ${Columns.ATTENDANCEID} INTEGER PRIMARY KEY AUTOINCREMENT,
            ${Columns.SUBJECTID} INTEGER ,
            ${Columns.DATE} TEXT,
            ${Columns.TIME} TEXT
            );
        """.trimIndent()
        fun addAttendanceRecord(db:SQLiteDatabase,attendanceRecord: AttendanceRecord):Long{

            val row = ContentValues()
            row.put(Columns.ATTENDANCEID,attendanceRecord.attendanceid)
            row.put(Columns.SUBJECTID,attendanceRecord.subjectid)
            row.put(Columns.DATE,attendanceRecord.attendancedate)
            row.put(Columns.TIME,attendanceRecord.attendacetime)

            return db.insert(TABLE_NAME,null,row)

        }
        fun lastAttendanceId(db: SQLiteDatabase):Int{

            val query = " SELECT MAX(${Columns.ATTENDANCEID}) from $TABLE_NAME"
            var cursor =db.rawQuery(query,null)
            var lastAid:Int = 1
            if(cursor.moveToFirst()){
                cursor.moveToFirst()
                lastAid = cursor.getInt(0)
                cursor.close()
            }
            return lastAid

        }
        fun getAllAttendanceRecord(db:SQLiteDatabase):ArrayList<AttendanceRecord>{
            val attendanceRecordList = ArrayList<AttendanceRecord>()
            val cursor = db.query(
                    TABLE_NAME,
                    arrayOf(
                            Columns.ATTENDANCEID,
                            Columns.SUBJECTID,
                            Columns.TIME,
                            Columns.DATE),
                    null,null,null,
                    null,null,null
                    )
            val attendanceId = cursor.getColumnIndex(Columns.ATTENDANCEID)
            val subjectId = cursor.getColumnIndex(Columns.SUBJECTID)
            val time = cursor.getColumnIndex(Columns.TIME)
            val date = cursor.getColumnIndex(Columns.DATE)

            while(cursor.moveToNext()){
                val rowTask = AttendanceRecord(
                        cursor.getInt(attendanceId),
                        cursor.getInt(subjectId),
                        cursor.getString(time),
                        cursor.getString(date)
                )
                attendanceRecordList.add(rowTask)
            }
            return attendanceRecordList
        }

//        fun getAttendanceRecordFromId(db: SQLiteDatabase,attendanceId:Int):AttendanceTable{
//            var c :Cursor
//            c = db.rawQuery(" SELECT * from ${TABLE_NAME} where ${Columns.ATTENDANCEID}=?", arrayOf(attendanceId.toString()))
//            c.moveToNext()
//            return c.getColumnIndex(Columns.)
//
//        }
        object Columns {
            val ATTENDANCEID = "attendanceid"
            val SUBJECTID = "subjectid"
            val DATE = "date"
            val TIME = "time"

        }
    }


}
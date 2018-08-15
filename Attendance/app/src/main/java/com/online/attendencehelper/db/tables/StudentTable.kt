package com.online.attendencehelper.db.tables

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.online.attendencehelper.models.Student

class StudentTable{

    companion object {
        val TABLE_NAME = "student"
        val CMD_CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS ${TABLE_NAME}(
            ${Columns.STUDENTID} INTEGER ,
            ${Columns.STUDENTNAME} TEXT,
            ${Columns.SUBJECTID} INT
            );
        """.trimIndent()
        fun addStudents(db:SQLiteDatabase,studentList:ArrayList<Student>){

            val row = ContentValues()
            for(i in studentList) {
                row.put(Columns.STUDENTID, i.studentId)
                row.put(Columns.STUDENTNAME, i.studentName)
                row.put(Columns.SUBJECTID, i.subjectId
                )
                db.insert(TABLE_NAME,null,row)
            }


        }
        fun getStudentListFromSubjectId(db:SQLiteDatabase,subjectId:Int):ArrayList<Student>{
            val studentList = ArrayList<Student>()
            val cursor = db.query(
                    TABLE_NAME,
                    arrayOf(Columns.STUDENTID,
                            Columns.STUDENTNAME,
                            Columns.SUBJECTID
                    ),
                    Columns.SUBJECTID+"=?",
                    arrayOf(subjectId.toString()),
                    null,null,
                    null

            )

            val studentIdCol = cursor.getColumnIndex(Columns.STUDENTID)
            val studentNameCol = cursor.getColumnIndex(Columns.STUDENTNAME)
            val subjectIdCol = cursor.getColumnIndex(Columns.SUBJECTID)

            while(cursor.moveToNext()){
                val rowTask = Student(
                        cursor.getInt(studentIdCol),
                        cursor.getString(studentNameCol),
                        cursor.getInt(subjectIdCol)
                )
                studentList.add(rowTask)
            }
            return studentList
        }
        fun changeStudentname(db: SQLiteDatabase,subjectId: Int,studentId: Int,studentName:String){
            val row = ContentValues()
            row.put(Columns.STUDENTNAME,studentName)
            Log.d("EDIT","\nchangestudent Name"+studentName+"\nstudentid"+studentId)
            db.update(TABLE_NAME,row,"${Columns.STUDENTID}="+studentId +" AND ${Columns.SUBJECTID}="+subjectId,null)
        }

    }


        object Columns {
            val STUDENTID = "studentid"
            val STUDENTNAME = "studentname"
            val SUBJECTID = "subjectid"
        }

}

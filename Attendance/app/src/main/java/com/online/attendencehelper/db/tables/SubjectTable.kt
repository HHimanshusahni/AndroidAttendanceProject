package com.online.attendencehelper.db.tables

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.online.attendencehelper.models.Subject
class SubjectTable{
    companion object {
        val TABLE_NAME = "subjects"
        val CMD_CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS ${TABLE_NAME}(

            ${Columns.SUBJECTID} INTEGER PRIMARY KEY AUTOINCREMENT,
            ${Columns.SUBJECTNAME} TEXT,
            ${Columns.YEAR} INT,
            ${Columns.DEPARTMENT} TEXT,
            ${Columns.TOTALROLLNOS} INT
            );
        """.trimIndent()

        fun addSubject(db:SQLiteDatabase,subject: Subject):Long{
            val row = ContentValues()
            row.put(Columns.SUBJECTID,subject.subjectid)
            row.put(Columns.SUBJECTNAME,subject.subjectname)
            row.put(Columns.YEAR,subject.year)
            row.put(Columns.DEPARTMENT,subject.department)
            row.put(Columns.TOTALROLLNOS,subject.totalrollnos)

            return db.insert(TABLE_NAME,null,row)
        }
        fun getAllSubject(db:SQLiteDatabase):ArrayList<Subject>{
            val subjects = ArrayList<Subject>()
            val cursor = db.query(
                    TABLE_NAME,
                    arrayOf(Columns.SUBJECTID,
                            Columns.SUBJECTNAME,
                            Columns.YEAR,
                            Columns.DEPARTMENT,
                            Columns.TOTALROLLNOS),
                            null,null,
                            null,null,
                            null

            )
            val subjectidCol = cursor.getColumnIndex( Columns.SUBJECTID)
            val subjectnameidCol = cursor.getColumnIndex( Columns.SUBJECTNAME)
            val yearidCol = cursor.getColumnIndex( Columns.YEAR)
            val departmentidCol = cursor.getColumnIndex( Columns.DEPARTMENT)
            val totalrollnoidCol = cursor.getColumnIndex( Columns.TOTALROLLNOS)
            while (cursor.moveToNext()) {
                val rowTask = Subject(
                        cursor.getInt(subjectidCol),
                        cursor.getString(subjectnameidCol),
                        cursor.getInt(yearidCol),
                        cursor.getString(departmentidCol),
                        cursor.getInt(totalrollnoidCol)

                )
                        subjects.add(rowTask)
            }
            cursor.close()
            return subjects
        }
        fun getSubjectFromId(db:SQLiteDatabase, subjectId:Int):Subject{

            val cursor = db.query(
                    TABLE_NAME,
                    arrayOf(Columns.SUBJECTID,
                            Columns.SUBJECTNAME,
                            Columns.YEAR,
                            Columns.DEPARTMENT,
                            Columns.TOTALROLLNOS),
                    Columns.SUBJECTID+"=?",
                    arrayOf(subjectId.toString()),
                    null,null,
                    null

            )

            val subjectidCol = cursor.getColumnIndex( Columns.SUBJECTID)
            val subjectnameidCol = cursor.getColumnIndex( Columns.SUBJECTNAME)
            val yearidCol = cursor.getColumnIndex( Columns.YEAR)
            val departmentidCol = cursor.getColumnIndex( Columns.DEPARTMENT)
            val totalrollnoidCol = cursor.getColumnIndex( Columns.TOTALROLLNOS)
            lateinit var subject:Subject
            if(cursor.moveToFirst()){
                 subject = Subject(
                        cursor.getInt(subjectidCol),
                        cursor.getString(subjectnameidCol),
                        cursor.getInt(yearidCol),
                        cursor.getString(departmentidCol),
                        cursor.getInt(totalrollnoidCol)

                )
            }

            return subject
        }

        fun getAllSubjectName(db: SQLiteDatabase):ArrayList<String>{
            val subjectlist = ArrayList<String>()
            val cursor = db.query(
                    TABLE_NAME,
                    arrayOf(Columns.SUBJECTNAME),
                    null,null,null,null,Columns.SUBJECTNAME

            )
            val subjectnameidCol = cursor.getColumnIndex( Columns.SUBJECTNAME)
            while (cursor.moveToNext()) {
                val subjectname  = cursor.getString(subjectnameidCol)

                subjectlist.add(subjectname)
            }

            return subjectlist

        }

         fun deleteRowFromId(db:SQLiteDatabase,subjectId :Int){


             db.delete(TABLE_NAME,"${Columns.SUBJECTID}=?", arrayOf(subjectId.toString()))
        }
        fun editSubject(db:SQLiteDatabase,subject: Subject){
            val row = ContentValues()
            row.put(Columns.SUBJECTID,subject.subjectid)
            row.put(Columns.SUBJECTNAME,subject.subjectname)
            row.put(Columns.YEAR,subject.year)
            row.put(Columns.DEPARTMENT,subject.department)
            row.put(Columns.TOTALROLLNOS,subject.totalrollnos)
            db.update(TABLE_NAME,row,"${Columns.SUBJECTID}="+subject.subjectid,null)
        }
        fun lastSubjectId(db: SQLiteDatabase):Int{

            val query = " SELECT MAX(${Columns.SUBJECTID}) from $TABLE_NAME"
            var cursor =db.rawQuery(query,null)
            var lastSid:Int = 1
            if(cursor.moveToFirst()){
                cursor.moveToFirst()
                lastSid = cursor.getInt(0)
                cursor.close()
            }
            return lastSid

        }
//        fun getsubjectname(subid: Int){
//            "SELECT ${Columns.subjectname} from ${TABLE_NAME} where ${Columns.SUBJECTID}==${subid}"
//         }
        // update subject to be written


        object Columns{
            val SUBJECTID = "subjectId"
            val SUBJECTNAME = "subjectname"
            val YEAR = "year"
            val DEPARTMENT ="department"
            val TOTALROLLNOS = "totalrollnos"
        }

    }
}
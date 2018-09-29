package com.online.attendencehelper.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.online.attendencehelper.R
import com.online.attendencehelper.db.tables.SubjectScheduleTable
import com.online.attendencehelper.db.tables.SubjectTable
import com.online.attendencehelper.db.tables.TableHelper
import com.online.attendencehelper.models.SubjectSchedule
import kotlinx.android.synthetic.main.activity_my_schedule.*

class MySchedule : AppCompatActivity() {
    val tag = "schedule"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_schedule)
        val db = TableHelper(this).writableDatabase
        var  arrayListSubject =  SubjectTable.getAllSubject(db)
        var arrayListSchedule :ArrayList<SubjectSchedule>

        for(subject in arrayListSubject) {

           arrayListSchedule =  SubjectScheduleTable.getScheduleFromSubjectId(db, subject.subjectid!!)
            Log.d(tag,"Subject Name : "+subject.subjectname+"\n")

            for(x in arrayListSchedule){
                Log.d(tag,"Days:"+x.days+"timings"+x.timings+"\n")
            }

        }


    }
}

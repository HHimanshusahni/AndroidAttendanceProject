package com.online.attendencehelper.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.online.attendencehelper.R
import com.online.attendencehelper.db.tables.AttendanceTable
import com.online.attendencehelper.db.tables.TableHelper
import kotlinx.android.synthetic.main.activity_show_attendance.*

class ShowAttendanceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_attendance)

        var attendanceId:Int= getIntent().getIntExtra("ATTENDANCE_ID",0)


        val db = TableHelper(this).readableDatabase
        val attendancelist = AttendanceTable.getAttendanceFromId(db,attendanceId)

        val arrayList = ArrayList<String> ()

        for (i in attendancelist){
            val char:Char
            if(i.present==true)
                char= 'P'
            else
                char = 'A'

            arrayList.add("${i.studentid}           ${char} ")
        }
        val arrayAdapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                arrayList

        )

        lvShowReport.setAdapter(arrayAdapter);

    }

}

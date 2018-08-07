package com.online.attendencehelper.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.online.attendencehelper.R
import com.online.attendencehelper.models.AttendanceRecord
class ShowAttendanceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_attendance)

        var attendanceId:Int= getIntent().getIntExtra("ATTENDANCE_ID",0)
        Log.d("ATTENDANCECHECK","Attendanceid is ${attendanceId}")
        Toast.makeText(this," $attendanceId is the attendanceid",Toast.LENGTH_SHORT).show()
//        val db = TableHelper(this).readableDatabase
//        val attendancelist = AttendanceTable.getAllAttendance(db)
////        tvDate.setText(attendancelist[0].date)
////        tvTime.setText(attendancelist[0].time)
//
//        val arrayList = ArrayList<String> ()
//
//
//
//        for (i in attendancelist){
//
////             arrayList.add( i.studentid.toString()+"   "+i.studentName.toString()+"  "+i.present+" dt"+i.date)
//            val char:Char
//            if(i.present==true)
//                char= 'P'
//            else
//                char = 'A'
//
//            arrayList.add("${i.studentid} ${i.studentName} ${char} ${i.date} ${i.time}")
//        }
//        val arrayAdapter = ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_list_item_1,
//                android.R.id.text1,
//                arrayList
//
//        )
//
//        lvShowReport.setAdapter(arrayAdapter);
//
//    }
    }
}

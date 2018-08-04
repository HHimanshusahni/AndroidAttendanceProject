package com.online.attendencehelper.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.online.attendencehelper.R
import com.online.attendencehelper.adapters.AttendanceRecyclerAdapter
import com.online.attendencehelper.datetime.DatePickerFragment
import com.online.attendencehelper.datetime.DateTime
import com.online.attendencehelper.datetime.TimePickerFragment
import com.online.attendencehelper.db.AttendanceTableHelper
import com.online.attendencehelper.db.tables.AttendanceTable
import com.online.attendencehelper.db.tables.SubjectTable
import com.online.attendencehelper.db.tables.SubjectTableHelper
import com.online.attendencehelper.models.Attendance
import com.online.attendencehelper.models.Subject
import kotlinx.android.synthetic.main.activity_take_attendance.*

class TakeAttendance : AppCompatActivity() {

    var attendance = ArrayList<Attendance>()
    lateinit var attendanceAdapter : AttendanceRecyclerAdapter

    lateinit var actIntent :Intent
    val TAG : String = "TakeAttendance"


    var subjectList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_attendance)

        var  intentThatStartedThisActivity = getIntent()
            var subjectName = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT)
            tvTitleSubject.setText(subjectName)


        // set the date and time from systemtime
        val dateTime= DateTime()
        dateTime.setTime(btnAttendanceTime)
        dateTime.setDate(btnAttendanceDate)


        // date and timepicker dialog
        btnAttendanceTime.setOnClickListener{
            val newFragment = TimePickerFragment(btnAttendanceTime)
            newFragment.show(fragmentManager,"Time Picker")
        }


        btnAttendanceDate.setOnClickListener{
            val newFragment2 = DatePickerFragment(btnAttendanceDate)
            newFragment2.show(fragmentManager,"Date Picker")

        }

            // storing in tables

        attendance = createArrayListAttendance(attendance,20)


        val db = AttendanceTableHelper(this).writableDatabase

        rvAttendance.layoutManager = LinearLayoutManager(this)

        attendanceAdapter = AttendanceRecyclerAdapter(attendance)
        rvAttendance.adapter = attendanceAdapter


        fun refreshAttendance(){
            attendance.clear()
            attendance.addAll(AttendanceTable.getAllAttendance(db))
            attendanceAdapter.notifyDataSetChanged()
        }


//    val onAttendanceUpdateName = {
//        attendance:Attendance ->
//        AttendanceTable.updateStudentName(db,attendance)
//        refreshAttendance()
//    }

        btnClear.setOnClickListener{
            for(i in attendance.indices){
                attendance[i].present = false
                attendanceAdapter.notifyDataSetChanged()
            }

        }
        btnMarkAll.setOnClickListener{
            for(i in attendance.indices){
                attendance[i].present = true
                attendanceAdapter.notifyDataSetChanged()
            }

        }

        // submit

        btnFabSubmit.setOnClickListener {


            // code to submint date in datebase
//            attendance.addAll(AttendanceTable.getAllAttendance(db))
            for(i in attendance)
               AttendanceTable.addAttendance(db,i)

            actIntent = Intent(this, MainActivity::class.java)
            startActivity(actIntent)
            Toast.makeText(this,"Attendance Submitted",Toast.LENGTH_SHORT).show()
        }




    }

    private fun createArrayListAttendance(attendance: ArrayList<Attendance>,Number:Int) :ArrayList<Attendance>{


        for (i in 1..Number){

            attendance.add(Attendance(
                    0,
                         i,
                    "Student $i",
                    false,
                    btnAttendanceTime.text.toString(),
                    btnAttendanceDate.text.toString())
            )
        }
        return attendance
    }

}



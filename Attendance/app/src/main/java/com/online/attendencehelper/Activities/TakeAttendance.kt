package com.online.attendencehelper.Activities

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.online.attendencehelper.R
import com.online.attendencehelper.adapters.AttendanceRecyclerAdapter
import com.online.attendencehelper.datetime.DatePickerFragment
import com.online.attendencehelper.datetime.DateTime
import com.online.attendencehelper.datetime.TimePickerFragment
import com.online.attendencehelper.db.tables.AttendanceRecordTable
import com.online.attendencehelper.db.tables.AttendanceTable
import com.online.attendencehelper.db.tables.TableHelper
import com.online.attendencehelper.models.Attendance
import com.online.attendencehelper.models.AttendanceRecord
import com.online.attendencehelper.models.Student
import com.online.attendencehelper.models.Subject
import kotlinx.android.synthetic.main.activity_take_attendance.*

class TakeAttendance : AppCompatActivity() {

    var attendancelist = ArrayList<Attendance>()
    lateinit var attendanceAdapter : AttendanceRecyclerAdapter
    lateinit var actIntent :Intent
    lateinit var subject:Subject
    lateinit var attendanceTable:AttendanceTable

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_attendance)

        subject = getIntent().getSerializableExtra("serialize_data") as Subject
        tvTitleSubject.setText(subject.subjectname)



        val db = TableHelper(this).writableDatabase
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


       val lastAttendanceId =  AttendanceRecordTable.lastAttendanceId(db)
        attendancelist = createArrayListAttendance(attendancelist,subject, lastAttendanceId+1)
        rvAttendance.layoutManager = LinearLayoutManager(this)
        attendanceAdapter = AttendanceRecyclerAdapter(attendancelist)
        rvAttendance.adapter = attendanceAdapter





        btnClear.setOnClickListener{
            for(i in attendancelist.indices){
                attendancelist[i].present = false
                attendanceAdapter.notifyDataSetChanged()
            }

        }
        btnMarkAll.setOnClickListener{
            for(i in attendancelist.indices){
                attendancelist[i].present = true
                attendanceAdapter.notifyDataSetChanged()
            }

        }

        btnFabSubmit.setOnClickListener {
            submitData(db)

            actIntent = Intent(this, MainActivity::class.java)
            startActivity(actIntent)
            Toast.makeText(this,"Attendance Submitted",Toast.LENGTH_SHORT).show()
        }




    }

    // Submitting in the database
    private fun submitData(db:SQLiteDatabase){

        var attendanceRecord = AttendanceRecord(
                null,
                subject.subjectid!!,
                btnAttendanceTime.text.toString(),
                btnAttendanceDate.text.toString()
        )
        AttendanceRecordTable.addAttendanceRecord(db,attendanceRecord)
        for(i in attendancelist)
            AttendanceTable.addAttendance(db,i)


    }

    private fun createArrayListAttendance(attendance: ArrayList<Attendance>,subject:Subject,attendanceid:Int) :ArrayList<Attendance>{


        for (i in 1..subject.totalrollnos){

            attendance.add(Attendance(
                    attendanceid ,
                    i,
                    false
            ))
        }
        return attendance
    }

}



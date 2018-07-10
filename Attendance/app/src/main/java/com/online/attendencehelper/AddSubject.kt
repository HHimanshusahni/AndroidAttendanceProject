package com.online.attendencehelper


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.online.attendencehelper.datetime.DatePickerFragment
import com.online.attendencehelper.datetime.DateTime
import com.online.attendencehelper.datetime.TimePickerFragment
import com.online.attendencehelper.db.AttendanceTableHelper
import com.online.attendencehelper.db.tables.SubjectTable
import com.online.attendencehelper.db.tables.SubjectTableHelper
import com.online.attendencehelper.models.Attendance
import com.online.attendencehelper.models.Subject

import kotlinx.android.synthetic.main.activity_add_subject.*
import kotlinx.android.synthetic.main.activity_take_attendance.*

class AddSubject : AppCompatActivity() {


    lateinit  var actIntent: Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_subject)

        val datetime = DateTime()
        datetime.setTime(btnTime)

        btnTime.setOnClickListener {
            // Initialize a new TimePickerFragment
            val newFragment = TimePickerFragment(btnTime)
            // Show the time picker dialog
            newFragment.show(fragmentManager, "Time Picker")
        }

        spinnerDays()

        // store in tables

        btnSubmitSubject.setOnClickListener{
            var subject:Subject = Subject(null,
                            etSubjectName.text.toString(),
                            Integer.valueOf(etYear.text.toString()),
                            etDepartment.text.toString(),
                            Integer.valueOf( etRollNo.text.toString())
            )

            val db = AttendanceTableHelper(this).writableDatabase
            SubjectTable.addSubject(db,subject)
            actIntent = Intent(this, MainActivity::class.java)
            startActivity(actIntent)
            Toast.makeText(this,"Subject Added", Toast.LENGTH_SHORT).show()


        }


    }



    private fun spinnerDays(){
        //https://developer.android.com/guide/topics/ui/controls/spinner
        val spinner : Spinner = findViewById(R.id.spinnerDays)
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
                this,
                R.array.days_array,
                android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.setAdapter(adapter)

    }

}

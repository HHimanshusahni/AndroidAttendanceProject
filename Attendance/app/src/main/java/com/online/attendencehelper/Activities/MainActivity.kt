package com.online.attendencehelper.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.online.attendencehelper.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var actIntent : Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       btnAddSubject.setOnClickListener({
            actIntent = Intent(this, AddSubject::class.java)
            startActivity(actIntent)
        })

        btnAttendance.setOnClickListener({
            actIntent = Intent(this, TakeAttendance::class.java)
            startActivity(actIntent)

        })

        btnShowSchedule.setOnClickListener({
            actIntent = Intent(this, MySchedule::class.java)
            startActivity(actIntent)

        })
        btnShowAttendance.setOnClickListener({
            actIntent = Intent(this, ShowAttendanceReport::class.java)
            startActivity(actIntent)
        })
        btnShowSubject.setOnClickListener{
            actIntent = Intent(this, ShowSubject::class.java)
            startActivity(actIntent)
        }
    }
}

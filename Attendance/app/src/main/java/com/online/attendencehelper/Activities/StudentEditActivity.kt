package com.online.attendencehelper.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.online.attendencehelper.R
import com.online.attendencehelper.adapters.StudentEditableAdapter
import com.online.attendencehelper.db.tables.StudentTable
import com.online.attendencehelper.db.tables.TableHelper
import kotlinx.android.synthetic.main.activity_student_edit.*

class StudentEditActivity : AppCompatActivity() {

    lateinit var StudentEditableAdapter:StudentEditableAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = TableHelper(this).readableDatabase

        var subjectId:Int = getIntent().getIntExtra("SubjectIdForStudentName",0)
        val studentList = StudentTable.getStudentListFromSubjectId(db,subjectId)

        setContentView(R.layout.activity_student_edit)
        rvShowStudentName.layoutManager = LinearLayoutManager(this)
        StudentEditableAdapter = StudentEditableAdapter(studentList)
        rvShowStudentName.adapter = StudentEditableAdapter

    }
}

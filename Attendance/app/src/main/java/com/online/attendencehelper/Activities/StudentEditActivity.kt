package com.online.attendencehelper.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.online.attendencehelper.R
import com.online.attendencehelper.adapters.StudentEditableAdapter
import com.online.attendencehelper.db.tables.StudentTable
import com.online.attendencehelper.db.tables.SubjectTable
import com.online.attendencehelper.db.tables.TableHelper
import com.online.attendencehelper.models.Student
import kotlinx.android.synthetic.main.activity_student_edit.*
import kotlinx.android.synthetic.main.list_studentlist.view.*

class StudentEditActivity : AppCompatActivity() {

    lateinit var StudentEditableAdapter:StudentEditableAdapter
    lateinit var actIntent: Intent
    var studentList = ArrayList<Student>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = TableHelper(this).readableDatabase

        var subjectId:Int = getIntent().getIntExtra("SubjectIdForStudentName",0)
        studentList = StudentTable.getStudentListFromSubjectId(db,subjectId)

        setContentView(R.layout.activity_student_edit)
        rvShowStudentName.layoutManager = LinearLayoutManager(this)
        StudentEditableAdapter = StudentEditableAdapter(studentList)
        rvShowStudentName.adapter = StudentEditableAdapter
        btnFabSubmit.setOnClickListener{
           var limit =  studentList.size
            Log.d("EDIT","The value  of the limit is"+limit)
            for(student in studentList){


                rvShowStudentName.findViewHolderForAdapterPosition(student.studentId)
                StudentTable.changeStudentname(db,subjectId,student.studentId,"${student.studentName}")
                Log.d("EDIT","For loop"+student.studentName+"\n")
             }
            Log.d("EDIT","After the Loop")
            actIntent = Intent(this,MainActivity::class.java)
            startActivity(actIntent)


        }
        rvShowStudentName.setOnClickListener{

        }

    }
}

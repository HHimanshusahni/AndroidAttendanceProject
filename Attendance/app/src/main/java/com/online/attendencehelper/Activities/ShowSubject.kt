package com.online.attendencehelper.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import com.online.attendencehelper.R
import com.online.attendencehelper.adapters.ShowSubjectRecyclerAdapter
import com.online.attendencehelper.db.tables.SubjectTable
import com.online.attendencehelper.db.tables.SubjectTableHelper
import com.online.attendencehelper.models.Subject
import kotlinx.android.synthetic.main.activity_show_subject.*

class ShowSubject : AppCompatActivity() {
    var subjectList = ArrayList<Subject>()
    lateinit var ShowSubjectAdapter :ShowSubjectRecyclerAdapter
    lateinit var subjectObject: Subject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_subject)

        val db = SubjectTableHelper(this).readableDatabase
        val subject = SubjectTable.getAllSubject(db)

        for (i in subject){

            subjectList.add(Subject(i.subjectid,i.subjectname,i.year,i.department,i.totalrollnos))
        }
        rvShowSubject.layoutManager = LinearLayoutManager(this)
        ShowSubjectAdapter = ShowSubjectRecyclerAdapter(subjectList)
        rvShowSubject.adapter = ShowSubjectAdapter


    }
}

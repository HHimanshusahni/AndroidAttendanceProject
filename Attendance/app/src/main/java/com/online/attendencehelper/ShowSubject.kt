package com.online.attendencehelper

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.online.attendencehelper.db.tables.SubjectTable
import com.online.attendencehelper.db.tables.SubjectTableHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_show_subject.*
import javax.security.auth.Subject

class ShowSubject : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_subject)

        val db = SubjectTableHelper(this).readableDatabase
        val subject = SubjectTable.getAllSubject(db)

        val arrayList = ArrayList<String> ()

        for (i in subject){

//             arrayList.add( i.studentid.toString()+"   "+i.studentName.toString()+"  "+i.present+" dt"+i.date)

            arrayList.add("${i.subjectname} ${i.department} ${i.year} ${i.totalrollnos} ")
        }
        val arrayAdapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                arrayList

        )

        lvShowSubject.setAdapter(arrayAdapter);

    }
}

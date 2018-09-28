package com.online.attendencehelper.Activities


import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.online.attendencehelper.R
import com.online.attendencehelper.datetime.DateTime
import com.online.attendencehelper.datetime.TimePickerFragment
import com.online.attendencehelper.db.tables.StudentTable
import com.online.attendencehelper.db.tables.SubjectTable
import com.online.attendencehelper.db.tables.TableHelper
import com.online.attendencehelper.models.Student
import com.online.attendencehelper.models.Subject

import kotlinx.android.synthetic.main.activity_add_subject.*
import kotlinx.android.synthetic.main.activity_add_subject.view.*
import android.widget.TextView



class AddSubject : AppCompatActivity() {


    lateinit  var actIntent: Intent
    lateinit var linearLayoutNewView :LinearLayout
    lateinit var btn :Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_subject)
        var subjectid:Int? =getIntent().getIntExtra("subject_id",-1)
        Log.d("SUBB","$subjectid")
        if(subjectid!=-1&&subjectid!=null) {

            editSubject(subjectid!!)
        }else{
            subjectid =null
        }
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

        btnAddNew.setOnClickListener{
           addDynamicLayoutSchedule()

        }
        btnSubmitSubject.setOnClickListener{

            if(etSubjectName.text.toString().length==0||
                    etDepartment.text.toString().length==0||
                    etRollNo.text.toString().length==0||
                    etYear.text.toString().length==0){
                Toast.makeText(this,"Empty Field!!",Toast.LENGTH_SHORT).show()
            }else {

                var subject: Subject = Subject(subjectid,
                        etSubjectName.text.toString(),
                        Integer.valueOf(etYear.text.toString()),
                        etDepartment.text.toString(),
                        Integer.valueOf(etRollNo.text.toString())
                )
                val db = TableHelper(this).writableDatabase


                if(subjectid!=-1&&subjectid!=null) {
                    SubjectTable.editSubject(db, subject)
                }else{
                    SubjectTable.addSubject(db,subject)

                    val lastSubjectId = SubjectTable.lastSubjectId(db)
                    val studentList =  ArrayList<Student>()
                    for( i  in 1..subject.totalrollnos){
                        studentList.add( Student(i,"Student"+i, lastSubjectId))
                    }
                    StudentTable.addStudents(db,studentList)
                }
                // Modify Code  for case Rollno's entered as different range of roll no's required and skipped  rollno's

                actIntent = Intent(this, MainActivity::class.java)
                startActivity(actIntent)
                Toast.makeText(this, "Subject Added", Toast.LENGTH_SHORT).show()
            }

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
    private fun editSubject(subjectId:Int){
        val db = TableHelper(this).readableDatabase
        val subject = SubjectTable.getSubjectFromId(db, subjectId)
        etSubjectName.setText(subject.subjectname)
        etDepartment.setText(subject.department)
        etRollNo.setText(""+subject.totalrollnos)
        etYear.setText(""+subject.year)

    }
    private fun addDynamicLayoutSchedule(){
        var linearLayout = LinearLayout(this)
        linearLayoutNewView = findViewById(R.id.parentLinearSchdule)


        var buttontime = Button(this)
        val datatime = DateTime()
        buttontime.setText(datatime.getTime())
        buttontime.setOnClickListener{
            val newFragment = TimePickerFragment(buttontime)
            // Show the time picker dialog
            newFragment.show(fragmentManager, "Time Picker")
        }



        val spinner  = Spinner(this)
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
                this,
                R.array.days_array,
                android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.setAdapter(adapter)



        var deleteButton = Button(this)
        deleteButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_delete_black_24dp, 0, 0, 0);
        deleteButton.setBackgroundColor(Color.TRANSPARENT)
        linearLayout.setOrientation(LinearLayout.HORIZONTAL)
        linearLayout.addView(buttontime)
        linearLayout.addView(spinner)
        linearLayout.addView(deleteButton)
        linearLayoutNewView.addView(linearLayout)

    }

}

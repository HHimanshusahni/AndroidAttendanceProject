    package com.online.attendencehelper.Activities


    import android.content.Context
    import android.content.Intent
    import android.support.v7.app.AppCompatActivity
    import android.os.Bundle
    import android.view.View
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
    import android.view.LayoutInflater
    import kotlinx.android.synthetic.main.schedule_field.view.*


    class AddSubject : AppCompatActivity() {


        lateinit  var actIntent: Intent;
        lateinit var  parentLinearLayout:LinearLayout


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_add_subject)

            parentLinearLayout = LinearLayout(this)
            parentLinearLayout= findViewById(R.id.parentLinearSchdule)

            var subjectid:Int? =getIntent().getIntExtra("subject_id",-1)
            if(subjectid!=-1&&subjectid!=null) {

                editSubject(subjectid!!)
            }else{
                subjectid =null
            }


            onAddField(findViewById(R.id.btnAddNew))// adding the first schdule detail

            btnSubmitSubject.setOnClickListener{

                submitSubject(subjectid)


            }


        }




        fun submitSubject(subjectid: Int?){

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



         fun editSubject(subjectId:Int){
            val db = TableHelper(this).readableDatabase
            val subject = SubjectTable.getSubjectFromId(db, subjectId)
            etSubjectName.setText(subject.subjectname)
            etDepartment.setText(subject.department)
            etRollNo.setText(""+subject.totalrollnos)
            etYear.setText(""+subject.year)

        }




        fun onAddField( view : View){

            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val rowView = inflater.inflate(R.layout.schedule_field, null)
            parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount())

            val datetime = DateTime()
            datetime.setTime(parentLinearLayout.getChildAt(parentLinearLayout.childCount-1).btnTime)


        }



        fun onDelete(view :View){
            parentLinearLayout.removeView(view.getParent()as View)

        }


        fun showTimePicker(view:View){

            val newFragment = TimePickerFragment(view as TextView)
            newFragment.show(fragmentManager, "Time Picker")
        }

    }

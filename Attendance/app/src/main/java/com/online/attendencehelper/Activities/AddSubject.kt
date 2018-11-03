        package com.online.attendencehelper.Activities

// All the work of adding the subject and editing the subject is done in this activity

        import android.content.Context
        import android.content.Intent
        import android.database.sqlite.SQLiteDatabase
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
        import com.online.attendencehelper.db.tables.SubjectScheduleTable
        import com.online.attendencehelper.models.SubjectSchedule
        import kotlinx.android.synthetic.main.schedule_field.view.*

        class AddSubject : AppCompatActivity() {


            lateinit  var actIntent: Intent;
            lateinit var  parentLinearLayout:LinearLayout
            lateinit var arrayListOfViewSchedule: ArrayList<View> // arraylist for dynamically created views of schedule


            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_add_subject)
                arrayListOfViewSchedule = ArrayList()
                parentLinearLayout = LinearLayout(this)
                parentLinearLayout= findViewById(R.id.parentLinearSchdule)

                var subjectid:Int? =getIntent().getIntExtra("subject_id",-1)
                if(subjectid!=-1&&subjectid!=null) { // The edit subject work has to be done
                    setTitle("EDIT SUBJECT")
                    editSubject(subjectid!!)

                }else{
                    subjectid =null
                    onAddField(findViewById(R.id.btnAddNew))// adding the first schdule detail

                }



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
                        submitInScheduleTableOnEdit(db,arrayListOfViewSchedule,subjectid)

                    }else{
                        SubjectTable.addSubject(db,subject)
                        val lastSubjectId = SubjectTable.lastSubjectId(db)
                        val studentList =  ArrayList<Student>()
                        for( i  in 1..subject.totalrollnos){
                            studentList.add( Student(i,"Student"+i, lastSubjectId))

                        }
                        StudentTable.addStudents(db,studentList)
                        submitInScheduleTable(db,arrayListOfViewSchedule);
                    }


                    // Modify Code  for case Rollno's entered as different range of roll no's required and skipped  rollno's
                    actIntent = Intent(this, MainActivity::class.java)
                    startActivity(actIntent)
                    if(subjectid==-1||subjectid==null){
                        Toast.makeText(this, "Subject Added", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this, "Subject Edited", Toast.LENGTH_SHORT).show()
                    }
                }
            }



             fun editSubject(subjectId:Int){
                val db = TableHelper(this).readableDatabase
                val subject = SubjectTable.getSubjectFromId(db, subjectId)
                etSubjectName.setText(subject.subjectname)
                etDepartment.setText(subject.department)
                etRollNo.setText(""+subject.totalrollnos)
                etYear.setText(""+subject.year)

                 var subjectScheduleList:ArrayList<SubjectSchedule> =  SubjectScheduleTable.getScheduleFromSubjectId(db,subjectId)
                 SubjectScheduleTable.deleteScheduleFromSubjectId(db,subjectId)

                 for(subjectSchedule in subjectScheduleList) {
                     val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                     val rowView = inflater.inflate(R.layout.schedule_field, null)
                     parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount())
                     rowView.btnTime.setText(subjectSchedule.timings)


                     when(subjectSchedule.days){
                         "Monday" ->rowView.spinnerDays.setSelection(0)
                         "Tuesday" ->rowView.spinnerDays.setSelection(1)
                         "Wednesday" ->rowView.spinnerDays.setSelection(2)
                         "Thursday" ->rowView.spinnerDays.setSelection(3)
                         "Friday" ->rowView.spinnerDays.setSelection(4)
                         "Saturday" ->rowView.spinnerDays.setSelection(5)
                         "Sunday" ->rowView.spinnerDays.setSelection(6)
                     }

                     arrayListOfViewSchedule.add(rowView)


                 }



            }


            fun submitInScheduleTable(db:SQLiteDatabase,arrayListView:ArrayList<View>){
                for(view in arrayListView){
                    var subjectSchedule: SubjectSchedule = SubjectSchedule(
                            SubjectTable.lastSubjectId(db),
                            view.btnTime.text.toString(),
                            view.spinnerDays.selectedItem.toString())
                    SubjectScheduleTable.addSchedule(db,subjectSchedule)


                }
            }
            fun submitInScheduleTableOnEdit(db:SQLiteDatabase,arrayListView:ArrayList<View>,subjectId: Int){
                    for(view in arrayListView){
                        var subjectSchedule: SubjectSchedule = SubjectSchedule(
                                subjectId,
                                view.btnTime.text.toString(),
                                view.spinnerDays.selectedItem.toString())
                        SubjectScheduleTable.addSchedule(db,subjectSchedule)


                    }
            }
        // function gets called to add new schedule button is pressed and creates the view for it
            fun onAddField( view : View){

                val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val rowView = inflater.inflate(R.layout.schedule_field, null)
                parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount())

                val datetime = DateTime()
                datetime.setTime(parentLinearLayout.getChildAt(parentLinearLayout.childCount-1).btnTime)

                arrayListOfViewSchedule.add(rowView)


            }



// It deletes the shedule view from the UI when delete icon button is  pressed
            fun onDelete(view :View){
                arrayListOfViewSchedule.remove(view.getParent()as View)
                parentLinearLayout.removeView(view.getParent()as View)

            }


            fun showTimePicker(view:View){

                val newFragment = TimePickerFragment(view as TextView)
                newFragment.show(fragmentManager, "Time Picker")
            }

        }

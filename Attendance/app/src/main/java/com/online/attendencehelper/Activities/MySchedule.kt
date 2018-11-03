        package com.online.attendencehelper.Activities

        import android.database.sqlite.SQLiteDatabase
        import android.support.v7.app.AppCompatActivity
        import android.os.Bundle
        import android.util.Log
        import android.view.View
        import com.online.attendencehelper.R
        import com.online.attendencehelper.db.tables.SubjectScheduleTable
        import com.online.attendencehelper.db.tables.SubjectTable
        import com.online.attendencehelper.db.tables.TableHelper
        import com.online.attendencehelper.models.SubjectSchedule
        import kotlinx.android.synthetic.main.activity_my_schedule.*

        class MySchedule : AppCompatActivity() {
            val tag = "schedule"
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_my_schedule)
                val db = TableHelper(this).writableDatabase
                var arrayListSubject = SubjectTable.getAllSubject(db)
                var arrayListSchedule: ArrayList<SubjectSchedule>

                for (subject in arrayListSubject) {

                    arrayListSchedule = SubjectScheduleTable.getScheduleFromSubjectId(db, subject.subjectid!!)
                    Log.d(tag, "Subject Name : " + subject.subjectname + "\n")

                    for (x in arrayListSchedule) {
                        Log.d(tag, "Days:" + x.days + "timings" + x.timings + "\n")
                    }

                }
                btnMonday.setOnClickListener {
                    schedule(db, "Monday")
                }
                btnTuesday.setOnClickListener {
                    schedule(db, "Tuesday")

                }
                btnWednesday.setOnClickListener {
                    schedule(db, "Wednesday")
                }
                btnThursday.setOnClickListener {
                    schedule(db, "Thursday")
                }
                btnFriday.setOnClickListener {
                    schedule(db, "Friday")
                }
                btnSaturday.setOnClickListener {
                    schedule(db, "Saturday")
                }
                btnSunday.setOnClickListener {
                    schedule(db, "Sunday")
                }


            }

            fun schedule(db: SQLiteDatabase, day: String) {
                var ListSchedule: ArrayList<SubjectSchedule>
                ListSchedule = SubjectScheduleTable
                        .getScheduleFromSubjectDays(db, day)
                if (ListSchedule.size != 0) {
                    var temp: String = SubjectTable.getSubjectFromId(db, ListSchedule[0].subjectId!!).subjectname!! + "\n"
                    var subname = SubjectTable.getSubjectFromId(db, ListSchedule[0].subjectId!!).subjectname!!

                    for (schedule in ListSchedule) {

                        if (subname.equals(SubjectTable.getSubjectFromId(db, schedule.subjectId!!).subjectname!!))
                            temp = temp + schedule.timings + "\n"
                        else {
                            subname = SubjectTable.getSubjectFromId(db, schedule.subjectId!!).subjectname!!
                            temp = temp + schedule.timings + "\n" + subname + "\n"
                        }

                    }
                    tvDemoSchedule.setText(temp)
                } else {
                    tvDemoSchedule.setText("No schedule")
                }

            }
        }

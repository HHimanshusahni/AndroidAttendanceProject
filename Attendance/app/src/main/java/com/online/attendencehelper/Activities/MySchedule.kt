    package com.online.attendencehelper.Activities

    import android.content.Context
    import android.database.sqlite.SQLiteDatabase
    import android.support.v7.app.AppCompatActivity
    import android.os.Bundle
    import android.util.Log
    import android.view.View
    import com.online.attendencehelper.R
    import com.online.attendencehelper.R.id.tvMonday
    import com.online.attendencehelper.adapters.MyPagerAdapter
    import com.online.attendencehelper.db.tables.SubjectScheduleTable
    import com.online.attendencehelper.db.tables.SubjectTable
    import com.online.attendencehelper.db.tables.TableHelper
    import com.online.attendencehelper.fragmentsschedule.MondayFragment
    import com.online.attendencehelper.models.SubjectSchedule
    import kotlinx.android.synthetic.main.activity_my_schedule.*
    import kotlinx.android.synthetic.main.fragment_monday.*

    class MySchedule : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            val tag = "schedule"
            super.onCreate(savedInstanceState)

            setContentView(R.layout.activity_my_schedule)

            // SETTING UP THE FRAGMENTS
            val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
            viewpager_main.adapter = fragmentAdapter

            tabs_main.setupWithViewPager(viewpager_main)



        }

        companion object {


            fun schedule(context: Context, day: String): String {
                val db = TableHelper(context).writableDatabase

                var ListSchedule: ArrayList<SubjectSchedule>

                ListSchedule = SubjectScheduleTable
                        .getScheduleFromSubjectDays(db, day)

                var temp: String = ""

                if (ListSchedule.size != 0) {
                    temp = SubjectTable.getSubjectFromId(db, ListSchedule[0].subjectId!!).subjectname!! + "\n"
                    var subname = SubjectTable.getSubjectFromId(db, ListSchedule[0].subjectId!!).subjectname!!

                    for (schedule in ListSchedule) {

                        if (subname.equals(SubjectTable.getSubjectFromId(db, schedule.subjectId!!).subjectname!!))
                            temp = temp + schedule.timings + "\n"
                        else {
                            subname = SubjectTable.getSubjectFromId(db, schedule.subjectId!!).subjectname!!
                            temp = temp +"\n"+subname+"\n" + schedule.timings+"\n"
                        }

                    }
                } else {
                    temp = "No schedule"
                }
                return temp
            }
        }
    }



package com.online.attendencehelper.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.online.attendencehelper.R
import com.online.attendencehelper.adapters.ShowSubjectRecyclerAdapter
import com.online.attendencehelper.db.tables.SubjectTable
import com.online.attendencehelper.db.tables.SubjectTableHelper
import com.online.attendencehelper.models.Subject
import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.android.synthetic.main.activity_show_subject.*
import kotlinx.android.synthetic.main.app_bar_nav.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var actIntent : Intent


    var subjectList = ArrayList<Subject>()
    lateinit var ShowSubjectAdapter : ShowSubjectRecyclerAdapter
    lateinit var subjectObject: Subject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        showSubject()

        btnFabAddSub.setOnClickListener{

            actIntent = Intent(this, AddSubject::class.java)
            startActivity(actIntent)
        }
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.nav, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {

            R.id.nav_schedule -> {

                actIntent = Intent(this, MySchedule::class.java)
                startActivity(actIntent)

            }
            R.id.nav_add_subject -> {
                actIntent = Intent(this, AddSubject::class.java)
                startActivity(actIntent)

            }
            R.id.nav_report -> {
                actIntent = Intent(this, ShowAttendanceReport::class.java)
                startActivity(actIntent)

            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun showSubject() {
        val db = SubjectTableHelper(this).readableDatabase
        val subject = SubjectTable.getAllSubject(db)

        for (i in subject){

            subjectList.add(Subject(i.subjectid,i.subjectname,i.year,i.department,i.totalrollnos))
        }
        rvShowSubject.layoutManager = LinearLayoutManager(this)
        ShowSubjectAdapter = ShowSubjectRecyclerAdapter(subjectList,{subject -> subjectItemClicked(subject) })
        rvShowSubject.adapter = ShowSubjectAdapter


    }
    private fun subjectItemClicked(subject: Subject){

        actIntent = Intent(this,TakeAttendance::class.java)
        actIntent.putExtra("serialize_data",subject)
        startActivity(actIntent)



    }

}
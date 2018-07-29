package com.online.attendencehelper.Activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.online.attendencehelper.R
import kotlinx.android.synthetic.main.activity_nav.*
import kotlinx.android.synthetic.main.app_bar_nav.*

class navActi : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var actIntent : Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
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
            R.id.nav_take_attendance -> {
                // Handle the camera action
                actIntent = Intent(this, TakeAttendance::class.java)
                startActivity(actIntent)
            }
            R.id.nav_schedule -> {

                actIntent = Intent(this, MySchedule::class.java)
                startActivity(actIntent)

            }
            R.id.nav_add_subject -> {
                actIntent = Intent(this, AddSubject::class.java)
                startActivity(actIntent)

            }
            R.id.nav_show_subject-> {
                actIntent = Intent(this, ShowSubject::class.java)
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
}

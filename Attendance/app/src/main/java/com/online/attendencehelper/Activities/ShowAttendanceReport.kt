package com.online.attendencehelper.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.ArrayAdapter
import com.online.attendencehelper.R
import com.online.attendencehelper.R.layout.activity_show_attendance_report
import com.online.attendencehelper.adapters.ShowAttendanceReportRecyclerAdapter
import com.online.attendencehelper.adapters.ShowSubjectRecyclerAdapter
import com.online.attendencehelper.db.tables.AttendanceRecordTable
import com.online.attendencehelper.db.tables.AttendanceTable
import com.online.attendencehelper.db.tables.TableHelper
import com.online.attendencehelper.models.Attendance
import com.online.attendencehelper.models.AttendanceRecord
import kotlinx.android.synthetic.main.activity_show_attendance_report.*

class ShowAttendanceReport : AppCompatActivity() {

    lateinit var showAttendanceAdapter:ShowAttendanceReportRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(activity_show_attendance_report)

        val db = TableHelper(this).readableDatabase
        val attendanceRecordList = AttendanceRecordTable.getAllAttendanceRecord(db)

        rvShowReport.layoutManager = LinearLayoutManager(this)
        showAttendanceAdapter = ShowAttendanceReportRecyclerAdapter(attendanceRecordList)
        rvShowReport.adapter = showAttendanceAdapter



    }
}

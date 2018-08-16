package com.online.attendencehelper.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.online.attendencehelper.R
import com.online.attendencehelper.R.layout.activity_show_attendance_report
import com.online.attendencehelper.adapters.ShowAttendanceReportRecyclerAdapter
import com.online.attendencehelper.adapters.ShowSubjectRecyclerAdapter
import com.online.attendencehelper.db.tables.AttendanceRecordTable
import com.online.attendencehelper.db.tables.AttendanceTable
import com.online.attendencehelper.db.tables.TableHelper
import com.online.attendencehelper.models.Attendance
import com.online.attendencehelper.models.AttendanceRecord
import com.online.attendencehelper.models.Subject
import kotlinx.android.synthetic.main.activity_show_attendance_report.*

class ShowAttendanceReport : AppCompatActivity() {
    lateinit var actIntent :Intent
    lateinit var showAttendanceAdapter:ShowAttendanceReportRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(activity_show_attendance_report)

        val db = TableHelper(this).readableDatabase
        val attendanceRecordList = AttendanceRecordTable.getAllAttendanceRecord(db)

        rvShowReport.layoutManager = LinearLayoutManager(this)
        showAttendanceAdapter = ShowAttendanceReportRecyclerAdapter(
                attendanceRecordList,
                {attendancerecord: AttendanceRecord -> AttendanceRecordItemClicked(attendancerecord)},
                {Int ->onDeleteButtonClicked(Int)}
        )
        rvShowReport.adapter = showAttendanceAdapter



    }
    private fun AttendanceRecordItemClicked(attendanceRecord: AttendanceRecord){

        actIntent = Intent(this,ShowAttendanceActivity::class.java)
        actIntent.putExtra("ATTENDANCE_ID",attendanceRecord.attendanceid)
        Log.d("ATTENDANCECHECK","Attendanceid is ${attendanceRecord.attendanceid}")
        startActivity(actIntent)



    }
    private fun onDeleteButtonClicked(attendanceId:Int){
        var builder = AlertDialog.Builder(this)
        builder.setMessage("Do you want to delete the report")
                .setPositiveButton("OK",{dialog, which ->
                    val db = TableHelper(this).writableDatabase
                    AttendanceRecordTable.deleteRowFromAttendanceId(db,attendanceId)
                    AttendanceTable.deleteRowFromAttendanceId(db,attendanceId)
                    Toast.makeText(this,"Report Deleted ",Toast.LENGTH_SHORT).show()
                    this.recreate()

                })
                .setNegativeButton("Cancel",{dialog, which ->
                    Toast.makeText(this,"Cancelled",Toast.LENGTH_SHORT).show()
                })
                .create()
                .show()


    }

}

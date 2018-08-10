package com.online.attendencehelper.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.online.attendencehelper.R
import com.online.attendencehelper.db.tables.SubjectTable
import com.online.attendencehelper.db.tables.TableHelper
import com.online.attendencehelper.models.Attendance
import com.online.attendencehelper.models.AttendanceRecord
import com.online.attendencehelper.models.Subject
import kotlinx.android.synthetic.main.list_item_show_attendance_report.view.*

class ShowAttendanceReportRecyclerAdapter(
        val  reports :ArrayList<AttendanceRecord>,
        val clickListener: (AttendanceRecord) -> Unit,
        val reportDeleteListener: (Int)-> Unit
)
    :RecyclerView.Adapter<ShowAttendanceReportRecyclerAdapter.ShowAttendanceReportViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShowAttendanceReportViewHolder {

        val li = parent!!.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
        val itemView = li.inflate(R.layout.list_item_show_attendance_report,parent,false)
        return ShowAttendanceReportViewHolder(itemView)
    }

    override fun getItemCount(): Int = reports.size

    override fun onBindViewHolder(holder: ShowAttendanceReportViewHolder?, position: Int) {
        (holder as ShowAttendanceReportViewHolder).bind(reports[position],clickListener,reportDeleteListener)
    }


    class ShowAttendanceReportViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {



        fun bind(attendancerecord:AttendanceRecord,
                 clickListener:(AttendanceRecord)->Unit,
                 reportDeleteListener: (Int) -> Unit)
        {

            val db = TableHelper(itemView.context).readableDatabase
            val subject = SubjectTable.getSubjectFromId(db,attendancerecord.subjectid)
            itemView.tvSubjectName.text = subject.subjectname
            itemView.tvBranch.text = subject.department
            itemView.tvyear.text = subject.year.toString()
            itemView.tvDate.text = attendancerecord.attendancedate
            itemView.tvTime.text = attendancerecord.attendacetime
            itemView.setOnClickListener{clickListener(attendancerecord) }
            itemView.btnAttendanceRecoreDelete.setOnClickListener{reportDeleteListener(attendancerecord.attendanceid!!)}
        }
    }


}


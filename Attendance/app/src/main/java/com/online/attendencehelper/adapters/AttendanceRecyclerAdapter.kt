

package com.online.attendencehelper.adapters

import android.content.Context
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.online.attendencehelper.R
import com.online.attendencehelper.models.Attendance
import com.online.attendencehelper.models.Student
import kotlinx.android.synthetic.main.activity_take_attendance.view.*
import kotlinx.android.synthetic.main.list_item_attendance.view.*

class AttendanceRecyclerAdapter(
        val attendances :ArrayList<Attendance>,
        val studentList:ArrayList<Student>

): RecyclerView.Adapter<AttendanceRecyclerAdapter.AttendanceViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int):AttendanceViewHolder {

        val li = parent!!.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = li.inflate(R.layout.list_item_attendance,parent,false)
       return AttendanceViewHolder(itemView)
    }

    override fun getItemCount(): Int = attendances.size


    override fun onBindViewHolder(holder: AttendanceViewHolder, position: Int) {
        holder.itemView.checkbox.setOnCheckedChangeListener(null)
        holder.itemView.tvStudentName.setOnEditorActionListener(null)

        holder.itemView.tvId.text = attendances[position].studentid.toString()
        holder.itemView.checkbox.isChecked = attendances[position].present


        holder.itemView.tvStudentName.text = studentList[position].studentName


        holder.itemView.checkbox.setOnCheckedChangeListener{
            _,isChecked ->
            attendances[position].present = isChecked
          //  onAttendanceUpdate(attendances[position])

        }
//        holder.itemView.setOnLongClickListener{
//            AlertDialog.Builder(holder.itemView.context)
//                    .setTitle("Update studentName")
//                    .setMessage("Do you really want to update student name?")
//                    .setPositiveButton(
//                            "YES",
//                            {_,_->onAttendanceUpdateName(attendances[position])}
//
//                    )
//                    .setNegativeButton("NO",{_,_ ->Unit})
//                    .show()
//            true
//        }

    }
    class AttendanceViewHolder(itemView: View?):RecyclerView.ViewHolder(itemView) {

    }


}

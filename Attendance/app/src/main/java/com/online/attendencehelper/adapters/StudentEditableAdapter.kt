package com.online.attendencehelper.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.online.attendencehelper.R
import com.online.attendencehelper.models.Student
import kotlinx.android.synthetic.main.list_studentlist.view.*

class StudentEditableAdapter(
        val studentList :ArrayList<Student>

):RecyclerView.Adapter<StudentEditableAdapter.StudentViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): StudentViewHolder {

        val li = parent!!.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = li.inflate(R.layout.list_studentlist,parent,false)
        return StudentViewHolder(itemView)
    }

    override fun getItemCount(): Int = studentList.size

    override fun onBindViewHolder(holder: StudentViewHolder?, position: Int) {
       holder!!.itemView.etStudentName.setText(studentList[position].studentName)
    }


    class StudentViewHolder(itemView: View?):RecyclerView.ViewHolder(itemView){

    }
}
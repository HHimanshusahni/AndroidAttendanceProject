package com.online.attendencehelper.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.online.attendencehelper.R
import com.online.attendencehelper.models.Student
import kotlinx.android.synthetic.main.list_studentlist.view.*
class StudentEditableAdapter (
        private val studentList :ArrayList<Student>
): RecyclerView.Adapter<StudentEditableAdapter.StudentViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): StudentViewHolder {

        val li = parent!!.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = li.inflate(R.layout.list_studentlist,parent,false)
        return StudentViewHolder(itemView,MyCustomEditTextListener())
    }

    override fun getItemCount(): Int = studentList.size

    override fun onBindViewHolder(holder: StudentViewHolder?, position: Int) {
//       holder!!.itemView.etStudentName.setText(studentList[position].studentName)
       holder!!.myCustomEditTextListener.updatePosition(holder.adapterPosition)
        holder.itemView.etStudentName.setText(studentList[holder.adapterPosition].studentName)


    }


    class StudentViewHolder(itemView: View?, var myCustomEditTextListener: MyCustomEditTextListener):RecyclerView.ViewHolder(itemView){

        var mEditText: EditText

        init {

            this.mEditText = itemView!!.findViewById(R.id.etStudentName)
            this.mEditText.addTextChangedListener(myCustomEditTextListener)
        }
    }

    inner class MyCustomEditTextListener : TextWatcher {
        private var position: Int = 0

        fun updatePosition(position: Int) {
            this.position = position
        }

        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {

        }

        override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {
            studentList[position]. studentName= charSequence.toString()
        }

        override fun afterTextChanged(editable: Editable) {

        }
    }
}
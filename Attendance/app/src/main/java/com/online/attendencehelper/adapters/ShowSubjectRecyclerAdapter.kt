package com.online.attendencehelper.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.online.attendencehelper.R
import com.online.attendencehelper.models.Subject
import kotlinx.android.synthetic.main.list_item_show_subject.view.*

class ShowSubjectRecyclerAdapter(
        val subjects : ArrayList<Subject>,
        val clickListener: (Subject) -> Unit, // The return type is unit as click handler does not need to return anything
        val clickDeleteListener:(Int) ->Unit,
        val clickEditListener:(Int)->Unit,
        val clickedStudentListener:(Int)->Unit
) :RecyclerView.Adapter<ShowSubjectRecyclerAdapter.ShowSubjectViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int):ShowSubjectViewHolder{
        val li = parent!!.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
        val itemView = li.inflate(R.layout.list_item_show_subject,parent,false)
        return ShowSubjectViewHolder(itemView)
    }

    override fun getItemCount(): Int = subjects.size

    override fun onBindViewHolder(holder: ShowSubjectViewHolder, position: Int) {


        (holder as ShowSubjectViewHolder).bind(subjects[position],clickListener,clickDeleteListener,clickEditListener,clickedStudentListener)
    }


    class ShowSubjectViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bind(subject: Subject,
                 clickListener: (Subject) -> Unit,
                 clickdeleteListener: (Int) -> Unit,
                 clickeditListener: (Int) -> Unit,
                 clickedStudentListener: (Int) -> Unit
                ){

            itemView.tvSubjectName.text = subject.subjectname
            itemView.tvSubjectDeparment.text = "("+subject.department.toString()+")"
//            itemView.tvSubjectDeparment.text = subject.department
            itemView.tvSubjectYear.text = "${subject.year.toString()}"+"Year"
            itemView.tvtotalrollnos.text = subject.totalrollnos.toString()+" Students"

            itemView.setOnClickListener{
                clickListener(subject)
            }

            itemView.btnDeleteSubject.setOnClickListener{
                clickdeleteListener(subject.subjectid!!)
            }
            itemView.btnEditSubject.setOnClickListener{
                clickeditListener(subject.subjectid!!)
            }
            itemView.btnStudentName.setOnClickListener{
                clickedStudentListener(subject.subjectid!!)
            }


        }

    }


}

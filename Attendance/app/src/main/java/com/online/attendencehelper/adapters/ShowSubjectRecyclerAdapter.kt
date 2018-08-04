package com.online.attendencehelper.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.online.attendencehelper.R
import com.online.attendencehelper.models.Subject
import kotlinx.android.synthetic.main.list_item_show_subject.view.*

class ShowSubjectRecyclerAdapter(
        val subjects : ArrayList<Subject>,
        val clickListener: (Subject) -> Unit // The return type is unit as click handler does not need to return anything
) :RecyclerView.Adapter<ShowSubjectRecyclerAdapter.ShowSubjectViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int):ShowSubjectViewHolder{
        val li = parent!!.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
        val itemView = li.inflate(R.layout.list_item_show_subject,parent,false)
        return ShowSubjectViewHolder(itemView)
    }

    override fun getItemCount(): Int = subjects.size

    override fun onBindViewHolder(holder: ShowSubjectViewHolder, position: Int) {


        (holder as ShowSubjectViewHolder).bind(subjects[position],clickListener)
    }


    class ShowSubjectViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bind(subject: Subject,clickListener: (Subject) -> Unit){
            itemView.tvSubjectName.text = subject.subjectname
            itemView.tvSubjectDeparment.text = subject.department
            itemView.tvSubjectDeparment.text = subject.department
            itemView.tvSubjectYear.text = "-${subject.year.toString()}"
            itemView.tvtotalrollnos.text = subject.totalrollnos.toString()
            itemView.setOnClickListener{clickListener(subject)}


        }

    }


}

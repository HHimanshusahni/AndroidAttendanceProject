package com.online.attendencehelper.adapters

import android.content.Context
import android.content.Intent
import android.support.constraint.R.id.parent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.online.attendencehelper.Activities.TakeAttendance
import com.online.attendencehelper.R
import com.online.attendencehelper.models.Subject
import kotlinx.android.synthetic.main.list_item_show_subject.view.*

class ShowSubjectRecyclerAdapter(
        val subjects : ArrayList<Subject>
) :RecyclerView.Adapter<ShowSubjectRecyclerAdapter.ShowSubjectViewHolder>(){

//    lateinit var actIntent : Intent

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int):ShowSubjectViewHolder{
        val li = parent!!.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
        val itemView = li.inflate(R.layout.list_item_show_subject,parent,false)
        return ShowSubjectViewHolder(itemView)
    }

    override fun getItemCount(): Int = subjects.size

    override fun onBindViewHolder(holder: ShowSubjectViewHolder, position: Int) {
        holder.itemView.tvSubjectName.text = subjects[position].subjectname
        holder.itemView.tvSubjectDeparment.text = subjects[position].department
        holder.itemView.tvSubjectYear.text = "-${subjects[position].year.toString()}"
        holder.itemView.tvtotalrollnos.text = subjects[position].totalrollnos.toString()
        holder.itemView.btntakeAttendance.setOnClickListener{
//            val actIntent : Intent
//            actIntent = Intent(con,TakeAttendance::class.java)
//            startActivity(actIntent)
        }

    }
    class ShowSubjectViewHolder(itemView: View?):RecyclerView.ViewHolder(itemView) {

    }

}
package com.online.attendencehelper.datetime

import android.widget.TextView
import kotlinx.android.synthetic.main.activity_add_subject.*
import java.text.SimpleDateFormat

class DateTime{
    fun setTime(textview: TextView) {

        val date = System.currentTimeMillis()

        val sdf = SimpleDateFormat(" h:mm a")
        val dateString = sdf.format(date)
        textview.setText(dateString)

        // for the dialog box


    }
    fun setDate(textview: TextView) {
        val time = System.currentTimeMillis()
        val sdf = SimpleDateFormat("dd/MM/yy")
        val timeString = sdf.format(time)
        textview.setText(timeString)

    }
    fun setDay(textView:TextView){
        val day = System.currentTimeMillis()
        val sdf = SimpleDateFormat("EEEE")
        val dayString = sdf.format(day)
        textView.setText(dayString)
    }
    fun getTime():String{
        val date = System.currentTimeMillis()

        val sdf = SimpleDateFormat(" h:mm a")
        return  sdf.format(date)

    }
}
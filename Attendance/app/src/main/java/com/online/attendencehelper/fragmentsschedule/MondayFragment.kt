
package com.online.attendencehelper.fragmentsschedule


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.online.attendencehelper.Activities.MySchedule
import com.online.attendencehelper.R
import com.online.attendencehelper.db.tables.TableHelper
import kotlinx.android.synthetic.main.fragment_monday.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MondayFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val inf = inflater.inflate(R.layout.fragment_monday, container, false)
        val tv = inf.findViewById(R.id.tvMonday) as TextView
        tv.text = MySchedule.schedule(this.context!!,"Monday")
        return inf
    }


}

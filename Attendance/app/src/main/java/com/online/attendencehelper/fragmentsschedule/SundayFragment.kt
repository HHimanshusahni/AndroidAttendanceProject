package com.online.attendencehelper.fragmentsschedule


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.online.attendencehelper.Activities.MySchedule
import com.online.attendencehelper.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SundayFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val inf = inflater.inflate(R.layout.fragment_sunday, container, false)
        val tv = inf.findViewById(R.id.tvSunday) as TextView
        tv.text = MySchedule.schedule(this.context!!,"Sunday")
        return inf
    }


}

package com.online.attendencehelper.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.online.attendencehelper.fragmentsschedule.*

class   MyPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm){
    override fun getItem(p0: Int): Fragment {
        return when(p0){
            0->{
                MondayFragment()
            }
            1->{
                TuesdayFragment()
            }
            2 ->{
                WednesdayFragment()
            }
            3->{
                ThursdayFragment()
            }
            4->{
                FridayFragment()
            }
            5->{
                SaturdayFragment()
            }
            else ->{
                return SundayFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 7;
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->"MONDAY"
            1->"TUESDAY"
            2->"WEDNESDAY"
            3->"THURSDAY"
            4->"FRIDAY"
            5->"SATURDAY"
            6->"SUNDAY"
            else->{
                return "SUN"
            }
        }
    }

}
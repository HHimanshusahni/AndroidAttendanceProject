package com.online.attendencehelper.models

data class AttendanceRecord (

        var attendanceid: Int?, //Primary Key
        var subjectid:Int,
        var attendacetime: String,
        var attendancedate:String

)
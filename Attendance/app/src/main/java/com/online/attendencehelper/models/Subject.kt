package com.online.attendencehelper.models

data class Subject(
        var subjectid: Int?,
        var subjectname:String,
        var year :Int,
        var department :String,
        var totalrollnos:Int
)
data class SubjectSchedule(
        var subjectid: Int,
        var timings: String,
        var days :String
)

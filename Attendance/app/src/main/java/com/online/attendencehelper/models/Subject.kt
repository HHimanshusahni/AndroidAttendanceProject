package com.online.attendencehelper.models

import java.io.Serializable

data class Subject(
        var subjectid: Int?,
        var subjectname:String,
        var year :Int,
        var department :String,
        var totalrollnos:Int
):Serializable


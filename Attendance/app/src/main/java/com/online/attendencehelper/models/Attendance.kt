package com.online.attendencehelper.models

data class Attendance(

                      var subjectid: Int,
                      var studentid :Int,
                      var studentName:String,
                      var present :Boolean,
                      var time :String,
                      var date :String


)

package com.lifei.kotlinstudy.密封类

sealed class Exam {
    object Fraction1:Exam()
    object Fraction2:Exam()
    object Fraction3:Exam()

    class Fraction4(val name:String):Exam()
}

class Teacher(private val exam:Exam){

    fun show()=
        when(exam){
            is Exam.Fraction1->{}
            is Exam.Fraction2->{}
            is Exam.Fraction3->{}
            is Exam.Fraction4->{}
        }
 }
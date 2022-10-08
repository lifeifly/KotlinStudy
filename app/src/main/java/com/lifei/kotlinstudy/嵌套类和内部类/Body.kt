package com.lifei.kotlinstudy.嵌套类和内部类

class Body {
    val name = "dasdasd"


    //内部类
    //需要加inner，才能成为内部类，访问外部类
    inner class Heart {
        fun run() {
            println(name)
        }
    }
    //嵌套类，不能访问外部类
    class Hand{
        val info="asa"
    }
}
package com.lifei.kotlinstudy.继承和重载
//所有类都是final的不能被继承，需要使用open才能被继承
open class Person {
    //方法也都是final的，不能被重写，也需要使用open
    open fun show(){

    }
}

class Student:Person(){
    override fun show(){

    }
}
package com.lifei.kotlinstudy.空安全

fun main() {
    var name:String?=null
    name?.length//？空安全调用，如果name为空就不会调用后面的方法
    name!!.length//!!断言不是空的
    //空合并操作符
    var info:String?=null
    // ?:  info为空会执行后面的代码
    println(info ?:"原来你是null")
}
package com.lifei.kotlinstudy.解构

class Person(val name: String, val age: Int) {
    //结构必须，顺序一一对应
    operator fun component1() = name
    operator fun component2() = age
}

fun main() {
    val (name, age) = Person("l", 18)
}

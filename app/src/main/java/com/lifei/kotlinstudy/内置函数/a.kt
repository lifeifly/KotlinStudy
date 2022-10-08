package com.lifei.kotlinstudy.内置函数

fun main() {

    var name = "lifei"
    //apply返回name自身
    var a = name.apply {
        length
    }.apply {

    }

    var name1: String? = "l"

    var b: Int = name1?.let {
        it.toString()
        it.length
    }?:123

    name1.run {

    }
}


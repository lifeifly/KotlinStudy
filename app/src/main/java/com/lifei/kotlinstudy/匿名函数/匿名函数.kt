package com.lifei.kotlinstudy.匿名函数

fun main() {

    //匿名函数作为对象传给引用
    var method = { a: String, b: String ->
        "加油$a$b"
    }
    method=::show2
}

//匿名函数作为返回值
fun show(): (String, String) -> String {
    println("匿名函数")
    return { a, b ->
        "加油$a$b"
    }
}

//匿名函数作为参数
fun show1(a: (String, String) -> String) {
    println("匿名函数")
    a("a", "b")
}

//具名函数
fun show2(a: String, b: String): String {
    return "b"
}
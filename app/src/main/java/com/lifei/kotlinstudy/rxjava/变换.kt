package com.lifei.kotlinstudy.rxjava

fun main() {
    val list = listOf("as")
    list.map {
        1
    }

    list.flatMap {
        //返回一个集合，最后被加入到新集合
        listOf(1, 2, 3)
    }
    println(list)

    list.filter {
        it == "asa"
    }

    val d: List<List<String>> = listOf(
        listOf("a", "b", "c"),
        listOf("d", "e", "f")
    )

    d.flatMap {
        it.filter {
            it == "a"
        }
    }

    val a: List<Pair<String, List<String>>> = list.zip(d)
}
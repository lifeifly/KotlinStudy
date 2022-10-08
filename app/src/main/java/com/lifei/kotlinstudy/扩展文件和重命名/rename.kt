package com.lifei.kotlinstudy.扩展文件和重命名

//重命名
import com.lifei.kotlinstudy.扩展文件和重命名.randomItem as g

fun main() {
    val list= listOf("a")
    list.g()

    list.apply {  }
}
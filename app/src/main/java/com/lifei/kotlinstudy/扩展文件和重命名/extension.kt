package com.lifei.kotlinstudy.扩展文件和重命名

fun <T> Iterable<T>.randomItem() = this.shuffled().first()
fun main() {
    val list = listOf("l", "as", "av")
    list.shuffled().first()
}
package com.lifei.kotlinstudy.运算符重载

data class AddClass(var name: Int, var age: Int) {
    operator fun plus(p1: AddClass): Int {
        return (name + p1.name) + age + p1.age;
    }
}

fun main() {
    val total = AddClass(1, 2) + AddClass(2, 3)
}
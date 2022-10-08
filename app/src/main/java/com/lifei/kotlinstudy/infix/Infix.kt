package com.lifei.kotlinstudy.infix

//infix中缀表达式
fun main() {
    mapOf("-" to 1)
    mapOf("-".to(1))
    mapOf("1" go 1)


}
//自定义中缀表达式
private infix fun <A, B> A.go(b: B) = Pair(this, b)
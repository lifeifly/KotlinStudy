package com.lifei.kotlinstudy.协程

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CoroutineTest{
    fun start(){
        //方式1
        runBlocking {
            log("runBlocking")
        }
        //方式2
        GlobalScope.launch {
            log("launch")
        }
        //方式3
        GlobalScope.async {
            log("async")
        }
    }
}

fun main() {
    val coroutineTest = CoroutineTest()
    coroutineTest.start()
}
package com.lifei.kotlinstudy.协程

import android.os.Build
import android.util.Log
import java.text.SimpleDateFormat

val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

inline fun Long.date(): String = sdf.format(this)
inline fun isAndroid() = try {
    Build.VERSION.SDK_INT != 0
} catch (e: ClassNotFoundException) {
    false
}

fun log(value: Any?) = log(value.toString())
fun log(msg: String) = if (isAndroid()) Log.i(
    "isAndroid",
    "[${System.currentTimeMillis().date()}]-[${Thread.currentThread().name}] $msg"
) else println("noAndroid[${System.currentTimeMillis().date()}]-[${Thread.currentThread().name}] $msg")

fun main() {
    log("hello world")
}
package com.lifei.kotlinstudy.dsl

import java.io.File

//DSL 领域专用语言
class Context {
    val info = "das"
    fun toast(str: String) {
        println(str)
    }
}

//apply5就是编程范式，定义lam输入输出等规则,匿名函数lambda中持有it和this
//输入:Context才能调用apply5
//输出:始终输出Context
inline fun Context.apply5(lambda: Context.(String) -> Unit): Context {
    this.lambda(this.info)
    return this
}

inline fun File.applyFile(action: (String, String?) -> Unit): File {
    action(name, readLine())
    return this
}

fun main() {


    Context().apply5 {
        toast("success")
        toast(it)
        toast(info)
    }

    File("D:\\a.txt")
        .applyFile { filename, fileContent ->

            println(filename + fileContent)
        }
}
@file:JvmName("Stu")//修改kotlin生成的类名，只能写在包名外

package com.lifei.kotlinstudy.注解


fun getValue() = "as"
//Java端不能用默认参数
@JvmOverloads//这样JAVA端也可以用，会生成一个重载函数提供给JAVA用
fun show(name: String, age: Int = 10) {

}

class Person {

    @JvmField//kotlin编译后不会生成get、set，直接字段public暴露给外部
    val name = "asa"

    companion object{
        //JAVA端调用还需.Companion
        @JvmField//会直接放在类外部,JAVA可以直接调用
        val target="asda"
        @JvmStatic//会直接把方法放在类外部，不放在companion中
        fun showAction(){
            println(target)
        }
    }
}

fun main() {

}
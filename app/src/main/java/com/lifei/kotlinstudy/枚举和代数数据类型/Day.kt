package com.lifei.kotlinstudy.枚举和代数数据类型
class Info(val length:Int){
    fun show(){

    }
}
//枚举中的每个实例必须拥有和枚举的构造一致
enum class Body(private val info:Info) {
    Hand(Info(10)), FOOT(Info(20));

    //枚举的方法相当于类中的方法
    fun show(){
        println(info.show())
    }
}

fun main() {
    val body=Body.FOOT

    fun show(){
        when(body){
            Body.FOOT->{}
            Body.Hand->{}
            //可以不用写else，因为枚举实例个数是有限的
        }
    }
}
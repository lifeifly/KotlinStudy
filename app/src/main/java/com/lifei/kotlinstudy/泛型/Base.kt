package com.lifei.kotlinstudy.泛型

class Base<T>(val obj: T) {

    fun show() = println(obj)

    fun <K> show1(o: K) {

    }

    //变化
    inline fun <R> map(action: (T) -> R): R? {
        return action(obj)
    }
}


inline fun <I, O> map(boolean: Boolean = true, input: I, mapLambda: (I) -> O) =
    if (boolean) {
        mapLambda(input)
    } else {
        null
    }

open class AnyClass(name: String)

open class PersonClass(name: String) : AnyClass(name)

class StudentClass(name: String) : PersonClass(name)

class TeacherClass(name: String) : PersonClass(name)

//T：Class只能使用class或class的子类
class Base1<T : PersonClass>(val value: T) {

}

//out T只能提供，不能修改
interface Producer<out T>{

    fun produce(): T

}
//in T只能修改，不能被读取
interface Consumer<in T> {


    fun set(b: T)
}

fun main() {
    var base = Base("AS")
    base.show()
}
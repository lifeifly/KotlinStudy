package com.lifei.kotlinstudy.rxjava.手写

fun main() {
    create {
        "lifei"
    }
}

class RxJavaCoreClassObject<T>(val item: T) {

}


inline fun <T> create(action: () -> T): RxJavaCoreClassObject<T> {
    return RxJavaCoreClassObject(action())
}

inline fun <T, R> RxJavaCoreClassObject<T>.map(action: (T) -> R): RxJavaCoreClassObject<R> {
    return RxJavaCoreClassObject(action(this.item))
}

inline fun <T> RxJavaCoreClassObject<T>.observe(action: T.() -> Unit) {
    this.item.action()
}



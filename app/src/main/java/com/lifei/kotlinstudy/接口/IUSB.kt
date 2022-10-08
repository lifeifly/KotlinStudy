package com.lifei.kotlinstudy.接口

//接口不能有构造
interface IUSB {
    //所有都是public open的
    var usbVersion: String
    var usbDevice: String
    //一版不用，因为接口是用来声明的
    val name:String
        get() = "asa"

    fun insertUSB(): String
}

//实现接口，需要重写成员属性和方法
class Mouse(override var usbVersion: String, override var usbDevice: String) : IUSB {
    override fun insertUSB(): String {
        return "as"
    }
}

class Keyboard : IUSB {
    //必须赋值才能持有field
    override var usbVersion: String = "s"
        get() = field
        set(value) {
            field = value
        }
    override var usbDevice: String = "a"
        get() = field
        set(value) {
            field=value
        }

    override fun insertUSB(): String {
        return "as"
    }

}
package com.lifei.kotlinstudy.委托

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

interface IUSB {


    fun insert()
}


class Mouse : IUSB {


    override fun insert() {

    }


}

class Keyboard(iusb: IUSB) : IUSB by iusb

class DogComparator : Comparable<Int> {
    override fun compareTo(other: Int): Int {
        return 0
    }

}

class CatComparator : Comparable<Int> by DogComparator()

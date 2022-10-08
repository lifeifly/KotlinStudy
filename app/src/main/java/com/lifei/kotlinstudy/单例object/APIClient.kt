package com.lifei.kotlinstudy.单例object

class APIClient {

    companion object{
        val instance = APIClient()
    }
}
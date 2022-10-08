package com.lifei.kotlinstudy.pluginreflect

import android.content.Context
import android.content.Intent
import android.os.Message
import com.lifei.kotlinstudy.RegisterActivity
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

object PluginReflect {

    private const val TARGET_INTENT: String = "intent"
    private const val TAG: String = "PluginReflect"

    fun changeIntent(context: Context) {
        //获取ActivityTaskManager的属性IActivityTaskManagerSingleton
        val classLoader = context.classLoader
        val atmClazz = classLoader.loadClass("android.app.ActivityTaskManager")
        val iActivityTaskManagerSingletonField =
            atmClazz.getDeclaredField("IActivityTaskManagerSingleton")
        iActivityTaskManagerSingletonField.isAccessible = true
        val iActivityTaskManagerSingleton = iActivityTaskManagerSingletonField.get(null)
        val iActivityTaskManagerSingletonClass = classLoader.loadClass("android.util.Singleton")
        //调用create方法获取系统的IActivityTaskManager
        val getMethod = iActivityTaskManagerSingletonClass.getMethod("get")
        getMethod.isAccessible = true
        val iatm = getMethod.invoke(iActivityTaskManagerSingleton)
        //创建iatm代理对象
        val iatmClass = classLoader.loadClass("android.app.IActivityTaskManager")
        val iatmProxy =
            Proxy.newProxyInstance(classLoader, arrayOf(iatmClass), object : InvocationHandler {
                override fun invoke(proxy: Any?, method: Method?, args: Array<Any>?): Any? {
                    //如果是startActivityAsUser方法，找到intent参数进行修改
                    if (method?.name.equals("startActivity")) {
                        args?.let {
                            it.forEachIndexed { index, any ->
                                if (any is Intent) {
                                    val actualComponentName = any.component!!

                                    val proxyIntent = Intent()
                                    proxyIntent.setClassName(
                                        actualComponentName.packageName,
                                        RegisterActivity::class.java.name
                                    )
                                    proxyIntent.putExtra("intent", any)

                                    args[index] = proxyIntent
                                }
                            }
                        }
                    }
                    //真正仍交给实际对象执行
                    return method?.invoke(iatm, *(args ?: arrayOfNulls<Any>(0)))
                }
            })
        //将代理对象设置进IActivityTaskManagerSingleton
        val mInstanceFiled = iActivityTaskManagerSingletonClass.getDeclaredField("mInstance")
        mInstanceFiled.isAccessible = true
        mInstanceFiled.set(iActivityTaskManagerSingleton, iatmProxy)

    }

    fun restoreIntent(context: Context) {
        val classLoader = context.classLoader
        //获取ActivtyThread类对象
        val activityThreadClass = classLoader.loadClass("android.app.ActivityThread")
        //获取sCurrentActivityThread
        val sCurrentActivityThreadField =
            activityThreadClass.getDeclaredField("sCurrentActivityThread")
        sCurrentActivityThreadField.isAccessible = true
        var sCurrentActivityThread = sCurrentActivityThreadField.get(null)
        //获取mH
        val mHFiled = activityThreadClass.getDeclaredField("mH")
        mHFiled.isAccessible = true
        val mH = mHFiled.get(sCurrentActivityThread)
        //为mH设置CallBack
        val handlerClass = classLoader.loadClass("android.os.Handler")
        val mCallbackFiled = handlerClass.getDeclaredField("mCallback")
        mCallbackFiled.isAccessible = true
        mCallbackFiled.set(mH, object : android.os.Handler.Callback {
            override fun handleMessage(msg: Message): Boolean {
                if (msg.what == 159) {
                    // 获取 List<ClientTransactionItem> mActivityCallbacks 对象

                    // 获取 List<ClientTransactionItem> mActivityCallbacks 对象
                    val mActivityCallbacksField = msg.obj.javaClass
                        .getDeclaredField("mActivityCallbacks")
                    mActivityCallbacksField.isAccessible = true
                    val mActivityCallbacks = mActivityCallbacksField[msg.obj] as List<*>

                    for (i in mActivityCallbacks.indices) {
                        // 打印 mActivityCallbacks 的所有item:
                        //android.app.servertransaction.WindowVisibilityItem
                        //android.app.servertransaction.LaunchActivityItem

                        // 如果是 LaunchActivityItem，则获取该类中的 mIntent 值，即 proxyIntent
                        if (mActivityCallbacks[i]!!.javaClass.name
                            == "android.app.servertransaction.LaunchActivityItem"
                        ) {
                            val launchActivityItem = mActivityCallbacks[i]!!
                            val mIntentField = launchActivityItem.javaClass
                                .getDeclaredField("mIntent")
                            mIntentField.isAccessible = true
                            val proxyIntent = mIntentField[launchActivityItem] as Intent

                            // 获取启动插件的 Intent，并替换回来
                            val intent =
                                proxyIntent.getParcelableExtra<Intent>(TARGET_INTENT)
                            if (intent != null) {
                                mIntentField[launchActivityItem] = intent
                            }
                        }
                    }
                }
                return false;
            }
        })
    }
}
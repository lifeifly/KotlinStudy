package com.lifei.kotlinstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.lifei.kotlinstudy.pluginreflect.HookUtil
import com.lifei.kotlinstudy.pluginreflect.PluginReflect

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PluginReflect.changeIntent(this)
        PluginReflect.restoreIntent(this)
//        HookUtil.hookAMS()

        val tv: TextView = findViewById(R.id.tv)
        tv.setOnClickListener {
            startActivity(Intent(this, UnregisterActivity::class.java))
        }

    }
}
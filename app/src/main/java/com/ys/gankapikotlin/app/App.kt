package com.ys.gankapikotlin.app

import android.app.Application
import android.content.Context
import androidx.core.content.ContextCompat
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.BallPulseFooter
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.ys.gankapikotlin.R

/**
 * author : liutiantian
 * e-mail : Tareafengye@163.com
 * date   : 2020/4/74:57 PM
 * desc   :
 * version: 1.0
 */
class App : Application() {
    companion object {
        @JvmStatic
        var context: Context? = null
            private set

        var instance: App? = null
            private set

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        instance = this
        initUI()
    }
    /**
     * 初始化全局UI设置
     */
    private fun initUI() {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->  ClassicsHeader(context) }
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            val ballPulseFooter = BallPulseFooter(context)
            ballPulseFooter.setPrimaryColors(ContextCompat.getColor(context, R.color.colorPrimary))
            ballPulseFooter
        }
    }
}
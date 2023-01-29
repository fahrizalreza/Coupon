package com.transvision.tv.coupon.application

import android.app.Application
import com.transvision.tv.coupon.module.networkModule
import com.transvision.tv.coupon.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(listOf(networkModule, viewModelModule))
        }
    }
}
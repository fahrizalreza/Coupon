package com.transvision.tv.coupon.module

import android.content.Context
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.transvision.tv.coupon.service.ApiService
import com.transvision.tv.coupon.utilities.AppConfig
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single { okHttp(get()) }

    single { connect(get()) }
}

private fun okHttp(context: Context): OkHttpClient {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(OkHttpProfilerInterceptor())
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)

    return okHttpClient.build()
}

private fun connect(context: Context): ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl(AppConfig.URL)
        .client(okHttp(context))
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    return retrofit.create(ApiService::class.java)
}

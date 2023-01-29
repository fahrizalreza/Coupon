package com.transvision.tv.coupon.service

import com.transvision.test.sampletv.tv.model.CategoryResponse
import com.transvision.test.sampletv.tv.model.CouponResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    // category list
    @GET("getAllCategory")
    fun loadCategory(): Single<CategoryResponse>

    // coupon list
    @GET("getCoupon")
    fun loadCoupon(): Single<CouponResponse>
}
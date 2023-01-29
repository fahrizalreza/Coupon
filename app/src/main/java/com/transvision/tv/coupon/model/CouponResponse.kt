package com.transvision.test.sampletv.tv.model

import com.google.gson.annotations.SerializedName

data class CouponResponse(

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("statusCode")
    val code: String? = null,

    @field: SerializedName("result")
    val coupons: ArrayList<Coupon>
)

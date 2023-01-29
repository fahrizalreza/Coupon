package com.transvision.test.sampletv.tv.model

import com.google.gson.annotations.SerializedName
import com.transvision.tv.coupon.model.Category

data class CategoryResponse (
    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("statusCode")
    val code: String? = null,

    @field: SerializedName("result")
    val category: ArrayList<Category>
)
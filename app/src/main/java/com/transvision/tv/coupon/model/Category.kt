package com.transvision.tv.coupon.model

import com.google.gson.annotations.SerializedName

data class Category(
    @field:SerializedName("categoryId")
    val categoryId: String? = null,

    @field:SerializedName("categoryName")
    val categoryName: String? = null
)

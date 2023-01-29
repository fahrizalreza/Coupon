package com.transvision.test.sampletv.tv.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Coupon(

    @field:SerializedName("couponId")
    val id: String? = null,

    @field:SerializedName("couponName")
    val name: String? = null,

    @field:SerializedName("couponBrandName")
    val brand: String? = null,

    @field:SerializedName("couponBrandLogo")
    val icon: String? = null,

    @field:SerializedName("couponBenefitType")
    val type: String? = null,

    @field:SerializedName("couponBenefitValue")
    val value: String? = null,

    @field:SerializedName("couponQuota")
    val quota: String? = null,

    @field:SerializedName("couponCategoryId")
    val categoryId: String? = null,

    @field:SerializedName("couponCategoryName")
    val categoryName: String? = null,

    @field:SerializedName("couponTnc")
    val desc: String? = null
): Serializable


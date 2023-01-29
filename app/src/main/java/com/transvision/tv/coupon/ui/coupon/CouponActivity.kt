package com.transvision.tv.coupon.ui.coupon

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.squareup.picasso.Picasso
import com.transvision.test.sampletv.tv.model.Coupon
import com.transvision.tv.coupon.R
import com.transvision.tv.coupon.databinding.ActivityCouponBinding


class CouponActivity : FragmentActivity() {
    private lateinit var binding: ActivityCouponBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCouponBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val selectedCoupon = intent.getSerializableExtra(COUPON) as Coupon
        val ivBasicImage: ImageView = findViewById<View>(R.id.iv_Coupon) as ImageView
        ivBasicImage.outlineAmbientShadowColor

        binding.tvTitle.text = selectedCoupon.brand
        binding.tvDesc.setText(Html.fromHtml(selectedCoupon.desc, Html.FROM_HTML_MODE_COMPACT))
        Picasso.with(this).load(selectedCoupon.icon).fit().into(binding.ivCoupon)
        binding.ivCoupon.setColorFilter(
            ContextCompat.getColor(this, R.color.black),
            android.graphics.PorterDuff.Mode.DST_OVER
        )

    }

    companion object {
        const val COUPON_ACTIVITY= "CouponActivity"
        const val COUPON = "Coupon"
    }
}
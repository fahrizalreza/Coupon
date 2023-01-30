package com.transvision.tv.coupon.ui.menu

import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.transvision.test.sampletv.tv.model.Coupon
import com.transvision.tv.coupon.R
import kotlin.properties.Delegates

/**
 * A CardPresenter is used to generate Views and bind Objects to them on demand.
 * It contains an ImageCardView.
 */
class CardPresenter : Presenter() {
    private var mDefaultCardImage: Drawable? = null
    private var sDefaultBackgroundColor: Int by Delegates.notNull()

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        sDefaultBackgroundColor = ContextCompat.getColor(parent.context, R.color.black)
        mDefaultCardImage = ContextCompat.getDrawable(parent.context, R.drawable.image_placeholder)

        val cardView = object : ImageCardView(parent.context) {
            override fun setSelected(selected: Boolean) {
                updateCardBackgroundColor(this, selected)
                super.setSelected(selected)
            }
        }
        cardView.infoVisibility = 2
        cardView.isFocusable = true
        cardView.isFocusableInTouchMode = true
        updateCardBackgroundColor(cardView, false)
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val coupon = item as Coupon
        val cardView = viewHolder.view as ImageCardView

        if (coupon.icon != null) {
            cardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT)
//            Glide.with(viewHolder.view.context)
//                .load(coupon.icon)
//                .centerCrop()
//                .error(mDefaultCardImage)
//                .into(cardView.mainImageView)

            Picasso.get().load(coupon.icon).placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder).into(cardView.mainImageView)
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        val cardView = viewHolder.view as ImageCardView
        cardView.infoVisibility = 2
        cardView.badgeImage = null
        cardView.mainImage = null
    }

    private fun updateCardBackgroundColor(view: ImageCardView, selected: Boolean) {
        view.infoVisibility = 2
    }

    companion object {
        private const val CARD_WIDTH = 313
        private const val CARD_HEIGHT = 176
    }
}
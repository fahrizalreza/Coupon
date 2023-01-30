package com.transvision.tv.coupon.ui.menu

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.transvision.test.sampletv.tv.model.Coupon
import com.transvision.tv.coupon.BrowseErrorActivity
import com.transvision.tv.coupon.ErrorFragment
import com.transvision.tv.coupon.R
import com.transvision.tv.coupon.extension.CustomRowPresenter
import com.transvision.tv.coupon.extension.IconHeaderItem
import com.transvision.tv.coupon.ui.coupon.CouponActivity
import org.koin.android.ext.android.inject
import java.util.*

/**
 * Loads a grid of cards with coupon to browse.
 */
class MainFragment() : BrowseSupportFragment() {

    private val viewModel: MenuViewModel by inject()
    private lateinit var mBackgroundManager: BackgroundManager
    private var mDefaultBackground: Drawable? = null
    private lateinit var mMetrics: DisplayMetrics
    private var mBackgroundTimer: Timer? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initialize()
    }

    private fun initialize() {
        mBackgroundManager = BackgroundManager.getInstance(activity)
        mBackgroundManager.attach(requireActivity().window)
        mDefaultBackground = ContextCompat.getDrawable(requireContext(), R.drawable.default_background)
        mMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(mMetrics)

        showTitle(false)
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true

        brandColor = ContextCompat.getColor(requireContext(), R.color.black)
        searchAffordanceColor = ContextCompat.getColor(requireContext(), R.color.black)

        loadRows()
        onItemViewClickedListener = ItemViewClickedListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBackgroundTimer?.cancel()
    }

    private fun loadRows() {
        viewModel.loadData()
        viewModel.browseCoupon.observe(viewLifecycleOwner) { list ->

            val rowsAdapter = ArrayObjectAdapter(CustomRowPresenter())
            val cardPresenter = CardPresenter()
            viewModel.listCategory.forEachIndexed { index, cate ->
                val listRowAdapter = ArrayObjectAdapter(cardPresenter)
                val listByCate = list.filter { it.categoryName == cate.categoryName }
                if (listByCate.isNotEmpty()) {
                    listByCate.forEach {
                        listRowAdapter.add(it)
                    }

                    rowsAdapter.add(
                        ListRow(
                            IconHeaderItem(
                                index.toLong(),
                                cate.categoryName.toString()
                            ), listRowAdapter
                        )
                    )
                }
            }

            adapter = rowsAdapter

            viewModel.errorResponse.observe(viewLifecycleOwner) { error ->
                if (error == 1){
                    val intent = Intent(requireContext(), BrowseErrorActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private inner class ItemViewClickedListener : OnItemViewClickedListener {
        override fun onItemClicked(
            itemViewHolder: Presenter.ViewHolder,
            item: Any,
            rowViewHolder: RowPresenter.ViewHolder,
            row: Row
        ) {

            if (item is Coupon) {
                val intent = Intent(context!!, CouponActivity::class.java)
                intent.putExtra(CouponActivity.COUPON, item)

                val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity!!,
                    (itemViewHolder.view as ImageCardView).mainImageView,
                    CouponActivity.COUPON_ACTIVITY
                ).toBundle()
                startActivity(intent, bundle)
            }
        }
    }
}
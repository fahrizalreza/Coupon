package com.transvision.tv.coupon.extension

import androidx.leanback.widget.FocusHighlight
import androidx.leanback.widget.ListRowPresenter

class CustomRowPresenter : ListRowPresenter(FocusHighlight.ZOOM_FACTOR_XSMALL) {
    init {
        headerPresenter = CustomRowHeaderPresenter()
    }
}
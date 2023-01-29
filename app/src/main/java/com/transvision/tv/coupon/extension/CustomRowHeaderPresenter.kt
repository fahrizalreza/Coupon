package com.transvision.tv.coupon.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.RowHeaderPresenter
import androidx.leanback.widget.RowHeaderView
import com.transvision.tv.coupon.R

class CustomRowHeaderPresenter: RowHeaderPresenter() {

    override fun onCreateViewHolder(parent: ViewGroup?): Presenter.ViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val view: View = inflater.inflate(R.layout.custom_header_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder?, item: Any?) {
        val iconHeaderItem = (item as? ListRow)?.headerItem as? IconHeaderItem
        val view = viewHolder?.view

        val textView = view?.findViewById<RowHeaderView>(R.id.row_header)
        textView?.text = iconHeaderItem?.name
    }

}
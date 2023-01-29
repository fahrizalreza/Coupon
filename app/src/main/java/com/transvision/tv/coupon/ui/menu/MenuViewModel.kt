package com.transvision.tv.coupon.ui.menu

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.transvision.test.sampletv.tv.model.CategoryResponse
import com.transvision.test.sampletv.tv.model.Coupon
import com.transvision.test.sampletv.tv.model.CouponResponse
import com.transvision.tv.coupon.extension.RepositoryExtension.applySchedulers
import com.transvision.tv.coupon.model.Category
import com.transvision.tv.coupon.service.ApiService
import com.transvision.tv.coupon.service.connect
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class MenuViewModel(context: Context): ViewModel() {
    private lateinit var call: Single<CouponResponse>
    private lateinit var callCategory: Single<CategoryResponse>
    private val apiService: ApiService = connect(context)
    private val compositeDisposable = CompositeDisposable()
    private lateinit var list: List<Coupon>
    var listCategory: MutableList<Category> = mutableListOf()
    val browseCoupon = MutableLiveData<List<Coupon>>()

    fun loadData() {
        callCategory = apiService.loadCategory()
        val disposable = callCategory.applySchedulers().subscribe({ result ->
            Log.d(LOG_CONNECTION, "connect to api master data = \n$result")
            val items = result.category
            if (items.isNotEmpty()) {
                items.forEach { listCategory.add(Category(it.categoryId, it.categoryName)) }
                loadItems()
            }

        }, { error ->
            Log.d(
                LOG_CONNECTION, "error connection: ${error.message} "
            )

        })
        compositeDisposable.add(disposable)
    }

    fun loadItems() {
        call = apiService.loadCoupon()
        val disposable = call.applySchedulers().subscribe({ result ->
            Log.d(LOG_CONNECTION, "connect to api master data = \n$result")
            val items = result.coupons
            if (items.isNotEmpty()) {
                browseCoupon.value = items
            }

        }, { error ->
            Log.d(
                LOG_CONNECTION, "error connection: ${error.message} "
            )

        })
        compositeDisposable.add(disposable)
    }

    companion object {
        const val LOG_CONNECTION: String = "CouponTrace"
    }
}
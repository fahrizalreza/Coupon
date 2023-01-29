package com.transvision.tv.coupon.module

import com.transvision.tv.coupon.ui.menu.MenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MenuViewModel(get(), get()) }
}
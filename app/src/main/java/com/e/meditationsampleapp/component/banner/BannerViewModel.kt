package com.e.meditationsampleapp.component.banner

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class BannerViewModel : ViewModel(){

    val descriptionObservable = ObservableField<String>()

    fun handleBannerData(bannerData: BannerData?) {
        bannerData?.run {
            descriptionObservable.set(description)
        }
    }
}
package com.e.meditationsampleapp.component.banner

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.e.meditationsampleapp.R
import com.e.meditationsampleapp.databinding.BannerViewBinding

@BindingAdapter("viewData")
fun BannerView.setData(data: BannerData?) {
    viewModel.handleBannerData(data)
}

class BannerView @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(ctx, attrs, defStyleAttr) {

    var viewModel: BannerViewModel = BannerViewModel()
    var binding: BannerViewBinding =
        DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.banner_view, this, true)

    init {
        binding.viewModel = viewModel
    }
}
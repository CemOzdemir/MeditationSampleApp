package com.e.meditationsampleapp.component.tile

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.e.meditationsampleapp.R
import com.e.meditationsampleapp.databinding.TileViewBinding

@BindingAdapter("viewData")
fun TileView.setData(data: TileData?) {
    viewModel.handleTileData(data)
}

class TileView @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(ctx, attrs, defStyleAttr) {

    var viewModel: TileViewModel = TileViewModel()
    var binding: TileViewBinding =
        DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.tile_view, this, true)

    init {
        binding.viewModel = viewModel
    }
}
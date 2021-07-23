package com.e.meditationsampleapp.base

import android.content.res.Resources
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.e.meditationsampleapp.R


const val EMPTY = ""

fun ImageView.loadImage(uri: String?) {
    Glide.with(context)
        .load(uri)
        .error(R.drawable.ic_broken_image)
        .into(this)
}

fun Int?.orZero() = this ?: 0

@BindingAdapter("android:imageUrl")
fun loadImageView(view: ImageView, url: String?) {
    view.loadImage(url)
}

@BindingAdapter("android:visibility")
fun changeVisibility(view: View, visibility: Boolean?) {
    view.visibility = if (visibility == true) View.VISIBLE else View.GONE
}

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
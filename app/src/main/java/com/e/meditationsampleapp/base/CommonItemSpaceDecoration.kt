package com.e.meditationsampleapp.base

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class CommonItemSpaceDecoration(private val paddingHorizontal: Int, private val paddingVertical: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        rect: Rect,
        view: View,
        parent: RecyclerView,
        s: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
            .let { if (it == RecyclerView.NO_POSITION) return else it }
        rect.right =
            if (position % 2 == 0) paddingHorizontal / 2
            else 0
        rect.left =
            if (position % 2 != 0) paddingHorizontal / 2
            else 0
        rect.bottom = paddingVertical
    }
}
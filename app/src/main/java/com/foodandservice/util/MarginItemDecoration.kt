package com.foodandservice.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(private val leftMargin: Int = 0, private val topMargin: Int = 0) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (leftMargin > 0)
                if (parent.getChildAdapterPosition(view) == 0)
                    left = leftMargin
            if (topMargin > 0)
                if (parent.getChildAdapterPosition(view) == 0)
                    top = topMargin
            right = leftMargin
            bottom = leftMargin
        }
    }
}
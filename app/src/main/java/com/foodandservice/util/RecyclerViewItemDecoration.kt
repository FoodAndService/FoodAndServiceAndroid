package com.foodandservice.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewItemDecoration(
    private val leftMargin: Int = 0,
    private val topMargin: Int = 0,
    private val bottomMargin: Int = 0
) :
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
            if (bottomMargin > 0)
                if (parent.getChildAdapterPosition(view) == state.itemCount - 1)
                    bottom = bottomMargin
            right = leftMargin
        }
    }
}
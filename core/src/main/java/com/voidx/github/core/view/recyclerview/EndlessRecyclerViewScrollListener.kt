package com.voidx.github.core.view.recyclerview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.voidx.github.core.view.recyclerview.EndlessRecyclerViewScrollListener.Companion.DIRECTION_BOTTOM
import com.voidx.github.core.view.recyclerview.EndlessRecyclerViewScrollListener.Companion.DIRECTION_TOP

fun RecyclerView.withBottomLoad(visibleThreshold: Int): InfiniteLoadMore {
    val scrollListener = EndlessRecyclerViewScrollListener(
        visibleThreshold,
        DIRECTION_BOTTOM,
        layoutManager as LinearLayoutManager
    )
    addOnScrollListener(scrollListener)
    return scrollListener
}

fun RecyclerView.withTopLoad(visibleThreshold: Int): InfiniteLoadMore {
    val scrollListener = EndlessRecyclerViewScrollListener(
        visibleThreshold,
        DIRECTION_TOP,
        layoutManager as LinearLayoutManager
    )
    addOnScrollListener(scrollListener)
    return scrollListener
}

interface InfiniteLoadMore {

    var onLoadMore: (() -> Unit)?

    var isLoading: Boolean

}

class EndlessRecyclerViewScrollListener(
    private val visibleThreshold: Int,
    private val direction: Int,
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener(), InfiniteLoadMore {

    companion object {
        const val DIRECTION_TOP = 0
        const val DIRECTION_BOTTOM = 1
    }

    override var onLoadMore: (() -> Unit)? = null

    override var isLoading: Boolean = false

    private var isScrollingEnabled: Boolean = false

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        isScrollingEnabled = newState != RecyclerView.SCROLL_STATE_IDLE
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val totalItemCount = layoutManager.itemCount
        val visibleItem = getVisibleItem()
        if (isLoading.not() &&
            totalItemCount > 0 &&
            visibleItem > -1 &&
            hasToLoadMore(visibleItem, totalItemCount) &&
            isScrollingEnabled
        ) {
            onLoadMore?.invoke()
            isLoading = true
        }
    }

    private fun getVisibleItem(): Int {
        return when (direction) {
            DIRECTION_TOP -> {
                layoutManager.findFirstVisibleItemPosition()
            }
            DIRECTION_BOTTOM -> {
                layoutManager.findLastVisibleItemPosition()
            }
            else -> {
                -1
            }
        }
    }

    private fun hasToLoadMore(visibleItem: Int, totalItemCount: Int): Boolean {
        return when (direction) {
            DIRECTION_TOP -> {
                (visibleItem - visibleThreshold) <= 0
            }
            DIRECTION_BOTTOM -> {
                totalItemCount <= (visibleItem + visibleThreshold)
            }
            else -> {
                false
            }
        }
    }

}
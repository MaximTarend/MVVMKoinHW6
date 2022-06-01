package by.hometrainng.oroutineshw5.extentions

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addSpaceDecoration(bottomSpace: Int) {
    addItemDecoration(object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val itemCount = parent.adapter?.itemCount ?: return
            val position = parent.getChildAdapterPosition(view)
            if (position != itemCount - 1) {
                outRect.bottom = bottomSpace
            }
        }
    })
}

fun RecyclerView.addPaginationScrollListener(
    layoutManager: LinearLayoutManager,
    itemsToLoad: Int,
    onLoadMore: () -> Unit
) {
    addOnScrollListener( object : RecyclerView.OnScrollListener() {  // object - анонимный класс
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val totalItemCount = layoutManager.itemCount
            val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

            if (dy != 0 && totalItemCount <= (lastVisibleItem + itemsToLoad)) {
                onLoadMore()
            }
        }
    })
}
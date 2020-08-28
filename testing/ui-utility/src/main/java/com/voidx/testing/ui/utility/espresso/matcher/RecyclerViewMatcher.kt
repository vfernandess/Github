package com.voidx.testing.ui.utility.espresso.matcher

import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun withRecyclerViewItem(recyclerViewId: Int, itemAt: Int, itemId: Int): Matcher<View?>? {
    return RecyclerViewMatcher(recyclerViewId).atPositionOnView(itemAt, itemId)
}

class RecyclerViewMatcher(private val recyclerViewId: Int) {

    fun atPosition(position: Int): Matcher<View?>? {
        return atPositionOnView(position, -1)
    }

    fun atPositionOnView(position: Int, targetViewId: Int): TypeSafeMatcher<View?> {

        return object : TypeSafeMatcher<View?>() {
            var resources: Resources? = null
            var childView: View? = null

            override fun describeTo(description: Description?) {
                var idDescription = recyclerViewId.toString()
                if (resources != null) {
                    idDescription = try {
                        resources?.getResourceName(recyclerViewId) ?: ""
                    } catch (var4: Resources.NotFoundException) {
                        String.format(
                            "%s (resource name not found)",
                            *arrayOf<Any?>(Integer.valueOf(recyclerViewId))
                        )
                    }
                }
                description?.appendText("with id: $idDescription")
            }

            override fun matchesSafely(view: View?): Boolean {
                resources = view?.resources
                if (childView == null) {
                    val recyclerView =
                        view?.rootView?.findViewById(recyclerViewId) as RecyclerView
                    childView = if (recyclerView.id == recyclerViewId) {
                        recyclerView.findViewHolderForAdapterPosition(position)!!.itemView
                    } else {
                        return false
                    }
                }
                return if (targetViewId == -1) {
                    view === childView
                } else {
                    childView?.findViewById<View>(targetViewId) === view
                }
            }
        }
    }

}
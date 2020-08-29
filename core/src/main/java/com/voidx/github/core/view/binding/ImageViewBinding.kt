package com.voidx.github.core.view.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object ImageViewBinding {

    @JvmStatic
    @BindingAdapter("src_url")
    fun ImageView.loadImageUrl(url: String) {
        Picasso
            .get()
            .load(url)
            .into(this)
    }
}
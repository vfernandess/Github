package com.voidx.github.core.view.imageview.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.voidx.github.core.view.util.image.CropCircleTransformation

object ImageViewBinding {

    @JvmStatic
    @BindingAdapter(value = ["src_url", "placeholder"], requireAll = true)
    fun ImageView.loadImageUrl(url: String, placeholder: Drawable) {
        Picasso
            .get()
            .load(url)
            .placeholder(placeholder)
            .into(this)
    }

    @JvmStatic
    @BindingAdapter(value = ["circle_src_url", "placeholder"], requireAll = true)
    fun ImageView.loadCircleImageUrl(url: String, @Nullable placeholder: Drawable?) {
        val creator = Picasso
            .get()
            .load(url)
            .transform(CropCircleTransformation())

        placeholder?.let {
            creator.placeholder(it)
        }

        creator.into(this)
    }
}
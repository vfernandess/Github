package com.voidx.github.core.view.recyclerview.binding

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BindableViewHolder<VDB : ViewDataBinding>(val viewDataBinding: VDB) :
    RecyclerView.ViewHolder(viewDataBinding.root) {

    var onClick: ((position: Int) -> Unit)? = null

    init {
        viewDataBinding.executePendingBindings()
        viewDataBinding.root.setOnClickListener { onClick?.invoke(this.adapterPosition) }
    }

}

fun <VDB: ViewDataBinding> BindableViewHolder<VDB>.binding(): VDB {
    return this.viewDataBinding
}
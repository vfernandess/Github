package com.voidx.search.repo.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.voidx.github.core.view.recyclerview.OnItemClick
import com.voidx.github.core.view.recyclerview.binding.BindableViewHolder
import com.voidx.github.core.view.recyclerview.binding.RecyclerViewBinding
import com.voidx.repo.databinding.RepoItemBinding
import com.voidx.repo.model.RepoDTO
import com.voidx.user.R
import javax.inject.Inject

class SearchRepoAdapter @Inject constructor() :
    RecyclerView.Adapter<BindableViewHolder<RepoItemBinding>>(),
    RecyclerViewBinding.BindableAdapter<List<RepoDTO>> {

    private val items = mutableListOf<RepoDTO>()

    var onItemClick: OnItemClick<RepoDTO>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindableViewHolder<RepoItemBinding> {
        val binding =
            RepoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return BindableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindableViewHolder<RepoItemBinding>, position: Int) {
        holder.onClick = {
            onItemClick?.invoke(items.elementAtOrNull(it))
        }

        holder.viewDataBinding.repo = items.elementAtOrNull(position)
        holder.viewDataBinding.avatarPlaceholder =
            ContextCompat.getDrawable(holder.itemView.context, R.drawable.ic_account)
    }

    override fun getItemCount(): Int = items.size

    override fun setData(data: List<RepoDTO>?) {
        val positionStart = items.size
        items.addAll(data ?: emptyList())
        notifyItemRangeInserted(positionStart, data?.size ?: 0)
    }

    fun reset() {
        val size = itemCount
        items.clear()
        notifyItemRangeRemoved(0, size)
    }

}
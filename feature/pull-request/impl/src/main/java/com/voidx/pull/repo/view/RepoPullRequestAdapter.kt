package com.voidx.pull.repo.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.voidx.github.core.view.recyclerview.OnItemClick
import com.voidx.github.core.view.recyclerview.binding.BindableViewHolder
import com.voidx.github.core.view.recyclerview.binding.RecyclerViewBinding
import com.voidx.pull.databinding.PullItemBinding
import com.voidx.pull.repo.domain.model.PullRequestDTO
import javax.inject.Inject

class RepoPullRequestAdapter @Inject constructor() :
    RecyclerView.Adapter<BindableViewHolder<PullItemBinding>>(),
    RecyclerViewBinding.BindableAdapter<List<PullRequestDTO>> {

    private var items: List<PullRequestDTO> = emptyList()

    var onItemClick: OnItemClick<PullRequestDTO>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindableViewHolder<PullItemBinding> {
        val binding =
            PullItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return BindableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindableViewHolder<PullItemBinding>, position: Int) {
        holder.onClick = {
            onItemClick?.invoke(items.elementAtOrNull(position))
        }

        holder.viewDataBinding.pull = items.elementAtOrNull(position)
        holder.viewDataBinding.avatarPlaceholder =
            ContextCompat.getDrawable(holder.itemView.context, com.voidx.user.R.drawable.ic_account)
    }

    override fun getItemCount(): Int = items.size

    override fun setData(data: List<PullRequestDTO>?) {
        items = data ?: emptyList()
        notifyDataSetChanged()
    }
}
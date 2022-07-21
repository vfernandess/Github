package com.voidx.photo.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.voidx.github.core.view.recyclerview.binding.BindableViewHolder
import com.voidx.photo.impl.R
import com.voidx.photo.impl.databinding.PhotoItemBinding
import com.voidx.photo.ui.domain.model.PhotoDTO
import javax.inject.Inject

class PhotoListAdapter @Inject constructor() :
    RecyclerView.Adapter<PhotoListItemHolder>() {

    var list = mutableListOf<PhotoDTO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PhotoItemBinding.inflate(inflater, parent, false)
        return PhotoListItemHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoListItemHolder, position: Int) {
        holder.putValues(list.elementAtOrNull(position))
    }

    override fun getItemCount(): Int = list.size

    fun submitList(newList: List<PhotoDTO>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    fun addItems(newItems: List<PhotoDTO>) {
        val startPosition = list.size
        list.addAll(startPosition, newItems)
        notifyItemRangeInserted(startPosition, newItems.size)
    }
}

class PhotoListItemHolder(
    binding: PhotoItemBinding
) : BindableViewHolder<PhotoItemBinding>(binding) {

    fun putValues(item: PhotoDTO?) {
        if (item == null) {
            return
        }

        viewDataBinding.avatarPlaceholder =
            ResourcesCompat.getDrawable(itemView.resources, R.drawable.ic_downasaur, null)
        viewDataBinding.photo = item

//        TODO("adjust image aspect ratio")
        //        final int _height = (int) Math.floor(width * size.getHeight() / size.getWidth());
    }
}

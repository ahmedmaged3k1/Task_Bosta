package com.example.bosta_task.ui.features.common.homeFragment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bionic_time.data.dataSource.remoteDataSource.entities.User
import com.example.bosta_task.databinding.AlbumsItemHolderBinding

val diffCallbackAd = object : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }
}
class UserAlbumRecyclerView :
    ListAdapter<User, UserAlbumRecyclerView.AlbumsViewHolder>(diffCallbackAd) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        return from(parent)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    inner class AlbumsViewHolder constructor(private val binding: AlbumsItemHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.user = user

        }

        init {

        }

    }

    fun from(parent: ViewGroup): AlbumsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AlbumsItemHolderBinding.inflate(inflater, parent, false)
        return AlbumsViewHolder(binding)
    }
}
package com.example.bosta_task.ui.features.common.homeFragment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bionic_time.data.dataSource.remoteDataSource.entities.Albums
import com.example.bosta_task.databinding.AlbumsItemHolderBinding
import com.example.bosta_task.ui.features.common.mainNavigationFragment.MainNavigationFragmentDirections

val diffCallbackAlbum = object : DiffUtil.ItemCallback<Albums>() {
    override fun areItemsTheSame(oldItem: Albums, newItem: Albums): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: Albums, newItem: Albums): Boolean {
        return oldItem.id == newItem.id
    }
}
class UserAlbumRecyclerView () :
    ListAdapter<Albums, UserAlbumRecyclerView.AlbumsViewHolder>(diffCallbackAlbum) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        return from(parent)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    inner class AlbumsViewHolder constructor(private val binding: AlbumsItemHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(albums: Albums) {
            binding.albums = albums
            binding.textViewAlbumName.setOnClickListener {
                val action = MainNavigationFragmentDirections.actionMainNavigationFragmentToDetailsFragment(albums.id)
                binding.root.findNavController().navigate(action)

            }

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
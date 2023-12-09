package com.example.bosta_task.ui.features.common.detailsFragment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bionic_time.data.dataSource.remoteDataSource.entities.Photos
import com.example.bosta_task.databinding.PhotoItemHolderBinding


val diffCallbackPhoto = object : DiffUtil.ItemCallback<Photos>() {
    override fun areItemsTheSame(oldItem: Photos, newItem: Photos): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: Photos, newItem: Photos): Boolean {
        return oldItem.id == newItem.id
    }
}
class PhotoRecyclerView :
    ListAdapter<Photos, PhotoRecyclerView.PhotosViewHolder>(diffCallbackPhoto) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return from(parent)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    inner class PhotosViewHolder constructor(private val binding: PhotoItemHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photos: Photos) {
            binding.photo = photos
            binding
        }

        init {

        }

    }

    fun from(parent: ViewGroup): PhotosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PhotoItemHolderBinding.inflate(inflater, parent, false)
        return PhotosViewHolder(binding)
    }
}
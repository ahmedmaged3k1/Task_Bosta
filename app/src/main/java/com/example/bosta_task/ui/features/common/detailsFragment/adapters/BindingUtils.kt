package com.example.bosta_task.ui.features.common.detailsFragment.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.bosta_task.data.dataSource.remoteDataSource.entities.Photos

@BindingAdapter("photoThumbnail")
fun ImageView.setProductImage(photo: Photos) {

    photo.let {
        Glide.with(context)
            .load(photo.thumbnailUrl)
            .into(this)
    }
}
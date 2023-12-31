package com.example.bosta_task.data.dataSource.remoteDataSource.entities

import com.google.gson.annotations.SerializedName

data class Photos(
    @SerializedName("albumId"      ) var albumId      : Int,
    @SerializedName("id"           ) var id           : Int,
    @SerializedName("title"        ) var title        : String,
    @SerializedName("url"          ) var url          : String,
    @SerializedName("thumbnailUrl" ) var thumbnailUrl : String
)

package com.example.bionic_time.data.dataSource.remoteDataSource.entities

import com.google.gson.annotations.SerializedName

data class Geo(
    @SerializedName("lat" ) var lat : String,
    @SerializedName("lng" ) var lng : String
)

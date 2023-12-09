package com.example.bionic_time.data.dataSource.remoteDataSource.entities

import com.google.gson.annotations.SerializedName

data class Address (

    @SerializedName("street"  ) var street  : String,
    @SerializedName("suite"   ) var suite   : String,
    @SerializedName("city"    ) var city    : String,
    @SerializedName("zipcode" ) var zipcode : String,
    @SerializedName("geo"     ) var geo     : Geo

)
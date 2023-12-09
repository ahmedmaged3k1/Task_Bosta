package com.example.bosta_task.ui.features.common.detailsFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bionic_time.data.dataSource.remoteDataSource.entities.Photos
import com.example.bionic_time.domain.useCases.PhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsFragmentViewModel @Inject constructor ( private val photosUseCase: PhotosUseCase
)  : ViewModel(){

    val albumPhotosList = MutableLiveData<ArrayList<Photos>>()
    fun getAlbumPhotos(albumId : Int){
        viewModelScope.launch {
            albumPhotosList.postValue(photosUseCase.getAllAlbumsPhotos(albumId) as ArrayList<Photos>?)

        }
    }
}
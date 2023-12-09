package com.example.bosta_task.ui.features.common.homeFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bosta_task.data.dataSource.remoteDataSource.entities.Albums
import com.example.bosta_task.data.dataSource.remoteDataSource.entities.User
import com.example.bosta_task.domain.useCases.AlbumsUseCase
import com.example.bosta_task.domain.useCases.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor (private val userUseCase: UserUseCase,private val albumsUseCase: AlbumsUseCase
)  : ViewModel() {
    val usersList = MutableLiveData<ArrayList<User>>()
    val userAlbumList = MutableLiveData<ArrayList<Albums>>()

    fun getUser() {
        viewModelScope.launch {
            usersList.postValue(userUseCase.getAllUsers() as ArrayList<User>?)
        }
    }
    fun getUserAlbums(userId : Int){
        viewModelScope.launch {
            userAlbumList.postValue(albumsUseCase.getAllUserAlbums(userId) as ArrayList<Albums>?)

        }
    }

}
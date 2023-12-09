package com.example.bosta_task.ui.features.common.homeFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bionic_time.data.dataSource.remoteDataSource.entities.User
import com.example.bosta_task.domain.useCases.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor (private val userUseCase: UserUseCase)  : ViewModel() {
     val usersList = MutableLiveData<ArrayList<User>>()

    fun getAllUsers() {
        viewModelScope.launch {
            //userUseCase.getAllUsers()
            usersList.postValue(  userUseCase.getAllUsers() as ArrayList<User>?)


        }

    }
}
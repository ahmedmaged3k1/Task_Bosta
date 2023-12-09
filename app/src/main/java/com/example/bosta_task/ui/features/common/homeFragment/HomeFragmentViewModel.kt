package com.example.bosta_task.ui.features.common.homeFragment

import androidx.lifecycle.ViewModel
import com.example.bionic_time.domain.useCases.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor (private val userUseCase: UserUseCase)  : ViewModel() {
}
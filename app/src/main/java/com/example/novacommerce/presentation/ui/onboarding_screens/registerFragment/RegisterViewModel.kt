package com.example.novacommerce.presentation.ui.onboarding_screens.registerFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novacommerce.common.utils.PrefManager
import com.example.novacommerce.common.utils.Resource
import com.example.novacommerce.common.utils.UiState
import com.example.novacommerce.domain.use_case.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val prefManager: PrefManager
) : ViewModel(){


    private val _state = MutableLiveData<UiState<String>>()
    val state : LiveData<UiState<String>>
        get() = _state


    fun register(email : String, password : String, username : String){
        viewModelScope.launch {
            registerUseCase(email, password).collectLatest {
                when(it){
                    is Resource.Loading -> _state.value = UiState.Loading
                    is Resource.Error -> _state.value = UiState.Error(it.message)
                    is Resource.Success -> {
                        prefManager.uid = it.data
                        prefManager.username = username
                        _state.value = UiState.Success("Registered successfully!")
                    }
                }
            }
        }
    }
}
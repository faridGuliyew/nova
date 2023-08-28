package com.example.novacommerce.presentation.ui.onboarding_screens.signInFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novacommerce.common.utils.PrefManager
import com.example.novacommerce.common.utils.Resource
import com.example.novacommerce.common.utils.UiState
import com.example.novacommerce.domain.use_case.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val prefManager: PrefManager
): ViewModel() {

    private val _state = MutableLiveData<UiState<String>>()
    val state : LiveData<UiState<String>>
        get() = _state


    fun login(email : String, password : String){
        viewModelScope.launch {
            loginUseCase(email, password).collectLatest {
                when(it){
                    is Resource.Loading -> _state.value = UiState.Loading
                    is Resource.Error -> _state.value = UiState.Error(it.message)
                    is Resource.Success -> {
                        prefManager.uid = it.data
                        _state.value = UiState.Success("Logged in successfully!")
                    }
                }
            }
        }
    }
}
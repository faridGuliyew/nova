package com.example.novacommerce.presentation.ui.main_screens.promotionDescFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novacommerce.common.utils.Resource
import com.example.novacommerce.common.utils.UiState
import com.example.novacommerce.domain.model.ProductUiModel
import com.example.novacommerce.domain.use_case.GetAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PromotionDescViewModel @Inject constructor (
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {

    private val _state = MutableLiveData<UiState<List<ProductUiModel>>>()
    val state : LiveData<UiState<List<ProductUiModel>>>
        get() = _state

    init {
        getAllProducts()
    }

    private fun getAllProducts(){
        viewModelScope.launch {
            getAllProductsUseCase().collectLatest {
                when(it){
                    is Resource.Loading -> _state.value = UiState.Loading
                    is Resource.Error -> _state.value = UiState.Error(it.message)
                    is Resource.Success -> {
                        _state.value = UiState.Success(it.data.shuffled())
                    }
                }
            }
        }
    }
}
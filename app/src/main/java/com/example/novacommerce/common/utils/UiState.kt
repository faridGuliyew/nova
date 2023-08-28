package com.example.novacommerce.common.utils

sealed class UiState <out T>{
    object Loading : UiState<Nothing>()
    data class Success <T> (val data: T) : UiState<T>()
    data class Error (val message : String) : UiState<Nothing>()
}

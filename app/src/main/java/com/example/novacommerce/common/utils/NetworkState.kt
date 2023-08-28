package com.example.novacommerce.common.utils

sealed class NetworkState <out T>{

    data class Success <out T>(val data : T) : NetworkState<T>()
    data class Error (val message : String) : NetworkState<Nothing>()
}

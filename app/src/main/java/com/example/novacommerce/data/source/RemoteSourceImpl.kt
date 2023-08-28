package com.example.novacommerce.data.source

import com.example.novacommerce.common.utils.NetworkState
import com.example.novacommerce.data.remote.CommerceApi
import com.example.novacommerce.data.remote.dto.allProducts.AllProductsResponseDto
import javax.inject.Inject

class RemoteSourceImpl @Inject constructor(
    private val commerceApi: CommerceApi,
) : RemoteSource {
    override suspend fun getAllProducts() =
        try {
            val request = commerceApi.getAllProducts()
            if (request.isSuccessful) NetworkState.Success(request.body())
            else NetworkState.Error("Internal error")
        } catch (e: Exception) {
            NetworkState.Error(e.localizedMessage ?: "Unexpected error occurred")
        }
}
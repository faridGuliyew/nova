package com.example.novacommerce.data.source

import com.example.novacommerce.common.utils.NetworkState
import com.example.novacommerce.data.remote.dto.allProducts.AllProductsResponseDto

interface RemoteSource {

    suspend fun getAllProducts() : NetworkState<AllProductsResponseDto?>
}
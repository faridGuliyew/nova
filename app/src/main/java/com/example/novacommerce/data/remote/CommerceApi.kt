package com.example.novacommerce.data.remote

import com.example.novacommerce.data.remote.dto.allProducts.AllProductsResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface CommerceApi {

    @GET("products")
    suspend fun getAllProducts() : Response<AllProductsResponseDto>
}
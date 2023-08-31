package com.example.novacommerce.data.source

import android.net.Uri
import android.util.Log
import com.example.novacommerce.common.utils.NetworkState
import com.example.novacommerce.data.remote.CommerceApi
import com.example.novacommerce.data.remote.dto.allProducts.AllProductsResponseDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RemoteSourceImpl @Inject constructor(
    private val commerceApi: CommerceApi,
    private val firestore: FirebaseFirestore
) : RemoteSource {
    override suspend fun getAllProducts() =
        try {
            val request = commerceApi.getAllProducts()
            if (request.isSuccessful) NetworkState.Success(request.body())
            else NetworkState.Error("Internal error")
        } catch (e: Exception) {
            NetworkState.Error(e.localizedMessage ?: "Unexpected error occurred")
        }

    override suspend fun getAllPromotions() =
        try {
            NetworkState.Success(firestore.collection("promotions").get().await())
        }catch (e:Exception){
            NetworkState.Error(e.localizedMessage?:"Unexpected error occurred")
        }
}
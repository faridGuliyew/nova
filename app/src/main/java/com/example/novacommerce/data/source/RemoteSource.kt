package com.example.novacommerce.data.source

import android.net.Uri
import com.example.novacommerce.common.utils.NetworkState
import com.example.novacommerce.data.remote.dto.allProducts.AllProductsResponseDto
import com.example.novacommerce.domain.model.ViewPagerUiModel
import com.google.firebase.firestore.QuerySnapshot

interface RemoteSource {

    suspend fun getAllProducts() : NetworkState<AllProductsResponseDto?>

    suspend fun getAllPromotions() : NetworkState<QuerySnapshot?>
}
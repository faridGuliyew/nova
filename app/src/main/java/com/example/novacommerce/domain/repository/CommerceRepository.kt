package com.example.novacommerce.domain.repository

import android.net.Uri
import com.example.novacommerce.common.utils.Resource
import com.example.novacommerce.domain.model.ProductUiModel
import com.example.novacommerce.domain.model.ViewPagerUiModel
import kotlinx.coroutines.flow.Flow

interface CommerceRepository {

    suspend fun getAllProducts() : Flow<Resource<List<ProductUiModel>>>

    suspend fun getAllPromotions() : Flow<Resource<List<ViewPagerUiModel>>>

}
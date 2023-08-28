package com.example.novacommerce.domain.repository

import com.example.novacommerce.common.utils.Resource
import com.example.novacommerce.domain.model.ProductUiModel
import kotlinx.coroutines.flow.Flow

interface CommerceRepository {

    suspend fun getAllProducts() : Flow<Resource<List<ProductUiModel>>>

}
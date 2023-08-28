package com.example.novacommerce.domain.repository

import com.example.novacommerce.common.utils.Resource
import com.example.novacommerce.domain.model.ProductUiModel
import kotlinx.coroutines.flow.Flow

//todo
interface FirebaseRepository {

    suspend fun authentication(email: String, password: String, mode : AuthMode) : Flow<Resource<String>>
}

enum class AuthMode{
    LOGIN, REGISTER
}
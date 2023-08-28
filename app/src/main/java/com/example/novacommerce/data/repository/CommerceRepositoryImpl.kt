package com.example.novacommerce.data.repository

import com.example.novacommerce.common.utils.NetworkState
import com.example.novacommerce.common.utils.Resource
import com.example.novacommerce.data.mapper.toProductUiModels
import com.example.novacommerce.data.source.RemoteSource
import com.example.novacommerce.domain.model.ProductUiModel
import com.example.novacommerce.domain.repository.CommerceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CommerceRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteSource
) : CommerceRepository {
    override suspend fun getAllProducts() = flow {

        emit(Resource.Loading)
        when(val request = remoteSource.getAllProducts()){
            is NetworkState.Error -> emit(Resource.Error(request.message))
            is NetworkState.Success -> emit(Resource.Success(request.data.orEmpty().toProductUiModels()))
        }
    }.catch {
        emit(Resource.Error(it.localizedMessage?:"Unexpected error occurred"))
    }.flowOn(Dispatchers.IO)
}
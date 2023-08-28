package com.example.novacommerce.data.repository

import com.example.novacommerce.common.utils.NetworkState
import com.example.novacommerce.common.utils.Resource
import com.example.novacommerce.data.mapper.toProductUiModels
import com.example.novacommerce.data.source.RemoteSource
import com.example.novacommerce.domain.model.ProductUiModel
import com.example.novacommerce.domain.repository.AuthMode
import com.example.novacommerce.domain.repository.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : FirebaseRepository {


    override suspend fun authentication(
        email: String,
        password: String,
        mode: AuthMode,
    ) = flow {
        emit(Resource.Loading)
        val request =
            if (mode == AuthMode.LOGIN) auth.signInWithEmailAndPassword(email, password).await()
            else auth.createUserWithEmailAndPassword(email, password).await()
        emit(Resource.Success(request.user?.uid ?: ""))
    }.catch { e ->
        emit(Resource.Error(e.localizedMessage ?: "Unexpected error occurred"))
    }.flowOn(Dispatchers.IO)
}
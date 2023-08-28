package com.example.novacommerce.domain.use_case

import com.example.novacommerce.domain.repository.AuthMode
import com.example.novacommerce.domain.repository.FirebaseRepository
import javax.inject.Inject



class LoginUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {

    suspend operator fun invoke(email : String, password : String) = firebaseRepository.authentication(email, password, AuthMode.LOGIN)
}
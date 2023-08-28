package com.example.novacommerce.domain.use_case

import com.example.novacommerce.domain.repository.CommerceRepository
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val commerceRepository: CommerceRepository
){
    suspend operator fun invoke() = commerceRepository.getAllProducts()
}
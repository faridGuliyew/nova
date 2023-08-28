package com.example.novacommerce.data.mapper

import com.example.novacommerce.data.remote.dto.allProducts.AllProductsResponseDto
import com.example.novacommerce.data.remote.dto.allProducts.AllProductsResultDto
import com.example.novacommerce.domain.model.ProductUiModel


fun List<AllProductsResultDto>.toProductUiModels() = map { responseDto ->
    ProductUiModel(
        responseDto.id,
        responseDto.title,
        responseDto.image,
        responseDto.price
    )
}

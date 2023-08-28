package com.example.novacommerce.domain.model

import com.example.novacommerce.common.base.DiffItem
import com.example.novacommerce.data.remote.dto.allProducts.Rating
import com.google.gson.annotations.SerializedName

data class ProductUiModel(
    val id: Int,
    val title: String,
    val image: String,
    val currentPrice: Double,
    val previousPrice : Double = 0.0,
    val saleRate : Int = 24
) : DiffItem() {
    override fun areItemsTheSame(newItem: DiffItem): Boolean {
        newItem as ProductUiModel
        return id == newItem.id
    }

    override fun areContentsTheSame(newItem: DiffItem): Boolean {
        return this == newItem
    }
}

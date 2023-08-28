package com.example.novacommerce.common.mock_models

import android.graphics.drawable.Drawable
import com.example.novacommerce.common.base.DiffItem
import com.example.novacommerce.common.base.DiffItemCallbackBase

data class CategoryModel(
    val categoryId : String = "unknown",
    val categoryDisplayName : String = "undefined",
    val categoryIcon : Drawable?
) : DiffItem() {
    override fun areItemsTheSame(newItem: DiffItem): Boolean {
        newItem as CategoryModel
        return categoryId == newItem.categoryId
    }

    override fun areContentsTheSame(newItem: DiffItem): Boolean {
        return this == newItem
    }
}

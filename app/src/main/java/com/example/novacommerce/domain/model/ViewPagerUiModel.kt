package com.example.novacommerce.domain.model

import com.example.novacommerce.common.base.DiffItem
import java.util.Calendar

data class ViewPagerUiModel(
    val id : Int = 0,
    val title : String = "No need for title",
    val description : String = "No need to describe!",
    val image : String = "broken link",
    val isCountdown : Int = 0,
    val timeLeftInMillis : Long = Calendar.getInstance().timeInMillis
) : DiffItem() {
    override fun areItemsTheSame(newItem: DiffItem): Boolean {
        newItem as ViewPagerUiModel
        return id == newItem.id
    }

    override fun areContentsTheSame(newItem: DiffItem): Boolean {
        return this == newItem
    }
}
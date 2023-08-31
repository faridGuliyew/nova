package com.example.novacommerce.domain.model

import android.os.Parcelable
import com.example.novacommerce.common.base.DiffItem
import com.google.firebase.firestore.PropertyName
import kotlinx.parcelize.Parcelize
import java.util.Calendar
@Parcelize
data class ViewPagerUiModel(
    val id : Int = 0,
    val title : String = "No need for title",
    val description : String = "No need to describe!",
    val image : String = "broken link",
    val countdown : Boolean = false,
    val timeLeftInMillis : Long = Calendar.getInstance().timeInMillis
) : DiffItem() , Parcelable {
    override fun areItemsTheSame(newItem: DiffItem): Boolean {
        newItem as ViewPagerUiModel
        return id == newItem.id
    }

    override fun areContentsTheSame(newItem: DiffItem): Boolean {
        return this == newItem
    }
}
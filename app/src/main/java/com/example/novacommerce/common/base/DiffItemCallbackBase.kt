package com.example.novacommerce.common.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class DiffItemCallbackBase<T : DiffItem> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.areItemsTheSame(newItem)
    }
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.areContentsTheSame(newItem)
    }
}



abstract class DiffItem{

    abstract fun areItemsTheSame(newItem : DiffItem) : Boolean

    abstract fun areContentsTheSame(newItem: DiffItem) : Boolean
}
package com.example.novacommerce.presentation.adapter.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.novacommerce.common.base.DiffItemCallbackBase
import com.example.novacommerce.common.mock_models.CategoryModel
import com.example.novacommerce.databinding.ItemCategoryBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    inner class CategoryViewHolder (private val itemCategoryBinding: ItemCategoryBinding) : RecyclerView.ViewHolder(itemCategoryBinding.root){
        fun bind(categoryModel : CategoryModel){
            with(itemCategoryBinding){
                category = categoryModel
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }


    val differ = AsyncListDiffer(this, DiffItemCallbackBase<CategoryModel>())
}
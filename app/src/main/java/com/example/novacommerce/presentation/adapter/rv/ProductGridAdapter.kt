package com.example.novacommerce.presentation.adapter.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.novacommerce.common.base.DiffItemCallbackBase
import com.example.novacommerce.databinding.ItemProductGridBinding
import com.example.novacommerce.domain.model.ProductUiModel

class ProductGridAdapter : RecyclerView.Adapter<ProductGridAdapter.ProductGridViewHolder>() {
    inner class ProductGridViewHolder (private val itemProductGridBinding: ItemProductGridBinding) : RecyclerView.ViewHolder(itemProductGridBinding.root){
        fun bind(productModel : ProductUiModel){
            with(itemProductGridBinding){
                product = productModel
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductGridViewHolder {
        return ProductGridViewHolder(
            ItemProductGridBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ProductGridViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    val differ = AsyncListDiffer(this, DiffItemCallbackBase<ProductUiModel>())
}
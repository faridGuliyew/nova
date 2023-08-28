package com.example.novacommerce.presentation.adapter.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.novacommerce.common.base.DiffItemCallbackBase
import com.example.novacommerce.databinding.ItemProductBinding
import com.example.novacommerce.domain.model.ProductUiModel

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    inner class ProductViewHolder (private val itemProductBinding: ItemProductBinding) : RecyclerView.ViewHolder(itemProductBinding.root){
        fun bind(productModel : ProductUiModel){
            with(itemProductBinding){
                product = productModel
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    val differ = AsyncListDiffer(this, DiffItemCallbackBase<ProductUiModel>())
}
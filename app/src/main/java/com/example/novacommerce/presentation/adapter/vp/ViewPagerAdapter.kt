package com.example.novacommerce.presentation.adapter.vp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.novacommerce.common.base.DiffItemCallbackBase
import com.example.novacommerce.common.utils.millisToReadable
import com.example.novacommerce.databinding.ItemViewpagerCountdownBinding
import com.example.novacommerce.databinding.ItemViewpagerNormalBinding
import com.example.novacommerce.domain.model.ViewPagerUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.DecisiveViewPagerViewHolder>() {

    inner class ViewPagerNormalViewHolder(private val itemViewpagerNormalBinding: ItemViewpagerNormalBinding) :
        DecisiveViewPagerViewHolder(itemViewpagerNormalBinding.root) {
        fun bind(uiModel: ViewPagerUiModel) {
            with(itemViewpagerNormalBinding) {
                model = uiModel
            }
        }
    }

    var onPromotionClicked = fun(promotion : ViewPagerUiModel){}
    inner class ViewPagerCountdownViewHolder(private val itemViewpagerCountdownBinding: ItemViewpagerCountdownBinding) :
        DecisiveViewPagerViewHolder(itemViewpagerCountdownBinding.root) {
        fun bind(uiModel: ViewPagerUiModel) {
            with(itemViewpagerCountdownBinding) {
                model = uiModel
                main.setOnClickListener { onPromotionClicked(uiModel) }
                CoroutineScope(Dispatchers.Main).launch {
                    millisToReadable(textViewHour,textViewMinute,textViewSecond, uiModel.timeLeftInMillis)
                }
            }
        }
    }

    open inner class DecisiveViewPagerViewHolder (view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DecisiveViewPagerViewHolder {
        return if (viewType == 0){
            val binding = ItemViewpagerNormalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ViewPagerNormalViewHolder(binding)
        }else{
            val binding = ItemViewpagerCountdownBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ViewPagerCountdownViewHolder(binding)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    override fun getItemViewType(position: Int): Int {
        return if (differ.currentList[position].countdown) 1 else 0
    }

    override fun onBindViewHolder(holder: DecisiveViewPagerViewHolder, position: Int) {
        val item = differ.currentList[position]
        Log.e("wtf?",item.toString())
        if (!item.countdown) (holder as ViewPagerNormalViewHolder).bind(item)
        else (holder as ViewPagerCountdownViewHolder).bind(item)
    }

    val differ = AsyncListDiffer(this, DiffItemCallbackBase<ViewPagerUiModel>())
}
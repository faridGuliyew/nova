package com.example.novacommerce.presentation.ui.main_screens.promotionDescFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.novacommerce.R
import com.example.novacommerce.common.base.BaseFragment
import com.example.novacommerce.common.utils.CustomLoadingDialog
import com.example.novacommerce.common.utils.UiState
import com.example.novacommerce.common.utils.showMotionToast
import com.example.novacommerce.databinding.FragmentPromotionDescBinding
import com.example.novacommerce.presentation.adapter.rv.ProductGridAdapter
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class PromotionDescFragment : BaseFragment<FragmentPromotionDescBinding>(FragmentPromotionDescBinding::inflate) {

    private val args by navArgs<PromotionDescFragmentArgs>()
    private val viewModel by viewModels<PromotionDescViewModel>()
    private val productAdapter = ProductGridAdapter()
    override fun onViewCreatedLight() {
        binding.promotion = args.promotion
        setRv()
        observe()
    }

    private fun observe(){
        val loadingDialog = CustomLoadingDialog(requireContext())
        with(viewModel){
            state.observe(viewLifecycleOwner){
                when(it){
                    is UiState.Loading -> {
                        loadingDialog.show()
                    }
                    is UiState.Error -> {
                        requireActivity().showMotionToast("Oops!",it.message,MotionToastStyle.ERROR)
                        loadingDialog.dismiss()
                    }
                    is UiState.Success -> {
                        loadingDialog.dismiss()
                        productAdapter.differ.submitList(it.data)
                    }
                }
            }
        }
    }

    private fun setRv(){
        binding.allProductsRv.adapter = productAdapter

    }
}
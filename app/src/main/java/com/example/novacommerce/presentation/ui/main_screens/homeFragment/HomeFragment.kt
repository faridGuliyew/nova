package com.example.novacommerce.presentation.ui.main_screens.homeFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.novacommerce.R
import com.example.novacommerce.common.base.BaseFragment
import com.example.novacommerce.common.utils.CustomLoadingDialog
import com.example.novacommerce.databinding.FragmentHomeBinding
import com.example.novacommerce.presentation.adapter.rv.CategoryAdapter
import com.example.novacommerce.presentation.adapter.rv.ProductAdapter
import com.example.novacommerce.presentation.adapter.vp.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeFragmentViewModel>()

    private val viewPagerTopAdapter = ViewPagerAdapter()
    private val categoryAdapter = CategoryAdapter()
    private val flashSaleAdapter = ProductAdapter()
    private val megaSaleAdapter = ProductAdapter()
    //change layout for all products, make it mini todo
    private val allProductsAdapter = ProductAdapter()

    override fun onViewCreatedLight() {

        setViewPager()
        setRv()
        observe()
    }

    private fun observe(){
        val loadingDialog = CustomLoadingDialog(requireContext())
        with(viewModel){
            state.observe(viewLifecycleOwner){
                Log.e("home",it.toString())
                when(it){
                    is HomeFragmentViewModel.HomeUiState.Loading->{
                        loadingDialog.show()
                    }
                    is HomeFragmentViewModel.HomeUiState.ItemsTopSuccess->{
                        flashSaleAdapter.differ.submitList(it.data)
                        megaSaleAdapter.differ.submitList(it.data.reversed())
                        allProductsAdapter.differ.submitList(it.data)
                        loadingDialog.dismiss()
                    }
                    is HomeFragmentViewModel.HomeUiState.ViewPagerTopSuccess->{
                        viewPagerTopAdapter.differ.submitList(it.data)
                        loadingDialog.dismiss()
                    }
                    is HomeFragmentViewModel.HomeUiState.CategorySuccess->{
                        categoryAdapter.differ.submitList(it.data)
                        loadingDialog.dismiss()
                    }
                    else->{}
                }
            }
        }
    }

    private fun setRv(){
        //category rv
        binding.categoryRv.adapter = categoryAdapter
        binding.flashSaleRv.adapter = flashSaleAdapter
        binding.megaSaleRv.adapter = megaSaleAdapter
        binding.allProductsRv.adapter = allProductsAdapter
    }
    private fun setViewPager(){
        //Top
        binding.viewPagerTop.adapter = viewPagerTopAdapter
        binding.dotsIndicator.attachTo(binding.viewPagerTop)
    }
}
package com.example.novacommerce.presentation.ui.main_screens.homeFragment

import android.content.Context
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novacommerce.R
import com.example.novacommerce.common.mock_models.CategoryModel
import com.example.novacommerce.common.utils.Resource
import com.example.novacommerce.domain.model.ProductUiModel
import com.example.novacommerce.domain.model.ViewPagerUiModel
import com.example.novacommerce.domain.use_case.GetAllProductsUseCase
import com.example.novacommerce.domain.use_case.GetAllPromotionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    @ApplicationContext private val context : Context,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getAllPromotionsUseCase: GetAllPromotionsUseCase
) : ViewModel(){

    private val _state = MutableLiveData<HomeUiState>()
    val state : LiveData<HomeUiState>
        get() = _state

    init {
        getAllPromotions()
        getCategories()
        getAllProducts()
    }

    private fun getAllProducts(){
        viewModelScope.launch {
            getAllProductsUseCase().collectLatest {
                when(it){
                    is Resource.Loading -> _state.value = HomeUiState.Loading
                    is Resource.Error -> _state.value = HomeUiState.Error(it.message)
                    is Resource.Success -> _state.value = HomeUiState.ItemsTopSuccess(it.data)
                }
            }
        }
    }
    private fun getAllPromotions(){
        viewModelScope.launch {
            getAllPromotionsUseCase().collectLatest {
                when(it){
                    is Resource.Loading -> _state.value = HomeUiState.Loading
                    is Resource.Error -> _state.value = HomeUiState.Error(it.message)
                    is Resource.Success -> _state.value = HomeUiState.ViewPagerTopSuccess(it.data)
                }
            }
            /*val mockList = arrayListOf<ViewPagerUiModel>()
            mockList.add(ViewPagerUiModel(image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"))
            mockList.add(ViewPagerUiModel(image = "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg"))
            mockList.add(ViewPagerUiModel(image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg", isCountdown = 1, title = "Flash sale of the year!"))
            mockList.add(ViewPagerUiModel(image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"))
            mockList.add(ViewPagerUiModel(image = "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg"))
            _state.value = HomeUiState.ViewPagerTopSuccess(mockList)*/
        }
    }
    private fun getCategories(){
        viewModelScope.launch {
            val mockList = arrayListOf<CategoryModel>()
            mockList.add(CategoryModel(categoryDisplayName = "Man shirt", categoryIcon = ResourcesCompat.getDrawable(context.resources, R.drawable.cat_men_shirt, context.theme)))
            mockList.add(CategoryModel(categoryDisplayName = "Dress", categoryIcon = ResourcesCompat.getDrawable(context.resources, R.drawable.cat_dress, context.theme)))
            mockList.add(CategoryModel(categoryDisplayName = "Man work equipment", categoryIcon = ResourcesCompat.getDrawable(context.resources, R.drawable.cat_man_work, context.theme)))
            mockList.add(CategoryModel(categoryDisplayName = "Woman bag", categoryIcon = ResourcesCompat.getDrawable(context.resources, R.drawable.cat_woman_bag, context.theme)))
            _state.value = HomeUiState.CategorySuccess(mockList)
        }
    }


    sealed class HomeUiState {
        object Loading : HomeUiState()
        data class Error(val message :String) : HomeUiState()
        data class ViewPagerTopSuccess (val data : List<ViewPagerUiModel>) : HomeUiState()
        data class ViewPagerBottomSuccess (val data :  List<ViewPagerUiModel>) : HomeUiState()
        data class ItemsTopSuccess (val data : List<ProductUiModel>) : HomeUiState()
        data class ItemsBottomSuccess <out T>(val data : T) : HomeUiState()
        data class CategorySuccess (val data : List<CategoryModel>) : HomeUiState()
    }
}
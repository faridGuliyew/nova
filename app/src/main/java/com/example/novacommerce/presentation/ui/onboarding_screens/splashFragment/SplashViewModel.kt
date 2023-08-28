package com.example.novacommerce.presentation.ui.onboarding_screens.splashFragment

import androidx.lifecycle.ViewModel
import com.example.novacommerce.common.utils.PrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel  @Inject constructor(
    private val prefManager: PrefManager
): ViewModel(){

    val isAuthenticated = prefManager.uid!=""
}
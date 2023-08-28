package com.example.novacommerce.presentation.ui.onboarding_screens.splashFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.novacommerce.R
import com.example.novacommerce.common.base.BaseFragment
import com.example.novacommerce.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private val viewModel by viewModels<SplashViewModel>()

    override fun onViewCreatedLight() {

        goToLoginFragment()
    }

    private fun goToLoginFragment(){
        lifecycleScope.launch {
            delay(1600)
            val destination = if (!viewModel.isAuthenticated) SplashFragmentDirections.actionSplashFragmentToSignInFragment()
            else SplashFragmentDirections.actionSplashFragmentToHomeFragment()
            findNavController().navigate(destination)
        }
    }

}
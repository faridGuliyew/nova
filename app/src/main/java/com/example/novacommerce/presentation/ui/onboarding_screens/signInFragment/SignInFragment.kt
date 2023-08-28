package com.example.novacommerce.presentation.ui.onboarding_screens.signInFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.novacommerce.R
import com.example.novacommerce.common.base.BaseFragment
import com.example.novacommerce.common.utils.CustomLoadingDialog
import com.example.novacommerce.common.utils.UiState
import com.example.novacommerce.common.utils.ValidationHelper
import com.example.novacommerce.common.utils.ValidationResult
import com.example.novacommerce.common.utils.showMotionToast
import com.example.novacommerce.databinding.FragmentSignInBinding
import com.example.novacommerce.presentation.ui.onboarding_screens.registerFragment.RegisterFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::inflate) {

    private val viewModel by viewModels<SignInViewModel>()

    override fun onViewCreatedLight() {

        signIn()
        goToRegister()
        observe()
    }

    private fun observe() {
        val loadingDialog = CustomLoadingDialog(requireContext())
        with(viewModel) {
            state.observe(viewLifecycleOwner) {
                when (it) {
                    is UiState.Loading -> {
                        loadingDialog.show()
                    }
                    is UiState.Error -> {
                        loadingDialog.cancel()
                        requireActivity().showMotionToast("Error", it.message, MotionToastStyle.ERROR)
                    }
                    is UiState.Success -> {
                        loadingDialog.cancel()
                        requireActivity().showMotionToast("Success", it.data, MotionToastStyle.SUCCESS)
                        findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToHomeFragment())
                    }
                }
            }
        }
    }

    private fun signIn(){
        binding.buttonSignIn.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()

            if (isValid(email, password)){
                viewModel.login(email, password)
            }
        }
    }


    private fun isValid(email : String, password : String) : Boolean{
        val mailValidation = ValidationHelper.mailValidation(email)
        val passwordValidation = ValidationHelper.singlePasswordValidation(password)

        return if (mailValidation is ValidationResult.Success){
            if (passwordValidation is ValidationResult.Success){
                true
            }else{
                requireActivity().showMotionToast("Validation", (passwordValidation as ValidationResult.Error).message,MotionToastStyle.ERROR)
                false
            }
        }else{
            requireActivity().showMotionToast("Validation", (mailValidation as ValidationResult.Error).message,MotionToastStyle.ERROR)
            false
        }
    }

    private fun goToRegister() {
        binding.textViewRegister.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToRegisterFragment())
        }
    }
}
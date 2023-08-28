package com.example.novacommerce.presentation.ui.onboarding_screens.registerFragment

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
import com.example.novacommerce.databinding.FragmentRegisterBinding
import com.example.novacommerce.presentation.ui.onboarding_screens.signInFragment.SignInFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel by viewModels<RegisterViewModel>()

    override fun onViewCreatedLight() {

        register()
        goToLogin()
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
                        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToHomeFragment())
                    }
                }
            }
        }
    }

    private fun register(){
        binding.buttonSignIn.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val passwordAgain = binding.editTextPasswordAgain.text.toString().trim()
            val username = binding.editTextName.text.toString().trim()

            if (isValid(email, password,passwordAgain)){
                viewModel.register(email, password,username)
            }

        }
    }

    private fun isValid(email : String, password : String, passwordAgain : String) : Boolean{
        val mailValidation = ValidationHelper.mailValidation(email)
        val passwordValidation = ValidationHelper.passwordMatchValidation(password, passwordAgain)

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

    private fun goToLogin(){
        binding.textViewSignIn.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToSignInFragment())
        }
    }

}
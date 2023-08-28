package com.example.novacommerce.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment <VB : ViewBinding> (private val layoutInflater: (LayoutInflater) -> VB) : Fragment() {

    private var _binding : VB? = null
    val binding get() = _binding!!

    abstract fun onViewCreatedLight()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = layoutInflater(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onViewCreatedLight()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
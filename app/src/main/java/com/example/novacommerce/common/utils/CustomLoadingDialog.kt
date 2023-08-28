package com.example.novacommerce.common.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Looper
import android.view.ViewPropertyAnimator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.novacommerce.R
import com.example.novacommerce.databinding.LoadingDialogBinding
import kotlinx.coroutines.delay
import java.util.logging.Handler

class CustomLoadingDialog(context : Context) : Dialog(context) {

    init {
        val binding = LoadingDialogBinding.inflate(layoutInflater)
        this.setContentView(binding.root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val rotation = AnimationUtils.loadAnimation(context, R.anim.anim_rotation);
        rotation.fillAfter = true;
        binding.imageView3.startAnimation(rotation);
    }

}
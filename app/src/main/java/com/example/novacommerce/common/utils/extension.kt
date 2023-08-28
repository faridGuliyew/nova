package com.example.novacommerce.common.utils

import android.app.Activity
import androidx.core.content.res.ResourcesCompat
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

fun Activity.showMotionToast(title :String, content : String, style : MotionToastStyle ){

    MotionToast.createColorToast(this,
        title,
        content,
        style,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(this, www.sanju.motiontoast.R.font.helvetica_regular))
}
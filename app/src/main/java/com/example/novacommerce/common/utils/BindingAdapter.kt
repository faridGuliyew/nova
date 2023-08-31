package com.example.novacommerce.common.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.novacommerce.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.util.Calendar

@BindingAdapter("custom-loadImageWithGlide")
fun ImageView.loadImageWithGlide(url: String?) {
    Glide.with(this).load(url)
        .placeholder(R.drawable.placeholder)
        .error(R.drawable.placeholder)
        .into(this)
}

suspend fun millisToReadable(
    hoursTextView: TextView,
    minutesTextView: TextView,
    secondsTextView: TextView,
    millis: Long,
) {
    val time = Calendar.getInstance()
    val current = time.timeInMillis
    time.timeInMillis = millis - current

    fun unitToText(unit: Int): String {
        return if (unit > 9) unit.toString() else "0$unit"
    }

    var hours = time.get(Calendar.HOUR_OF_DAY)
    var minutes = time.get(Calendar.MINUTE)
    var seconds = time.get(Calendar.SECOND)

    fun updateTimer(){
        hoursTextView.text = unitToText(hours)
        minutesTextView.text = unitToText(minutes)
        secondsTextView.text = unitToText(seconds)
    }

    updateTimer()

    while (true) {
        delay(1000)
        if (seconds > 0) {
            seconds -= 1
        } else {
            if (minutes > 0){
                seconds = 59
                minutes -= 1
            }else{
                if (hours > 0){
                    minutes = 59
                    hours -=1
                }else{
                    hours = 0
                }
            }
        }
        updateTimer()
    }
}

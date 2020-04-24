package com.ashwanisng.covid19_tracker

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan

//  for the value inside the total numners
class SpanniableDelta( text : String, color: String, start: Int) : SpannableString(text) {

    init {
        setSpan(
            ForegroundColorSpan(Color.parseColor(color)),
//            start index
            start,
//            end index
            text.length,
//            it know weather to include start or end index or not
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE

        )
    }

}
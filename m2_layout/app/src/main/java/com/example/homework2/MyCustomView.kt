package com.example.homework2

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.homework2.databinding.MyCustomViewBinding

class MyCustomView @JvmOverloads constructor (
    context: Context, attrs: AttributeSet? = null
) : LinearLayout (context, attrs) {
    private val binding: MyCustomViewBinding
    init {
        val inflatedView = inflate(context, R.layout.my_custom_view, this)
        binding = MyCustomViewBinding.bind(inflatedView)
    }

    fun setTopStringText(message: String) {
        binding.topText.text = message
    }

    fun setBottomStringText(message: String) {
        binding.bottomText.text = message
    }

    fun changeBlackSquareColor(color: Int) {
        binding.blackSquare.setBackgroundColor(color)
    }
}
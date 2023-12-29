package com.example.foodydemomj_28thnov.adapters.bindingadpaters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.example.foodydemomj_28thnov.R

class RecipesRowBingding {

    companion object {

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, url: String) {

            imageView.load(url){
                crossfade(600)
                error(R.drawable.error_placeholder)
            }
        }

        @BindingAdapter("setNumberOfLikes")
        @JvmStatic
        fun setNumberOfLikes(textView: TextView, likes: Int) {

            textView.text = likes.toString()
        }

        @BindingAdapter("setTimeTakeToPrepare")
        @JvmStatic
        fun setTimeTakeToPrepare(textView: TextView, time: Int) {

            textView.text = time.toString()
        }

        @BindingAdapter("applyVeganColor")
        @JvmStatic
        fun applyVeganColor(view: View, vegan: Boolean) {

            if (vegan) {

                when (view) {

                    is TextView -> {
                        view.setTextColor(ContextCompat.getColor(view.context, R.color.green))
                    }

                    is ImageView -> {
                        view.setColorFilter(ContextCompat.getColor(view.context, R.color.green))
                    }
                }
            }
        }
    }
}
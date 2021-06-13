package com.yigitcanalasoy.spotify.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideUtil {
    fun UrldekiResmiAl(url : String, context: Context, imageView: ImageView){
        Glide.with(context)
                .load(url)
                .into(imageView)
    }

}
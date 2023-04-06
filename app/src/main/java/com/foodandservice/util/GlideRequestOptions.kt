package com.foodandservice.util

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Priority
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

@GlideModule
class GlideRequestOptions : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val requestOptions: RequestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .skipMemoryCache(true)
            .centerCrop()
            .dontAnimate()
            .dontTransform()
            .priority(Priority.IMMEDIATE)
            .encodeFormat(Bitmap.CompressFormat.PNG)
            .format(DecodeFormat.DEFAULT)

        builder.setDefaultRequestOptions(requestOptions)
    }
}
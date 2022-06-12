package com.foodandservice.common

import android.graphics.Bitmap
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

object GlideRequestOptions : RequestOptions() {
    private val requestOptions: RequestOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .skipMemoryCache(true)
        .centerCrop()
        .dontAnimate()
        .dontTransform()
        .priority(Priority.IMMEDIATE)
        .encodeFormat(Bitmap.CompressFormat.PNG)
        .format(DecodeFormat.DEFAULT)

    fun getGlideRequestOptions(): RequestOptions {
        return requestOptions
    }
}
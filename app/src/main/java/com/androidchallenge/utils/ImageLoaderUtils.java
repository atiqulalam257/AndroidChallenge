package com.androidchallenge.utils;


import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class ImageLoaderUtils {

    /**
     * Loads an image into an ImageView using Glide with caching and error handling.
     *
     * @param context   The application or activity context.
     * @param imageUrl  The URL or resource to load.
     * @param imageView The ImageView where the image will be displayed.
     * @param placeholderRes The placeholder image while loading.
     * @param errorRes The error image if the load fails.
     * @param cornerRadius The radius of the corners to apply.
     */

    public static void loadImage(Context context, String imageUrl, ImageView imageView,
                                 @DrawableRes int placeholderRes, @DrawableRes int errorRes, int cornerRadius) {
        Glide.with(context)
                .load(imageUrl)
                .apply(new RequestOptions()
                        .placeholder(placeholderRes)
                        .error(errorRes)
                        .diskCacheStrategy(DiskCacheStrategy.ALL) // Cache both original & resized image
                        .transform(new MultiTransformation<>(new CenterCrop(), new RoundedCorners(cornerRadius)))) // Apply both CenterCrop & RoundedCorners
                .into(imageView);
    }





    /**
     * Loads an image from a drawable resource.
     *
     * @param context   The application or activity context.
     * @param imageRes  The drawable resource ID.
     * @param imageView The ImageView where the image will be displayed.
     */
    public static void loadImage(Context context, @DrawableRes int imageRes, ImageView imageView) {
        Glide.with(context)
                .load(imageRes)
                .into(imageView);
    }

    /**
     * Clears Glide cache (useful when refreshing images).
     *
     * @param context The application context.
     */
    public static void clearCache(Context context) {
        new Thread(() -> Glide.get(context).clearDiskCache()).start();
        Glide.get(context).clearMemory();
    }
}

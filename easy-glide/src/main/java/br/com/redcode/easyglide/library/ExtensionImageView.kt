package br.com.redcode.easyglide.library

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.media.ThumbnailUtils
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import java.io.File


/**
 * Created by pedrofsn on 16/10/2017.
 */

private const val placeholder = android.R.drawable.stat_sys_download
private val error = R.drawable.ic_error

// URL
fun ImageView.load(url: String?,
                   onSuccess: (() -> Unit)? = null,
                   enableCrossfade: Boolean? = null) {
    this.loadCompleteUrlImage(url = url,
        glideRequestOption = buildRequestOptions(
            false, placeholder, false, error),
        onSuccess = onSuccess,
        enableCrossfade = enableCrossfade)
}

fun ImageView.load(url: String?,
                   withPlaceholder: Boolean = false,
                   withError: Boolean = false,
                   onSuccess: (() -> Unit)? = null,
                   enableCrossfade: Boolean? = null) {
    this.loadCompleteUrlImage(url = url,
        glideRequestOption = buildRequestOptions(
            withPlaceholder, placeholder, withError, error),
        onSuccess = onSuccess,
        enableCrossfade = enableCrossfade)
}

fun ImageView.load(url: String?,
                   withPlaceholder: Boolean = false,
                   withError: Int,
                   onSuccess: (() -> Unit)? = null,
                   enableCrossfade: Boolean? = null) {
    this.loadCompleteUrlImage(url = url,
        glideRequestOption = buildRequestOptions(
            withPlaceholder, placeholder, true, withError),
        onSuccess = onSuccess,
        enableCrossfade = enableCrossfade)
}

fun ImageView.load(url: String?,
                   withPlaceholder: Int,
                   withError: Boolean = false,
                   onSuccess: (() -> Unit)? = null,
                   enableCrossfade: Boolean? = null) {
    this.loadCompleteUrlImage(url = url,
        glideRequestOption = buildRequestOptions(
            true, withPlaceholder, withError, error),
        onSuccess = onSuccess,
        enableCrossfade = enableCrossfade)
}

fun ImageView.load(url: String?,
                   withPlaceholder: Int,
                   withError: Int,
                   onSuccess: (() -> Unit)? = null,
                   enableCrossfade: Boolean? = null) {
    this.loadCompleteUrlImage(url = url,
        glideRequestOption = buildRequestOptions(
            true, withPlaceholder, true, withError),
        onSuccess = onSuccess,
        enableCrossfade = enableCrossfade)
}

fun ImageView.loadCompleteUrlImage(
    url: String?,
    glideRequestOption: RequestOptions,
    onSuccess: (() -> Unit)? = null,
    enableCrossfade: Boolean? = null
) {
    url?.let {
        if (it.isNotBlank()) {
            val callback = object : RequestListener<Drawable> {
                override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                ): Boolean {
                    return true
                }

                override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                ): Boolean {
                    onSuccess?.invoke()
                    return false
                }
            }


            Glide.with(context)
                        .load(url)
                        .apply(glideRequestOption)
                        .apply {
                            if (enableCrossfade == null || enableCrossfade) {
                                transition(DrawableTransitionOptions.withCrossFade())
                            }
                        }
                        .thumbnail(0.25f)
                        .listener(callback)
                        .into(this)

    }
}}


// Bitmap
fun ImageView.load(bitmap: Bitmap?,
                   onSuccess: (() -> Unit)? = null,
                   enableCrossfade: Boolean? = null) {
    this.loadCompleteBitmapImage(bitmap = bitmap,
        glideRequestOption = buildRequestOptions(
            false, placeholder, false, error),
        onSuccess = onSuccess,
        enableCrossfade = enableCrossfade)
}

fun ImageView.load(bitmap: Bitmap?,
                   withPlaceholder: Boolean = false,
                   withError: Boolean = false,
                   onSuccess: (() -> Unit)? = null,
                   enableCrossfade: Boolean? = null) {
    this.loadCompleteBitmapImage(bitmap = bitmap,
        glideRequestOption = buildRequestOptions(
            withPlaceholder, placeholder, withError, error),
        onSuccess = onSuccess,
        enableCrossfade = enableCrossfade)
}

fun ImageView.load(bitmap: Bitmap?,
                   withPlaceholder: Boolean,
                   withError: Int,
                   onSuccess: (() -> Unit)? = null,
                   enableCrossfade: Boolean? = null) {
    this.loadCompleteBitmapImage(bitmap = bitmap,
        glideRequestOption = buildRequestOptions(
            withPlaceholder, placeholder, true, withError),
        onSuccess = onSuccess,
        enableCrossfade = enableCrossfade)
}

fun ImageView.load(bitmap: Bitmap?,
                   withPlaceholder: Int,
                   withError: Boolean = false,
                   onSuccess: (() -> Unit)? = null,
                   enableCrossfade: Boolean? = null) {
    this.loadCompleteBitmapImage(bitmap = bitmap,
        glideRequestOption = buildRequestOptions(
            true, withPlaceholder, withError, error),
        onSuccess = onSuccess,
        enableCrossfade = enableCrossfade)
}

fun ImageView.load(bitmap: Bitmap?,
                   withPlaceholder: Int,
                   withError: Int,
                   onSuccess: (() -> Unit)? = null,
                   enableCrossfade: Boolean? = null) {
    this.loadCompleteBitmapImage(bitmap = bitmap,
        glideRequestOption = buildRequestOptions(
            true, withPlaceholder, true, withError),
        onSuccess = onSuccess,
        enableCrossfade = enableCrossfade)
}


private fun ImageView.loadCompleteBitmapImage(
        bitmap: Bitmap?,
        glideRequestOption: RequestOptions,
        onSuccess: (() -> Unit)? = null,
        enableCrossfade: Boolean? = null
) {
    if (bitmap != null) {
        val callback = object : RequestListener<Drawable> {
            override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
            ): Boolean {
                return true
            }

            override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
            ): Boolean {
                onSuccess?.invoke()
                return false
            }
        }

        Glide.with(context)
            .load(bitmap)
            .apply(glideRequestOption)
            .apply {
                if (enableCrossfade == null || enableCrossfade) {
                    transition(DrawableTransitionOptions.withCrossFade())
                }
            }
            .thumbnail(0.25f)
            .listener(callback)
            .into(this)
    }
}
fun ImageView.load(drawable: Int?, requestOption: RequestOptions? = RequestOptions()) {
    drawable.let {
        Glide.with(context)
            .load(drawable)
            .apply(requestOption!!)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}

fun View.loadInView(drawable: Int, requestOption: RequestOptions? = RequestOptions()) = Glide.with(this)
    .load(drawable)
    .apply(requestOption!!)
    .into(object : SimpleTarget<Drawable>() {
        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
            background = resource
        }
    })


fun View.loadInView(drawable: Drawable, requestOption: RequestOptions? = RequestOptions()) = Glide.with(this)
    .load(drawable)
    .apply(requestOption!!)
    .into(object : SimpleTarget<Drawable>() {
        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
            background = resource
        }
    })

fun ImageView.load(file: File?, requestOption: RequestOptions? = RequestOptions()) {
    file.let {
        Glide.with(context)
            .load(file)
            .apply(requestOption!!)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}

fun ImageView.load(drawable: Drawable?, requestOption: RequestOptions? = RequestOptions()) {
    drawable.let {
        val callback = object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return true
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {

                return false
            }
        }

        Glide.with(context)
            .load(drawable)
            .apply(requestOption!!)
            .transition(DrawableTransitionOptions.withCrossFade())
            .listener(callback)
            .into(this)
    }
}

fun ImageView.load(fragment: Fragment, drawable: Int, requestOption: RequestOptions? = RequestOptions()) {
    drawable.let {
        Glide.with(fragment)
            .load(drawable)
            .apply(requestOption!!)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}

fun ImageView.loadWithCircleTransform(
    url: String?,
    diskStrategy: DiskCacheStrategy = DiskCacheStrategy.AUTOMATIC,
    enableCrossfade: Boolean? = null,
    roundingRadiusCorner: Int? = null
) {
    url?.let {
        //RoundedCorners
        val requestOptions = RequestOptions
            .circleCropTransform()
            .diskCacheStrategy(diskStrategy)
            .skipMemoryCache(diskStrategy == DiskCacheStrategy.NONE)

        if (roundingRadiusCorner != null) {
            requestOptions.transforms(RoundedCorners(roundingRadiusCorner))
        }

        Glide.with(context)
            .load(it)
            .apply(requestOptions)
            .apply {
                if (enableCrossfade == null || enableCrossfade) {
                    transition(DrawableTransitionOptions.withCrossFade())
                }
            }
            .into(this)
    }
}

fun ImageView.tint(cor: String?) {
    cor?.let {
        val colorString = Color.parseColor(cor)
        val colorStateList = ColorStateList.valueOf(colorString)
        ImageViewCompat.setImageTintList(this, colorStateList)
    }
}

fun ImageView.tint(colorInt: Int?) {
    colorInt?.let {
        val color = ContextCompat.getColor(context, colorInt)
        val colorStateList = ColorStateList.valueOf(color)
        ImageViewCompat.setImageTintList(this, colorStateList)
    }
}

fun getBitmapThumbnail(pathFile: String, thumbSquare: Int? = null, width: Int? = null, height: Int? = null): Bitmap? {
    val mSquare = thumbSquare ?: 64
    val mWidth = width ?: mSquare
    val mHeight = height ?: mSquare

    return ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(pathFile), mWidth, mHeight)
}

private fun buildRequestOptions(withPlaceholder: Boolean,
                                placeholderResId: Int,
                                withError: Boolean,
                                errorResId: Int): RequestOptions {
    val requestOptions: RequestOptions = if (withPlaceholder)
        RequestOptions().placeholder(placeholderResId) else RequestOptions()
    return if (withError) requestOptions.error(errorResId) else requestOptions
}
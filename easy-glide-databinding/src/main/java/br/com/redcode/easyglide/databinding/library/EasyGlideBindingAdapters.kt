package br.com.redcode.easyglide.databinding.library

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import br.com.redcode.easyglide.library.getBitmapThumbnail
import br.com.redcode.easyglide.library.load
import br.com.redcode.easyglide.library.loadInView
import br.com.redcode.easyglide.library.loadWithCircleTransform
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


// -------------------------------------------------------------------------------------------------> ImageView

@BindingAdapter(value = ["app:loadCircle", "app:diskStrategy", "app:enableCrossfade", "app:roundingRadiusCorner"], requireAll = false)
fun loadCircle(imageView: ImageView?, url: String?, diskStrategy: DiskCacheStrategy?, enableCrossfade: Boolean?, roundingRadiusCorner: Int?) {
    imageView?.loadWithCircleTransform(
        url = url,
        diskStrategy = diskStrategy ?: DiskCacheStrategy.AUTOMATIC,
        enableCrossfade = enableCrossfade,
        roundingRadiusCorner = roundingRadiusCorner
    )
}


@BindingAdapter(
    value = ["app:load", "app:enableCrossfade", "app:thumbnailWidth", "app:thumbnailHeight", "app:thumbnailSquare"],
    requireAll = false
)
fun load(
    imageView: ImageView?,
    url: String?,
    enableCrossfade: Boolean?,
    thumbnailWidth: Int?,
    thumbnailHeight: Int?,
    thumbnailSquare: Int?
) {

    if (url != null) {
        when {
            thumbnailWidth != null && thumbnailHeight != null -> {

                val bitmap = getBitmapThumbnail(pathFile = url, width = thumbnailWidth, height = thumbnailHeight)

                Glide.with(imageView!!.context)
                    .load(bitmap)
                    .apply {
                        if (enableCrossfade == null || enableCrossfade) {
                            transition(DrawableTransitionOptions.withCrossFade())
                        }
                    }
                    .into(imageView)
            }

            thumbnailSquare != null -> {

                val bitmap = getBitmapThumbnail(pathFile = url, thumbSquare = thumbnailSquare)

                Glide.with(imageView!!.context)
                    .load(bitmap)
                    .apply {
                        if (enableCrossfade == null || enableCrossfade) {
                            transition(DrawableTransitionOptions.withCrossFade())
                        }
                    }
                    .into(imageView)

            }

            else -> {
                imageView?.load(
                    url = url,
                    enableCrossfade = enableCrossfade
                )
            }
        }
    }
}

@BindingAdapter("app:loadInView")
fun load(view: View?, @DrawableRes drawable: Int) = view?.loadInView(drawable)

@BindingAdapter("app:loadInView")
fun load(view: View?, drawable: Drawable) = view?.loadInView(drawable)

@BindingAdapter("app:load")
fun load(imageView: ImageView?, drawable: Drawable?) = imageView?.load(drawable)

@BindingAdapter("app:load")
fun load(imageView: ImageView?, @DrawableRes drawable: Int?) = imageView?.load(drawable)
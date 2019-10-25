package br.com.redcode.easyglide.databinding.library

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import br.com.redcode.easyglide.library.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions


// -------------------------------------------------------------------------------------------------> ImageView

private const val placeholder = android.R.drawable.stat_sys_download
private val error = R.drawable.ic_error

@BindingAdapter(
    value = ["app:loadCircle", "app:diskStrategy", "app:enableCrossfade", "app:roundingRadiusCorner"],
    requireAll = false
)
fun loadCircle(
    imageView: ImageView,
    url: String?,
    diskStrategy: DiskCacheStrategy?,
    enableCrossfade: Boolean?,
    roundingRadiusCorner: Int?
) {
    imageView.loadWithCircleTransform(
        url = url,
        diskStrategy = diskStrategy ?: DiskCacheStrategy.AUTOMATIC,
        enableCrossfade = enableCrossfade,
        roundingRadiusCorner = roundingRadiusCorner
    )
}


@BindingAdapter(
    value = ["app:load", "app:withPlaceholder", "app:placeholderDrawable", "app:withError", "app:errorDrawable", "app:enableCrossfade", "app:thumbnailWidth", "app:thumbnailHeight", "app:thumbnailSquare"],
    requireAll = false
)
fun load(
    imageView: ImageView,
    url: String?,
    withPlaceholder: Boolean = false,
    placeholderDrawable: Drawable?,
    withError: Boolean = false,
    errorDrawable: Drawable?,
    enableCrossfade: Boolean?,
    thumbnailWidth: Int?,
    thumbnailHeight: Int?,
    thumbnailSquare: Int?
) {
    val requestOptions = RequestOptions().apply {

        // placeholder drawable
        when {
            // Use defined by user
            withPlaceholder && placeholderDrawable != null -> placeholder(placeholderDrawable)

            // Use library default
            withPlaceholder && placeholderDrawable == null -> placeholder(placeholder)
        }

        // error drawable
        when {
            // Use defined by user
            withError && errorDrawable != null -> error(errorDrawable)

            // Use library default
            withError && errorDrawable == null -> error(error)
        }
    }


    if (url != null) {
        when {
            thumbnailWidth != null && thumbnailHeight != null -> {

                val bitmap = getBitmapThumbnail(
                    pathFile = url,
                    width = thumbnailWidth,
                    height = thumbnailHeight
                )

                Glide.with(imageView.context)
                    .load(bitmap)
                    .apply {
                        if (enableCrossfade == null || enableCrossfade) {
                            transition(DrawableTransitionOptions.withCrossFade())
                        }
                    }
                    .apply(requestOptions)
                    .into(imageView)
            }

            thumbnailSquare != null -> {
                val bitmap = getBitmapThumbnail(pathFile = url, thumbSquare = thumbnailSquare)

                Glide.with(imageView.context)
                    .load(bitmap)
                    .apply {
                        if (enableCrossfade == null || enableCrossfade) {
                            transition(DrawableTransitionOptions.withCrossFade())
                        }
                    }
                    .apply(requestOptions)
                    .into(imageView)
            }

            else -> {
                imageView.loadCompleteUrlImage(
                    url = url,
                    glideRequestOption = requestOptions,
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
fun load(imageView: ImageView, drawable: Drawable?) = imageView.load(drawable)

@BindingAdapter("app:load")
fun load(imageView: ImageView, @DrawableRes drawable: Int?) = imageView.load(drawable)
package br.com.redcode.easyglide.sample

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.redcode.easyglide.library.load
import br.com.redcode.easyglide.library.loadWithCircleTransform
import br.com.redcode.easyglide.sample.databinding.ActivityMainBinding


/*
* This sample is using 'implementation project(':easy-glide-databinding')' to show
* usage WITH databinding AND WITHOUT databinding.
*
* To use this library WITHOUT databinding you can use
* 'implementation project(':easy-glide')'
*  and traditional way to setContetView and inflate your views.
* */
class MainActivity : AppCompatActivity() {

    private val image = "http://pedrofsn.com.br/images/me.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContetView -> "DataBinding version"
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        // Applying value to UI (databinding)
        binding.data = image

        // -------------------- USAGE WITHOTU DATA BINDING
        val imageView1 = findViewById<ImageView>(R.id.imageView1)
        val imageView2 = findViewById<ImageView>(R.id.imageView2)
        val imageView3 = findViewById<ImageView>(R.id.imageView3)

        imageView1.load(image)
        imageView2.loadWithCircleTransform(
            url = image,
            roundingRadiusCorner = 30
        )
        imageView3.loadWithCircleTransform(url = image)
    }
}
package br.com.redcode.easyglide.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.redcode.easyglide.library.loadWithCircleTransform
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView.loadWithCircleTransform("http://pedrofsn.com.br/images/me.jpg")
    }
}

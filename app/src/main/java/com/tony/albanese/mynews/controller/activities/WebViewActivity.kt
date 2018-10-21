package com.tony.albanese.mynews.controller.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.tony.albanese.mynews.R
import com.tony.albanese.mynews.controller.utilities.URL_EXTRA
import com.tony.albanese.mynews.controller.utilities.networkIsAvailable
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val intent = getIntent()
        if (intent != null) {
            val url = intent.extras.getString(URL_EXTRA)
            if (!url.isNullOrEmpty() && !url.isNullOrBlank() && networkIsAvailable(this)) {
                article_web_view.loadUrl(url)
            }
        } else {
            val toast = Toast.makeText(this, "An error occured", Toast.LENGTH_SHORT)
            toast.show()
        }

    }
}

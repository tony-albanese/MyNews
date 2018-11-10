package com.tony.albanese.mynews.controller.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.WebView
import android.widget.TextView
import android.widget.Toast
import com.tony.albanese.mynews.R
import com.tony.albanese.mynews.controller.utilities.URL_EXTRA
import com.tony.albanese.mynews.controller.utilities.networkIsAvailable
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val webView = findViewById<WebView>(R.id.article_web_view)
        val emptyView = findViewById<TextView>(R.id.webview_empty_view)

        val intent = getIntent()
        if (intent != null && networkIsAvailable(this)) {
            val url = intent.extras.getString(URL_EXTRA)
            //Set the visibility there is something wrong with the url.
            if (!url.isNullOrEmpty() && !url.isNullOrBlank()) {
                article_web_view.loadUrl(url)
                webView.visibility = View.VISIBLE
                emptyView.visibility = View.GONE
            }
        } else {
            val toast = Toast.makeText(this, "An error occured", Toast.LENGTH_SHORT)
            toast.show()
            webView.visibility = View.GONE
            emptyView.visibility = View.VISIBLE
        }

    }
}

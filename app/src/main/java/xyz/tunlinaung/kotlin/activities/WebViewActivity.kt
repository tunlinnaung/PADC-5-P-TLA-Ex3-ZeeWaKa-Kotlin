package xyz.tunlinaung.kotlin.activities

import android.webkit.WebViewClient
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_webview.*
import xyz.tunlinaung.zeewaka_kotlin.R


class WebViewActivity : AppCompatActivity() {
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)

        val url = intent.getStringExtra(WEBSITE_ADDRESS)
        if (url == null || url.isEmpty()) finish()

        setContentView(R.layout.activity_webview)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)

        btnClose.setOnClickListener {
            super.onBackPressed()
            finish()
        }
    }

    companion object {
        val WEBSITE_ADDRESS = "website_address"
    }
}
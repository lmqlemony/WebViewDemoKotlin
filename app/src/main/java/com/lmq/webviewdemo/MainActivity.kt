package com.lmq.webviewdemo

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.view.KeyEvent
import android.webkit.WebView
import android.webkit.WebViewClient


class MainActivity : AppCompatActivity() {
    lateinit var myWebView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myWebView = findViewById(R.id.webview)
        myWebView.webViewClient = MyWebViewClient()
        myWebView.settings.javaScriptEnabled = true
        myWebView.loadUrl("https://lmqlemony.github.io/")
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && myWebView.canGoBack()) {
            myWebView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}

private class MyWebViewClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (Uri.parse(url).host == "https://lmqlemony.github.io/") {
            return false
        }
        Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            //            startActivity(this)
            if (view != null) {
                view.loadUrl(url)
            }
        }
        return true
    }
}



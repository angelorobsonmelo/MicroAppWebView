package com.angelorobson.customwebview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.angelorobson.customwebview.databinding.ActivityCustomWebViewBinding
import kotlinx.android.synthetic.main.activity_custom_web_view.view.*

class CustomMicroAppWebView(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {

    private lateinit var binding: ActivityCustomWebViewBinding

    init {
        init(context, attrs)
    }

    @SuppressLint("JavascriptInterface", "SetJavaScriptEnabled", "AddJavascriptInterface")
    private fun init(context: Context, attrs: AttributeSet) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.activity_custom_web_view, this, true)
        val myWebView = loadWebView(attrs)

        myWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url.equals("https://www.soluevo.com.br/sobre-nos")) {
                    showToastButton?.visibility = View.VISIBLE
                }
                return true
            }
        }

        binding.showToastButton.setOnClickListener {
            Toast.makeText(context, "Exibindo Toast", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("JavascriptInterface", "SetJavaScriptEnabled")
    private fun loadWebView(attrs: AttributeSet): WebView {
        var url = ""

        if (attrs != null) {
            val packageName = "http://schemas.android.com/apk/res-auto"
            url = attrs.getAttributeValue(packageName, "websiteUrl")
        }

        val myWebView = binding.webView
        val webSettings = myWebView.settings
        webSettings.javaScriptEnabled = true
        myWebView.addJavascriptInterface(this, "ExemploWebView")
        webSettings.domStorageEnabled = true
        myWebView.webChromeClient = WebChromeClient()
        val filePath = url
        myWebView.loadUrl(filePath)
        return myWebView
    }

}
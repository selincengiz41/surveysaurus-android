package com.android.surveysaurus

import android.webkit.WebView
import android.webkit.WebViewClient



  class MyWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {

            return false

        }
    }


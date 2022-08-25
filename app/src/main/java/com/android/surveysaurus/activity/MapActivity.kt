package com.android.surveysaurus.activity

import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import androidx.annotation.RequiresApi
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebViewClientCompat
import com.android.surveysaurus.databinding.ActivityMainBinding
import com.android.surveysaurus.databinding.ActivityMapBinding



class MapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMapBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

      setRequestedOrientation(
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        val assetLoader = WebViewAssetLoader.Builder()
            .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(binding.root.context))
            .addPathHandler("/res/", WebViewAssetLoader.ResourcesPathHandler(binding.root.context))
            .build()
        binding.webview.webViewClient =
           LocalContentWebViewClient(assetLoader)
        binding.webview.requestFocus();

        binding.webview.getSettings().setLightTouchEnabled(true);
        binding.webview.getSettings().setJavaScriptEnabled(true);
        binding.webview.getSettings().setGeolocationEnabled(true);
        binding.webview.getSettings().builtInZoomControls=true
        binding.webview.setSoundEffectsEnabled(true);
        binding.webview.loadUrl("https://appassets.androidplatform.net/assets/index.html" );


    }
}

private class LocalContentWebViewClient(private val assetLoader: WebViewAssetLoader) : WebViewClientCompat() {
    @RequiresApi(21)
    override fun shouldInterceptRequest(
        view: WebView,
        request: WebResourceRequest
    ): WebResourceResponse? {
        return assetLoader.shouldInterceptRequest(request.url)
    }

    // to support API < 21
    override fun shouldInterceptRequest(
        view: WebView,
        url: String
    ): WebResourceResponse? {
        return assetLoader.shouldInterceptRequest(Uri.parse(url))
    }
}
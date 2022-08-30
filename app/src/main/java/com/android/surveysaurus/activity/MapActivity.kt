package com.android.surveysaurus.activity

import android.Manifest
import android.R.attr
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebViewClientCompat
import com.android.surveysaurus.databinding.ActivityMapBinding
import com.android.surveysaurus.model.IsFilledModel
import com.android.surveysaurus.service.ApiService
import java.io.*


class MapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMapBinding
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setRequestedOrientation(
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        );

        val intent = intent
        val titleIntent = intent.getStringExtra("title")

        if (!titleIntent.isNullOrEmpty()) {
            try {
                val apiService = ApiService()
                val title: IsFilledModel = IsFilledModel(titleIntent!!)
                println(title.title)
                apiService.getMap(title) {

                    if (it.toString() != null) {
                        Toast.makeText(
                            view.context,
                            "Succesful", Toast.LENGTH_SHORT
                        ).show();
                        try {

                            val content = it!!.csv

                            val data = content.split("/n").toTypedArray()


                            if (ContextCompat.checkSelfPermission(
                                    this@MapActivity,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                                ) != PackageManager.PERMISSION_GRANTED
                            ) {
                                ActivityCompat.requestPermissions(
                                    this@MapActivity,
                                    arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                                    0
                                );

                            } else {

                            }

                        } catch (e: IOException) {
                            e.printStackTrace()
                        }

                    } else {
                        Toast.makeText(
                            view.context,
                            "Fail", Toast.LENGTH_SHORT
                        ).show();

                    }

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


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
        binding.webview.getSettings().builtInZoomControls = true
        binding.webview.setSoundEffectsEnabled(true);
        binding.webview.loadUrl("https://appassets.androidplatform.net/assets/index.html");


    }
}

private class LocalContentWebViewClient(private val assetLoader: WebViewAssetLoader) :
    WebViewClientCompat() {
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
package com.rizkysiregar.mymovieapp.about

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.rizkysiregar.mymovieapp.databinding.ActivityAboutBinding

@SuppressLint("SetJavaScriptEnabled")
class AboutActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "WebView TMDB API"

        val webView = binding.webView
        webView.settings.javaScriptEnabled = true

        webView.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                Toast.makeText(this@AboutActivity,"Halaman About Berhasil dimuat", Toast.LENGTH_LONG).show()
            }
        }
        webView.loadUrl("https://www.themoviedb.org/about")
    }


}
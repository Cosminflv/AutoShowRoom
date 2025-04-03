package com.example.autoshowroomapp;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class CarWebActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_web);

        webView = findViewById(R.id.webViewWiki);
        String url = getIntent().getStringExtra("wikipediaUrl");

        webView.getSettings().setJavaScriptEnabled(true); // Enable if needed
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}

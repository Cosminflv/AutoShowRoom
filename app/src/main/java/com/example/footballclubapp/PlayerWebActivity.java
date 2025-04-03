package com.example.footballclubapp;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerWebActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_web);

        webView = findViewById(R.id.webViewWiki);

        String url = getIntent().getStringExtra("wikipediaUrl");

        webView.getSettings().setJavaScriptEnabled(true); // doar dacă e nevoie
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}

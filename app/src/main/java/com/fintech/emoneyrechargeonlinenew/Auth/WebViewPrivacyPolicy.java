package com.fintech.emoneyrechargeonlinenew.Auth;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.fintech.emoneyrechargeonlinenew.R;

public class WebViewPrivacyPolicy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainweb);
        View closeBtn = findViewById(R.id.closeIv);
        closeBtn.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });

        // Find the WebView by its unique ID
        WebView webView = findViewById(R.id.web);
        // loading http://www.google.com url in the WebView.
        webView.loadUrl("https://emoneyrecharge.online/privacy.html");

        // this will enable the javascript.
        webView.getSettings().setJavaScriptEnabled(true);

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webView.setWebViewClient(new WebViewClient());
    }
}
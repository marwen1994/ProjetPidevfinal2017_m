package com.example.marwen.projetpidevfinal2017.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.example.marwen.projetpidevfinal2017.R;

public class WebView extends AppCompatActivity {
   private android.webkit.WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        String URL =  bundle.getString("url");
        webView = (android.webkit.WebView) findViewById(R.id.web);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(URL);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){

            webView.goBack();
        } else {

            super.onBackPressed();
        }

    }
}

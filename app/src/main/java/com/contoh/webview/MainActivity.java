package com.contoh.webview;

import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    private String targetUrl = "https://google.com";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        
        // 1. Mengaktifkan Penyimpanan Lokal (Sangat penting untuk website modern)
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        
        // 2. Mengaktifkan Cookie & Third-Party Cookie
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setAcceptThirdPartyCookies(webView, true);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(targetUrl);
    }

    // --- FUNGSI BARU: PAKSA SIMPAN LOGIN SAAT APLIKASI DI-MINIMIZE ---
    @Override
    protected void onPause() {
        super.onPause();
        CookieManager.getInstance().flush();
    }

    // --- FUNGSI BARU: PAKSA SIMPAN LOGIN SAAT APLIKASI DITUTUP PAKSA ---
    @Override
    protected void onDestroy() {
        super.onDestroy();
        CookieManager.getInstance().flush();
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}

package com.contoh.webview; // Ganti "com.contoh.webview" sesuai package aplikasi Anda

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    // JANGKAR URL: Jangan ubah format baris di bawah ini karena akan dibaca oleh GitHub Actions
    private String targetUrl = "https://google.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.webView);
        
        // Mengaktifkan Javascript (Penting untuk website modern)
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        // Memastikan link terbuka di dalam aplikasi, bukan lari ke browser luar (Chrome/Safari)
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(targetUrl);
    }

    // Mengatur tombol "Back" di HP agar kembali ke halaman web sebelumnya, bukan keluar aplikasi
    @Override
    public void onBackPressed() {
        WebView webView = findViewById(R.id.webView);
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}

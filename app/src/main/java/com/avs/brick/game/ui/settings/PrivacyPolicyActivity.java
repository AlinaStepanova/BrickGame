package com.avs.brick.game.ui.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.avs.brick.game.R;
import com.avs.brick.game.Values;

public class PrivacyPolicyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        WebView webView = findViewById(R.id.wvPrivacyPolicy);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(Values.PRIVACY_POLICY_URL);
    }
}

package com.andrey.kostenko.vrg_soft;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.app_bar)
    protected Toolbar appbar;
    @BindView(R.id.web)
    protected WebView webView;
    @BindView(R.id.errors_layout)
    protected ConstraintLayout errors;

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        url = intent.getStringExtra(MainActivity.EXTRA_URL);

        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState);
        } else {
            webView.loadUrl(url);
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (webView.getVisibility() == View.GONE) {
                    errors.setVisibility(View.GONE);
                    webView.setVisibility(View.VISIBLE);
                }
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                webView.setVisibility(View.GONE);
                errors.setVisibility(View.VISIBLE);
                super.onReceivedError(view, request, error);
            }
        });

        appbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack())webView.goBack();
        else super.onBackPressed();
    }
}



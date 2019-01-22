package com.example.tsnt.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tsnt.R;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

public class WebViewTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_test);
        BridgeWebView webView = (BridgeWebView) findViewById(R.id.web_view);
        webView.registerHandler("testHandler", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {

            }
        });
        webView.callHandler("testHandler", "data", new CallBackFunction() {
            @Override
            public void onCallBack(String data) {

            }
        });
    }
}

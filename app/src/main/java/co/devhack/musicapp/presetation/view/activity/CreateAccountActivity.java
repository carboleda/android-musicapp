package co.devhack.musicapp.presetation.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import co.devhack.musicapp.R;
import co.devhack.musicapp.helpers.Constants;

public class CreateAccountActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //Fuente: https://developer.android.com/guide/webapps/webview
        WebView myWebView = findViewById(R.id.wbBrowser);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl(Constants.CREATE_LASTFM_ACCOUNT_URL);
    }
}

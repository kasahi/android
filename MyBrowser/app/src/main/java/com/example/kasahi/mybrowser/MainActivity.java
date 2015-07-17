package com.example.kasahi.mybrowser;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import java.util.regex.Pattern;


public class MainActivity extends ActionBarActivity {

    private WebView myWebView;
    private EditText myUrl;
    private static final String INIRIAL_WEBSITE = "http://dotinstall.com";

    @SuppressLint("SetJavScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myUrl = (EditText) findViewById(R.id.myUrl);
        myWebView = (WebView) findViewById(R.id.myWebView);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                android.support.v7.app.ActionBar actionBar = getSupportActionBar();
                //ActionBar actionBar = getActionBar();
                actionBar.setSubtitle(myWebView.getTitle());
                myUrl.setText(myWebView.getUrl());
            }
        });
        myWebView.loadUrl(INIRIAL_WEBSITE);
    }

    public void showWebsite(View v) {
        String url = myUrl.getText().toString().trim();
        if (Patterns.WEB_URL.matcher(url).matches()) {
            // http://dotinstall.com -> OK
            // dotinstall.com -> OK
            if (!url.startsWith("http://")) {
                url = "http://" + url;
            }
            myWebView.loadUrl(url);
        } else {
            myUrl.setError("Not a valid URL!");
        }
    }

    public void clearUrl(View v) {
        myUrl.setText("");
    }

    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myWebView.stopLoading();
        myWebView.destroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem forwardItem = (MenuItem) menu.findItem(R.id.action_forward);
        MenuItem backItem = (MenuItem) menu.findItem(R.id.action_back);
        forwardItem.setEnabled(myWebView.canGoForward());
        backItem.setEnabled(myWebView.canGoBack());
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

//        int id = item.getItemId();
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        switch (item.getItemId()) {
            case R.id.action_reload:
                myWebView.reload();
                return true;
            case R.id.action_forward:
                myWebView.goForward();
                return true;
            case R.id.action_back:
                myWebView.goBack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        //return super.onOptionsItemSelected(item);
    }
}

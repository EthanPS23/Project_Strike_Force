package com.example.travelexperts;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/** Loads the web application into a web view activity
 * Author: Brandon Ezekiel and James Cockriel Co-Author: Ethan Shipley
 * Course CMPP 264
 * Date: April 24 2019
 */
public class WebViewActivity extends AppCompatActivity {
    Button btnDownload;
    EditText etWebPage;
    StringBuffer buffer = new StringBuffer();

    private WebView webView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        this.webView = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        MyWebViewClient webViewClient = new MyWebViewClient(this);
        webView.setWebViewClient(webViewClient);

        webView.loadUrl("http://10.163.37.7:8080/TravelExpertsWebApp/");
    }
    class Download extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            etWebPage.setText(Html.fromHtml(buffer.toString()));
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("http://10.163.37.7:8080/TravelExpertsWebApp/");
                BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));
                String line;
                while ((line = br.readLine()) != null){
                    buffer.append(line + "\n");
                }
                br.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
    private class MyWebViewClient extends WebViewClient {
        private Activity activity = null;

        public MyWebViewClient(Activity activity) {
            this.activity = activity;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            return false;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}

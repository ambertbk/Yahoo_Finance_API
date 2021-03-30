package com.example.yahoo_finance_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button btnPull;
    Button btnPush;

    TextView txtPulledInfo;
    EditText edtPushInfo;

    String[] watchlist;
    URL[] urls;

    String url;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPull = findViewById(R.id.btnPull);
//        txtPulledInfo = findViewById(R.id.txtPulledInfo);
        webView =  findViewById(R.id.webView);

        btnPull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getYahooFinance yahooFinance = new getYahooFinance();
                yahooFinance.execute();

//                txtPulledInfo.setText(url);

//                WebView myWebView = new WebView(R.layout.activity_main);
//                ((ViewGroup)webView.getParent()).removeView(webView);
//                setContentView(webView);
                webView.loadUrl(url);
            }
        });

    }

    private class getYahooFinance extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String[] params) {
//            OkHttpClient client = new OkHttpClient();
//
//            Request request = new Request.Builder()
//                    .url("https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/get-popular-watchlists")
//                    .get()
//                    .addHeader("x-rapidapi-key", "f55858111fmshee3fce031d25022p19647cjsn90b05365ac87")
//                    .addHeader("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
//                    .build();
//
//            try {
//                Response response = client.newCall(request).execute();
//                String jsonData = response.body().string();
//
//                JSONObject Jobject = new JSONObject(jsonData);
//                JSONArray watchlist_portfolios = Jobject.getJSONObject("finance")
//                                                        .getJSONArray("result")
//                                                        .getJSONObject(0)
//                                                        .getJSONArray("portfolios");
//
//                watchlist = new String[watchlist_portfolios.length()];
//
//                Log.i("aydry",""+watchlist_portfolios.length());
//
//                for (int i = 0; i < watchlist_portfolios.length(); i++) {
//                    watchlist[i] = watchlist_portfolios.getJSONObject(i).getString("name");
//                }
//
//                Log.i("aydry", Arrays.toString(watchlist));
//            } catch (IOException | JSONException e) {
//                e.printStackTrace();
//            }

            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "Pass in the value of uuids field returned right in this endpoint to load the next page, or leave empty to load first page");
            Request request = new Request.Builder()
                    .url("https://apidojo-yahoo-finance-v1.p.rapidapi.com/news/v2/list?region=US&snippetCount=28")
                    .post(body)
                    .addHeader("content-type", "text/plain")
                    .addHeader("x-rapidapi-key", "f55858111fmshee3fce031d25022p19647cjsn90b05365ac87")
                    .addHeader("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String jsonData = response.body().string();

                JSONObject Jobject = new JSONObject(jsonData);
                url = Jobject.getJSONObject("data").getJSONObject("main").getJSONArray("stream")
                        .getJSONObject(0).getJSONObject("content").getJSONObject("clickThroughUrl")
                        .getString("url");
//                JSONArray news = Jobject.getJSONObject("data")
//                                        .getJSONObject("main")
//                                        .getJSONArray("stream");

//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                startActivity(browserIntent);

                Log.i("aydry",url);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return "some message";
        }

        @Override
        protected void onPostExecute(String message) {
            //process message
        }
    }
}
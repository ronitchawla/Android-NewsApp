package com.example.android.newsapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class GuardianLoader extends AsyncTaskLoader<List<Guardian>> {

    private String mUrl;
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    public GuardianLoader(Context context, String url)
    {
        super(context);
        mUrl=url;
    }



    @Override
    public List<Guardian> loadInBackground() {
        List<Guardian> allNews = null;
        try {
            URL url = QueryUtils.createUrl();
            String jsonResponse = QueryUtils.makeHttpRequest(url);
            allNews = QueryUtils.parseJson(jsonResponse);
        } catch (IOException e) {
            Log.e("Queryutils", "Error Loader LoadInBackground: ", e);
        }
        return allNews;
    }
}

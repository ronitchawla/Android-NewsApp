package com.example.android.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Guardian>>, SwipeRefreshLayout.OnRefreshListener
{

    private GuardianAdapter adapter;
    private static int LOADER_ID= 0;
    SwipeRefreshLayout swipe;
    private static final String GUARDIAN_REQUEST_URL="https://content.guardianapis.com/search?api-key=test&format=json&order-by=newest&q=Android";
    private TextView emptyStateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipe.setOnRefreshListener(this);
        swipe.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        ListView listView = (ListView) findViewById(R.id.list_item);
        emptyStateTextView = findViewById(R.id.empty_state);
        adapter = new GuardianAdapter(this);
        listView.setAdapter(adapter);

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (cm != null) {
            networkInfo = cm.getActiveNetworkInfo();
        }
        if(networkInfo != null && networkInfo.isConnected()) {
            emptyStateTextView.setVisibility(View.INVISIBLE);

            android.app.LoaderManager loaderManager = getLoaderManager();
        }else{
            emptyStateTextView.setText("No internet connection");
            emptyStateTextView.setVisibility(View.VISIBLE);
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Guardian news = adapter.getItem(i);
                String url = news.getmUrl();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        getSupportLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();
    }
    @Override
    public Loader<List<Guardian>> onCreateLoader(int id, Bundle args) {
        return new GuardianLoader(this,GUARDIAN_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Guardian>> loader, List<Guardian> data) {
        swipe.setRefreshing(false);
        if (data != null) {
            adapter.setNotifyOnChange(false);
            adapter.clear();
            adapter.setNotifyOnChange(true);
            adapter.addAll(data);
        }
        else
        {
            emptyStateTextView.setText("No news to display");
            emptyStateTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Guardian>> loader) {

    }

    @Override
    public void onRefresh() {
        getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
    }
}





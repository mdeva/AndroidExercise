package com.example.deverajan.androidexcercise;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import adapter.ListAdapter;
import model.ListModel;
import utils.Utils;

public class ListActivity extends AppCompatActivity {

    RecyclerView list;
    Toolbar toolbar;
    ListAdapter listAdapter;
    Context context = ListActivity.this;
    SwipeRefreshLayout swipeRefreshLayout;
    private static final int PHONE_PORTRAIT_COLUMNS = 1;
    private static final int PHONE_LANDSCAPE_COLUMNS = 2;
    int mCardsColumns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();
        onclick();
        getListdata();
    }

    private void onclick() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getListdata();
            }
        });
    }

    //get the data from server using volly library
    private void getListdata() {
        final Boolean online =
                Utils.isOnline((ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE));
        if (online) {
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                    getResources().getString(R.string.list_api), null,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            parseJSON(response.toString());
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d("Error: " + error.getMessage());
                }
            });
            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(jsonObjReq, String.valueOf(jsonObjReq));
        } else {
            Toast.makeText(getApplicationContext(),"No internet connection!",Toast.LENGTH_SHORT).show();
            if (swipeRefreshLayout != null ) {
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    //parse the json object using GSON library
    private void parseJSON(String response) {
        if (swipeRefreshLayout != null ) {
            swipeRefreshLayout.setRefreshing(false);
        }
        Gson gson = new GsonBuilder().create();
        ListModel listModel = gson.fromJson(response, ListModel.class);
        updateUI(listModel);

    }

    //update the list
    private void updateUI(ListModel listModel) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(listModel.getTitle());
        if (Utils.isPortrait(context)) {
            mCardsColumns = PHONE_PORTRAIT_COLUMNS;
        } else {
            mCardsColumns = PHONE_LANDSCAPE_COLUMNS;
        }
        if (listAdapter != null) {
            listAdapter.notifyDataSetChanged();
        } else {
            listAdapter = new ListAdapter(listModel.getListrows(), context);
            list.setLayoutManager(new StaggeredGridLayoutManager(mCardsColumns, StaggeredGridLayoutManager.VERTICAL));
            list.setItemAnimator(new DefaultItemAnimator());
            list.setAdapter(listAdapter);
        }
    }

    private void init() {
        list = (RecyclerView) findViewById(R.id.list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (Utils.isPortrait(context)) {
             mCardsColumns = PHONE_PORTRAIT_COLUMNS;
        } else {
             mCardsColumns = PHONE_LANDSCAPE_COLUMNS;
        }
        list.setLayoutManager(new StaggeredGridLayoutManager(mCardsColumns, StaggeredGridLayoutManager.VERTICAL));
        list.scrollToPosition(0);
    }
}

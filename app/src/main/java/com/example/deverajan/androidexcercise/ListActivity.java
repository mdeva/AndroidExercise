package com.example.deverajan.androidexcercise;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

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

public class ListActivity extends AppCompatActivity {

    RecyclerView list;
    Toolbar toolbar;
    ListAdapter listAdapter;
    Context context = ListActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();
        getListdata();
    }

    //get the data from server using volly library
    private void getListdata() {
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
    }

    //parse the json object using GSON library
    private void parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        ListModel listModel = gson.fromJson(response, ListModel.class);
        updateUI(listModel);

    }

    //update the list
    private void updateUI(ListModel listModel) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(listModel.getTitle());
        listAdapter = new ListAdapter(listModel.getListrows(), context);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        list.setLayoutManager(mLayoutManager);
        list.setItemAnimator(new DefaultItemAnimator());
        list.setAdapter(listAdapter);

    }

    private void init() {
        list = (RecyclerView) findViewById(R.id.list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}

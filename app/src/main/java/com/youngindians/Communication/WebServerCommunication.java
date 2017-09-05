package com.youngindians.Communication;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.youngindians.Models.Event;
import com.youngindians.Models.User;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import Utils.ServerInfo;
import cz.msebera.android.httpclient.Header;

/**
 * Created by shrivatsa on 14/08/17.
 */

public class WebServerCommunication {

    private HTTPCallback callback;
    private Context context;
    private Gson gson = new Gson();

    public WebServerCommunication(HTTPCallback callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    public void getUsers(){
        String url = ServerInfo.getInstance().getUsers_path();
        Log.d("URL", url);
        YiRestClient.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[]headers, JSONArray response) {
                Type type = new TypeToken<List<User>>(){}.getType();
                ArrayList<User> users = gson.fromJson(response.toString(), type);
                callback.onSuccess(users);
            }
        });
    }

    public void getEvents() {
        String url = ServerInfo.getInstance().getEvents_path();
        Log.d("URL", url);
        YiRestClient.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[]headers, JSONArray response) {
                Type type = new TypeToken<List<Event>>(){}.getType();
                ArrayList<Event> events = gson.fromJson(response.toString(), type);
                callback.onSuccess(events);
            }
        });
    }

}

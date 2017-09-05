package com.youngindians.Communication;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.youngindians.CallBacks.HTTPCallback;
import com.youngindians.Models.Event;
import com.youngindians.Models.User;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.youngindians.Utils.Check;
import com.youngindians.Utils.CommonFunctions;
import com.youngindians.Utils.Constants;
import com.youngindians.Utils.ServerInfo;

import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.Header;

/**
 * Created by shrivatsa on 14/08/17.
 */

public class WebServerCommunication {

    private HTTPCallback callback;
    private Context context;
    private CommonFunctions commonFunctions;
    private Gson gson = new Gson();

    public WebServerCommunication(HTTPCallback callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.commonFunctions = new CommonFunctions(context);
    }

    public void getInformation(int callCode){

        if(Check.isNetworkAvailable(context)){

            String url = commonFunctions.getUrl(callCode);
            get(url, callCode);

        }else {

            callback.onFailure(Constants.CB_CODE_NO_INTERNET, callCode);
        }

    }

    public void get(String url, final int callCode){

        YiRestClient.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[]headers, JSONArray response) {

                callback.onSuccess(response.toString(), callCode);
            }
        });

    }


//    public void getEvents() {
//        Log.d("URL", url);
//        YiRestClient.get(url, null, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[]headers, JSONArray response) {
//                Type type = new TypeToken<List<Event>>(){}.getType();
//                ArrayList<Event> events = gson.fromJson(response.toString(), type);
//                callback.onSuccess(events);
//            }
//        });
//    }

}

package com.youngindians.Utils;

import android.content.Context;
import android.util.Log;

/**
 * Created by pradumanpraduman on 05/09/17.
 */

public class CommonFunctions {

    private Context context;

    public CommonFunctions(Context context){
        this.context = context;
    }

    public String getUrl(int callCode){

        if(callCode == Constants.CL_CODE_GET_MEMBERS){
            return ServerInfo.getInstance().getUsers_path();

        }else if(callCode == Constants.CL_CODE_GET_EVENTS){

            return ServerInfo.getInstance().getEvents_path();
        }

        return "";
    }

    public void log(String tag,String value){

        Log.d(tag, value);
    }
}

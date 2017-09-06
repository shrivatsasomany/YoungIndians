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

    public String getBirthdayForCards(String bDayFromDB){

        String [] dateArray = bDayFromDB.split("-");
        DateAndTime dateAndTime = new DateAndTime();

        String month = dateArray [1];
        String day = dateArray [2].split("T")[0];

        return dateAndTime.getDateByName(Integer.valueOf(day)) +
                Constants.SPACE +
                dateAndTime.getMonthByName(Integer.valueOf(month));
    }
}

package com.youngindians.Utils;

import android.content.Context;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by pradumanpraduman on 06/09/17.
 */

public class DateAndTime {

    private Calendar calendar;

    public DateAndTime(){

        calendar = new GregorianCalendar();
    }

    public long getTimeStamp(){

        return System.currentTimeMillis();
    }

    public int getDate(){

        return  calendar.get(Calendar.DATE);
    }

    public int getMonth(){

        return calendar.get(Calendar.MONTH);
    }

    public int getYear(){
        return calendar.get(Calendar.YEAR);
    }

    public String getMonthByName(int monthNumber){

        String month ;

        switch (monthNumber){

            case  0 :
                month = "January";
                break;

            case 1 :
                month = "February";
                break;

            case 2 :
                month = "March";
                break;

            case  3 :
                month = "April";
                break;

            case 4 :
                month = "May";
                break;

            case 5 :
                month = "June";
                break;

            case  6 :
                month = "July";
                break;

            case 7 :
                month = "August";
                break;

            case 8 :
                month = "September";
                break;

            case  9 :
                month = "October";
                break;

            case 10 :
                month = "November";
                break;

            case 11 :
                month = "December";
                break;

            default:
                month = "this month";
                break;

        }

        return month;
    }


    public String getDateByName(int dateNumber){

        String date;

        switch (dateNumber){

            case 1 :
                date = dateNumber+"st";
                break;

            case 2 :
                date = dateNumber+"nd";
                break;
            case 3 :
                date = dateNumber+"rd";
                break;

            case 21 :
                date = dateNumber+"st";
                break;

            case 22 :
                date = dateNumber+"nd";
                break;

            case 23 :
                date = dateNumber+"rd";
                break;

            default:
                date = dateNumber+"th";
                break;

        }

        return date;
    }

}

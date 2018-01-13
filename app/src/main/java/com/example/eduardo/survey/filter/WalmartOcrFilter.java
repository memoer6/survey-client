package com.example.eduardo.survey.filter;

import android.os.Bundle;
import android.util.Log;

import com.example.eduardo.survey.utility.Store;
import com.google.android.gms.vision.text.Text;

/**
 * Ocr detection filter to extract fields related to Walmart receipts
 */

public class WalmartOcrFilter implements OcrFilter {

    private static final String TAG = "WalmartOcrFilter";

    private String tc;
    private String date;
    private String time;
    private String storeNum;
    private FilterListener mListener;


    //OcrCaptureActivity is the listener
    WalmartOcrFilter(FilterListener listener) {
        this.mListener = listener;
    }


    //Method defined in OcrFilter interface
    public void filterText(Text line) {

        final String tcMatcher = "^TC[#tE].*";
        final String dateMatcher = "^(0[1-9]|1[012])/(0[1-9]|[12][0-9]|3[01])/\\d\\d"; //mm/dd/yy
        final String timeMatcher = "^([01][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])"; //hh:mm:ss
        final String storeMatcher1 = "^Your.*\\d\\d\\d\\d";
        final String storeMatcher2 = "^STOR.*\\d\\d\\d\\d";

        // TC#
        if (line.getValue().matches(tcMatcher)) {
            if (tc == null) {
                tc = line.getValue();
                Log.d("Processor", "TC# detected: " + tc);
            }
        }

        if (line.getValue().matches(dateMatcher)) {
            if (date == null) {
                date = line.getValue();
                Log.d("Processor", "Date detected: " + date);
            }
        }

        if (line.getValue().matches(timeMatcher)) {
            if (time == null) {
                time = line.getValue();
                Log.d("Processor", "Time detected: " + time);
            }
        }

        if (line.getValue().matches(storeMatcher1) || line.getValue().matches(storeMatcher2)) {
            if (storeNum == null) {
                storeNum = line.getValue();
                Log.d("Processor", "Store detected: " + storeNum);
            }

        }


        if (tc != null && date !=null && time != null && storeNum != null) {

            Bundle args = new Bundle();

            args.putString(Store.KEY_FIELD_DATE, date);
            args.putString(Store.KEY_FIELD_TIME, time);
            args.putString(Store.KEY_FIELD_STORE_NUMBER, storeNum.substring(storeNum.length() - 4)); //only the four digits
            args.putString(Store.KEY_FIELD_TC, tc.substring(4)); //remove TC#
            //Notify event that all the data has been captured
            mListener.onCaptureAllText(args);

        }

    }

}

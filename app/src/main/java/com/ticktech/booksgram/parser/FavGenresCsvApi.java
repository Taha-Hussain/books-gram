package com.ticktech.booksgram.parser;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ticktech.booksgram.service.HttpService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Taha on 04/06/2017.
 */

public class FavGenresCsvApi {

    SharedPreferences mSharedPreferences;
    public String callGenresCsv(Context context) {
        HttpService MyHttpService = new HttpService();
        String response = "";

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences (context);
        String strEmail = mSharedPreferences.getString("key_email", "");

        String genreCallResponse = MyHttpService.httpGet("https://bookgram.000webhostapp.com/app/getUserCategoriesCSV.php?email="+strEmail);
//        String genreCallResponse = MyHttpService.httpGet("http://friendsfashion.net/android/book/getUserCategoriesCSV.php?email="+strEmail);

        try {
            JSONArray json = new JSONArray(genreCallResponse);
            for (int i = 0; i < json.length(); i++) {
                JSONObject MyJsonObject = json.getJSONObject(i);
                response = MyJsonObject.getString("categoriesCSV");
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return response;
    }
}

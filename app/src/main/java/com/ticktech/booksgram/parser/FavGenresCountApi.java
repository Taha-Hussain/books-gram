package com.ticktech.booksgram.parser;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ticktech.booksgram.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;


public class FavGenresCountApi {

   static SharedPreferences mSharedPreferences;
    public static String callGenresCount(Context context) {

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences (context);
        String strEmail = mSharedPreferences.getString("key_email", "");


        HttpClient httpClient = new DefaultHttpClient();
//        HttpGet httpGet = new HttpGet("https://bookgram.000webhostapp.com/app/getUserCategories.php?email="+strEmail);
        String rootUrl = context.getResources().getString(R.string.server_url);
        HttpGet httpGet = new HttpGet(rootUrl+"getUserCategoriesCount.php?email="+strEmail);

        String response = "";


//        String genreCallResponse = MyHttpService.httpGet("http://friendsfashion.net/android/book/getUserCategories.php?email="+strEmail);
//        String genreCallResponse = MyHttpService.httpGet("https://bookgram.000webhostapp.com/app/getUserCategories.php?email="+strEmail);

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            response = EntityUtils.toString(httpResponse.getEntity());

//            JSONArray json = new JSONArray(genreCallResponse);

//            response = EntityUtils.toString(httpResponse.getEntity());

//            for (int i = 0; i < json.length(); i++) {
//                JSONObject MyJsonObject = json.getJSONObject(i);
//                response = MyJsonObject.getString("count");
//            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}

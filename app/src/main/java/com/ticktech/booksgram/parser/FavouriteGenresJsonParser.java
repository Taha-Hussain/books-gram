package com.ticktech.booksgram.parser;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ticktech.booksgram.R;
import com.ticktech.booksgram.model.Books;
import com.ticktech.booksgram.model.Genres;
//import com.ticktech.booksgram.service.HttpService;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;


public class FavouriteGenresJsonParser {

    SharedPreferences mSharedPreferences;
    public static ArrayList<String> selectedCategories = new ArrayList<>();

    public ArrayList<Genres> getParsedGenres(Context context) {

        ArrayList<Genres> MyArraylist = new ArrayList<>();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String strEmail = mSharedPreferences.getString("key_email", "");

        String JsonFavouriteGenres = "";
        HttpClient httpClient = new DefaultHttpClient();

        String rootUrl = context.getResources().getString(R.string.server_url);
        HttpGet httpGet = new HttpGet(rootUrl+"getFavouriteCategories.php?email="+strEmail);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            JsonFavouriteGenres = EntityUtils.toString(httpResponse.getEntity());
            JSONArray jsonArray = new JSONArray(JsonFavouriteGenres);
            for (int i = 0; i < jsonArray.length(); i++) {
                Genres genres = new Genres();
                JSONObject MyJsonObject = jsonArray.getJSONObject(i);
                genres.setGenere_id(MyJsonObject.getString("id"));
                genres.setGenere_Name(MyJsonObject.getString("categoryName"));
                genres.setSelected(Boolean.parseBoolean(MyJsonObject.getString("selected")));
                MyArraylist.add(genres);

                if(MyJsonObject.getString("selected").equals("true"))
                {
                    selectedCategories.add(MyJsonObject.getString("id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return MyArraylist;
    }
}
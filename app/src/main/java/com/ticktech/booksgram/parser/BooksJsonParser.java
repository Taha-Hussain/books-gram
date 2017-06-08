package com.ticktech.booksgram.parser;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ticktech.booksgram.model.Books;
import com.ticktech.booksgram.service.HttpService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import org.json.JSONException;

/**
 * Created by Taha on 04/06/2017.
 */

public class BooksJsonParser {

    SharedPreferences mSharedPreferences;
    public ArrayList<Books> getParsedBooks(Context context) {
        HttpService MyHttpService = new HttpService();
        ArrayList<Books> MyArraylist = new ArrayList<>();

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences (context);
        String strEmail = mSharedPreferences.getString("key_email", "");



        String JsonBooks = MyHttpService.httpGet("https://bookgram.000webhostapp.com/app/getBooksApi.php?email="+strEmail);
//        String JsonBooks = MyHttpService.httpGet("http://friendsfashion.net/android/book/getBooksApi.php?email="+strEmail);
        try {
            JSONArray json = new JSONArray(JsonBooks);
            for (int i = 0; i < json.length(); i++) {
                Books books = new Books();
                JSONObject MyJsonObject = json.getJSONObject(i);

                books.setBook_id(Integer.parseInt(MyJsonObject.getString("id")));
                books.setBook_name(MyJsonObject.getString("bookName"));
                books.setBook_rating(4);
                books.setBook_publisher("by "+MyJsonObject.getString("bookAuthor"));
                books.setBook_logo(MyJsonObject.getString("bookCoverImageUrl"));
                books.setBook_price(MyJsonObject.getString("price"));
                books.setBook_link(MyJsonObject.getString("amzaonLink"));
                books.setBook_publishYear(MyJsonObject.getString("publishYear"));
                books.setBook_shortDescription(MyJsonObject.getString("bookShorDescriptions"));
                books.setBook_categoryName(MyJsonObject.getString("categoryName"));


                MyArraylist.add(books);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return MyArraylist;
    }
}

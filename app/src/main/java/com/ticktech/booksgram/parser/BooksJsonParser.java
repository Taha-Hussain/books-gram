package com.ticktech.booksgram.parser;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.ticktech.booksgram.model.Books;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;


public class BooksJsonParser {

    SharedPreferences mSharedPreferences;
    public ArrayList<Books> getParsedBooks(Context context) {
//        HttpService MyHttpService = new HttpService();
        ArrayList<Books> MyArraylist = new ArrayList<>();

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences (context);
        String strEmail = mSharedPreferences.getString("key_email", "");

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("https://bookgram.000webhostapp.com/app/getBooksApi.php?email="+strEmail);

//        String JsonBooks = MyHttpService.httpGet("https://bookgram.000webhostapp.com/app/getBooksApi.php?email="+strEmail);
//        String JsonBooks = MyHttpService.httpGet("http://friendsfashion.net/android/book/getBooksApi.php?email="+strEmail);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String JsonBooks = EntityUtils.toString(httpResponse.getEntity());
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return MyArraylist;
    }
}

package com.ticktech.booksgram.parser;

import com.ticktech.booksgram.model.Books;
import com.ticktech.booksgram.model.Genres;
import com.ticktech.booksgram.service.HttpService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Taha on 04/06/2017.
 */

public class GenresJsonParser {

    public ArrayList<Genres> getParsedGenres() {
        HttpService MyHttpService = new HttpService();
        ArrayList<Genres> MyArraylist = new ArrayList<>();


        String JsonQuotes = MyHttpService.httpGet("http://friendsfashion.net/android/book/getBookCategory.php");
        try {
            JSONArray json = new JSONArray(JsonQuotes);
            for (int i = 0; i < json.length(); i++) {
                Genres genres = new Genres();
                JSONObject MyJsonObject = json.getJSONObject(i);

                genres.setGenere_id(Integer.parseInt(MyJsonObject.getString("id")));
                genres.setGenere_Name(MyJsonObject.getString("categoryName"));
                genres.setStatus(MyJsonObject.getString("Status"));
//                books.setBook_publisher("by "+MyJsonObject.getString("bookAuthor"));
//                books.setBook_logo(MyJsonObject.getString("bookCoverImageUrl"));
//                books.setBook_price(MyJsonObject.getString("price"));
                MyArraylist.add(genres);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return MyArraylist;
    }
}

package com.ticktech.booksgram.parser;

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

    public ArrayList<Books> getParsedQuotes() {
        HttpService MyHttpService = new HttpService();
        ArrayList<Books> MyArraylist = new ArrayList<>();


        String JsonQuotes = MyHttpService.httpGet();
        try {
//            JSONArray jsonArray = new JSONArray(JsonQuotes);
            JSONArray json = new JSONArray(JsonQuotes);

//            JSONObject jsonObject = new JSONObject(JsonQuotes);
//            JSONArray quotesJsonArray = jsonObject.getJSONArray("quotations");
            for (int i = 0; i < json.length(); i++) {
                Books books = new Books();
                JSONObject MyJsonObject = json.getJSONObject(i);

                books.setBook_id(Integer.parseInt(MyJsonObject.getString("id")));
                books.setBook_name(MyJsonObject.getString("bookName"));
                books.setBook_rating(4);
                books.setBook_publisher("by "+MyJsonObject.getString("bookAuthor"));
                books.setBook_logo(MyJsonObject.getString("bookCoverImageUrl"));
                books.setBook_price(MyJsonObject.getString("price"));

                MyArraylist.add(books);



            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return MyArraylist;
    }
}

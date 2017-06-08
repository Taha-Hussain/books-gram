package com.ticktech.booksgram.model;

import android.content.Context;

import com.ticktech.booksgram.parser.BooksJsonParser;

import java.util.ArrayList;

/**
 * Created by Taha on 18/03/2016.
 */
public class BookDatasource {

    public ArrayList<Books> getList(Context context) {

        BooksJsonParser booksParser = new BooksJsonParser();
        return booksParser.getParsedBooks(context);
    }
}

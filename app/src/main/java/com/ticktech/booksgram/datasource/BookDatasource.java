package com.ticktech.booksgram.datasource;

import android.content.Context;

import com.ticktech.booksgram.model.Books;
import com.ticktech.booksgram.parser.BooksJsonParser;
import java.util.ArrayList;

public class BookDatasource {

    public ArrayList<Books> getList(Context context)
    {
        BooksJsonParser booksParser = new BooksJsonParser();
        return booksParser.getParsedBooks(context);
    }
}

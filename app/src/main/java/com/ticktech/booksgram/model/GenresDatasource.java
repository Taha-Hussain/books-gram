package com.ticktech.booksgram.model;

import android.content.Context;

import com.ticktech.booksgram.parser.BooksJsonParser;
import com.ticktech.booksgram.parser.GenresJsonParser;

import java.util.ArrayList;

/**
 * Created by Taha on 18/03/2016.
 */
public class GenresDatasource {

    public ArrayList<Genres> getList(Context context) {

        GenresJsonParser genresParser = new GenresJsonParser();
        return genresParser.getParsedGenres(context);
    }
}

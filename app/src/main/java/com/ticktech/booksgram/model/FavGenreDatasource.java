package com.ticktech.booksgram.model;

import android.content.Context;

import com.ticktech.booksgram.parser.BooksJsonParser;
import com.ticktech.booksgram.parser.FavGenresApi;

import java.util.ArrayList;

/**
 * Created by Taha on 18/03/2016.
 */
public class FavGenreDatasource {

    public String getResult(String genresStr, Context context) {

        FavGenresApi favGenereResponse = new FavGenresApi();
        return favGenereResponse.callGenres(genresStr,context);
    }
}

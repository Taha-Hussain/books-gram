package com.ticktech.booksgram.datasource;

import android.content.Context;
import com.ticktech.booksgram.model.Genres;
import com.ticktech.booksgram.parser.FavouriteGenresJsonParser;
import java.util.ArrayList;

public class GenresDatasource {

    public ArrayList<Genres> getList(Context context) {
        FavouriteGenresJsonParser genresParser = new FavouriteGenresJsonParser();
        return genresParser.getParsedGenres(context);
    }
}

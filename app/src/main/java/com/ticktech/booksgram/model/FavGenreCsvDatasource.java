package com.ticktech.booksgram.model;

import android.content.Context;

import com.ticktech.booksgram.parser.FavGenresApi;
import com.ticktech.booksgram.parser.FavGenresCsvApi;

/**
 * Created by Taha on 18/03/2016.
 */
public class FavGenreCsvDatasource {

    public String getResult(Context context) {

        FavGenresCsvApi favGenereCsvResponse = new FavGenresCsvApi();
        return favGenereCsvResponse.callGenresCsv(context);
    }
}

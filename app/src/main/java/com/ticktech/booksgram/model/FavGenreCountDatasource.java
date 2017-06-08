package com.ticktech.booksgram.model;

import android.content.Context;

import com.ticktech.booksgram.parser.FavGenresCountApi;
import com.ticktech.booksgram.parser.FavGenresCsvApi;

/**
 * Created by Taha on 18/03/2016.
 */
public class FavGenreCountDatasource {

    public String getResult(Context context) {

        FavGenresCountApi favGenresCountApi = new FavGenresCountApi();
        return favGenresCountApi.callGenresCount(context);
    }
}

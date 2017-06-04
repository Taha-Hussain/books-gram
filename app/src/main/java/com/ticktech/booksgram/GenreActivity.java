package com.ticktech.booksgram;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.ticktech.booksgram.R;
import com.ticktech.booksgram.adapter.BookAdapter;
import com.ticktech.booksgram.adapter.GenreAdapter;
import com.ticktech.booksgram.model.BookDatasource;
import com.ticktech.booksgram.model.Genres;
import com.ticktech.booksgram.model.GenresDatasource;

import java.util.ArrayList;

/**
 * Created by Taha on 04/06/2017.
 */

public class GenreActivity  extends AppCompatActivity {
    Context context;
    ArrayList<Genres> array_list;
    GenresDatasource genresDatasource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
        context = this;
        new asyncTask_httpGet().execute();
    }

    public class asyncTask_httpGet extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog = new ProgressDialog(context);

        @Override
        protected void onPreExecute() {
            dialog.setTitle("Please wait...");
            dialog.setMessage("Loading Genres!");
            dialog.show();
            array_list = new ArrayList<>();
            genresDatasource = new GenresDatasource();


            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            array_list = genresDatasource.getList();
            return null;
        }

        @Override
        protected void onPostExecute(Void s) {

            ListView mListViewBooks = (ListView) findViewById(R.id.genrelist_listview);
            GenreAdapter mGenresAdapter = new GenreAdapter(context, R.layout.row_genre_list, array_list);
            mListViewBooks.setAdapter(mGenresAdapter);
            super.onPostExecute(s);
            dialog.dismiss();
        }
    }
}

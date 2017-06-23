package com.ticktech.booksgram;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.ticktech.booksgram.adapter.GenreAdapter;
import com.ticktech.booksgram.model.Genres;
import com.ticktech.booksgram.datasource.GenresDatasource;
import com.ticktech.booksgram.parser.FavouriteGenresJsonParser;
import com.ticktech.booksgram.parser.InsertUpdateFavouriteGenresApi;
import java.util.ArrayList;
import android.widget.Toast;


public class GenreActivity  extends AppCompatActivity {
    Context context;
    ArrayList<Genres> array_list;
    GenresDatasource genresDatasource;
    String genresStr;

    GenreAdapter adapter;
    StringBuffer sb=null;
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
            array_list = genresDatasource.getList(context);
            return null;
        }

        @Override
        protected void onPostExecute(Void s) {

           final ListView mListViewBooks = (ListView) findViewById(R.id.genrelist_listview);

            final GenreAdapter mGenresAdapter = new GenreAdapter(context, R.layout.row_genre_list, array_list);
            mListViewBooks.setAdapter(mGenresAdapter);

            Button button = (Button) findViewById(R.id.selectGenreButton);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    genresStr = FavouriteGenresJsonParser.selectedCategories.toString();
                    genresStr = genresStr.substring(1, genresStr.length() - 1);

                    if (genresStr.length() > 0) {
                        new asyncTask_InsertFavouriteCategories().execute();
                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(context, "Please Select Atleast One Genre", Toast.LENGTH_SHORT).show();
                    }
                }
            });


            super.onPostExecute(s);
            dialog.dismiss();

        }



        public class asyncTask_InsertFavouriteCategories extends AsyncTask<Void, Void, Void> {

            String response;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {
                response = InsertUpdateFavouriteGenresApi.callGenres(genresStr,context);
                return null;
            }
            @Override
            protected void onPostExecute(Void s) {

                Toast.makeText(context,response,Toast.LENGTH_SHORT).show();
                super.onPostExecute(s);
            }
        }

    }
}

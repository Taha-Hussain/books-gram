package com.ticktech.booksgram;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ticktech.booksgram.R;
import com.ticktech.booksgram.adapter.BookAdapter;
import com.ticktech.booksgram.adapter.GenreAdapter;
import com.ticktech.booksgram.model.BookDatasource;
import com.ticktech.booksgram.model.FavGenreCsvDatasource;
import com.ticktech.booksgram.model.FavGenreDatasource;
import com.ticktech.booksgram.model.Genres;
import com.ticktech.booksgram.model.GenresDatasource;
import com.ticktech.booksgram.parser.FavGenresApi;

import java.util.ArrayList;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

/**
 * Created by Taha on 04/06/2017.
 */

public class GenreActivity  extends AppCompatActivity {
    Context context;
    ArrayList<Genres> array_list;
    GenresDatasource genresDatasource;
    FavGenreDatasource favGenreDatasource;
    FavGenreCsvDatasource favGenreCsvDatasource;
    String genresStr;

    GenreAdapter adapter;
    StringBuffer sb=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);

        context = this;
        new asyncTask_httpGet().execute();
        new asyncTask_httpfavCategories().execute();
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

            ListView mListViewBooks = (ListView) findViewById(R.id.genrelist_listview);

            final GenreAdapter mGenresAdapter = new GenreAdapter(context, R.layout.row_genre_list, array_list);
            mListViewBooks.setAdapter(mGenresAdapter);

            Button button = (Button) findViewById(R.id.selectGenreButton);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    sb=new StringBuffer();

                    for(Genres genres : mGenresAdapter.checkedGenres)
                    {
                        sb.append(genres.getGenere_id());
                        sb.append(",");
                    }


                    if(mGenresAdapter.checkedGenres.size()>0)
                    {
                        sb.deleteCharAt(sb.length() - 1);
                        Toast.makeText(context,sb.toString(),Toast.LENGTH_SHORT).show();
                        genresStr = sb.toString();
                        asyncTask_httpfavCategories asyncTask =new asyncTask_httpfavCategories();
                        asyncTask.execute();

//
//                Intent mintent = new Intent(context, MainActivity.class);
//                mintent.putExtra("favCategories",sb.toString());
//                startActivity(mintent);
//
                        Intent mintent = new Intent(context, MainActivity.class);
                        startActivity(mintent);


                    }else
                    {
                        Toast.makeText(context,"Please Check Categories",Toast.LENGTH_SHORT).show();
                    }
                }
            });


            super.onPostExecute(s);
            dialog.dismiss();

        }



        public class asyncTask_httpfavCategories extends AsyncTask<Void, Void, Void> {

            String response;
            @Override
            protected void onPreExecute() {
                favGenreDatasource = new FavGenreDatasource();
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {
                response = favGenreDatasource.getResult(genresStr,context);
                return null;
            }
            @Override
            protected void onPostExecute(Void s) {

                Toast.makeText(context,response,Toast.LENGTH_SHORT).show();
                super.onPostExecute(s);
            }
        }

    }

    public class asyncTask_httpfavCategories extends AsyncTask<Void, Void, Void> {

        String categoriesCsv;
        @Override
        protected void onPreExecute() {
            favGenreCsvDatasource = new FavGenreCsvDatasource();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            categoriesCsv = favGenreCsvDatasource.getResult(context);

            return null;
        }
        @Override
        protected void onPostExecute(Void s) {

            Toast.makeText(context,categoriesCsv,Toast.LENGTH_SHORT).show();
            super.onPostExecute(s);
        }
    }
}

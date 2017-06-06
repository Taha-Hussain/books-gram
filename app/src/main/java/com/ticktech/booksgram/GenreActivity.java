package com.ticktech.booksgram;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import com.ticktech.booksgram.model.Genres;
import com.ticktech.booksgram.model.GenresDatasource;

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
            array_list = genresDatasource.getList();
            return null;
        }

        @Override
        protected void onPostExecute(Void s) {


            //RECYCLER
//            RecyclerView rv= (RecyclerView) findViewById(R.id.genrelist_listview);
//            rv.setLayoutManager(new LinearLayoutManager(context));
//            rv.setItemAnimator(new DefaultItemAnimator());
//            adapter=new GenreAdapter(context,array_list);
//            //SET ADAPTER
//            rv.setAdapter(adapter);


            ListView mListViewBooks = (ListView) findViewById(R.id.genrelist_listview);
            final GenreAdapter mGenresAdapter = new GenreAdapter(context, R.layout.row_genre_list, array_list);
            mListViewBooks.setAdapter(mGenresAdapter);


//            adapter=new GenreAdapter(context,R.layout.row_genre_list,array_list);

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
                    }else
                    {
                        Toast.makeText(context,"Please Check Categories",Toast.LENGTH_SHORT).show();
                    }


//                Intent mintent = new Intent(context, MainActivity.class);
//                startActivity(mintent);
                }
            });


            super.onPostExecute(s);
            dialog.dismiss();
        }
    }
}

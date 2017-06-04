package com.ticktech.booksgram.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ticktech.booksgram.R;
import com.ticktech.booksgram.model.Books;
import com.ticktech.booksgram.model.Genres;

import java.util.ArrayList;

/**
 * Created by Taha on 18/03/2016.
 */
public class GenreAdapter extends ArrayAdapter<Genres> {
    Context context;

    public GenreAdapter(Context context, int resource, ArrayList<Genres> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Genres item = getItem(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_genre_list,parent,false);

//        TextView GenreName = (TextView) rowView.findViewById(R.id.row_genre_name);
        CheckBox GenreNamecb = (CheckBox) rowView.findViewById(R.id.row_genre_checkbox);
//        TextView GenreName = (TextView) rowView.findViewById(R.id.row_genre_name);
//        TextView BookPublisher = (TextView) rowView.findViewById(R.id.row_book_publisher_name);
//        TextView BookPrice = (TextView) rowView.findViewById(R.id.row_book_price);
//        ImageView BookLogo = (ImageView) rowView.findViewById(R.id.row_book_logo);
//        RatingBar AppRatingBar = (RatingBar) rowView.findViewById(R.id.row_book_ratingbar);


          GenreNamecb.setText(item.getGenere_Name());
//        BookPrice.setText(item.getBook_price());
//        BookPublisher.setText(item.getBook_publisher());
//        Picasso.with(context).load(item.getBook_logo()).into(BookLogo);
        return rowView;
    }
}

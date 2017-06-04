package com.ticktech.booksgram.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ticktech.booksgram.R;
import com.ticktech.booksgram.model.Books;


import java.util.ArrayList;

/**
 * Created by Taha on 18/03/2016.
 */
public class BookAdapter extends ArrayAdapter<Books> {
    Context context;

    public BookAdapter(Context context, int resource, ArrayList<Books> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Books item = getItem(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_book_list,parent,false);

        TextView BookName = (TextView) rowView.findViewById(R.id.row_book_name);
        TextView BookPublisher = (TextView) rowView.findViewById(R.id.row_book_publisher_name);
        TextView BookPrice = (TextView) rowView.findViewById(R.id.row_book_price);
        ImageView BookLogo = (ImageView) rowView.findViewById(R.id.row_book_logo);
        RatingBar AppRatingBar = (RatingBar) rowView.findViewById(R.id.row_book_ratingbar);

        AppRatingBar.setRating(item.getBook_rating());
        BookName.setText(item.getBook_name());
        BookPrice.setText(item.getBook_price());
        BookPublisher.setText(item.getBook_publisher());
        Picasso.with(context).load(item.getBook_logo()).into(BookLogo);
        return rowView;
    }
}

package com.ticktech.booksgram.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.ticktech.booksgram.BookDetailActivity;
import com.ticktech.booksgram.R;
import com.ticktech.booksgram.model.Books;


import java.util.ArrayList;


public class BookAdapter extends ArrayAdapter<Books> {
    Context context;
    private int layout;

    public BookAdapter(Context context, int resource, ArrayList<Books> objects) {
        super(context, resource, objects);
        this.context = context;
        layout = resource;

    }

    public class ViewHolder
    {
        TextView BookName;
        RatingBar BookRating;
        TextView BookPrice;
        TextView BookPublisher;
        TextView BookCategoryName;
        ImageView Book_share_button;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Books item = getItem(position);
        ViewHolder mainViewHolder = null;

        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(layout,parent,false);
        ViewHolder viewHolder = new ViewHolder();

        viewHolder.BookName = (TextView) convertView.findViewById(R.id.row_book_name);
        viewHolder.BookPrice = (TextView) convertView.findViewById(R.id.row_book_price);
        viewHolder.BookPublisher = (TextView) convertView.findViewById(R.id.row_book_publisher_name);
        viewHolder.BookRating = (RatingBar) convertView.findViewById(R.id.row_book_ratingbar);
        viewHolder.Book_share_button = (ImageView) convertView.findViewById(R.id.row_share_button);
        viewHolder.BookCategoryName = (TextView) convertView.findViewById(R.id.row_heading);

        ImageView BookLogo = (ImageView) convertView.findViewById(R.id.row_book_logo);
        viewHolder.Book_share_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BookDetailActivity.class);
                context.startActivity(intent);

//                String ShareBookContent = String.format("Book '"+item.getBook_name() +"'\n"+"written "+ item.getBook_publisher()+" in year "+ item.getBook_publishYear()+" is an amazing book with rating "+item.getBook_rating()+"/5.0 You can get this book from amazon by this link "+"\n "+ item.getBook_link() );
//                Intent mIntent = new Intent();
//                mIntent.setAction(Intent.ACTION_SEND);
//                mIntent.putExtra(Intent.EXTRA_TEXT, ShareBookContent);
//                mIntent.setType("text/plain");
//                context.startActivity(Intent.createChooser(mIntent,"Share Book"));
            }
        });
        convertView.setTag(viewHolder);

        mainViewHolder = (ViewHolder) convertView.getTag();
        mainViewHolder.BookName.setText(item.getBook_name());
        mainViewHolder.BookPrice.setText(item.getBook_price());
        mainViewHolder.BookPublisher.setText("'" + item.getBook_publisher() + "'");
        mainViewHolder.BookRating.setRating(item.getBook_rating());
        mainViewHolder.BookCategoryName.setText("Most Read This Week In "+item.getBook_categoryName());
        Picasso.with(context).load(item.getBook_logo()).into(BookLogo);
        return convertView;
    }
}

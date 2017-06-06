package com.ticktech.booksgram.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ticktech.booksgram.R;
import com.ticktech.booksgram.model.Books;
import com.ticktech.booksgram.model.Genres;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taha on 18/03/2016.
 */
public class GenreAdapter  extends ArrayAdapter<Genres> {

    private final List<Genres> list;
    private final Context context;
    boolean checkAll_flag = false;
    boolean checkItem_flag = false;

    public ArrayList<Genres> checkedGenres=new ArrayList<>();



    public GenreAdapter(Context context, int resource, List<Genres> list) {
        super(context, resource, list);
        this.context = context;
        this.list = list;

    }

    static class ViewHolder {
        protected TextView genreName;
        protected CheckBox genreCb;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflator = LayoutInflater.from(getContext());
            convertView = inflator.inflate(R.layout.row_genre_list, null);
            viewHolder = new ViewHolder();
            viewHolder.genreName = (TextView) convertView.findViewById(R.id.row_genrename_textview);
            viewHolder.genreCb = (CheckBox) convertView.findViewById(R.id.row_genre_checkbox);
            viewHolder.genreCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int getPosition = (Integer) buttonView.getTag();  // Here we get the position that we have set for the checkbox using setTag.
                    list.get(getPosition).setSelected(buttonView.isChecked()); // Set the value of checkbox to maintain its state.


                    if(buttonView.isChecked())
                    {

                        int a = checkedGenres.indexOf(list.get(getPosition));
                        if(a == -1)
                        {
                            checkedGenres.add(list.get(getPosition));
                        }


//                        Toast.makeText(context, "Checked : " + list.get(getPosition).getGenere_id(), Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        checkedGenres.remove(list.get(getPosition));
//                        Toast.makeText(context, "Unchecked : " + list.get(getPosition).getGenere_id(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
            convertView.setTag(viewHolder);
            convertView.setTag(R.id.row_genrename_textview, viewHolder.genreName);
            convertView.setTag(R.id.row_genre_checkbox, viewHolder.genreCb);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.genreCb.setTag(position); // This line is important.

        viewHolder.genreName.setText(list.get(position).getGenere_Name());
        viewHolder.genreCb.setChecked(list.get(position).isSelected());

        return convertView;
    }

//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//
//        final Genres genres = getItem(position);
//        ViewHolder mainViewHolder = null;
//
//
//        if(convertView == null)
//        {
//        LayoutInflater inflater = LayoutInflater.from(getContext());
//        convertView = inflater.inflate(layout,parent,false);
//        }
//
////        CheckBox checkbox = (CheckBox)v.findViewById(R.id.checkbox);
//
//
//        ViewHolder viewHolder = new ViewHolder();
//        viewHolder.genreName = (TextView) convertView.findViewById(R.id.row_genrename_textview);
//        viewHolder.genreCb = (CheckBox) convertView.findViewById(R.id.row_genre_checkbox);
//        viewHolder.genreId = (TextView) convertView.findViewById(R.id.row_genreid_textview);
//
////        viewHolder.genreCb.setChecked(checked[position]);
//
//        viewHolder.genreCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()  {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                    int getPosition = (Integer) buttonView.getTag();
//                    objects.get(getPosition).setSelected(buttonView.isChecked());
//
//                    if(isChecked)
//                    {
//                        Toast.makeText(context, "Checked : " + genres.getGenere_id(), Toast.LENGTH_SHORT).show();
////                        checked[position] = false;
//                    }
//                    else
//                    {
//                        Toast.makeText(context, "Unchecked : " + genres.getGenere_id(), Toast.LENGTH_SHORT).show();
////                        checked[position] = true;
//                    }
//                }
//            });
//
//        convertView.setTag(viewHolder);
//        viewHolder.genreCb.setTag(position);
//
//        mainViewHolder = (ViewHolder) convertView.getTag();
//        mainViewHolder.genreName.setText(genres.getGenere_Name());
//        mainViewHolder.genreId.setText(genres.getGenere_id());
//        return convertView;
//
//    }
}

package com.ticktech.booksgram;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ticktech.booksgram.model.BookDatasource;
import com.ticktech.booksgram.model.Books;
import com.ticktech.booksgram.adapter.BookAdapter;

import java.util.ArrayList;


public class BooksListFragment extends Fragment {
    ArrayList<Books> array_list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View view= inflater.inflate(R.layout.fragment_books_list, container, false);
        ListView mListViewBooks = (ListView)view.findViewById(R.id.booklist_listview);
        BookDatasource mApplicationDatasource = new BookDatasource();
        array_list = mApplicationDatasource.getList();
        BookAdapter mBookAdapter = new BookAdapter(getActivity(),R.layout.row_book_list,array_list);
        mListViewBooks.setAdapter(mBookAdapter);

        return view;
//        return inflater.inflate(R.layout.fragment_books_list, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Fragment Books List");
    }
}

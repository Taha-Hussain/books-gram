package com.ticktech.booksgram;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.app.ProgressDialog;

import com.ticktech.booksgram.model.BookDatasource;
import com.ticktech.booksgram.model.Books;
import com.ticktech.booksgram.adapter.BookAdapter;

import java.util.ArrayList;


public class BooksListFragment extends Fragment {
    ArrayList<Books> array_list;
    BookDatasource bookDatasource;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_books_list, container, false);
        new asyncTask_httpGet().execute();
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Books List");
    }

    public class asyncTask_httpGet extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog = new ProgressDialog(getActivity());

        @Override
        protected void onPreExecute() {
            dialog.setTitle("Please wait...");
            dialog.setMessage("Let The Fun Begin! ");
            dialog.show();
            array_list = new ArrayList<>();
            bookDatasource = new BookDatasource();


            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            array_list = bookDatasource.getList();
            return null;
        }

        @Override
        protected void onPostExecute(Void s) {

            ListView mListViewBooks = (ListView) view.findViewById(R.id.booklist_listview);
            BookAdapter mBookAdapter = new BookAdapter(getActivity(), R.layout.row_book_list, array_list);
            mListViewBooks.setAdapter(mBookAdapter);
            super.onPostExecute(s);
            dialog.dismiss();
        }
    }
}
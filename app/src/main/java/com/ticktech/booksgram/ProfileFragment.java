package com.ticktech.booksgram;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ProfileFragment extends Fragment {

    View view;
    TextView Email;
    SharedPreferences mSharedPreferences;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        Email = (TextView) view.findViewById(R.id.Email_textView);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences (getActivity());
        String strEmail = mSharedPreferences.getString("key_email", "");
        Email.setText(strEmail);


        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.editFavCategoriesLinearLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

        Intent mintent = new Intent(getContext(), GenreActivity.class);
        startActivity(mintent);
            }
        });

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Profile");
    }
}
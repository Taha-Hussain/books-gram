package com.ticktech.booksgram;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.ticktech.booksgram.GenreActivity;
import com.ticktech.booksgram.model.FavGenreCountDatasource;
import com.ticktech.booksgram.model.FavGenreDatasource;
import com.ticktech.booksgram.model.Genres;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText mEditTextUserName;
    EditText mEditTextPassword;
    RadioButton mRemember;
    SharedPreferences mSharedPreferences;

    FavGenreCountDatasource favGenreCountDatasource;


    String mUserName;
    String mPassword;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        init();
        populate();
    }

    private void init() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences (this);
        mEditTextUserName = (EditText) findViewById(R.id.login_Textview_userName);
        mEditTextPassword = (EditText) findViewById(R.id.login_Textview_password);
    }


    public boolean populate() {


        String strUserName = mSharedPreferences.getString("key_email", "");
//        String strPassword = mSharedPreferences.getString("key_password", "");

        mEditTextUserName.setText(strUserName);
//        mEditTextPassword.setText(strPassword);


//        if (strPassword.length() > 0 && strUserName.length() > 0) {
//            //  Intent mintent = new Intent(context, CarListsActivity.class);
//            //startActivity(mintent);
//            finish();
//
//        } else {
//            showMessage("Please Login ");
//            return false;
//
//        }
        return true;
    }

    private void savePreferences() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("key_email", mEditTextUserName.getText().toString());
        editor.commit();
    }

    public void onClick_login(View view) {

       if (isValidate()) {
            mUserName = mEditTextUserName.getText().toString();
            mPassword = mEditTextPassword.getText().toString();
            if (isNetworkConnected()) {
                new asyncTask_loginRequest().execute();
            } else {
                showMessage("Please Check Internet Connection");
            }

        }


    }

    public void onClick_Register(View view) {


        Intent mintent = new Intent(this, RegisterActivity.class);
        startActivity(mintent);


    }

    public void onClick_forgetPassword(View view) {

//        Intent mintent = new Intent(this, MainActivity.class);
//        startActivity(mintent);

        Intent mintent = new Intent(this, ForgetPasswordActivity.class);
        startActivity(mintent);

//        Intent mintent = new Intent(this, GenreActivity.class);
//        startActivity(mintent);


    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    private class asyncTask_loginRequest extends AsyncTask<Void, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(LoginActivity.this, "Sending...", "Please Wait!", true);

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            String msg = "";
            try {
                if (mUserName.length() > -0 && mPassword.length() > -0) {


                    msg = loginRequestToWebServer(mUserName, mPassword);

                }
            } catch (Exception ex) {
                msg = "Error :" + ex.getMessage();
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
//            showMessage(result);
            if (result.equals("200")) {
                savePreferences();
//                showMessage("Login sucess");
                afterLoginProcess();
            } else {
                showMessage("Invalid user/password");
            }

        }
    }

    public String loginRequestToWebServer(String mUserName, String mPassword) {
//        http://friendsfashion.net/android/book/login.php
//        String url = "http://friendsfashion.net/android/book/login.php";
        String url = "http://bookgram.000webhostapp.com/app/login.php";
        String strResponse = "No response";

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
            nameValuePairs.add(new BasicNameValuePair("email", mUserName));
            nameValuePairs.add(new BasicNameValuePair("password", mPassword));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            strResponse = EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            strResponse = e.getMessage();
        } catch (IOException e) {
            Log.e("IOException:", e.getMessage());
            strResponse = e.getMessage();
        }
        return strResponse;
    }

    public void afterLoginProcess() {

        Intent mintent = new Intent(context, MainActivity.class);
        startActivity(mintent);

//        new asyncTask_httpfavCategoriesCount().execute();


    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private boolean isValidate() {
        if (mEditTextUserName.getText().toString().equalsIgnoreCase("")) {
            showMessage("Please give your Username");
            return false;
        } else if (mEditTextPassword.getText().toString().equalsIgnoreCase("")) {
            showMessage("Please give your Password");
            return false;
        }
        return true;
    }


    public class asyncTask_httpfavCategoriesCount extends AsyncTask<Void, Void, Void> {

        String count;
        @Override
        protected void onPreExecute() {
            favGenreCountDatasource = new FavGenreCountDatasource();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            count = favGenreCountDatasource.getResult(context);
//            count = favGenreCountDatasource.getResult(context);
            return null;
        }
        @Override
        protected void onPostExecute(Void s) {

            if(count == "0") {
                Intent mintent = new Intent(context, MainActivity.class);
                startActivity(mintent);
            }
            else
                {
                Intent mintent = new Intent(context, Genres.class);
                startActivity(mintent);
                }
//            Toast.makeText(context,response,Toast.LENGTH_SHORT).show();
            super.onPostExecute(s);
        }
}

}

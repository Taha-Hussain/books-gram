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

    String mUserName;
    String mPassword;

    Context context;

    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        mEditTextUserName = (EditText) findViewById(R.id.login_Textview_userName);
        mEditTextPassword = (EditText) findViewById(R.id.login_Textview_password);

       // populate();
    }

    public boolean populate() {


        String strUserName = mSharedPreferences.getString("key_userName", "");
        String strPassword = mSharedPreferences.getString("key_password", "");

        mEditTextUserName.setText(strUserName);
        mEditTextPassword.setText(strPassword);


        if (strPassword.length() > 0 && strUserName.length() > 0) {
            //  Intent mintent = new Intent(context, CarListsActivity.class);
            //startActivity(mintent);
            finish();

        } else {
            showMessage("Please Login ");
            return false;

        }
        return true;
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
        Intent mintent = new Intent(this, ForgetPasswordActivity.class);
        startActivity(mintent);

    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    private class asyncTask_loginRequest extends AsyncTask<Void, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(LoginActivity.this, "Sending...", "wait", true);

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
            if (result.equals("\n200")) {
                showMessage("Login sucess");
                afterLoginProcess();
            } else {
                showMessage("Invalid user/password");
            }

        }
    }

    public String loginRequestToWebServer(String mUserName, String mPassword) {
        String url = "http://friendsfashion.net/android/book/login.php";
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

//        if (mRemember.isChecked()) {
//
//            SharedPreferences.Editor editor = mSharedPreferences.edit();
//            editor.putString("key_userName", mEditTextUserName.getText().toString());
//            editor.putString("key_password", mEditTextPassword.getText().toString());
//            editor.commit();

            // Intent mintent = new Intent(this, CarListsActivity.class);
            //startActivity(mintent);
//            showMessage("done");
        Intent mintent = new Intent(this, MainActivity.class);
        startActivity(mintent);
            // finish();
       // }
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

}

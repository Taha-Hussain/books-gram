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
import com.ticktech.booksgram.parser.FavGenresCountApi;
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
    SharedPreferences mSharedPreferences;
    String mUserName;
    String mPassword;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
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
        mEditTextUserName.setText(strUserName);
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
            } else
                {
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
            progressDialog = ProgressDialog.show(LoginActivity.this, "Signing In...", "Please Wait!", true);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            String msg = "";
            try {
                if (mUserName.length() > 0 && mPassword.length() > 0) {
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
//Un Comment This After Setting GCM ID
//            if (result.equals("200")) {
//                savePreferences();
//                new asyncTask_httpfavCategoriesCount().execute();
//            } else {
//                showMessage("Invalid user/password");
//            }
//UnComment This After Setting GCM ID

//Remove This After Setting GCM ID
                savePreferences();
                new asyncTask_httpfavCategoriesCount().execute();
//Remove This After Setting GCM ID

        }
    }

    public String loginRequestToWebServer(String mUserName, String mPassword) {

//      String url = "http://friendsfashion.net/android/book/login.php";
//      String url = "http://bookgram.000webhostapp.com/app/login.php";
        String rootUrl = getResources().getString(R.string.server_url);
        String url = rootUrl + "login.php";
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
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            count = FavGenresCountApi.callGenresCount(context);
            return null;
        }
        @Override
        protected void onPostExecute(Void s) {

            if(count.equals("0")) {
                Intent mintent = new Intent(context, GenreActivity.class);
                startActivity(mintent);
            }
            else
                {
                    Intent mintent = new Intent(context, MainActivity.class);
                    startActivity(mintent);
                    finish();
                }
            super.onPostExecute(s);
        }
    }

}

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
import android.widget.Spinner;
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

public class RegisterActivity extends AppCompatActivity {

    EditText mEditTextName;
    EditText mEditTextPassword;
    EditText mEditTextEmail;
    String mUserName;
    String mPassword;
    String mEmail;
    Context context;
    SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = this;
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditTextName = (EditText) findViewById(R.id.register_Textview_name);
        mEditTextEmail = (EditText) findViewById(R.id.register_Textview_email);
        mEditTextPassword = (EditText) findViewById(R.id.register_Textview_password);
    }

    public boolean populate() {


        String strUserName = mSharedPreferences.getString("key_userName", "");
        String strPassword = mSharedPreferences.getString("key_password", "");

        mEditTextName.setText(strUserName);
        mEditTextPassword.setText(strPassword);
        if (strPassword.length() > 0 && strUserName.length() > 0) {
            finish();
        } else {
            showMessage("Please Login ");
            return false;

        }
        return true;
    }

    public void onClick_register(View view) {

        if (isValidate()) {
            mUserName = mEditTextName.getText().toString();
            mPassword = mEditTextPassword.getText().toString();
            mEmail = mEditTextEmail.getText().toString();

            if (isNetworkConnected()) {
                new RegisterActivity.asyncTask_RegisterRequest().execute();
            } else {
                showMessage("Please Check Internet Connection");
            }

        }
    }
    public void onClick_login(View view) {

        Intent mintent = new Intent(this, LoginActivity.class);
        startActivity(mintent);
        finish();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    private class asyncTask_RegisterRequest extends AsyncTask<Void, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(RegisterActivity.this, "Sending...", "wait", true);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            String msg = "";
            try {
                if (mUserName.length() > -0 && mPassword.length() > -0) {
                    msg = loginRequestToWebServer(mUserName, mPassword,"2",mEmail);
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
            if (result.equals("400")) {
                showMessage("This User Already Registred");
                afterRegisterProcess();
            } else if (result.equals("201")){

                showMessage("Register Success");
            }
        }
    }

    public String loginRequestToWebServer(String mUserName, String mPassword , String mtype, String mEmail) {
        String rootUrl = getResources().getString(R.string.server_url);
        String url = rootUrl + "register.php";
        String strResponse = "No response";

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
            nameValuePairs.add(new BasicNameValuePair("userName", mUserName));
            nameValuePairs.add(new BasicNameValuePair("email", mEmail));
            nameValuePairs.add(new BasicNameValuePair("password", mPassword));
            nameValuePairs.add(new BasicNameValuePair("userType", mtype));
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

    public void afterRegisterProcess() {

             Intent mintent = new Intent(this, LoginActivity.class);
            startActivity(mintent);

             finish();

    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private boolean isValidate() {
        if (mEditTextEmail.getText().toString().equalsIgnoreCase("")) {
            showMessage("Please give your Email Address");
            return false;
        } else if (mEditTextPassword.getText().toString().equalsIgnoreCase("")) {
            showMessage("Please give your Password");
            return false;
        }
        else if (mEditTextName.getText().toString().equalsIgnoreCase("")) {
            showMessage("Please give your Name");
            return false;
        }
        return true;
    }

}

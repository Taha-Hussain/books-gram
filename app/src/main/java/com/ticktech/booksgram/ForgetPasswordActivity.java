package com.ticktech.booksgram;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class ForgetPasswordActivity extends AppCompatActivity {

    EditText mEditTextEmail;
    String mEmail;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        context = this;

        mEditTextEmail = (EditText) findViewById(R.id.forget_Textview_email);
    }



    public void onClick_resetPassword(View view) {

        if (isValidate()) {
            mEmail = mEditTextEmail.getText().toString();

            if (isNetworkConnected()) {
                new ForgetPasswordActivity.asyncTask_forgetRequest().execute();
            } else {
                showMessage("Please Check Internet Connection");
            }
        }
    }

    public void onClick_Register(View view) {

        Intent mintent = new Intent(this, RegisterActivity.class);
        startActivity(mintent);
    }
    public void onClick_login(View view) {

        Intent mintent = new Intent(this, LoginActivity.class);
        startActivity(mintent);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    private class asyncTask_forgetRequest extends AsyncTask<Void, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(ForgetPasswordActivity.this, "Sending...", "wait", true);

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            String msg = "";
            try {
                if (mEmail.length() > -0) {


                    msg = loginRequestToWebServer(mEmail);

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
            if (result.equals("200")) {
                showMessage("Your New Password Has been Sent To your Email Address.");
                afterLoginProcess();
            } else {
                showMessage("Invalid Email Address");
            }

        }
    }

    public String loginRequestToWebServer(String mEmail) {

        String url = "http://bookgram.000webhostapp.com/app/forgetPassword.php";
//      String url = "http://friendsfashion.net/android/book/forgetPassword.php";
        String strResponse = "No response";

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("email", mEmail));
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
        }
        return true;
    }
}

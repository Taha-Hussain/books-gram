package com.ticktech.booksgram.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
/**
 * Created by Taha on 04/06/2017.
 */

public class HttpService {

    public String httpGet() {
        String result="";
        final HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("http://friendsfashion.net/android/book/getBooksApi.php");
        try {
            HttpResponse mHttpResponse = httpClient.execute(httpGet);
            result = EntityUtils.toString(mHttpResponse.getEntity());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

package com.ticktech.booksgram.parser;

        import android.content.Context;
        import android.content.SharedPreferences;
        import android.preference.PreferenceManager;
        import org.apache.http.HttpResponse;
        import org.apache.http.NameValuePair;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.entity.UrlEncodedFormEntity;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.message.BasicNameValuePair;
        import org.apache.http.util.EntityUtils;
        import java.util.ArrayList;
        import static com.ticktech.booksgram.parser.FavouriteGenresJsonParser.selectedCategories;

public class InsertUpdateFavouriteGenresApi {

    static SharedPreferences mSharedPreferences;
    public static String callGenres(String genresStr, Context context) {
        String response = "";
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences (context);
        String strEmail = mSharedPreferences.getString("key_email", "");

        HttpClient httpClient = new DefaultHttpClient();

        HttpPost httpPost = new HttpPost("http://192.168.8.103:81/CheckBoxListView/insertUpdateFavouriteCategories.php");
        try {

            ArrayList<NameValuePair> nameValuePairs= new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("email",strEmail));
            nameValuePairs.add(new BasicNameValuePair("favouriteCategories",genresStr));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            response = EntityUtils.toString(httpResponse.getEntity());
            selectedCategories.clear();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return response;
    }
}

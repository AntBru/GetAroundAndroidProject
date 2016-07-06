package com.appgetaround.getaround.Model;

import android.util.Log;

import com.appgetaround.getaround.Helper.AppConfig;
import com.appgetaround.getaround.Helper.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.makeText;

public class LoginModel{

    private static final String CASE = "checkLogin";

    JSONParser jsonParser = new JSONParser();

    private static final String TAG_SUCCESS = "success";

    public int getLoginPassword(String username, String password) throws JSONException {
        Log.i("Sono nel Login Model"," nel metodo getLoginPassword");
        int mSuccess = -1;

        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();


        Log.i("URL backend: ", AppConfig.URL);

        params.add(new BasicNameValuePair("geoAction", CASE));
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        Log.i("Parametro passato: ", CASE);
        Log.i("Start LoginModel", "");
        Log.i("Username: ", username);
        Log.i("Password", password);


        // getting product details by making HTTP request
        JSONObject json = jsonParser.makeHttpRequest(AppConfig.URL, "POST", params);

        Log.i("JSON:", json.toString());

        mSuccess = json.getInt(TAG_SUCCESS);

        return mSuccess;
    }

}

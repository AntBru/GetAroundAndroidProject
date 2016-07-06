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

public class RegisterModel {
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    //private static final String TAG_MESSAGE = "message";
    private static final String CASE = "registerNewUser";

    JSONParser jsonParser = new JSONParser();

    public int getRegistration(String username, String password, String town, String email, String recoveryPasswordPhrase,
                               String firstname, String lastname) throws JSONException {
        Log.i("Sono nel Register Model"," nel metodo getRegistration");
        int mSuccess;

        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("geoAction",CASE));
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("nome", firstname));
        params.add(new BasicNameValuePair("cognome", lastname));
        params.add(new BasicNameValuePair("citta", town));
        params.add(new BasicNameValuePair("frase", recoveryPasswordPhrase));

        Log.i("Start RegisterModel","");
        Log.i("Username: ", username);
        Log.i("Password", password);
        Log.i("Email: ", email);
        Log.i("Nome", firstname);
        Log.i("Cognome: ", lastname);
        Log.i("Città", town);
        Log.i("Frase: ", recoveryPasswordPhrase);
        Log.i("Parametro passato: ",CASE);

        JSONObject json = jsonParser.makeHttpRequest(AppConfig.URL, "POST", params);
        Log.i("URL register backend: ", AppConfig.URL);

        Log.i("JSON register:", json.toString());

        mSuccess = json.getInt(TAG_SUCCESS);

        Log.i("Success: ", String.valueOf(mSuccess));

        return mSuccess;

    }
}

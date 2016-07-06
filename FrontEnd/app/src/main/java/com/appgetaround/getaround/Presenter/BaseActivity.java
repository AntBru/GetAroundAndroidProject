package com.appgetaround.getaround.Presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import com.appgetaround.getaround.Helper.AppConfig;
import com.appgetaround.getaround.Helper.SessionManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    protected boolean checkNetworkConnection() {
        Log.i("Sono nel ", "checkNetworkConnection");
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        Log.i("cm: ", String.valueOf(cm));

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        Log.i("activeNetwork: ", String.valueOf(activeNetwork));

        boolean isConnected = activeNetwork != null && activeNetwork.isConnected();
        //boolean isConnected = activeNetwork != null;

        Log.i("isConnected: ", String.valueOf(isConnected));

        return isConnected;
    }

    protected boolean checkURLConnection() {
        Log.i("Sono nel ", "checkURLConnection");

        try {
            URL url = new URL(AppConfig.URL);
            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            urlc.setConnectTimeout(3000);
            urlc.connect();
            if (urlc.getResponseCode() == 200) {
                return true;
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;

    }
}

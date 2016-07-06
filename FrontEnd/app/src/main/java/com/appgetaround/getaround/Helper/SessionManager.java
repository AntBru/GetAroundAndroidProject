package com.appgetaround.getaround.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SessionManager {

    // Shared Preferences
    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "GetAroundLogin";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    public SessionManager(Context context) {
        Log.i("Sono nel ", "SessionManager");
        Log.i("Context: ", String.valueOf(context));
        this._context = context;
        sharedPreferences = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        Log.i("Pref: ", String.valueOf(sharedPreferences));
        editor = sharedPreferences.edit();
    }

    public void setLogin(boolean isLoggedIn) {
        Log.i("Sono nel ", "setLogin del SessionManager");
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        Log.i("setLogin: ", String.valueOf(isLoggedIn));
        // commit changes
        editor.commit();
    }

    public boolean isLoggedIn(){
        Log.i("Sono in ", "isLoggedIn del SessionManager");
        Log.i("isLoggedIn: ", String.valueOf(sharedPreferences.getBoolean(KEY_IS_LOGGEDIN, false)));
        return sharedPreferences.getBoolean(KEY_IS_LOGGEDIN, false);
    }
}

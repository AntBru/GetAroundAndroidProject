package com.appgetaround.getaround.Presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.appgetaround.getaround.Helper.SessionManager;
import com.appgetaround.getaround.R;

public class GetAroundActivity extends Activity {
    private Button logout;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_around);

        sessionManager = new SessionManager(getApplicationContext());

        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Ho premuto ","il bottone di Logout");
                sessionManager.setLogin(false);
                Log.i("L'utente ha ","effettuato il logout");
                Intent i = new Intent(GetAroundActivity.this, WelcomeActivity.class);
                finish();
                startActivity(i);

            }
        });
    }
}

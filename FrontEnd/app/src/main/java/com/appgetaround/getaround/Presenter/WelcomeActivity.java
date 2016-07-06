package com.appgetaround.getaround.Presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.appgetaround.getaround.Helper.SessionManager;
import com.appgetaround.getaround.R;

public class WelcomeActivity extends Activity {

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("","Sono in getAroundActivity nel metodo onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button button = (Button) findViewById(R.id.loginIndex);
        Button button1 = (Button) findViewById(R.id.registerIndex);

        sessionManager = new SessionManager(getApplicationContext());

        if (sessionManager.isLoggedIn()) {
            Log.i("Utente già loggato: ","vado nella getAroundActivity");
            Intent intent = new Intent(WelcomeActivity.this, GetAroundActivity.class);
            startActivity(intent);
            finish();
        }else {
            button.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    Log.i("premo il bottone", " del login");
                    Intent i = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(i);

                }
            });

            button1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Log.i("", "E' stato premuto il bottone della registrazione");
                    Intent i = new Intent(getBaseContext(), RegisterActivity.class);
                    startActivity(i);
                }
            });
        }

    }

}

package com.appgetaround.getaround.Presenter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.appgetaround.getaround.Helper.SessionManager;
import com.appgetaround.getaround.Model.LoginModel;
import com.appgetaround.getaround.R;

import org.json.JSONException;

import static android.widget.Toast.makeText;

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private EditText user, pass;
    private CheckBox showPass;
    private Button login;
    private TextView forgotPass;

    LoginModel loginModel = new LoginModel();

    //BaseActivity baseActivity = new BaseActivity();

    //private TextView loginErrorMsg;

    int counter = 3;

    // Progress Dialog
    private ProgressDialog pDialog;

    private SessionManager sessionManager;

    private static final String ATTEMPTING_LOGIN = "Accesso in corso!";
    private static final String INSERT_USERNAME_KO = "Errore!Inserisci un username!";
    private static final String LOGIN_OK= "Login effettuato!";
    private static final String USER_KO = "User non valido!";
    private static final String PASSWORD_KO = "Password non valida!Tentativi rimasti: ";
    private static final String PASSWORD_FINISH_ATTEMPTS = "Password non valida!Non hai più tentativi";
    private static final String ERROR_MESSAGE = "Connessione Internet assente!";
    private static final String ERROR_SERVER = "Impossibile connettersi al server!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("","Sono nella classe LoginActivity nel metodo onCreate");
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.i("Parto da qui", "");

        //setup input fields
        user = (EditText)findViewById(R.id.editText1);
        pass = (EditText)findViewById(R.id.editText2);
        //loginErrorMsg = (TextView) findViewById(R.id.loginErrorMsg);

        showPass = (CheckBox) findViewById(R.id.showPass);

        //setup buttons
        login = (Button)findViewById(R.id.login);
        forgotPass = (TextView) findViewById(R.id.forgotPassword);

        //register listeners
        login.setOnClickListener(this);
        forgotPass.setOnClickListener(this);

        final String[] mUsername = new String[1];
        final String[] mPassword = new String[1];

        user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mUsername[0] = user.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(mUsername[0].length()==0){
                    user.requestFocus();
                    user.setError("Il campo non può essere vuoto");
                }
            }
        });

        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPassword[0] = pass.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(mPassword[0].length()==0){
                    pass.requestFocus();
                    pass.setError("Il campo non può essere vuoto");
                }
            }
        });

        showPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(!isChecked){
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else{
                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });



    }

    @Override
    public void onClick(View v) {
        // determine which button was pressed:
        Log.i("Sono in onClick","");
        switch (v.getId()) {
            case R.id.login:
                new Login().execute();
                /*user.setText("");
                pass.setText("");*/
                break;
            /*case R.id.forgotPassword:
                /*Intent i = new Intent(this, ForgotPasswordMainActivity.class);
                startActivity(i);
                finish();
                break;*/
                /*Intent intent = new Intent(getApplicationContext(), LoginSuccessMainActivity.class);
                startActivity(intent);
                break;*/

            default:
                break;
        }
    }


    /*class NetCheck extends AsyncTask<String, String, Boolean> {
        private ProgressDialog nDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            nDialog = new ProgressDialog(LoginActivity.this);
            nDialog.setMessage(NET_CHECK);
            nDialog.setIndeterminate(false);
            nDialog.setCancelable(true);
            nDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... args) {

            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            Log.i("netInfo: ", String.valueOf(netInfo));
            Log.i("netInfo.isConnected: ", String.valueOf(netInfo.isConnected()));
            if (netInfo != null && netInfo.isConnected()) {
                try {
                    URL url = new URL(AppConfig.URL);
                    Log.i("url: ", String.valueOf(url));
                    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                    urlc.setConnectTimeout(3000);
                    urlc.connect();
                    if (urlc.getResponseCode() == 200) {
                        return true;
                    }
                } catch (MalformedURLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Boolean th) {

            Log.i("Connessione: ", String.valueOf(th));
            if (th == true) {
                nDialog.dismiss();
                new Login().execute();
            } else {
                Log.i("Sono nell'else di ","netchek");
                nDialog.dismiss();
                loginErrorMsg.setText(ERROR_MESSAGE);
            }
        }
    }*/


    private class Login extends AsyncTask<String, String, String> {
        String mUsername = user.getText().toString().trim();
        String mPassword = pass.getText().toString().trim();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage(ATTEMPTING_LOGIN);
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.i("", "Sono in LoginActivity in doInBackground");
            boolean checkConnection = checkNetworkConnection();
            Log.i("checkConnection", String.valueOf(checkConnection));

            boolean checkURL = checkURLConnection();
            Log.i("checkURL", String.valueOf(checkURL));

            sessionManager = new SessionManager(getApplicationContext());

            /*if (sessionManager.isLoggedIn()) {
                Log.i("Utente già loggato: ","vado nella getAroundActivity");
                Intent intent = new Intent(LoginActivity.this, GetAroundActivity.class);
                startActivity(intent);
                finish();
            }else{*/

                Log.i("Utente non loggato: ","controllo connessione e successiva login");
                if (checkConnection && checkURL) {

                    Log.i("Sono entrato ", "nella condizione OK connessione e OK server");
                    if (mUsername.isEmpty()) {
                        LoginActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), INSERT_USERNAME_KO, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    try {
                        int mSuccess = loginModel.getLoginPassword(mUsername, mPassword);
                        Log.i("Success: ", String.valueOf(mSuccess));

                        if (mSuccess == 1) {
                            Log.i("Success 1: ", "Login OK");
                            sessionManager.setLogin(true);
                            Log.i("L'utente ha ","effettuato la login");
                            Intent i = new Intent(LoginActivity.this, GetAroundActivity.class);
                            i.putExtra("username", mUsername);
                            finish();
                            startActivity(i);
                            LoginActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), LOGIN_OK, Toast.LENGTH_LONG).show();
                                }
                            });
                        } else if (mSuccess == 0) {
                            Log.i("Success 0: ", "User KO");
                            LoginActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), USER_KO, Toast.LENGTH_LONG).show();
                                }
                            });
                        } else {
                            Log.i("Success 2: ", "Password KO");
                            counter--;
                            if (counter == 0) {
                                Intent i = new Intent(LoginActivity.this, WelcomeActivity.class);
                                finish();
                                startActivity(i);
                            }
                            LoginActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (counter != 0)
                                        Toast.makeText(getApplicationContext(), PASSWORD_KO + counter, Toast.LENGTH_LONG).show();
                                    else
                                        Toast.makeText(getApplicationContext(), PASSWORD_FINISH_ATTEMPTS, Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else if(checkConnection && !checkURL){
                    Log.i("Sono entrato ", "nella condizione OK connessione e KO server");
                    LoginActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), ERROR_SERVER, Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    Log.i("Sono entrato ", "nella condizione KO connessione");
                    LoginActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), ERROR_MESSAGE, Toast.LENGTH_LONG).show();
                        }
                    });
                }

            //}



            return null;
        }

        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            if (file_url != null) {
                makeText(LoginActivity.this, file_url, Toast.LENGTH_LONG).show();
            }

        }
    }
}

package com.appgetaround.getaround.Presenter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appgetaround.getaround.Helper.SessionManager;
import com.appgetaround.getaround.Model.RegisterModel;
import com.appgetaround.getaround.R;

import org.json.JSONException;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private ProgressDialog pDialog;

    private EditText email;
    private EditText name;
    private EditText surname;
    private EditText username;
    private EditText password;
    private EditText city;
    private EditText phrase;

    private TextView loadImage;
    private TextView loadBackgroundImage;

    private Button annul;
    private Button insert;

    RegisterModel registerModel = new RegisterModel();

    private SessionManager sessionManager;

    private static final String REGISTRATION_KO = "Impossibile inserire l'utente!";
    private static final String MISSING_FIELDS = "Riprovare! Uno o più campi sono mancanti!";
    private static final String REGISTRATION_OK = "Nuovo utente salvato!";
    private static final String USERNAME_AND_PASSWORD_ALREADY_EXSISTS = "Username o email già esistenti";
    private static final String EMAIL_KO= "Indirizzo email sbagliato!";
    private static final String PASSWORD_CHARACTERS_KO= "La password deve contenere almeno 8 caratteri!";
    private static final String PASSWORD_AT_LEAST_ONE_NUMBER = "La password deve contenere almeno un numero!";
    private static final String PASSWORD_AT_LEAST_ONE_CAPITAL_LETTER = "La password deve contenere almeno una lettera maiuscola!";
    private static final String PASSWORD_AT_LEAST_ONE_LOWER_LETTER = "La password deve contenere almeno una lettera minuscola!";
    private static final String USER_KO = "Lo username deve avere almeno 5 caratteri!";
    private static final String NAME_KO = "Il nome deve avere almeno 3 lettere!";
    private static final String SURNAME_KO = "Il cognome deve avere almeno 2 lettere!";
    private static final String ERROR_MESSAGE = "Connessione Internet assente!";
    private static final String ERROR_SERVER = "Impossibile connettersi al server!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("","Sono nella classe RegisterActivity nel metodo onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Log.i("Parto da qui register", "");

        username = (EditText) findViewById(R.id.usernameRegister);
        password = (EditText) findViewById(R.id.passwordRegister);
        email = (EditText) findViewById(R.id.emailRegister);
        name = (EditText) findViewById(R.id.nomeRegister);
        surname = (EditText) findViewById(R.id.cognomeRegister);
        city = (EditText) findViewById(R.id.cittaRegister);
        phrase = (EditText) findViewById(R.id.fraseRegister);

        insert = (Button) findViewById(R.id.confermaRegister);
        annul = (Button) findViewById(R.id.annullaRegister);
        loadImage = (TextView) findViewById(R.id.updateFoto);
        loadBackgroundImage = (TextView) findViewById(R.id.updateBackground);

        insert.setOnClickListener(this);
        annul.setOnClickListener(this);
        loadImage.setOnClickListener(this);
        loadBackgroundImage.setOnClickListener(this);

        final String[] mFirstname = new String[1];
        final String[] mLastname = new String[1];
        final String[] mUser = new String[1];
        final String[] mPass = new String[1];
        final String[] mMail = new String[1];
        final String[] mTown = new String[1];
        final String[] mRecoveryPasswordPhrase = new String[1];

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mFirstname[0] = name.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                    /*if(firstname[0].length()==0){
                        name.requestFocus();
                        name.setError("Il campo non può essere vuoto");
                    }*/
                Log.i("Sono nella funzione", "afterTextChanged");
                Log.i("Firstaname: ", mFirstname[0]);
                Log.i("Name: " , name.getText().toString());


                    /*if(!firstname[0].matches("[a-zA-Z ]+")){
                        name.requestFocus();
                        name.setError("Inserire solo caratteri alfabetici");
                    }
                    else if(firstname[0].length() < 3){
                        name.requestFocus();
                        name.setError("Inserire almeno 3 lettere!");
                    }*/
                //if(!mFirstname[0].matches("^(?=.*[a-zA-Z]).{3,}")){
                if(!mFirstname[0].matches("[a-zA-Z]*") || mFirstname[0].length()<3 || mFirstname[0].matches("[{}()\\\\[\\\\].!€@#+*?^$\\\\\\\\|]")){
                    name.setError("Inserire solo caratteri alfanumerici e minimo 3");
                }
            }
        });

        surname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mLastname[0] = surname.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // fieldIsEmpty(lastname[0], surname);

                    /*if(!lastname[0].matches("[a-zA-Z ]+")){
                        surname.requestFocus();
                        surname.setError("Inserire solo caratteri alfabetici");
                    }
                    else if(lastname[0].length() < 2){
                        surname.requestFocus();
                        surname.setError("Inserire almeno 2 lettere!");
                    }*/
                if(!mLastname[0].matches("[a-zA-Z]*") || mLastname[0].length()<2 || mLastname[0].matches("[{}()\\\\[\\\\].%!€@#+*?^$\\\\\\\\|]")){
                    surname.setError("Inserire solo caratteri alfanumerici e minimo 2");
                }
            }
        });

        city.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTown[0] = city.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                    /*if(town[0].length()==0){
                        city.requestFocus();
                        city.setError("Il campo non può essere vuoto");
                    }*/
                fieldIsEmpty(mTown[0], city);
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mMail[0] = email.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable editable) {

                //fieldIsEmpty(mail[0], email);
                    /*if(mail[0].length()==0){
                        email.requestFocus();
                        email.setError("Il campo non può essere vuoto");
                    }*/
                if(!mMail[0].matches("^[\\w\\.\\-]+@\\w+[\\w\\.\\-]*?\\.\\w{1,4}.{1,}$")){
                    email.setError("Email non corretta!");
                }
            }
        });

        phrase.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mRecoveryPasswordPhrase[0] = phrase.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                    /*if(recoveryPasswordPhrase[0].length()==0){
                        phrase.requestFocus();
                        phrase.setError("Il campo non può essere vuoto");
                    }*/
                fieldIsEmpty(mRecoveryPasswordPhrase[0], phrase);
            }
        });

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mUser[0] = username.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                    /*if(user[0].length()==0){
                        Log.i("Username : ", String.valueOf(user));
                        username.requestFocus();
                        username.setError("Il campo non può essere vuoto");
                    }*/
                //fieldIsEmpty(user[0], username);
                Log.i("Username: " , String.valueOf(username));
                //if(user[0].length() < 5){
                if(!mUser[0].matches(".{5,}")){
                    username.setError("Inserire almeno 5 caratteri!");
                }
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPass[0] = password.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!mPass[0].matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}")){
                    password.requestFocus();
                    password.setError("Inserire almeno 8 caratteri, un numero, una lettera maiuscola ed una minuscola");
                    Log.i("Password: ", mPass[0]);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confermaRegister:
                new AddNewUser().execute();

                /*if(username.getText().toString().equals("")){
                username.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                }
                else{
                    username.getBackground().setColorFilter(getResources().getColor(R.id.usernameRegister), PorterDuff.Mode.SRC_ATOP);
                }
                if(password.getText().toString().equals("")){
                    password.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                }
                else{
                    password.getBackground().setColorFilter(getResources().getColor(R.id.passwordRegister), PorterDuff.Mode.SRC_ATOP);
                }
                if(nome.getText().toString().equals("")){
                    nome.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                }
                else{
                    nome.getBackground().setColorFilter(getResources().getColor(R.id.nomeRegister), PorterDuff.Mode.SRC_ATOP);
                }
                if(cognome.getText().toString().equals("")){
                    cognome.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                }
                else{
                    cognome.getBackground().setColorFilter(getResources().getColor(R.id.cognomeRegister), PorterDuff.Mode.SRC_ATOP);
                }
                if(citta.getText().toString().equals("")){
                    citta.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                }
                else{
                    citta.getBackground().setColorFilter(getResources().getColor(R.id.cittaRegister), PorterDuff.Mode.SRC_ATOP);
                }
                if(email.getText().toString().equals("")){
                    email.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                }
                else{
                    email.getBackground().setColorFilter(getResources().getColor(R.id.emailRegister), PorterDuff.Mode.SRC_ATOP);
                }
                if(frase.getText().toString().equals("")){
                    frase.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                }
                else{
                    frase.getBackground().setColorFilter(getResources().getColor(R.id.fraseRegister), PorterDuff.Mode.SRC_ATOP);
                }*/

                break;

            case R.id.annullaRegister:
                Intent in = new Intent(RegisterActivity.this, WelcomeActivity.class);
                finish();
                startActivity(in);
                break;

            case R.id.updateFoto:
                Intent i = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 0);
                break;

            case R.id.updateBackground:
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                //if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                ImageView imageView = (ImageView) findViewById(R.id.profile_image);
                imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                //}
                break;
            case 1:
                //if (requestCode == RESULT_LOAD_BACKGROUND_IMAGE && resultCode == RESULT_OK && null != data) {
                Uri selectedImage1 = data.getData();
                String[] filePathColumn1 = {MediaStore.Images.Media.DATA};

                Cursor cursor1 = getContentResolver().query(selectedImage1,
                        filePathColumn1, null, null, null);
                cursor1.moveToFirst();

                int columnIndex1 = cursor1.getColumnIndex(filePathColumn1[0]);
                String picturePath1 = cursor1.getString(columnIndex1);
                cursor1.close();

                ImageView imageView1 = (ImageView) findViewById(R.id.background_image);
                imageView1.setImageBitmap(BitmapFactory.decodeFile(picturePath1));
                //}
                break;
        }
    }


    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.profile_image);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }*/

    protected void fieldIsEmpty(String field, EditText param){

        Log.i("parametro field: ", field);
        if(field.isEmpty()){
            String stringIsEmpty = String.valueOf(field.toString().isEmpty());
            Log.i("isEmpty: ", stringIsEmpty);
            param.setError("Il campo non può essere vuoto");
        }

    }

    class AddNewUser extends AsyncTask<String, String, String> {
        String mMail = email.getText().toString().trim();
        String mFirstname = name.getText().toString().trim();
        String mLastname = surname.getText().toString().trim();
        String mUser = username.getText().toString().trim();
        String mPass = password.getText().toString().trim();
        String mTown = city.getText().toString().trim();
        String mRecoveryPasswordPhrase = phrase.getText().toString().trim();

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            pDialog = new ProgressDialog(RegisterActivity.this);
            pDialog.setMessage("Add user..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String doInBackground(String... args) {
            Log.i("","Sono in RegisterActivity in doInBackground");

            boolean checkConnection = checkNetworkConnection();
            Log.i("checkConnection Reg", String.valueOf(checkConnection));

            boolean checkURL = checkURLConnection();
            Log.i("checkURL Reg", String.valueOf(checkURL));

            sessionManager = new SessionManager(getApplicationContext());

            if(checkConnection && checkURL){
                Log.i("Sono entrato ", "nella condizione OK connessione e OK server");
                try {
                    int mSuccess = registerModel.getRegistration(mUser, mPass, mTown, mMail, mRecoveryPasswordPhrase, mFirstname, mLastname);

                    if (mSuccess == 1) {
                        Log.i("Success 1: ", "Registrazione OK");
                        sessionManager.setLogin(true);
                        Log.i("L'utente ha ","effettuato la login dalla registrazione");
                        Intent i = new Intent(RegisterActivity.this, GetAroundActivity.class);
                        finish();
                        startActivity(i);
                        RegisterActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),REGISTRATION_OK ,Toast.LENGTH_LONG).show();
                            }
                        });
                        //return json.getString(TAG_MESSAGE);
                    } else if(mSuccess == 0){
                        Log.i("Success 0: ", "Username e password già esistenti");
                        RegisterActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),USERNAME_AND_PASSWORD_ALREADY_EXSISTS ,Toast.LENGTH_LONG).show();
                            }
                        });
                        // failed to create product
                        //Log.d("Registration Failure!Insert ", json.getString(TAG_MESSAGE));
                        //return json.getString(TAG_MESSAGE);
                    }
                    else if(mSuccess == 2){
                        Log.i("Success 2: ", "Name KO");
                        RegisterActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), NAME_KO,Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else if(mSuccess == 3){
                        Log.i("Success 3: ", "Surname KO");
                        RegisterActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), SURNAME_KO,Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else if(mSuccess == 4){
                        Log.i("Success 4: ", "Email KO");
                        RegisterActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),EMAIL_KO ,Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else if(mSuccess == 5){
                        Log.i("Success 5: ", "User KO");
                        RegisterActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), USER_KO,Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else if(mSuccess == 6){
                        Log.i("Success 6: ", "Password Characters KO");
                        RegisterActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), PASSWORD_CHARACTERS_KO,Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else if(mSuccess == 7){
                        Log.i("Success 7: ", "Password at least one number");
                        RegisterActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), PASSWORD_AT_LEAST_ONE_NUMBER,Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else if(mSuccess == 8){
                        Log.i("Success 8: ", "Password at least one capital letter");
                        RegisterActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), PASSWORD_AT_LEAST_ONE_CAPITAL_LETTER,Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else if(mSuccess == 9){
                        Log.i("Success 9: ", "Password at least one lower letter");
                        RegisterActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), PASSWORD_AT_LEAST_ONE_LOWER_LETTER,Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else if(mSuccess == 10){
                        Log.i("Success 10: ", "Registration KO");
                        RegisterActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), REGISTRATION_KO,Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else{
                        Log.i("Success 11: ", "Missing fields");
                        RegisterActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),MISSING_FIELDS ,Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if(checkConnection && !checkURL){
                Log.i("Sono entrato ", "nella condizione OK connessione e KO server");
                RegisterActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), ERROR_SERVER, Toast.LENGTH_LONG).show();
                    }
                });
            }else{
                Log.i("Sono entrato ", "nella condizione KO connessione");
                RegisterActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), ERROR_MESSAGE, Toast.LENGTH_LONG).show();
                    }
                });
            }


            return null;
        }


        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null) {
                Toast.makeText(RegisterActivity.this, file_url, Toast.LENGTH_LONG).show();
            }

        }
    }

}

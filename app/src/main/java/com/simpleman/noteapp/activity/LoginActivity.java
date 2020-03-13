package com.simpleman.noteapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.simpleman.noteapp.R;
import com.simpleman.noteapp.api.ApiClient;
import com.simpleman.noteapp.api.ApiInterface;
import com.simpleman.noteapp.model.User;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    //Define OBJ EditText
    EditText email, password;

    //Define OBJ ProgressDialog
    ProgressDialog pDialog;

    //Var
    String txtEmail;
    String txtPassword;
    String mUsername, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Set ProgressDialog
        pDialog = new ProgressDialog(LoginActivity.this);
        pDialog.setMessage("Please Wait");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);

    }

    public void btnLogin(View view) {
        //Call OBJ EditText
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        //Empty EditText when button login pressed

        //Get text from OBJ EditText
        txtEmail = email.getText().toString().trim();
        txtPassword = password.getText().toString().trim();

        //Show ProgressDialog
        pDialog.show();

        //Set Retrofit
        ApiInterface apiInterface = ApiClient.getApiCLient().create(ApiInterface.class);

        Call<List<User>> call = apiInterface.loginUser();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                email.setText("");
                password.setText("");
                pDialog.dismiss();

                List<User> userList = response.body();


                String[] username = new String[userList.size()];
                String[] password = new String[userList.size()];

                for (int i = 0; i < userList.size(); i++){
                    username[i] = userList.get(i).getUsername();
                    password[i] = userList.get(i).getPassword();

                    mUsername = username[i];
                    mPassword = password[i];

                    if (mUsername.equals(txtEmail) && mPassword.equals(txtPassword)){
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else {

                        if (i <= 0){
                            Toast.makeText(LoginActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                        }else {
                            Log.i("","Can't diplay toast anymore");
                        }
                    }
                }

                Log.i("Info data : ",""+ Arrays.toString(username) + Arrays.toString(password));
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });

    }
}

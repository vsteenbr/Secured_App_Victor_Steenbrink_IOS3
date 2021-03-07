package com.example.secured_app_victor_steenbrink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.InputMismatchException;

import io.github.cdimascio.dotenv.Dotenv;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    Button okButton,addButton;
    EditText idInput;

    String url = "https://60102f166c21e10017050128.mockapi.io/labbbank/";
    private ArrayList<Accounts> accounts;
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onStart() {
        super.onStart();
        txt = findViewById(R.id.account);
        okButton = findViewById(R.id.Ok);
        okButton.setOnClickListener(okButtonListener);
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(addButtonListener);
        idInput = findViewById(R.id.editTextID);
        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);



    }

    private void APIRequest (String url, int id){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiCall myApiCall = retrofit.create(ApiCall.class);
        Call<Accounts> call =  myApiCall.getData(id);

        call.enqueue(new Callback<Accounts>() {
            @Override
            public void onResponse(Call<Accounts> call, Response<Accounts> response) {


                //get the data into the textview
                String json = "";
                json =  "Id = " + response.body().getId() +
                        "\nAccount Name = " + response.body().getAccountName() +
                        "\nAmount = " + response.body().getAmount() +
                        "\nIBAN = " + response.body().getIban() +
                        "\nCurrency = " + response.body().getCurrency();

                txt.append(json);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("accounts"+ id ,json);
                editor.apply();
                Toast.makeText(getApplicationContext(), "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Accounts> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Error with connection, fetching data offline mode", Toast.LENGTH_SHORT).show();
                String data = sharedPreferences.getString("accounts"+ id, null);
                if (data != null) {
                    txt.append(data);
                }
                else{
                    txt.append("Could not find account");
                }

            }
        });


    }


    private int Getid() {

        String a = idInput.getText().toString();
        int id = 0;
        try {
            id = Integer.parseInt(a);
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(),""+a+" is not a number please try again", Toast.LENGTH_LONG).show();
        }
        return id;
    }

    private View.OnClickListener addButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(getApplicationContext(), AddAccount.class);

            startActivity(intent);


        }
    };


    private View.OnClickListener okButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            txt.setText("");
            int id= Getid();
            APIRequest(url,id);

        }
    };

}
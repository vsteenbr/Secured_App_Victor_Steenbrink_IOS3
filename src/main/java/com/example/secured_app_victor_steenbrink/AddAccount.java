package com.example.secured_app_victor_steenbrink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddAccount extends AppCompatActivity {

    EditText ETname,ETamount,ETiban,ETcurrency;
    Button saveButton, backButton;
    TextView printID;
    protected static int min = 26;
    protected static int max = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        ETname = findViewById(R.id.editTextName);
        ETamount = findViewById(R.id.editTextAmount);
        ETiban = findViewById(R.id.editTextIban);
        ETcurrency = findViewById(R.id.editTextCurrency);
        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(saveButtonListener);
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(backButtonListener);
        printID = findViewById(R.id.printId);



    }

    private View.OnClickListener saveButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = (int)(Math.random() * (max - min + 1) + min); // generating a random id from 30 to 1000
            String accountName = ETname.getText().toString();
            String b = ETamount.getText().toString();
            double amount = Double.valueOf(b);
            String iban = ETiban.getText().toString();
            String currency = ETcurrency.getText().toString();

            if (!accountName.isEmpty() && !iban.isEmpty() && !currency.isEmpty()) { //to check if all the fields are filled

                Accounts account = new Accounts(id, accountName, amount, iban, currency);
                String data = account.toString();

                SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("accounts" + id, data);
                editor.apply();
                printID.setText("ACCOUNT SAVED  ! YOUR NEW ACCOUNT ID IS : "+ id);

            }
            else {
                Toast.makeText(getApplicationContext(), "Please fill all fields ", Toast.LENGTH_SHORT).show();
            }

        }
    };

    private View.OnClickListener backButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
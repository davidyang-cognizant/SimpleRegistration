package com.example.simpleregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText email_et;
    private EditText password_et;
    private EditText first_name;
    private EditText last_name;
    private Spinner country_s;
    private RadioButton male_btn;
    private RadioButton female_btn;
    private String gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email_et = findViewById(R.id.email_et);
        password_et = findViewById(R.id.password_et);
        country_s = findViewById(R.id.country);
        male_btn = findViewById(R.id.male_btn);
        female_btn = findViewById(R.id.female_btn);
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        setUpCountryTV();
    }

    private void setUpCountryTV() {
        ArrayAdapter<CharSequence> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.countries));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country_s.setAdapter(arrayAdapter);
        country_s.setSelection(0);
        country_s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(), country_s.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void handleRegister(View view) {

        boolean valid = validate();
        if (valid) {
            String firstName = first_name.getText().toString();
            String lastName = last_name.getText().toString();
            String email = email_et.getText().toString();
            String password = password_et.getText().toString();
            String country = country_s.getSelectedItem().toString();
            User user = new User(firstName, lastName, email, password, country, gender);
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Error in form", Toast.LENGTH_SHORT).show();
        }

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        Log.d("Main", String.valueOf(view.getId()));
        switch (view.getId()) {
            case R.id.male_btn:
                if(checked){
                    gender = ((RadioButton) view).getText().toString();
                    Toast.makeText(this, gender , Toast.LENGTH_SHORT).show();

                }
            case R.id.female_btn:
                if(checked){
                    gender = ((RadioButton) view).getText().toString();
                    Toast.makeText(this,gender, Toast.LENGTH_SHORT).show();
                }
        }
    }

    private boolean validate() {
        if (first_name.getText().toString().equals("")) {
            return false;
        } else if (last_name.getText().toString().equals("")) {
            return false;
        } else if (email_et.getText().toString().equals("")) {
            return false;
        } else if (password_et.getText().toString().equals("")) {
            return false;
        } else if (country_s.getSelectedItem() == null) {
            Log.d("validate", "country");
            return false;
        } else if (!male_btn.isChecked() && !female_btn.isChecked()) {
            return false;
        } else {
            return true;
        }
    }
}
package com.example.vhukacodingchallengeme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.common.collect.Range;
import com.rengwuxian.materialedittext.MaterialEditText;
//d47hi2ohqdbdizf4uk53j5nw27hrqqihr3v2qz3ajvlg24mvhila
import com.basgeekball.awesomevalidation.AwesomeValidation;



public class MainActivity extends AppCompatActivity {


    private MaterialEditText name, email, phone_number, age;
    private RadioGroup radioGroup;
    private RadioButton gender;
    private CheckBox terms_and_conditions;
    private Button submit;
    private AwesomeValidation awesomeValidation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);


        initViews();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                radioGroup = findViewById(R.id.radioGroup);
                int selectedGender = radioGroup.getCheckedRadioButtonId();
                gender = radioGroup.findViewById(selectedGender);


                if (gender==null){

                    Toast.makeText(getApplicationContext(), " You did not select Your Gender", Toast.LENGTH_LONG).show();

                } else if (!terms_and_conditions.isChecked()) {

                    Toast.makeText(getApplicationContext(), " You must agree to the terms and conditions", Toast.LENGTH_LONG).show();

                } else if(awesomeValidation.validate()){

                    Intent intent = new Intent(MainActivity.this, MainMenu.class);

                    age = findViewById(R.id.age);
                    String getAge = age.getText().toString().trim();
                    String getUsername = name.getText().toString().trim();
                    intent.putExtra("age", getAge);
                    intent.putExtra("username", getUsername);
                    startActivity(intent);

                }




            }
        });

    }




    private void initViews(){


        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone_number = findViewById(R.id.phone_number);
        age = findViewById(R.id.age);
        terms_and_conditions = findViewById(R.id.terms_conditions);
        submit = findViewById(R.id.submit);

        addValidationToViews();

    }



    private void addValidationToViews() {

        awesomeValidation.addValidation(this, R.id.name, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        awesomeValidation.addValidation(this, R.id.email, Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        awesomeValidation.addValidation(this, R.id.phone_number, "^[+]?[0-9]{10,12}$", R.string.invalid_phonenumber);
        awesomeValidation.addValidation(this, R.id.age, Range.closed(18, 120), R.string.invalid_age);


    }





}

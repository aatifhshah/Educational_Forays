package com.xerorex.buvit;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AddUserActivity extends AppCompatActivity {

    private boolean submitButtonState = false;
    private boolean fname = false;
    private boolean lname = false;
    private boolean email = false;

    private EditText firstNameField = null;
    private EditText lastNameField = null;
    private EditText emailAddressField = null;

    //OnCreate method loads display screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        configureTextFields();
    }

    //Sets hints for text fields in activity and adds listeners to check for change in text fields
    private void configureTextFields() {

        enableSubmitButton(fname, lname, email);

        firstNameField = (EditText) findViewById(R.id.firstNameField);
        lastNameField = (EditText) findViewById(R.id.lastNameField);
        emailAddressField = (EditText) findViewById(R.id.emailAddress);

        firstNameField.setHint("First Name");
        lastNameField.setHint("Last Name");
        emailAddressField.setHint("Email");

        firstNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable text) {
                if (text.length() != 0) {
                    fname = true;
                }
                else {
                    fname = false;
                }

                enableSubmitButton(fname, lname, email);
            }
        });

        lastNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable text) {
                if(text.length() != 0)
                    lname = true;
                else{
                    lname = false;
                }

                enableSubmitButton(fname, lname, email);
            }
        });

        emailAddressField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable text) {
                if (text.length() != 0)
                    email = true;
                else {
                    email = false;
                }

                enableSubmitButton(fname, lname, email);
            }
        });

    }


    //Enables Submit button of this activity to add a new user to database
    private void enableSubmitButton(boolean fname, boolean lname, boolean email){

        ImageButton submitButton = (ImageButton) findViewById(R.id.addUserSubmitButton);

        //Change if statements to confirm name and email are real inputs
        if(fname && lname && email) {
            Drawable submitButtonIcon = getResources().getDrawable(R.drawable.submit_true);
            submitButton.setImageDrawable(submitButtonIcon);

            //Enter Submit button functionality here

            submitButtonState = true;
        }
        else{
            Drawable submitButtonIcon = getResources().getDrawable(R.drawable.submit_false);
            submitButton.setImageDrawable(submitButtonIcon);

            submitButtonState = false;
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = firstNameField.getText().toString();
                String lastName = lastNameField.getText().toString();
                String email = emailAddressField.getText().toString();

                if(checkName(firstName) && checkName(lastName) && checkEmail(email)){
                    //Add User profile to database
                    int newCard = 0;
                    UserProfile newUserProfile = new UserProfile(firstName, lastName, email, newCard);


                }
                else if(!checkName(firstName) || !checkName(lastName) || !checkEmail(email)){
                    //Reject input for invalid names
                    if(!checkName(firstName)){
                        Toast.makeText(getApplicationContext(), "Invalid First Name", Toast.LENGTH_SHORT);
                    }
                    if(!checkName(lastName)){
                        Toast.makeText(getApplicationContext(), "Invalid Last Name", Toast.LENGTH_SHORT);
                    }
                    if(!checkEmail(email)){
                        Toast.makeText(getApplicationContext(), "Invalid Email Address", Toast.LENGTH_SHORT);
                    }
                }

            }
        });

    }

    //Confirms name only contains letters and thus rejects strings containing special characters
    private boolean checkName(String name){

        for (int i = 0; i < name.length(); i++) {

            char c = name.charAt(i);
            if (Character.isLetter(c)) {
            }
            else {
                return false;
            }
        }

        return true;

    }

    //Confirms that the input is an email and rejects strings that aren't
    private boolean checkEmail(String email){

        int addressLength = email.length();
        String lastFour = "";
        boolean atExists = false;

        for (int i = addressLength - 4; i < addressLength; i++) {
            char character = email.charAt(i);
            lastFour += character;
        }


        for (int i = 0; i < addressLength; i++) {
            char character = email.charAt(i);
            if(character == '@')
                atExists = true;
        }

        if(lastFour.equals(".com") && atExists)
            return true;

        return false;
    }
}




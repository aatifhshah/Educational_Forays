package com.xerorex.buvit;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class UserProfileActivity extends AppCompatActivity {

    private RelativeLayout userProfileLayout;
    private GridLayout gridLayout;

    private int WRAP_CONTENT = RelativeLayout.LayoutParams.WRAP_CONTENT;
    private final int MAX_NUMBER_OF_PUNCHES = 9;
    private ImageButton[] arrayOfButtons;
    private ImageButtonListener buttonListener;
    int padding;

    UserProfile userProfile;
    private int usersPunches = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userProfile = getUserProfile();

        addProfileTitle();
        addPunchMarks();
        addSaveButton();
    }

    //Gets the passed user profile from the search activity
    private UserProfile getUserProfile(){
        UserProfile userProfile = (UserProfile) getIntent().getSerializableExtra("ChosenUserProfile");
        usersPunches = userProfile.getNumberOfPunches();
        return userProfile;
    }

    //Adds the name of the user to the title of the activity
    private void addProfileTitle() {
        TextView activityTitle = (TextView) findViewById(R.id.user_profile_acitivity_username);
        activityTitle.setText((CharSequence) userProfile.getFull_name());
    }

    //Adds the punch marks available in the UserProfileActivity to show how many the user orginally had and allows to add more
    private void addPunchMarks() {

        //Relative layout from xml file reference
        userProfileLayout = (RelativeLayout) findViewById(R.id.user_profile_acitivity_layout);
        arrayOfButtons = new ImageButton[MAX_NUMBER_OF_PUNCHES];

        //ButtonLayoutParams params configuration
        RelativeLayout.LayoutParams buttonLayoutParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        buttonLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        //Gridlayout params configuration
        RelativeLayout.LayoutParams gridLayoutParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        gridLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        gridLayoutParams.addRule(RelativeLayout.BELOW, R.id.search_user_activity_title);

        //GridLayout setup
        gridLayout = new GridLayout(getApplicationContext());
        gridLayout.setColumnCount(5);
        gridLayout.setRowCount(2);
        gridLayout.setLayoutParams(gridLayoutParams);
        gridLayout.setId(201);

        userProfileLayout.addView(gridLayout);

        int counter = 100;
        padding = Math.round((Float) getResources().getDimension(R.dimen.user_profile_activity_button_padding));

        buttonListener = new ImageButtonListener();

        ImageButton thisButton;

        //For loop populating gridlayout with buttons
        for (int i = 0; i < usersPunches; i++) {

            thisButton = new ImageButton(this);
            arrayOfButtons[i] = thisButton;

            thisButton.setPadding(padding, padding, padding, padding);
            thisButton.setLayoutParams(buttonLayoutParams);
            thisButton.setBackgroundColor(Color.WHITE);

            thisButton.setImageResource(R.drawable.user_profile_punch_pressed);
            thisButton.setTag(R.drawable.user_profile_punch_pressed);
            thisButton.setOnClickListener(buttonListener);

            thisButton.setId(counter++);
            gridLayout.addView(thisButton);
        }


        //For loop populating gridlayout with buttons
        for (int i = usersPunches; i < MAX_NUMBER_OF_PUNCHES; i++) {

            thisButton = new ImageButton(this);
            arrayOfButtons[i] = thisButton;

            thisButton.setPadding(padding, padding, padding, padding);
            thisButton.setLayoutParams(buttonLayoutParams);
            thisButton.setBackgroundColor(Color.WHITE);

            thisButton.setImageResource(R.drawable.user_profile_punch_unpressed);
            thisButton.setTag(R.drawable.user_profile_punch_unpressed);
            thisButton.setOnClickListener(buttonListener);

            thisButton.setId(counter++);
            gridLayout.addView(thisButton);
        }

        //Adding last image button of the free punch
        ImageButton freePunch = new ImageButton(this);

        freePunch.setPadding(padding, padding, padding, padding);
        freePunch.setLayoutParams(buttonLayoutParams);
        freePunch.setBackgroundColor(Color.WHITE);

        freePunch.setImageResource(R.drawable.user_profile_punch_free_false);
        freePunch.setTag(R.drawable.user_profile_punch_free_false);
        freePunch.setOnClickListener(buttonListener);

        freePunch.setId(counter++);
        gridLayout.addView(freePunch);


    }

    //Private class for the ImageButtonListener implementing OnClickListener
    private class ImageButtonListener implements View.OnClickListener {

        boolean freePunch = false;

        public void onClick(View view) {

            int button = view.getId();
            ImageButton imageButton = (ImageButton) view;

            switch (button) {
                case 109:
                    if (view.getTag().equals(R.drawable.user_profile_punch_free_false) && usersPunches == MAX_NUMBER_OF_PUNCHES) {
                        imageButton.setImageResource(R.drawable.user_profile_punch_free_true);
                        imageButton.setTag(R.drawable.user_profile_punch_free_true);
                        imageButton.setOnClickListener(buttonListener);
                        freePunch = true;
                    } else if (view.getTag().equals(R.drawable.user_profile_punch_free_true) && usersPunches == MAX_NUMBER_OF_PUNCHES) {
                        imageButton.setImageResource(R.drawable.user_profile_punch_free_false);
                        imageButton.setTag(R.drawable.user_profile_punch_free_false);
                        imageButton.setOnClickListener(buttonListener);
                        freePunch = false;
                    }
                    break;

                default:
                    if (view.getTag().equals(R.drawable.user_profile_punch_unpressed) && freePunch != true) {
                        imageButton.setImageResource(R.drawable.user_profile_punch_pressed);
                        imageButton.setTag(R.drawable.user_profile_punch_pressed);
                        imageButton.setOnClickListener(buttonListener);
                        usersPunches++;
                    } else if (view.getTag().equals(R.drawable.user_profile_punch_pressed) && freePunch != true) {
                        imageButton.setImageResource(R.drawable.user_profile_punch_unpressed);
                        imageButton.setTag(R.drawable.user_profile_punch_unpressed);
                        imageButton.setOnClickListener(buttonListener);
                        usersPunches--;
                        break;
                    }

            }

        }

    }

    //Adds an account save button which will update the userProfile object
    private void addSaveButton() {

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //Uses id that i set because the gridLayout.getId() didnt work for some reason
        layoutParams.addRule(RelativeLayout.BELOW, 201);

        ImageButton saveButton = new ImageButton(getApplicationContext());
        saveButton.setImageResource(R.drawable.user_profile_save_button);
        saveButton.setTag(R.drawable.user_profile_save_button);
        saveButton.setLayoutParams(layoutParams);
        saveButton.setBackgroundColor(Color.WHITE);
        saveButton.setPadding(padding, padding, 30, padding);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userProfile.setNumberOfPunches(usersPunches);
            }
        });

        userProfileLayout.addView(saveButton);

    }

}

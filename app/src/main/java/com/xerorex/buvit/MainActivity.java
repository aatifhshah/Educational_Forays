package com.xerorex.buvit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureMainButtons();

    }

    //Populates the main page of app and starting page and adds listeners to guide the user to next display activities
    private void configureMainButtons() {

        final ImageButton addUser = (ImageButton) findViewById(R.id.addUserButton);
        final ImageButton searchUser = (ImageButton) findViewById(R.id.searchUserButton);
        final ImageButton removeUser = (ImageButton) findViewById(R.id.removeUserButton);

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addUserActivity = new Intent(view.getContext(), AddUserActivity.class);
                startActivity(addUserActivity);
            }
        });

        searchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchUserActivity = new Intent(view.getContext(), SearchUserActivity.class);
                startActivity(searchUserActivity);
            }
        });

        removeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent removeUserActivity = new Intent(view.getContext(), RemoveUserActivity.class);
                startActivity(removeUserActivity);
            }
        });
    }


}

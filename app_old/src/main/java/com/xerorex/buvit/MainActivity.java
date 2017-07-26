package com.xerorex.buvit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateMain();

        setContentView(R.layout.activity_main);

        ImageButton addUser = (ImageButton) findViewById(R.id.imageButton);
        addUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Perform action on click
                Intent intent = new Intent(view.getContext(), AddUserActivity.class);
                startActivity(intent);
            }
        });


    }






    private void populateMain() {


    }
}

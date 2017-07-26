package com.xerorex.buvit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);

        configureSearchBar();
        populateFoundUsersList();
    }

    //Adds a hint to the search bar
    private void configureSearchBar() {

        EditText searchBar = (EditText) findViewById(R.id.search_user_activity_search_bar);
        searchBar.setHint("Search User...");
    }



    //Creates the list view for the display and adds the content by using a passed arraylist
    private void populateFoundUsersList() {

        ListView foundUserList = (ListView) findViewById(R.id.search_user_activity_found_users);


        final ArrayList<UserProfile> foundUsers = new ArrayList<>();
        ArrayList<String> foundUserString = new ArrayList<>();

        //Fill arraylist with random stuff
        foundUsers.add(new UserProfile("Henry", "Smith", "", 0));
        foundUsers.add(new UserProfile("Richard", "Smith", "", 0));
        foundUsers.add(new UserProfile("Chris", "Smith", "", 0));

        for(UserProfile user : foundUsers){
            String name = user.getFirst_name() + " " + user.getLast_name();
            foundUserString.add(name);
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.found_users_list_content, foundUserString);

        //Populates the list view with the userProfile ArrayList
        foundUserList.setAdapter(adapter);
        foundUserList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                UserProfile userProfile = foundUsers.get(position);

                Intent startUserActvity = new Intent(getApplicationContext(), UserProfileActivity.class);
                startUserActvity.putExtra("ChosenUserProfile", userProfile);
                startActivity(startUserActvity);
            }
        });



    }

}

package com.xerorex.buvit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class RemoveUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_user);
        configureView();
        populateFoundUsersList();
    }

    //Sets hint in search bar
    private void configureView() {

        //Search bar related methods
        EditText searchBar = (EditText) findViewById(R.id.remove_user_activity_search_bar);
        searchBar.setHint("Search User...");

    }

    //Shows the users available from passed arraylist
    private void populateFoundUsersList() {

        ListView foundUserList = (ListView) findViewById(R.id.remove_user_activity_found_users);


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


        foundUserList.setAdapter(adapter);
        foundUserList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                UserProfile userProfile = foundUsers.get(position);

                AlertDialog checkRemoveUser = AskOption();
                checkRemoveUser.show();
            }
        });

    }

    //Creates AlertDialog box to confirm the removal of a userProfile
    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Remove User")
                .setMessage("Deleting user will remove the user from the store's user directory, press \"delete\" to confirm.")

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        dialog.dismiss();
                    }

                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }

}

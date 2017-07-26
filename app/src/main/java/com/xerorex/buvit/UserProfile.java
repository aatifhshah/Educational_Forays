package com.xerorex.buvit;

import java.io.Serializable;

/**
 * Created by Abdullah Al-Shehabi on 11/21/2015.
 */
public class UserProfile implements Serializable{

    private String first_name;
    private String last_name;
    private String email_address;
    private int numberOfPunches;

    public UserProfile(String first, String last, String email, int numOfPunches){
        first_name = first;
        last_name = last;
        email_address = email;
        numberOfPunches = numOfPunches;
    }

    public String getFirst_name(){
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail_address() {
        return email_address;
    }

    public String getFull_name(){
        return first_name + " " + last_name;
    }

    public void setNumberOfPunches(int numOfPunches){
        numberOfPunches = numOfPunches;
    }

    public int getNumberOfPunches(){
        return numberOfPunches;
    }
}

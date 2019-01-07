package com.aldi.tambakudangfinal;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by harislightace on 30/10/2018.
 */
@IgnoreExtraProperties
public class Admin {

    public String username;
    public String email;

    public Admin() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Admin(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
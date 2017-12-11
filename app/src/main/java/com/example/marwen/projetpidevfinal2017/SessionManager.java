package com.example.marwen.projetpidevfinal2017;

/**
 * Created by chratah on 13/11/2017.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import java.util.HashMap;

public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "AndroidHiveLogin";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }



    public void UserDetail(String email){


        editor.putString("email",email);

        // commit changes
        editor.commit();
    }

    public HashMap<String, String> getUserDetail(){
        HashMap<String, String> user = new HashMap<String, String>();

        // user email id
        user.put("email", pref.getString("email",null));

        // return user
        return user;
    }



    public void setGroup(String group){


        editor.putString("group",group);

        // commit changes
        editor.commit();
    }

    public HashMap<String, String> getGroup(){
        HashMap<String, String> user = new HashMap<String, String>();

        // user email id
        user.put("group", pref.getString("group",null));

        // return user
        return user;
    }

























    public void setName(String name){


        editor.putString("name",name);


        // commit changes
        editor.commit();
    }

    public HashMap<String, String> getName(){
        HashMap<String, String> user = new HashMap<String, String>();

        // user email id
        user.put("name", pref.getString("name",null));

        // return user
        return user;
    }








    public void UserPassword(String Password){


        editor.putString("Password",Password);

        // commit changes
        editor.commit();
    }

    public HashMap<String, String> getUserPassword(){
        HashMap<String, String> user = new HashMap<String, String>();

        // user email id
        user.put("Password", pref.getString("Password",null));

        // return user
        return user;
    }



















    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }
}

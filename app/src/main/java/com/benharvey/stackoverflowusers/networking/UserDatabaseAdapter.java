package com.benharvey.stackoverflowusers.networking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.benharvey.stackoverflowusers.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserDatabaseAdapter {
    static final String DATABASE_NAME = "database.db";
    String ok="OK";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_CREATE = "create table USER( ID integer primary key autoincrement,NAME  text,GOLD  text,SILVER  text," +
            "BRONZE  text,GRAVATAR  text,AGE  text,LOCATION  text); ";

    public static SQLiteDatabase db;
    private final Context context;
    private static DataBaseHelper dbHelper;

    public  UserDatabaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  UserDatabaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();        return this;
    }
    public void close()
    {
        db.close();
    }
    // method returns an Instance of the Database
    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }
    // method to insert a record in Table
    public String insertEntry(String name,String gold,String silver,String bronze, String gravatar, String age, String loc)
    {
        try {
            ContentValues newValues = new ContentValues();
            // Assign values for each column.
            newValues.put("NAME", name);
            newValues.put("GOLD", gold);
            newValues.put("SILVER", silver);
            newValues.put("BRONZE", bronze);
            newValues.put("GRAVATAR", gravatar);
            newValues.put("AGE", age);
            newValues.put("LOCATION", loc);

            // Insert the row into your table
            db = dbHelper.getWritableDatabase();
            long result=db.insert("USER", null, newValues);
            System.out.print(result);
            Toast.makeText(context, "User Info Saved", Toast.LENGTH_LONG).show();
        }catch(Exception ex) {
            System.out.println("Exceptions " +ex);
            Log.e("Note", "One row entered");
        }
        return ok;
    }
    // method to delete a Record of UserName
    public int deleteEntry(String UserName)
    {
        String where="NAME=?";
        int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{UserName}) ;
        Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }
    // method to get the password  of userName
    public User getSingleEntry(String userName)
    {
        db=dbHelper.getReadableDatabase();
        Cursor cursor=db.query("USER", null, "NAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
            return null;
        cursor.moveToFirst();

        User returnUser = new User();
        returnUser.setDisplay_name(cursor.getString(cursor.getColumnIndex("NAME")));
        returnUser.setBadge_counts(cursor.getString(cursor.getColumnIndex("GOLD")),
                cursor.getString(cursor.getColumnIndex("SILVER")),
                        cursor.getString(cursor.getColumnIndex("BRONZE")));
        returnUser.setProfile_image(cursor.getString(cursor.getColumnIndex("GRAVATAR")));
        returnUser.setAge(cursor.getString(cursor.getColumnIndex("AGE")));
        returnUser.setLocation(cursor.getString(cursor.getColumnIndex("LOCATION")));

        return returnUser;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        //bygg query
        String query = "SELECT * FROM " + "USER";

        //fa referens
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        //iterera och bygg och lagg till
        User nextUser = null;
        if (cursor.moveToFirst()) {
            do {
                nextUser = new User();
                nextUser.setDisplay_name(cursor.getString(1));
                nextUser.setBadge_counts(cursor.getString(2),cursor.getString(3),cursor.getString(4));

                nextUser.setProfile_image(cursor.getString(5));
                nextUser.setAge(cursor.getString(6));
                nextUser.setLocation(cursor.getString(7));

                //lagg till
                users.add(nextUser);
            } while (cursor.moveToNext());
        }
        cursor.close();
        Log.d("getAllUsers()", users.toString());
        return users;
    }
}
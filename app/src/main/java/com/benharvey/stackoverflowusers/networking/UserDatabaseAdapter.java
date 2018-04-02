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
    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "USER";
    private static final String NAME_COLUMN = "NAME";
    private static final String GOLD_COLUMN = "GOLD";
    private static final String SILVER_COLUMN = "SILVER";
    private static final String BRONZE_COLUMN = "BRONZE";
    private static final String GRAVATAR_COLUMN = "GRAVATAR";
    private static final String AGE_COLUMN = "AGE";
    private static final String LOCATION_COLUMN = "LOCATION";

    static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "( ID integer primary key autoincrement,"
            + NAME_COLUMN + "  text,"
            + GOLD_COLUMN + "  text,"
            + SILVER_COLUMN + "  text,"
            + BRONZE_COLUMN + "  text,"
            + GRAVATAR_COLUMN + "  text,"
            + AGE_COLUMN + "  text,"
            + LOCATION_COLUMN + "  text); ";

    private static SQLiteDatabase db;
    private final Context context;
    private static DataBaseHelper dbHelper;

    public  UserDatabaseAdapter(Context c)
    {
        context = c;
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
    public boolean insertEntry(String name,String gold,String silver,String bronze, String gravatar, String age, String loc)
    {
        try {
            ContentValues newValues = new ContentValues();
            // Assign values for each column.
            newValues.put(NAME_COLUMN, name);
            newValues.put(GOLD_COLUMN, gold);
            newValues.put(SILVER_COLUMN, silver);
            newValues.put(BRONZE_COLUMN, bronze);
            newValues.put(GRAVATAR_COLUMN, gravatar);
            newValues.put(AGE_COLUMN, age);
            newValues.put(LOCATION_COLUMN, loc);

            // Insert the row into your table AND UPDATE DUPLICATES
            db = dbHelper.getWritableDatabase();
            long result=db.insertWithOnConflict(TABLE_NAME, null, newValues, SQLiteDatabase.CONFLICT_REPLACE);
            System.out.print(result);
        }catch(Exception ex) {
            Log.e("Database", "One row had an error while inserting");
            return false;
        }
        return true;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        String query = "SELECT * FROM " + "USER";

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        User nextUser;
        if (cursor.moveToFirst()) {
            do {
                nextUser = new User();
                nextUser.setDisplay_name(cursor.getString(1));
                nextUser.setBadge_counts(cursor.getString(2),cursor.getString(3),cursor.getString(4));

                nextUser.setProfile_image(cursor.getString(5));
                nextUser.setAge(cursor.getString(6));
                nextUser.setLocation(cursor.getString(7));

                users.add(nextUser);
            } while (cursor.moveToNext());
        }
        cursor.close();
        Log.d("getAllUsers()", users.toString());
        return users;
    }
}
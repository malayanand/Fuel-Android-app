package com.example.fuel_app.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.lang.UScript;
import android.provider.ContactsContract;
//importing from database contract
import com.example.fuel_app.data.FuelDbContract.UserRegistry;

public class FuelDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UserRegistry.db";
    public static final int DATABASE_VERSION = 1;

    public FuelDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_USERS_TABLE = "CREATE TABLE " + UserRegistry.TABLE_NAME + " ("
                + UserRegistry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UserRegistry.TABLE_FULLNAME + " TEXT NOT NULL, "
                + UserRegistry.TABLE_EMAIL + " TEXT NOT NULL, "
                + UserRegistry.TABLE_USERNAME + " TEXT NOT NULL, "
                + UserRegistry.TABLE_PASSWORD + " TEXT NOT NULL);";

        // Execute the SQL command for table creation
        db.execSQL(SQL_CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + UserRegistry.TABLE_NAME;
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

//    @Override
//    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        onUpgrade(db, oldVersion, newVersion);
//    }

    public Boolean insertData(String fullname, String email, String username, String password) {
        SQLiteDatabase users_db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("fullname", fullname);
        values.put("email", email);
        values.put("username", username);
        values.put("password", password);

        //Insert a new row into the signup database returning the ID of the row
        long result = users_db.insert(UserRegistry.TABLE_NAME, null, values);
        if(result == -1) return false;
        else return true;
    }

    public Boolean checkUserName(String username) {
        SQLiteDatabase users_db = this.getWritableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                UserRegistry._ID,
                UserRegistry.TABLE_USERNAME
        };

        //Filter results where "title" = username
        String selection = UserRegistry.TABLE_USERNAME + " = ?";
        String[] selectionArgs = {username};

        //How you want the results sorted in the resulting Cursor
        String sortOrder = UserRegistry.TABLE_USERNAME + " DESC";

        Cursor cursor = users_db.query(
                UserRegistry.TABLE_NAME,     //The table to query
                projection,                  //The array of columns to return (pass null to get all)
                selection,                   //The columns for the WHERE clause
                selectionArgs,               //The values for where clause
                null,
                null,
                sortOrder
        );
        if(cursor.getCount() > 0) return true;
        else return false;
    }

    public Boolean checkUsernameAndPassword(String username, String password) {
        SQLiteDatabase users_db = this.getWritableDatabase();

        String[] projection = {
                UserRegistry._ID,
                UserRegistry.TABLE_USERNAME,
                UserRegistry.TABLE_PASSWORD
        };

        //Filter results where "title" = username
        String selection = UserRegistry.TABLE_USERNAME + " =?" + " and " + UserRegistry.TABLE_PASSWORD + " =?";
        String[] selectionArgs = {username, password};

        //How you want the results sorted in the resulting Cursor
        String sortOrder = UserRegistry.TABLE_USERNAME + " DESC";

        Cursor cursor = users_db.query(
                UserRegistry.TABLE_NAME,     //The table to query
                projection,                  //The array of columns to return (pass null to get all)
                selection,                   //The columns for the WHERE clause
                selectionArgs,               //The values for where clause
                null,
                null,
                sortOrder
        );

        if(cursor.getCount() > 0) return true;
        else return false;
    }
}

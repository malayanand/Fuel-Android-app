package com.example.fuel_app.data;

import android.provider.BaseColumns;

public final class FuelDbContract {
    //To prevent accidental instantiating of the contract class,
    //make the constructor priavte.
    private FuelDbContract() {}

    public static class UserRegistry implements BaseColumns {
        public static final String TABLE_NAME = "users";

        public static final String _ID = BaseColumns._ID;
        public static final String TABLE_FULLNAME = "fullname";
        public static final String TABLE_EMAIL = "email";
        public static final String TABLE_USERNAME = "username";
        public static final String TABLE_PASSWORD = "password";
    }
}

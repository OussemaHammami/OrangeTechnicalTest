package com.example.orangetest.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.orangetest.object.Bored;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // creating constant variables for our database.
    // database name.
    private static final String DB_NAME = "boredDB";

    //version
    private static final int DB_VERSION = 1;

    //table name.
    private static final String TABLE_NAME = "myActivity";

    //columns name.
    private static final String KEY_COL = "key";
    private static final String ACTIVITY_COL = "activity";
    private static final String TYPE_COL = "type";
    private static final String PARTICIPANTS_COL = "participants";
    private static final String PRICE_COL = "price";
    private static final String LINK_COL = "link";
    private static final String ACCESSIBILITY_COL = "accessibility";

    // create dbhandler constructor
    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        System.out.println("dbhandler created: ");
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create sqlite query
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + KEY_COL + " TEXT PRIMARY KEY , "
                + ACTIVITY_COL + " TEXT,"
                + TYPE_COL + " TEXT,"
                + PARTICIPANTS_COL + " TEXT,"
                + PRICE_COL + " TEXT,"
                + LINK_COL + " TEXT,"
                + ACCESSIBILITY_COL + " TEXT)";

        System.out.println("query: "+query);

        //execute query
        sqLiteDatabase.execSQL(query);
    }

    public void addNewBored(String key, String activity, String type, String participants, String price, String link,  String accessibility){
        //method called to add new item to db
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_COL, key);
        values.put(ACTIVITY_COL, activity);
        values.put(TYPE_COL, type);
        values.put(PARTICIPANTS_COL, participants);
        values.put(PRICE_COL, price);
        values.put(LINK_COL, link);
        values.put(ACCESSIBILITY_COL, accessibility);

        // after adding all values we are passing content values to our table.
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        //close database after adding database.
        sqLiteDatabase.close();
    }

    public ArrayList<Bored> readBored() {
        //method for retrieving data items
        
        SQLiteDatabase db = this.getReadableDatabase();

        //creating a cursor with query to read data from database.
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<Bored> boredArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursor.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                boredArrayList.add(new Bored(   cursor.getString(0),
                                                cursor.getString(1),
                                                cursor.getString(2),
                                                cursor.getString(3),
                                                cursor.getString(4),
                                                cursor.getString(5),
                                                cursor.getString(6)));

            } while (cursor.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursor.close();
        return boredArrayList;
    }

    public void deleteBored(String key) {
        //method for deleting item.

        //creating a variable to write our database.
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        sqLiteDatabase.delete(TABLE_NAME, "key=?", new String[]{key});
        sqLiteDatabase.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //method called to check if the table exists already
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

package com.mvpdemo.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mvpdemo.datamodel.College;
import com.mvpdemo.utility.AppConstants;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static DatabaseManager instance;

    private static final String CREATE_TABLE = "create table if not exists " + AppConstants.TABLE_NAME
            + " ( " + AppConstants.COLUMN_NAME_ID + " integer primary key autoincrement not null, " +
            AppConstants.COLUMN_NAME_NAME + " text, " + AppConstants.COLUMN_NAME_ADDRESS + " text, "
            + AppConstants.COLUMN_NAME_DESCRIPTION + " text, " + AppConstants.COLUMN_NAME_COURSES + " text, "
            + AppConstants.COLUMN_NAME_EMAIL + " text, " + AppConstants.COLUMN_NAME_IMAGES + " text, " + AppConstants.COLUMN_NAME_PHONE + " text );";

    public static void initDataManager(Context context) {
        if (instance == null)
            instance = new DatabaseManager(context.getApplicationContext());
    }

    public static DatabaseManager getInstance() {
        return instance;
    }

    private DatabaseManager(Context context) {
        super(context, AppConstants.DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + AppConstants.TABLE_NAME);
        onCreate(db);
    }

    public int insert(College college) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(AppConstants.COLUMN_NAME_ID, id);
        contentValues.put(AppConstants.COLUMN_NAME_NAME, college.getName());
        contentValues.put(AppConstants.COLUMN_NAME_ADDRESS, college.getAddress());
        contentValues.put(AppConstants.COLUMN_NAME_DESCRIPTION, college.getDescription());
        contentValues.put(AppConstants.COLUMN_NAME_COURSES, college.getCoursesAvailable());
        contentValues.put(AppConstants.COLUMN_NAME_EMAIL, college.getEmail());
        contentValues.put(AppConstants.COLUMN_NAME_PHONE, college.getPhoneNumber());
        contentValues.put(AppConstants.COLUMN_NAME_IMAGES, college.getImages());
        db.insert(AppConstants.TABLE_NAME, null, contentValues);
        return 1;
    }


   // public int update(int id, String name, String address, String description, String email, String phone, String courses, String image) {
        public int update(College college){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
            contentValues.put(AppConstants.COLUMN_NAME_NAME, college.getName());
            contentValues.put(AppConstants.COLUMN_NAME_ADDRESS, college.getAddress());
            contentValues.put(AppConstants.COLUMN_NAME_DESCRIPTION, college.getDescription());
            contentValues.put(AppConstants.COLUMN_NAME_COURSES, college.getCoursesAvailable());
            contentValues.put(AppConstants.COLUMN_NAME_EMAIL, college.getEmail());
            contentValues.put(AppConstants.COLUMN_NAME_PHONE, college.getPhoneNumber());
            contentValues.put(AppConstants.COLUMN_NAME_IMAGES, college.getImages());
        int i = db.update(AppConstants.TABLE_NAME, contentValues, AppConstants.COLUMN_NAME_ID + " = " + college.getId(), null);
        return i;
    }

    public ArrayList<College> retrieve() {
        ArrayList<College> collegeName = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[]{AppConstants.COLUMN_NAME_ID, AppConstants.COLUMN_NAME_NAME, AppConstants.COLUMN_NAME_ADDRESS, AppConstants.COLUMN_NAME_DESCRIPTION, AppConstants.COLUMN_NAME_PHONE, AppConstants.COLUMN_NAME_EMAIL, AppConstants.COLUMN_NAME_COURSES, AppConstants.COLUMN_NAME_IMAGES};
        Cursor cursor = db.query(AppConstants.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToLast();
            do {
                int id = cursor.getInt(cursor.getColumnIndex(AppConstants.COLUMN_NAME_ID));
                String name = cursor.getString(cursor.getColumnIndex(AppConstants.COLUMN_NAME_NAME));
                String course = cursor.getString(cursor.getColumnIndex(AppConstants.COLUMN_NAME_COURSES));
                College bean = new College();
                bean.setName(name);
                bean.setId(id);
                bean.setCoursesAvailable(course);
                collegeName.add(bean);
            } while (cursor.moveToPrevious());
        }
        cursor.close();
        return collegeName;
    }

    public College fetchSpecificCollege(int collegeId) {
        //ArrayList<College> collegeName = new ArrayList<>();
        College bean = new College();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(AppConstants.TABLE_NAME, null, AppConstants.COLUMN_NAME_ID + "=?", new String[]{String.valueOf(collegeId)}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            int id = cursor.getInt(cursor.getColumnIndex(AppConstants.COLUMN_NAME_ID));
            String name = cursor.getString(cursor.getColumnIndex(AppConstants.COLUMN_NAME_NAME));
            String address = cursor.getString(cursor.getColumnIndex(AppConstants.COLUMN_NAME_ADDRESS));
            String description = cursor.getString(cursor.getColumnIndex(AppConstants.COLUMN_NAME_DESCRIPTION));
            String courses = cursor.getString(cursor.getColumnIndex(AppConstants.COLUMN_NAME_COURSES));
            String phone = cursor.getString(cursor.getColumnIndex(AppConstants.COLUMN_NAME_PHONE));
            String email = cursor.getString(cursor.getColumnIndex(AppConstants.COLUMN_NAME_EMAIL));
            String image = cursor.getString(cursor.getColumnIndex(AppConstants.COLUMN_NAME_IMAGES));
            bean.setId(id);
            bean.setName(name);
            bean.setAddress(address);
            bean.setDescription(description);
            bean.setCoursesAvailable(courses);
            bean.setPhoneNumber(phone);
            bean.setEmail(email);
            bean.setImages(image);
        }
        cursor.close();
        return bean;
    }

    public boolean del(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (id >= 0) {
            int rowAffected = db.delete(AppConstants.TABLE_NAME, AppConstants.COLUMN_NAME_ID + "=?", new String[]{String.valueOf(id)});
            return rowAffected == 1;
        }
        return false;
        /*else{
            db.delete(AppConstants.TABLE_NAME,null,null);
        }*/
    }

}

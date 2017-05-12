package com.example.jaker.wildlife2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jaker on 06/05/2017.
 */

public class DBHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 2;

    public DBHelper(Context context)
    {
        super(context,
                context.getDatabasePath(Contract.DB_NAME).getAbsolutePath(),
                null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //db.execSQL("DROP TABLE " + Contract.Document.TABLE_NAME);
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + Contract.Document.TABLE_NAME + " (" +
                        Contract.Document.COLNAME_id +
                        " integer NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                        Contract.Document.COLNAME_name + " varchar(32) DEFAULT NULL, " +
                        Contract.Document.COLNAME_activity + " varchar(32) DEFAULT NULL," +
                        Contract.Document.COLNAME_amount + " integer DEFAULT NULL," +
                        Contract.Document.COLNAME_category + " varchar(32) DEFAULT NULL," +
                        Contract.Document.COLNAME_location + " varchar(32) DEFAULT NULL)"
        );


        Cursor countCur = db.rawQuery("SELECT count(*) FROM " + Contract.Document.TABLE_NAME, null);
        countCur.moveToFirst();
        int count = countCur.getInt(0);
        if (count <= 0)
        {
            ContentValues cv = new ContentValues();
            cv.put(Contract.Document.COLNAME_name, "Blue Tit");
            cv.put(Contract.Document.COLNAME_activity, "Making Nest");
            cv.put(Contract.Document.COLNAME_amount, 3);
            cv.put(Contract.Document.COLNAME_category, "Bird");
            cv.put(Contract.Document.COLNAME_location, "Sutton Park");
            db.insert(Contract.Document.TABLE_NAME, null, cv);

            cv = new ContentValues();
            cv.put(Contract.Document.COLNAME_name, "Giraffe");
            cv.put(Contract.Document.COLNAME_activity, "Nurturing Young");
            cv.put(Contract.Document.COLNAME_amount, 5);
            cv.put(Contract.Document.COLNAME_category, "Mammal");
            cv.put(Contract.Document.COLNAME_location, "Zoo");
            db.insert(Contract.Document.TABLE_NAME, null, cv);

            cv = new ContentValues();
            cv.put(Contract.Document.COLNAME_name, "Monkey");
            cv.put(Contract.Document.COLNAME_activity, "Swinging in trees");
            cv.put(Contract.Document.COLNAME_amount, 2);
            cv.put(Contract.Document.COLNAME_category, "Mammal");
            cv.put(Contract.Document.COLNAME_location, "Safari Park");
            db.insert(Contract.Document.TABLE_NAME, null, cv);

            cv = new ContentValues();
            cv.put(Contract.Document.COLNAME_name, "Horse");
            cv.put(Contract.Document.COLNAME_activity, "Jumping Fences");
            cv.put(Contract.Document.COLNAME_amount, 1);
            cv.put(Contract.Document.COLNAME_category, "Mammal");
            cv.put(Contract.Document.COLNAME_location, "Park");
            db.insert(Contract.Document.TABLE_NAME, null, cv);

            cv = new ContentValues();
            cv.put(Contract.Document.COLNAME_name, "Rabbit");
            cv.put(Contract.Document.COLNAME_activity, "Running");
            cv.put(Contract.Document.COLNAME_amount, 5);
            cv.put(Contract.Document.COLNAME_category, "Mammal");
            cv.put(Contract.Document.COLNAME_location, "Park");
            db.insert(Contract.Document.TABLE_NAME, null, cv);

            cv = new ContentValues();
            cv.put(Contract.Document.COLNAME_name, "Python");
            cv.put(Contract.Document.COLNAME_activity, "Slithering");
            cv.put(Contract.Document.COLNAME_amount, 3);
            cv.put(Contract.Document.COLNAME_category, "Reptile");
            cv.put(Contract.Document.COLNAME_location, "Shop");
            db.insert(Contract.Document.TABLE_NAME, null, cv);

            cv = new ContentValues();
            cv.put(Contract.Document.COLNAME_name, "Carp");
            cv.put(Contract.Document.COLNAME_activity, "Swimming");
            cv.put(Contract.Document.COLNAME_amount, 6);
            cv.put(Contract.Document.COLNAME_category, "Fish");
            cv.put(Contract.Document.COLNAME_location, "Sea");
            db.insert(Contract.Document.TABLE_NAME, null, cv);

            cv = new ContentValues();
            cv.put(Contract.Document.COLNAME_name, "Goldfish");
            cv.put(Contract.Document.COLNAME_activity, "Swimming");
            cv.put(Contract.Document.COLNAME_amount, 3);
            cv.put(Contract.Document.COLNAME_category, "Fish");
            cv.put(Contract.Document.COLNAME_location, "Tank");
            db.insert(Contract.Document.TABLE_NAME, null, cv);

            cv = new ContentValues();
            cv.put(Contract.Document.COLNAME_name, "Worm");
            cv.put(Contract.Document.COLNAME_activity, "Crawling");
            cv.put(Contract.Document.COLNAME_amount, 1);
            cv.put(Contract.Document.COLNAME_category, "Insect");
            cv.put(Contract.Document.COLNAME_location, "Ground");
            db.insert(Contract.Document.TABLE_NAME, null, cv);

            cv = new ContentValues();
            cv.put(Contract.Document.COLNAME_name, "Spider");
            cv.put(Contract.Document.COLNAME_activity, "Making web");
            cv.put(Contract.Document.COLNAME_amount, 2);
            cv.put(Contract.Document.COLNAME_category, "Insect");
            cv.put(Contract.Document.COLNAME_location, "Bathroom");
            db.insert(Contract.Document.TABLE_NAME, null, cv);

            cv = new ContentValues();
            cv.put(Contract.Document.COLNAME_name, "Seagull");
            cv.put(Contract.Document.COLNAME_activity, "Flying");
            cv.put(Contract.Document.COLNAME_amount, 3);
            cv.put(Contract.Document.COLNAME_category, "Bird");
            cv.put(Contract.Document.COLNAME_location, "Field");
            db.insert(Contract.Document.TABLE_NAME, null, cv);


            cv.put(Contract.Document.COLNAME_name, "Salamander");
            cv.put(Contract.Document.COLNAME_activity, "Sleeping");
            cv.put(Contract.Document.COLNAME_amount, 3);
            cv.put(Contract.Document.COLNAME_category, "Amphibian");
            cv.put(Contract.Document.COLNAME_location, "Shop");
            db.insert(Contract.Document.TABLE_NAME, null, cv);
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + Contract.Document.TABLE_NAME);
        onCreate(db);
    }

    public Cursor getAll(SQLiteDatabase db)
    {
        // describes the record structure
        String[] columns = new String[]{
                Contract.Document.COLNAME_id,
                Contract.Document.COLNAME_name,
                Contract.Document.COLNAME_activity,
                Contract.Document.COLNAME_location,
                Contract.Document.COLNAME_category,
                Contract.Document.COLNAME_amount};
        // gives the query the table name and the list of columns
        return db.query(Contract.Document.TABLE_NAME, columns,
                null, null, null, null, null, null);
    }

    public void deleteById(SQLiteDatabase db, int id)
    {
        try
        {
            db.delete(Contract.Document.TABLE_NAME, Contract.Document.COLNAME_id + " = " + Integer.toString(id), null);
        }
        catch (Exception e)
        {
            Log.e("DELETE ERROR", e.getMessage());
        }
    }

    public void editById(SQLiteDatabase db, Document document, int id)
    {
        try
        {
            ContentValues cv = new ContentValues();
            cv.put(Contract.Document.COLNAME_name, document.getName());
            cv.put(Contract.Document.COLNAME_location, document.getLocation());
            cv.put(Contract.Document.COLNAME_category, document.getCategory());
            cv.put(Contract.Document.COLNAME_amount, document.getAmount());
            cv.put(Contract.Document.COLNAME_activity, document.getActivity());

            db.update(Contract.Document.TABLE_NAME, cv, Contract.Document.COLNAME_id + " = " + id, null);
        }

        catch(Exception e)
        {
            Log.e("CAN'T EDIT", e.getMessage());
        }
    }

    public void addRow(SQLiteDatabase db, Document document)
    {
        ContentValues cv = new ContentValues();
        cv.put(Contract.Document.COLNAME_name, document.getName());
        cv.put(Contract.Document.COLNAME_location, document.getLocation());
        cv.put(Contract.Document.COLNAME_category, document.getCategory());
        cv.put(Contract.Document.COLNAME_amount, document.getAmount());
        cv.put(Contract.Document.COLNAME_activity, document.getActivity());

        db.insert(Contract.Document.TABLE_NAME, null, cv);
    }

    public Cursor selectByCat(SQLiteDatabase db, String cat)
    {
        try
        {
            //Cursor c = db.rawQuery("SELECT * FROM " + Contract.Document.TABLE_NAME + " WHERE " + Contract.Document.COLNAME_category + " = " + cat, null);
            String[] columns = new String[]{
                    Contract.Document.COLNAME_id,
                    Contract.Document.COLNAME_name,
                    Contract.Document.COLNAME_activity,
                    Contract.Document.COLNAME_location,
                    Contract.Document.COLNAME_category,
                    Contract.Document.COLNAME_amount};
            String whereClause = Contract.Document.COLNAME_category + " = ?";
            String[] whereArgs = new String[] {cat};
            return db.query(Contract.Document.TABLE_NAME, columns, whereClause, whereArgs, null, null, null);
        }
        catch (Exception e)
        {
            Log.e("CAT SELECT ERROR", e.getMessage());
        }

        return null;
    }
}

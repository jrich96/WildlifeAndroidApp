package com.example.jaker.wildlife2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by jaker on 06/05/2017.
 */

public class AsyncDocumenter extends AsyncTask<Void, Integer, ArrayList<Document>>
{
    private ArrayList<Document> documents;
    private DocumentInterface delegate;
    private DBHelper DBhelper;
    private SQLiteDatabase DB;
    private int req;
    private int id;
    private Document document;

    public AsyncDocumenter(DocumentInterface delegate, Context context)
    {
        this.delegate = delegate;
        this.req = 1;
        DBhelper = new DBHelper(context);
        DB = DBhelper.getWritableDatabase();

    }

    public AsyncDocumenter(DocumentInterface delegate, Context context, int id)
    {
        this.delegate = delegate;
        this.req = 4;
        this.id = id;
        DBhelper = new DBHelper(context);
        DB = DBhelper.getWritableDatabase();
    }

    public AsyncDocumenter(DocumentInterface delegate, Context context, Document document)
    {
        this.delegate = delegate;
        this.req = 2;
        this.document = document;
        DBhelper = new DBHelper(context);
        DB = DBhelper.getWritableDatabase();
    }

    public AsyncDocumenter(DocumentInterface delegate, Context context, Document document, int id)
    {
        this.delegate = delegate;
        this.req = 3;
        this.document = document;
        this.id = id;
        DBhelper = new DBHelper(context);
        DB = DBhelper.getWritableDatabase();
    }


    @Override
    protected void onPreExecute() {

    }

    @Override
    protected ArrayList<Document> doInBackground(Void... params)
    {
        try
        {
            switch (req)
            {
                //get all
                case 1:
                    documents = convertToDocument(DBhelper.getAll(DB));
                    break;

                //add and get all
                case 2:
                    DBhelper.addRow(DB, document);
                    documents = convertToDocument(DBhelper.getAll(DB));
                    break;

                //edit and get all
                case 3:
                    DBhelper.editById(DB, document, id);
                    documents = convertToDocument(DBhelper.getAll(DB));
                    break;

                //delete and get all
                case 4:
                    DBhelper.deleteById(DB, id);
                    documents = convertToDocument(DBhelper.getAll(DB));
                    break;

            }
            DB.close();
        }
        catch (Exception e)
        {
            Log.e("ERROR", e.getMessage());
        }

        return documents;
    }

    @Override
    protected void onPostExecute(ArrayList<Document> documents) {delegate.processFinish(documents);}




    private ArrayList<Document> convertToDocument(Cursor cursor)
    {
        documents = new ArrayList<Document>();
        try
        {
            int column = 1;
            int idIndex = cursor.getColumnIndexOrThrow(Contract.Document.COLNAME_id);
            int nameIndex = cursor.getColumnIndexOrThrow(Contract.Document.COLNAME_name);
            int activityIndex = cursor.getColumnIndexOrThrow(Contract.Document.COLNAME_activity);
            int locationIndex = cursor.getColumnIndexOrThrow(Contract.Document.COLNAME_location);
            int catIndex = cursor.getColumnIndexOrThrow(Contract.Document.COLNAME_category);
            int amountIndex = cursor.getColumnIndexOrThrow(Contract.Document.COLNAME_amount);
            while (cursor.moveToNext())
            {
                Document d = new Document();
                d.setId(cursor.getInt(idIndex));
                d.setName(cursor.getString(nameIndex));
                d.setActivity(cursor.getString(activityIndex));
                d.setLocation(cursor.getString(locationIndex));
                d.setCategory(cursor.getString(catIndex));
                d.setAmount(cursor.getInt(amountIndex));
                documents.add(d);

                column++;
            }
        }
        catch (Exception e)
        {
            Log.e("CAN'T CONVERT", e.getMessage());
        }

        cursor.close();
        return documents;
    }
}

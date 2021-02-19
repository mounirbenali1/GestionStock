package com.example.geststock.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class    MyDBHelper extends SQLiteOpenHelper {
    public static final String CREATE_ARTICLE_TABLE= "create table article (id integer primary key, libelle text)";
    public static final String CREATE_OPERATION_TABLE="create table operation (num integer primary key,date text,qte float, article_id integer,foreign key (article_id) REFERENCES article (id))";


    public MyDBHelper(@Nullable Context context) {

        super(context, "GestStock", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_ARTICLE_TABLE);
        db.execSQL(CREATE_OPERATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists article");
            onCreate(db);
    }

    public void insertArticle(Article article) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put("libelle", article.getLibelle());
        db.insert("article",null,Values);
        db.close(); // Closing database connection

    }

    public void insertOperation(Operation operation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put("date", operation.getDate());
        Values.put("qte", operation.getQte());
        Values.put("article_id", operation.getArticle_id());


        db.insert("operation",null,Values);
        db.close(); // Closing database connection

    }

    public int updateOperation(Operation operation) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("qte", operation.getQte());
        values.put("date", operation.getDate());
        values.put("article_id", operation.getArticle_id());
        // updating row
        return db.update("operation", values,  "id" + " = ?",
                new String[] { String.valueOf(operation.getNum()) });
    }

    public void deleteOperation(Operation operation) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("operation", "id" + " = ?",
                new String[] { String.valueOf(operation.getNum()) });
        db.close();
    }

    public List getAllOperation() {
        List opList = new ArrayList();
        // Select All Query
        String selectQuery = "SELECT  * FROM  operation " ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Operation op = new Operation();
                op.setNum(Integer.parseInt(cursor.getString(0)));
                op.setDate(cursor.getString(1));
                op.setQte(Integer.parseInt(cursor.getString(2)));
                op.setArticle_id(Integer.parseInt(cursor.getString(3)));
                // Adding country to list
                opList.add(op);
            } while (cursor.moveToNext());
        }

        // return country list
        return opList;
    }


    public List getAllArticles() {
        List artList = new ArrayList();
        // Select All Query
        String selectQuery = "SELECT  * FROM article " ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Article art = new Article();
                art.setId(Integer.parseInt(cursor.getString(0)));
                art.setLibelle(cursor.getString(1));
                // Adding country to list
                artList.add(art);
            } while (cursor.moveToNext());
        }

        // return country list
        return artList;
    }
    private Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return  db.rawQuery("select * from article",null);
    }

    // Updating single country
    public int updateArticle(Article article) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("libelle", article.getLibelle());

        // updating row
        return db.update("article", values,  "id" + " = ?",
                new String[] { String.valueOf(article.getId()) });
    }

    // Deleting single country
    public void deleteArticle(Article article) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("article", "id" + " = ?",
                new String[] { String.valueOf(article.getId()) });
        db.close();
    }

    // Deleting all countries
    public void deleteAllArticle() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("article",null,null);
        db.close();
    }

    public int getArtID(String str){
        int id = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT  id FROM article WHERE libelle=?",new String [] {String.valueOf(str)});
        if (cursor.moveToFirst()) {
            do {

                id=Integer.parseInt(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        return id;
    }

    public String getArtname(int id){
        String name="" ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT  libelle FROM article WHERE id= ?",new String[] {String.valueOf(id)});
        if (cursor.moveToFirst()) {
            do {

                name=cursor.getString(1);
            } while (cursor.moveToNext());
        }

        return name;
    }



}

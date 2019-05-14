package com.andrey.kostenko.vrg_soft.model.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.andrey.kostenko.vrg_soft.model.pojo.Result;
import com.andrey.kostenko.vrg_soft.model.sql.ArticleContract.ArticleEntry;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TAG = DBHelper.class.getName();
    public static final String DATABASE_NAME = "nyt.db";
    public static final int DATABASE_VERSION = 1;


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ARTICLES_TABLE = "CREATE TABLE " + ArticleEntry.TABLE_NAME + " ("
                + ArticleEntry._ID + " INTEGER PRIMARY KEY, "
                + ArticleEntry.ARTICLE_TITLE + " TEXT NOT NULL, "
                + ArticleEntry.ARTICLE_ABSTRACT + " TEXT, "
                + ArticleEntry.ARTICLE_PUB_DATE + " TEXT, "
                + ArticleEntry.ARTICLE_THUMB + " TEXT, "
                + ArticleEntry.ARTICLE_URL + " TEXT NOT NULL);";
        db.execSQL(SQL_CREATE_ARTICLES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public synchronized void insertArticle(SQLiteDatabase database, Result result) {
        ContentValues values = new ContentValues();
        values.put(ArticleEntry._ID, result.getId());
        values.put(ArticleEntry.ARTICLE_TITLE, result.getTitle());
        values.put(ArticleEntry.ARTICLE_ABSTRACT, result.getAbstract());
        values.put(ArticleEntry.ARTICLE_PUB_DATE, result.getPublishedDate());
        values.put(ArticleEntry.ARTICLE_THUMB, result.getMedia().get(0).getMediaMetadata().get(2).getUrl());
        values.put(ArticleEntry.ARTICLE_URL, result.getUrl());

        long newRowId = database.insert(ArticleEntry.TABLE_NAME, null, values);
    }

    public synchronized void deleteArticle(SQLiteDatabase database, Result result) {
        String whereClause = "_id=?";
        String[] whereArgs = new String[] { String.valueOf(result.getId()) };
        database.delete(ArticleEntry.TABLE_NAME, whereClause, whereArgs);
    }

    public synchronized ArrayList<Result> readAll(SQLiteDatabase database) {
        ArrayList<Result> data = new ArrayList<>();
        String[] projection = {ArticleEntry._ID,
                ArticleEntry.ARTICLE_TITLE,
                ArticleEntry.ARTICLE_ABSTRACT,
                ArticleEntry.ARTICLE_PUB_DATE,
                ArticleEntry.ARTICLE_THUMB,
                ArticleEntry.ARTICLE_URL};

        Cursor cursor = database.query(ArticleEntry.TABLE_NAME, projection, null, null, null, null, null);
        int id = cursor.getColumnIndex(ArticleEntry._ID);
        int title = cursor.getColumnIndex(ArticleEntry.ARTICLE_TITLE);
        int a_abstract = cursor.getColumnIndex(ArticleEntry.ARTICLE_ABSTRACT);
        int pub_date = cursor.getColumnIndex(ArticleEntry.ARTICLE_PUB_DATE);
        int thumb = cursor.getColumnIndex(ArticleEntry.ARTICLE_THUMB);
        int url = cursor.getColumnIndex(ArticleEntry.ARTICLE_URL);
        while (cursor.moveToNext()) {
            data.add(new Result.Builder()
                    .setId(cursor.getLong(id))
                    .setTitle(cursor.getString(title))
                    .set_abstract(cursor.getString(a_abstract))
                    .setPublishedDate(cursor.getString(pub_date))
                    .setThumb(cursor.getString(thumb))
                    .setUrl(cursor.getString(url))
                    .build());
        }
        cursor.close();
        return data;
    }
}

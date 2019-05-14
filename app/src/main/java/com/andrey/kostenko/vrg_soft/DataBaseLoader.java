package com.andrey.kostenko.vrg_soft;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.andrey.kostenko.vrg_soft.model.pojo.Result;
import com.andrey.kostenko.vrg_soft.model.sql.DBHelper;

import java.util.ArrayList;

public class DataBaseLoader extends AsyncTask<Void, Void, Void> {

    private static final String TAG = DataBaseLoader.class.getName();

    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private onLoadDbListener listener;
    private Throwable throwable;
    private ArrayList<Result> list = new ArrayList<>();

    public DataBaseLoader(SQLiteDatabase database, DBHelper helper, onLoadDbListener listener) {
        this.database = database;
        this.dbHelper = helper;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            list = dbHelper.readAll(database);
        } catch (Exception e) {
            throwable = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (throwable == null) {
            listener.onLoad(list);
        } else {
            listener.onError(throwable);
        }
    }

    public interface onLoadDbListener {
        void onLoad(ArrayList<Result> results);
        void onError(Throwable throwable);
    }
}

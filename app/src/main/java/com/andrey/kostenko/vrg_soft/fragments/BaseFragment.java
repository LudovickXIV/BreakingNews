package com.andrey.kostenko.vrg_soft.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.andrey.kostenko.vrg_soft.DataBaseLoader;
import com.andrey.kostenko.vrg_soft.NYTApplication;
import com.andrey.kostenko.vrg_soft.adapters.ArticleAdapter;
import com.andrey.kostenko.vrg_soft.di.AppComponent;
import com.andrey.kostenko.vrg_soft.model.pojo.Result;
import com.andrey.kostenko.vrg_soft.model.sql.DBHelper;
import com.andrey.kostenko.vrg_soft.rest.RetrofitService;

import java.util.ArrayList;

import javax.inject.Inject;

public abstract class BaseFragment extends Fragment implements ArticleAdapter.OnRecyclerItemClickListener {

    public static final String TAG = BaseFragment.class.getName();

    @Inject
    protected RetrofitService retrofitService;

    @Inject
    protected DBHelper dbHelper;

    protected SQLiteDatabase database;

    protected LinearLayoutManager manager;
    protected ArticleAdapter adapter;
    protected ArrayList<Result> savedIndex = new ArrayList();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpComponent(NYTApplication.getAppComponent(this.getContext()));
        readArticles();
    }

    protected void setUpComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }

    protected void insertArticle(View view, Result result) {
        database = dbHelper.getWritableDatabase();
        dbHelper.insertArticle(database, result);
        readArticles();
    }

    protected void deleteArticle(int position, Result result) {
        database = dbHelper.getWritableDatabase();
        dbHelper.deleteArticle(database, result);
        readArticles();
    }

    public boolean isSaved(Result result) {
        try {
            for (Result res : savedIndex) {
                if (res.getId() == result.getId())return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    protected void readArticles() {
        database = dbHelper.getReadableDatabase();
        new DataBaseLoader(database, dbHelper, new DataBaseLoader.onLoadDbListener() {
            @Override
            public void onLoad(ArrayList<Result> results) {
                savedIndex = results;
            }

            @Override
            public void onError(Throwable throwable) {
                Log.d(TAG, "onError: " + throwable.getMessage());
            }
        }).execute();
    }

    protected abstract void getData();
}
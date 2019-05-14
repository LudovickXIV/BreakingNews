package com.andrey.kostenko.vrg_soft.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.andrey.kostenko.vrg_soft.DataBaseLoader;
import com.andrey.kostenko.vrg_soft.NavigationHost;
import com.andrey.kostenko.vrg_soft.R;
import com.andrey.kostenko.vrg_soft.adapters.ArticleAdapter;
import com.andrey.kostenko.vrg_soft.model.pojo.Result;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleSavedListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.refresh)
    protected SwipeRefreshLayout refresh;
    @BindView(R.id.errors_layout)
    protected ConstraintLayout errors;
    @BindView(R.id.recycler)
    protected RecyclerView recyclerView;
    @BindView(R.id.pgb)
    protected ProgressBar progressBar;
    @BindView(R.id.text_exception)
    protected TextView text_exception;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ArticleAdapter(this, new ArrayList<Result>(), true);
        manager = new LinearLayoutManager(getContext());
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        refresh.setOnRefreshListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        adapter.setOnClickListener(this);
        getData();
        return view;
    }

    @Override
    protected void getData() {
        database = dbHelper.getReadableDatabase();
        new DataBaseLoader(database, dbHelper, new DataBaseLoader.onLoadDbListener() {
            @Override
            public void onLoad(ArrayList<Result> results) {
                try {
                    if (recyclerView.getVisibility() == View.GONE) {
                        errors.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                    adapter.addAll(results);
                    progressBar.setVisibility(View.GONE);
                    refresh.setRefreshing(false);

                    if (adapter.getItemCount() == 0) {
                        recyclerView.setVisibility(View.GONE);
                        text_exception.setText(getResources().getString(R.string.empty));
                        errors.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {}
            }

            @Override
            public void onError(Throwable throwable) {
                refresh.setRefreshing(false);
                recyclerView.setVisibility(View.GONE);
                errors.setVisibility(View.VISIBLE);
            }
        }).execute();
    }

    @Override
    public void OnClick(String url) {
        ((NavigationHost)getActivity()).navigateTo(url);
    }

    @Override
    public void OnSaveBtnClick(View view, Result result) {

    }

    @Override
    public void OnDeleteBtnClick(int position, Result result) {
        deleteArticle(position, result);
        adapter.remove(position);
        adapter.notifyItemRemoved(position);
        if (adapter.getItemCount() == 0) {
            AlphaAnimation animation1 = new AlphaAnimation(0.2f, 1.0f);
            animation1.setDuration(700);
            errors.setAlpha(1f);
            errors.startAnimation(animation1);
            recyclerView.setVisibility(View.GONE);
            text_exception.setText(getResources().getString(R.string.empty));
            errors.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void deleteArticle(int position, Result result) {
        super.deleteArticle(position, result);
    }

    @Override
    public void onRefresh() {
        adapter.removeAll();
        adapter.notifyDataSetChanged();
        getData();
    }
}

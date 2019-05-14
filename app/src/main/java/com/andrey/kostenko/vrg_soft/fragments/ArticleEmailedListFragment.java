package com.andrey.kostenko.vrg_soft.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.andrey.kostenko.vrg_soft.NavigationHost;
import com.andrey.kostenko.vrg_soft.adapters.ArticleAdapter;
import com.andrey.kostenko.vrg_soft.R;
import com.andrey.kostenko.vrg_soft.model.pojo.NYTimesRequest;
import com.andrey.kostenko.vrg_soft.model.pojo.Result;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleEmailedListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.refresh)
    protected SwipeRefreshLayout refresh;
    @BindView(R.id.errors_layout)
    protected ConstraintLayout errors;
    @BindView(R.id.recycler)
    protected RecyclerView recyclerView;
    @BindView(R.id.pgb)
    protected ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ArticleAdapter(this, new ArrayList<Result>(), false);
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
        Call<NYTimesRequest> call = retrofitService.getEmailed();
        call.enqueue(new Callback<NYTimesRequest>() {
            @Override
            public void onResponse(Call<NYTimesRequest> call, Response<NYTimesRequest> response) {
                try {
                    if (recyclerView.getVisibility() == View.GONE) {
                        errors.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                    adapter.addAll(response.body().getResults());
                    progressBar.setVisibility(View.GONE);
                    refresh.setRefreshing(false);
                } catch (Exception e) {}
            }

            @Override
            public void onFailure(Call<NYTimesRequest> call, Throwable t) {
                refresh.setRefreshing(false);
                recyclerView.setVisibility(View.GONE);
                errors.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void OnClick(String url) {
        ((NavigationHost)getActivity()).navigateTo(url);
    }

    @Override
    public void OnSaveBtnClick(View view, Result result) {
        insertArticle(view, result);
    }

    @Override
    public void OnDeleteBtnClick(int position, Result result) {
        deleteArticle(position, result);
    }

    @Override
    protected void insertArticle(View view, Result result) {
        super.insertArticle(view, result);
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

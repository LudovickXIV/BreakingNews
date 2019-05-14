package com.andrey.kostenko.vrg_soft.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrey.kostenko.vrg_soft.R;
import com.andrey.kostenko.vrg_soft.adapters.view_holders.BaseViewHolder;
import com.andrey.kostenko.vrg_soft.fragments.BaseFragment;
import com.andrey.kostenko.vrg_soft.model.pojo.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final String TAG = ArticleAdapter.class.getName();

    private static final int VIEW_TYPE_API = 0;
    private static final int VIEW_TYPE_DB = 1;

    private BaseFragment baseFragment;
    private List<Result> data;
    private boolean isSaved;

    private OnRecyclerItemClickListener listener;

    public ArticleAdapter(BaseFragment fragment, List<Result> data, boolean isSaved) {
        this.baseFragment = fragment;
        this.data = data;
        this.isSaved = isSaved;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_API:
                return new ArticleViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.article_item, viewGroup, false), listener);
            case VIEW_TYPE_DB :
                return new ArticleSavedViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.article_saved_item, viewGroup, false), listener);
                default:
                    return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder, int position) {
        viewHolder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (isSaved) return VIEW_TYPE_DB;
        else return VIEW_TYPE_API;
    }

    public void setOnClickListener(OnRecyclerItemClickListener listener) {
        this.listener = listener;
    }

    public void add(Result response) {
        data.add(response);
        notifyItemInserted(data.size() - 1);
    }

    public void addAll(List<Result> photos) {
        for (Result response : photos) {
            add(response);
        }
    }

    public void remove(int index) {
        data.remove(index);
    }

    public void removeAll() {
        data.removeAll(data);
    }

    public class ArticleViewHolder extends BaseViewHolder implements View.OnClickListener {

        @BindView(R.id.title)
        protected TextView title;

        @BindView(R.id.subtitle)
        protected TextView subtitle;

        @BindView(R.id.pub_date)
        protected TextView pubdate;

        @BindView(R.id.article_image)
        protected ImageView thumb;

        @BindView(R.id.button_save)
        protected CheckBox save;


        private OnRecyclerItemClickListener listener;

        public ArticleViewHolder(@NonNull View itemView, final OnRecyclerItemClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            this.listener = listener;
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (save.isChecked()) {
                        listener.OnSaveBtnClick(v, data.get(getAdapterPosition()));
                    } else {
                        listener.OnDeleteBtnClick(getAdapterPosition(), data.get(getAdapterPosition()));
                    }
                }
            });
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            try {
                if (data != null) {
                    title.setText(data.get(position).getTitle());
                    subtitle.setText(data.get(position).getAbstract());
                    pubdate.setText(data.get(position).getPublishedDate());
                    save.setChecked(baseFragment.isSaved(data.get(position)));
                    Picasso.get()
                            .load(data.get(position).getMedia().get(0).getMediaMetadata().get(2).getUrl())
                            .error(R.drawable.nyt_icon)
                            .into(thumb);
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
        }

        @Override
        public void onClick(View v) {
            listener.OnClick(data.get(getAdapterPosition()).getUrl());
        }
    }

    public class ArticleSavedViewHolder extends BaseViewHolder implements View.OnClickListener {

        @BindView(R.id.title)
        protected TextView title;

        @BindView(R.id.subtitle)
        protected TextView subtitle;

        @BindView(R.id.pub_date)
        protected TextView pubdate;

        @BindView(R.id.article_image)
        protected ImageView thumb;

        @BindView(R.id.delete_item)
        protected Button delete;

        private OnRecyclerItemClickListener listener;

        public ArticleSavedViewHolder(@NonNull View itemView, final OnRecyclerItemClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            this.listener = listener;
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnDeleteBtnClick(getAdapterPosition(), data.get(getAdapterPosition()));
                }
            });
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            try {
                if (data != null) {
                    title.setText(data.get(position).getTitle());
                    subtitle.setText(data.get(position).getAbstract());
                    pubdate.setText(data.get(position).getPublishedDate());
                    Picasso.get()
                            .load(data.get(position).getThumb())
                            .error(R.drawable.nyt_icon)
                            .into(thumb);
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
        }

        @Override
        public void onClick(View v) {
            listener.OnClick(data.get(getAdapterPosition()).getUrl());
        }
    }

    public interface OnRecyclerItemClickListener {
        void OnClick(String url);
        void OnSaveBtnClick(View view, Result result);
        void OnDeleteBtnClick(int position, Result result);
    }
}

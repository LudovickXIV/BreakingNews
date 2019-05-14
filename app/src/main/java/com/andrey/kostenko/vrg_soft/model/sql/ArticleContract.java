package com.andrey.kostenko.vrg_soft.model.sql;

import android.provider.BaseColumns;

public final class ArticleContract {

    private ArticleContract() {}

    public static final class ArticleEntry implements BaseColumns {

        public static final String TABLE_NAME = "articles";
        public static final String _ID = BaseColumns._ID;
        public static final String ARTICLE_TITLE = "title";
        public static final String ARTICLE_ABSTRACT = "abstract";
        public static final String ARTICLE_PUB_DATE = "published_date";
        public static final String ARTICLE_THUMB = "thumb";
        public static final String ARTICLE_URL = "url";
    }

}

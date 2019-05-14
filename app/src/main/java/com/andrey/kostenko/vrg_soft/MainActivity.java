package com.andrey.kostenko.vrg_soft;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.andrey.kostenko.vrg_soft.adapters.TabsAdapter;
import com.andrey.kostenko.vrg_soft.di.AppComponent;
import com.andrey.kostenko.vrg_soft.model.sql.DBHelper;
import com.andrey.kostenko.vrg_soft.rest.RetrofitService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationHost{

    public static final String TAG = MainActivity.class.getName();
    public static final String EXTRA_URL = "m-SURl";

    @Inject
    protected DBHelper dbHelper;

    @Inject
    public RetrofitService retrofitService;

    @BindView(R.id.app_bar)
    protected Toolbar appbar;

    @BindView(R.id.pager)
    protected ViewPager pager;

    @BindView(R.id.tab)
    protected TabLayout tabLayout;

    private TabsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpComponent(NYTApplication.getAppComponent(this));
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new TabsAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        tabLayout.setupWithViewPager(pager);
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    protected void setUpComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public void navigateTo(String url) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra(EXTRA_URL, url);
        startActivity(intent);
    }
}

package io.mountblue.mealdbapp.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.List;

import io.mountblue.mealdbapp.R;
import io.mountblue.mealdbapp.adapter.ViewPagerCategoryAdapter;
import io.mountblue.mealdbapp.model.Category;

import static io.mountblue.mealdbapp.util.Constant.EXTRA_CATEGORY;
import static io.mountblue.mealdbapp.util.Constant.EXTRA_POSITION;

public class CategoryActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        initActionBar();
        initIntent();
    }

    private void initIntent() {
        Intent intent = getIntent();
        List<Category> categoryList = (List<Category>) intent.getSerializableExtra(EXTRA_CATEGORY);
        int position = intent.getIntExtra(EXTRA_POSITION, 0);
        ViewPagerCategoryAdapter viewPagerCategoryAdapter = new ViewPagerCategoryAdapter
                (getSupportFragmentManager(), categoryList);
        viewPager.setAdapter(viewPagerCategoryAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(position, true);
        viewPagerCategoryAdapter.notifyDataSetChanged();

    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}

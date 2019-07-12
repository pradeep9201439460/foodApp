package io.mountblue.mealdbapp.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.mountblue.mealdbapp.R;
import io.mountblue.mealdbapp.adapter.RecyclerViewAdapter;
import io.mountblue.mealdbapp.adapter.ViewPagerAdapter;
import io.mountblue.mealdbapp.model.Category;
import io.mountblue.mealdbapp.model.Meal;
import io.mountblue.mealdbapp.viewmodel.FoodViewModel;
import io.mountblue.mealdbapp.viewmodel.ViewModelProviderFactory;

import static io.mountblue.mealdbapp.util.Constant.EXTRA_CATEGORY;
import static io.mountblue.mealdbapp.util.Constant.EXTRA_DETAIL;
import static io.mountblue.mealdbapp.util.Constant.EXTRA_POSITION;


public class MainActivity extends DaggerAppCompatActivity {
    public static final String TAG = "MainActivity";
    RecyclerView recyclerViewCategory;
    ViewPager viewPagerMeal;
    FoodViewModel foodViewModel;
    RecyclerViewAdapter recyclerViewAdapter;

    @Inject
    String someString;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: " + someString);
        recyclerViewCategory = findViewById(R.id.recyclerCategory);
        viewPagerMeal = findViewById(R.id.viewPagerHeader);
        foodViewModel = ViewModelProviders.of(this, viewModelProviderFactory).
                get(FoodViewModel.class);
        showLoading();
        foodViewModel.getCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> categoryList) {
                initCategoryRecyclerView(categoryList);
            }
        });
        foodViewModel.getMeals().observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(@Nullable List<Meal> mealList) {
                initMealViewPager(mealList);
            }
        });

    }

    private void initCategoryRecyclerView(final List<Category> categoryList) {
        hideLoading();
        recyclerViewAdapter = new RecyclerViewAdapter(categoryList, MainActivity.this);
        recyclerViewCategory.setAdapter(recyclerViewAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3,
                GridLayoutManager.VERTICAL, false);
        recyclerViewCategory.setLayoutManager(gridLayoutManager);
        recyclerViewCategory.setNestedScrollingEnabled(true);
        recyclerViewAdapter.notifyDataSetChanged();
        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                intent.putExtra(EXTRA_CATEGORY, (Serializable) categoryList);
                intent.putExtra(EXTRA_POSITION, position);
                Log.d(TAG, "initCategoryRecyclerView: " + position);
                startActivity(intent);
            }
        });

        Log.d("Category", "" + categoryList.size());
    }

    private void initMealViewPager(List<Meal> mealList) {
        hideLoading();
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(mealList, MainActivity.this);
        viewPagerMeal.setAdapter(viewPagerAdapter);
        viewPagerMeal.setPadding(20, 0, 150, 0);
        viewPagerAdapter.notifyDataSetChanged();
        viewPagerAdapter.setOnItemClickListener(new ViewPagerAdapter.ClickListener() {
            @Override
            public void onClick(View v, int position) {
                TextView mealName = v.findViewById(R.id.mealName);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(EXTRA_DETAIL, mealName.getText().toString());
                startActivity(intent);
            }
        });
    }

    public void showLoading() {
        findViewById(R.id.shimmerMeal).setVisibility(View.VISIBLE);
        findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        findViewById(R.id.shimmerMeal).setVisibility(View.GONE);
        findViewById(R.id.shimmerCategory).setVisibility(View.GONE);
    }
}

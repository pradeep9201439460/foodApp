package io.mountblue.mealdbapp.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import io.mountblue.mealdbapp.fragment.CategoryFragment;
import io.mountblue.mealdbapp.model.Category;

public class ViewPagerCategoryAdapter extends FragmentPagerAdapter {

    private List<Category> categoryList;

    public ViewPagerCategoryAdapter(FragmentManager fm, List<Category> categoryList) {
        super(fm);
        this.categoryList = categoryList;
    }

    @Override
    public Fragment getItem(int i) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString("EXTRA_DATA_NAME", categoryList.get(i).getStrCategory());
        args.putString("EXTRA_DATA_DESC", categoryList.get(i).getStrCategoryDescription());
        args.putString("EXTRA_DATA_IMAGE", categoryList.get(i).getStrCategoryThumb());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return categoryList.get(position).getStrCategory();
    }
}

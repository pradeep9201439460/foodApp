package io.mountblue.mealdbapp.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import io.mountblue.mealdbapp.R;
import io.mountblue.mealdbapp.activity.DetailActivity;
import io.mountblue.mealdbapp.adapter.RecyclerViewMealByCategory;
import io.mountblue.mealdbapp.model.Meal;
import io.mountblue.mealdbapp.viewmodel.FoodViewModel;
import io.mountblue.mealdbapp.viewmodel.ViewModelProviderFactory;

import static io.mountblue.mealdbapp.util.Constant.EXTRA_DETAIL;

public class CategoryFragment extends Fragment {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ImageView imageCategory;
    ImageView imageCategoryBg;
    TextView textCategory;
    FoodViewModel foodViewModel;
    String mealCategory;
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        imageCategory = view.findViewById(R.id.imageCategory);
        imageCategoryBg = view.findViewById(R.id.imageCategoryBg);
        textCategory = view.findViewById(R.id.textCategory);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLoading();
        foodViewModel = ViewModelProviders.of(this, viewModelProviderFactory).
                get(FoodViewModel.class);

        if (getArguments() != null) {

            textCategory.setText(getArguments().getString("EXTRA_DATA_DESC"));
            Picasso.get()
                    .load(getArguments().getString("EXTRA_DATA_IMAGE"))
                    .into(imageCategory);
            Picasso.get()
                    .load(getArguments().getString("EXTRA_DATA_IMAGE"))
                    .into(imageCategoryBg);
            mealCategory = getArguments().getString("EXTRA_DATA_NAME");

        }
        foodViewModel.getMealByCategory(mealCategory).observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(@Nullable List<Meal> mealList) {
                initMealByCategoryRecyclerView(mealList);
            }
        });
    }

    private void initMealByCategoryRecyclerView(final List<Meal> mealList) {
        hideLoading();
        RecyclerViewMealByCategory recyclerViewMealByCategoryAdapter = new RecyclerViewMealByCategory(getActivity(), mealList);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(recyclerViewMealByCategoryAdapter);
        recyclerViewMealByCategoryAdapter.setOnItemClickListener(new RecyclerViewMealByCategory.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                TextView mealName = view.findViewById(R.id.mealName);
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(EXTRA_DETAIL, mealName.getText().toString());
                startActivity(intent);
            }
        });
    }

    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }
}

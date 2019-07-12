package io.mountblue.mealdbapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import io.mountblue.mealdbapp.model.Category;
import io.mountblue.mealdbapp.model.Meal;
import io.mountblue.mealdbapp.repository.FoodRepository;

public class FoodViewModel extends ViewModel {
    private FoodRepository mFoodRepo;

    @Inject
    public FoodViewModel() {
        mFoodRepo = new FoodRepository();
    }

    public LiveData<List<Category>> getCategories() {
        return mFoodRepo.getCategoriesMutableLiveData();
    }

    public LiveData<List<Meal>> getMeals() {
        return mFoodRepo.getMealsMutableLiveData();
    }

    public LiveData<List<Meal>> getMealByCategory(String mealCategory) {
        return mFoodRepo.getMealsByCategoryMutableLiveData(mealCategory);
    }

    public LiveData<List<Meal>> getMealByName(String mealCategory) {
        return mFoodRepo.getMealByName(mealCategory);
    }
}

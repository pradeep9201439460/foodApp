package io.mountblue.mealdbapp.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

import io.mountblue.mealdbapp.api.FoodClient;
import io.mountblue.mealdbapp.model.Categories;
import io.mountblue.mealdbapp.model.Category;
import io.mountblue.mealdbapp.model.Meal;
import io.mountblue.mealdbapp.model.Meals;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FoodRepository {
    private static final String TAG = "FoodRepository";
    private MutableLiveData<List<Category>> categoriesMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Meal>> mealsMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Meal>> mealsByCategoryMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Meal>> mealByNameMutableLiveData = new MutableLiveData<>();
    private static FoodRepository instance;

    public static FoodRepository getInstance() {
        if (instance == null) {
            instance = new FoodRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Category>> getCategoriesMutableLiveData() {
        Observable<Categories> categoriesObservable = FoodClient.getApi().getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        categoriesObservable.subscribe(new Observer<Categories>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Categories categories) {
                Log.d(TAG, "onNext: " + categories.getCategories().size());
                categoriesMutableLiveData.setValue(categories.getCategories());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onResponse Error :" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
        return categoriesMutableLiveData;
    }

    public MutableLiveData<List<Meal>> getMealsMutableLiveData() {
        Observable<Meals> mealsObservable = FoodClient.getApi().getMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        mealsObservable.subscribe(new Observer<Meals>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Meals meals) {
                Log.d(TAG, "onNext: " + meals.getMeals().size());
                mealsMutableLiveData.setValue(meals.getMeals());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onResponse Error :" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
        return mealsMutableLiveData;
    }

    public MutableLiveData<List<Meal>> getMealsByCategoryMutableLiveData(String mealCategory) {
        final Observable<Meals> mealByCategoryObservable = FoodClient.getApi().
                getMealByCategory(mealCategory)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        mealByCategoryObservable.subscribe(new Observer<Meals>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Meals meals) {
                mealsByCategoryMutableLiveData.setValue(meals.getMeals());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        return mealsByCategoryMutableLiveData;
    }

    public MutableLiveData<List<Meal>> getMealByName(String mealName) {
        Observable<Meals> mealsObservable = FoodClient.getApi()
                .getMealByName(mealName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        mealsObservable.subscribe(new Observer<Meals>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Meals meals) {
                mealByNameMutableLiveData.setValue(meals.getMeals());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        return mealByNameMutableLiveData;
    }
}

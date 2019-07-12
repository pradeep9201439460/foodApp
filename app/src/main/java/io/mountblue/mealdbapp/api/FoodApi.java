package io.mountblue.mealdbapp.api;

import io.mountblue.mealdbapp.model.Categories;
import io.mountblue.mealdbapp.model.Meals;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodApi {
    @GET("latest.php")
    Observable<Meals> getMeals();

    @GET("categories.php")
    Observable<Categories> getCategories();

    @GET("filter.php")
    Observable<Meals> getMealByCategory(@Query("c") String category);

    @GET("search.php")
    Observable<Meals> getMealByName(@Query("s") String mealName);

}
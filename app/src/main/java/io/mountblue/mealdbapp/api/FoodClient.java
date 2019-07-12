package io.mountblue.mealdbapp.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static io.mountblue.mealdbapp.util.Constant.BASE_URL;

public class FoodClient {

    private static FoodApi mInstance;
    private static Retrofit retrofit = null;

    public static Retrofit getFoodClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
        }
        return retrofit;
    }

    public static FoodApi getApi() {
        if (mInstance == null) {
            mInstance = getFoodClient().create(FoodApi.class);
        }
        return mInstance;
    }

}

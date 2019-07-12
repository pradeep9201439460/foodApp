package io.mountblue.mealdbapp.di;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import io.mountblue.mealdbapp.activity.DetailActivity;
import io.mountblue.mealdbapp.activity.MainActivity;
import io.mountblue.mealdbapp.di.bindViewModule.FoodViewModelModule;

@Module
public abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(
            modules = {FoodViewModelModule.class}
    )
    abstract MainActivity contributeMainActivity();

    @Provides
    static String someString() {
        return "this is test string";
    }
}


package io.mountblue.mealdbapp.root;


import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.mountblue.mealdbapp.di.DaggerAppComponent;

public class BaseApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}


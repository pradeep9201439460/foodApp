package io.mountblue.mealdbapp.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import io.mountblue.mealdbapp.root.BaseApplication;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuildersModule.class,
                ViewModelFactoryModule.class,

        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}

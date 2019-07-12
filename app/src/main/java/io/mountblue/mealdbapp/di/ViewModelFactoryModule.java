package io.mountblue.mealdbapp.di;

import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import io.mountblue.mealdbapp.viewmodel.ViewModelProviderFactory;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelFactory);

}

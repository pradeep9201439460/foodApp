package io.mountblue.mealdbapp.di.bindViewModule;

import android.arch.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import io.mountblue.mealdbapp.di.ViewModelKey;
import io.mountblue.mealdbapp.viewmodel.FoodViewModel;

@Module
public abstract class FoodViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FoodViewModel.class)
    public abstract ViewModel bindAuthViewModel(FoodViewModel viewModel);
}

package com.example.testtaskforalef.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtaskforalef.di.annatation.ViewModelKey
import com.example.testtaskforalef.di.factory.ViewModelFactory
import com.example.testtaskforalef.ui.fullimage.FullImageViewModel
import com.example.testtaskforalef.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    protected abstract fun mainViewModel(mainFragmentViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FullImageViewModel::class)
    protected abstract fun fullImageViewModel(fullImageViewModel: FullImageViewModel): ViewModel
}
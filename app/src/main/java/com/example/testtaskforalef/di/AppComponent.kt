package com.example.testtaskforalef.di

import android.content.Context
import com.example.testtaskforalef.di.modules.ApiModule
import com.example.testtaskforalef.di.modules.RepositoryModule
import com.example.testtaskforalef.di.modules.ViewModelModule
import com.example.testtaskforalef.ui.fullimage.FullImageFragment
import com.example.testtaskforalef.ui.main.MainFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
    ApiModule::class,
    ViewModelModule::class,
    RepositoryModule::class
    ]
)
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(mainFragment: MainFragment)
    fun inject(fullImageFragment: FullImageFragment)
}
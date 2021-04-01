package com.example.testtaskforalef.di.modules

import com.example.testtaskforalef.data.api.IImageDataSourse
import com.example.testtaskforalef.data.entites.DataImage
import com.example.testtaskforalef.data.state.AppState
import com.example.testtaskforalef.data_sourse.DataSourceImage
import com.example.testtaskforalef.data_sourse.IDataSource
import com.example.testtaskforalef.repository.IRepository
import com.example.testtaskforalef.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideDataSource(api: IImageDataSourse): IDataSource<List<String>> =
        DataSourceImage(api)

    @Provides
    @Singleton
    fun providesRepos(dataSource: IDataSource<List<String>>): IRepository<AppState> =
        RepositoryImpl(dataSource)
}
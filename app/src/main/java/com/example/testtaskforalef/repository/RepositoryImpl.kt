package com.example.testtaskforalef.repository

import com.example.testtaskforalef.data.entites.DataImage
import com.example.testtaskforalef.data.state.AppState
import com.example.testtaskforalef.data_sourse.IDataSource
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val dataSourse: IDataSource<List<String>>
) : IRepository<AppState> {
    override suspend fun getData(): AppState {
        return AppState.Succes(dataSourse.getData())
    }
}
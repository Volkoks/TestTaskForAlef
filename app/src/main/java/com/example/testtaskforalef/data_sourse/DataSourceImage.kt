package com.example.testtaskforalef.data_sourse

import com.example.testtaskforalef.data.api.IImageDataSourse
import com.example.testtaskforalef.data.entites.DataImage
import javax.inject.Inject

class DataSourceImage @Inject constructor(
    val api: IImageDataSourse
) : IDataSource<List<String>> {
    override suspend fun getData(): List<String> {
        return api.getListData().await()
    }
}
package com.example.testtaskforalef.data.api

import com.example.testtaskforalef.data.entites.DataImage
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface IImageDataSourse {
    @GET("task-m-001/list.php")
    fun getListData(): Deferred<List<String>>
}
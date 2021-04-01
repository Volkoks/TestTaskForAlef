package com.example.testtaskforalef.data_sourse

interface IDataSource<T> {
    suspend fun getData(): T
}
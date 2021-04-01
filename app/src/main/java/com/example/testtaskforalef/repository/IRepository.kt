package com.example.testtaskforalef.repository

interface IRepository<T> {
    suspend fun getData(): T
}
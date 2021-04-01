package com.example.testtaskforalef.data.state

import com.example.testtaskforalef.data.entites.DataImage

sealed class AppState {
    data class Succes(val dataImage: List<String>) : AppState()
    data class Loading(val progress: Int?) : AppState()
    data class Error(val error: Throwable) : AppState()
}

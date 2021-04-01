package com.example.testtaskforalef.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtaskforalef.data.state.AppState
import com.example.testtaskforalef.repository.IRepository
import kotlinx.coroutines.*
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val repo: IRepository<AppState>
) : ViewModel() {

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            errorReturned(throwable)
        })

    private var liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun subscribeLiveData(): LiveData<AppState> {
        return liveDataToObserve
    }

    fun getData() {
        liveDataToObserve.value = AppState.Loading(1)
        cancelJob()
        viewModelCoroutineScope.launch {
            liveDataToObserve.postValue(repo.getData())
        }

    }

    private fun errorReturned(t: Throwable) {
        liveDataToObserve.postValue(AppState.Error(t))
    }


    private fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }
}
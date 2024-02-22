package com.siba.myapplication.fetchDataDi

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siba.myapplication.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiViewModel @Inject constructor(private val repository: DiRepository):ViewModel() {
    val fetchData:LiveData<ApiResult<FetchResponse?>>
        get() = repository.fetchData


    fun fetchUserDataApiCall(fetchReqest: FetchReqest){
        viewModelScope.launch {
            repository.fetchData(fetchReqest)
        }
    }
}
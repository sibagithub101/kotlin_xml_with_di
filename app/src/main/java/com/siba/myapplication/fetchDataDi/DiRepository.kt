package com.siba.myapplication.fetchDataDi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.siba.myapplication.ApiResult
import org.json.JSONObject
import javax.inject.Inject

class DiRepository @Inject constructor(private val diApiService: DiApiService){
private val _fetchData = MutableLiveData<ApiResult<FetchResponse?>>()
    val fetchData:LiveData<ApiResult<FetchResponse?>>
        get() = _fetchData

    suspend fun fetchData(fetchReqest: FetchReqest){
        try{
            _fetchData.postValue(ApiResult.Loading(true))
            val result = diApiService.fetchData(fetchReqest)

            if(result.isSuccessful && result.body()!=null){
                _fetchData.postValue(ApiResult.Success(result.body()))
            }else if(result.code().toString().startsWith("4") || result.code().toString().startsWith("5")){
                  val error = result.errorBody()?.string()?.let { JSONObject(it) }
                   if(error?.has("message")==true){
                       _fetchData.postValue(ApiResult.Error(error.getString("message")))
                   }
            }
        }catch (e:Exception){
            _fetchData.postValue(ApiResult.Error(e.localizedMessage))
        }

    }
}
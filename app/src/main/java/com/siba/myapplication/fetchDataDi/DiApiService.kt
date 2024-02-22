package com.siba.myapplication.fetchDataDi

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface DiApiService {
@POST("report/fetchData")
 suspend fun fetchData(@Body fetchReqest: FetchReqest):Response<FetchResponse>
}
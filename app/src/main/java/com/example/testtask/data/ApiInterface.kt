package com.example.testtask.data

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("/task-m-001/list.php")
    suspend fun getImage():Response<List<String>>
}